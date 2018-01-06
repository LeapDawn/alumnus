package zqh.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zqh.commons.exception.DataViolationException;
import zqh.dao.ClazzApplyDAO;
import zqh.dto.RequestList;
import zqh.dto.ResultModel;
import zqh.model.Clazz;
import zqh.model.ClazzAlumnus;
import zqh.model.ClazzApply;
import zqh.service.ClazzAlumnusService;
import zqh.service.ClazzService;
import zqh.service.ClazzApplyService;

import java.util.List;

@Service("clazzApplyService")
public class ClazzApplyServiceImpl implements ClazzApplyService {

    @Autowired
    private ClazzApplyDAO clazzApplyDAO;

    @Autowired
    private ClazzService clazzService;

    @Autowired
    private ClazzAlumnusService clazzAlumnusService;

    @Override
    public void apply(ClazzApply clazzApply) {
        ClazzApply myApply = this.getMyApply(clazzApply.getAlumnus());
        if (myApply != null && myApply.getStatus() == 1) {
            throw new DataViolationException(501,"已有待审核的建立校友班级申请");
        }
        clazzApply.setStatus(0);
        clazzApplyDAO.insert(clazzApply);
    }

    @Transactional
    @Override
    public void approveApply(ClazzApply clazzApply) {
        clazzApply.setStatus(1);
        clazzApplyDAO.updateStatus(clazzApply);
        clazzApply = clazzApplyDAO.selectById(clazzApply.getId());
        Clazz clazz = new Clazz();
        clazz.setAdder(clazzApply.getAlumnus());
        clazz.setContent(clazzApply.getContent());
        clazz.setName(clazzApply.getName());
        clazzService.save(clazz);
        // 将申请人加入校友班级并赋予管理员权限
        ClazzAlumnus clazzAlumnus = new ClazzAlumnus();
        clazzAlumnus.setAlumnus(clazz.getAdder());
        clazzAlumnus.setClazz(clazz.getName());
        clazzAlumnus.setAdmin(true);
        clazzAlumnusService.save(clazzAlumnus);
    }

    @Override
    public void disapproveApply(ClazzApply clazzApply) {
        clazzApply.setStatus(2);
        clazzApplyDAO.updateStatus(clazzApply);
    }

    @Override
    public ClazzApply getMyApply(Integer alumnus) {
        return clazzApplyDAO.selectByAdder(alumnus);
    }

    @Override
    public ResultModel<ClazzApply> list(RequestList<Integer> rl) {
        int total = clazzApplyDAO.countByStatus(rl.getKey());
        ResultModel<ClazzApply> resultModel = new ResultModel<>(total, rl.getRows(), rl.getPage());
        List<ClazzApply> list = clazzApplyDAO.selectListByStatus(rl.getKey(),
                (resultModel.getCurrentPage() - 1) * resultModel.getRows(), resultModel.getRows());
        resultModel.setData(list);
        return resultModel;
    }
}
