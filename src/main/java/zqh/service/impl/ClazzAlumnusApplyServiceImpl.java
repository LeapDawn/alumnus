package zqh.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zqh.commons.exception.DataViolationException;
import zqh.dao.ClazzAlumnusApplyDAO;
import zqh.dto.RequestList;
import zqh.dto.ResultModel;
import zqh.model.Alumnus;
import zqh.model.ClazzAlumnus;
import zqh.model.ClazzAlumnusApply;
import zqh.service.ClazzAlumnusApplyService;
import zqh.service.ClazzAlumnusService;

import java.util.Date;
import java.util.List;

@Service("clazzAlumnusApply")
public class ClazzAlumnusApplyServiceImpl implements ClazzAlumnusApplyService {

    @Autowired
    private ClazzAlumnusApplyDAO clazzAlumnusApplyDAO;

    @Autowired
    private ClazzAlumnusService clazzAlumnusService;

    @Override
    public void apply(ClazzAlumnusApply apply) {
        apply.setStatus(0);
        apply.setDate(new Date());
        ClazzAlumnusApply myApply = this.getMyApply(apply.getAlumnus());
        if (myApply != null && myApply.getStatus() == 0) {
            throw new DataViolationException(601, "已有待审核的加入校友班级申请!");
        }
        
        clazzAlumnusApplyDAO.insert(apply);
    }

    @Override
    public void approveApply(ClazzAlumnusApply apply) {
        apply.setStatus(1);
        clazzAlumnusApplyDAO.updateStatus(apply);
        ClazzAlumnusApply record = clazzAlumnusApplyDAO.selectById(apply.getId());
        ClazzAlumnus clazzAlumnus = new ClazzAlumnus();
        clazzAlumnus.setClazz(record.getClazz());
        clazzAlumnus.setAlumnus(record.getAlumnus());
        clazzAlumnus.setAdmin(false);
        clazzAlumnusService.save(clazzAlumnus);
    }

    @Override
    public void disapproveApply(ClazzAlumnusApply apply) {
        apply.setStatus(2);
        clazzAlumnusApplyDAO.updateStatus(apply);
    }

    @Override
    public ClazzAlumnusApply getMyApply(Integer alumnus) {
        return clazzAlumnusApplyDAO.selectByAlumnus(alumnus);
    }

    @Override
    public ResultModel<Alumnus> list(RequestList<ClazzAlumnusApply> rl) {
        ClazzAlumnusApply key = rl.getKey();
        key = key != null ? key : new ClazzAlumnusApply();
        int total = clazzAlumnusApplyDAO.count(key);
        ResultModel<Alumnus> resultModel = new ResultModel<>(total, rl.getRows(), rl.getPage());
        List<Alumnus> list = clazzAlumnusApplyDAO.selectList(rl.getKey(),
                (resultModel.getCurrentPage() - 1) * resultModel.getRows(), resultModel.getRows());
        resultModel.setData(list);
        return resultModel;
    }
}
