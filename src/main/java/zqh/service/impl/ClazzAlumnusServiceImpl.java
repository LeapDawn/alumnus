package zqh.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zqh.commons.exception.DataViolationException;
import zqh.dao.ClazzAlumnusDAO;
import zqh.dto.RequestList;
import zqh.dto.ResultModel;
import zqh.model.Alumnus;
import zqh.model.Clazz;
import zqh.model.ClazzAlumnus;
import zqh.service.ClazzAlumnusService;
import zqh.service.ClazzService;

import java.util.List;

@Service("clazzAlumnusService")
public class ClazzAlumnusServiceImpl implements ClazzAlumnusService {

    @Autowired
    private ClazzAlumnusDAO clazzAlumnusDAO;

    @Autowired
    private ClazzService clazzService;

    @Override
    public void save(ClazzAlumnus clazzAlumnus) {
        clazzAlumnusDAO.insert(clazzAlumnus);
    }

    @Override
    public void delete(ClazzAlumnus clazzAlumnus) {
        clazzAlumnusDAO.deleteByClazzAndAlumnus(clazzAlumnus);
    }

    @Override
    public Clazz selectClazz(Integer alumnus) {
        ClazzAlumnus clazzAlumnus = this.hasJoined(alumnus);
        if (clazzAlumnus == null) {
            throw new DataViolationException(402, "没有加入校友班级!");
        } else {
            return clazzService.selectOne(clazzAlumnus.getClazz());
        }
    }

    @Override
    public ResultModel<Alumnus> listAlumnus(RequestList<ClazzAlumnus> rl) {
        ClazzAlumnus key = rl.getKey();
        int total = clazzAlumnusDAO.count(key);
        ResultModel<Alumnus> resultModel = new ResultModel<>(total, rl.getRows(), rl.getPage());
        List<Alumnus> list = clazzAlumnusDAO.selectList(key, (resultModel.getCurrentPage() - 1) * resultModel.getRows(), resultModel.getRows());
        resultModel.setData(list);
        return resultModel;
    }

    @Override
    public boolean isAdmin(ClazzAlumnus clazzAlumnus) {
        ClazzAlumnus record = clazzAlumnusDAO.selectByClazzAndAlumnus(clazzAlumnus);
        return  record.getAdmin();
    }

    @Override
    public ClazzAlumnus hasJoined(Integer alumnus) {
        ClazzAlumnus record = new ClazzAlumnus();
        record.setAlumnus(alumnus);
        return clazzAlumnusDAO.selectByAlumnus(record);
    }

    @Override
    public void appointAdmin(ClazzAlumnus clazzAlumnus, Integer adminId) {
        ClazzAlumnus entity = new ClazzAlumnus();
        entity.setAlumnus(adminId);
        entity.setClazz(clazzAlumnus.getClazz());
        if (this.isAdmin(entity)){
            clazzAlumnus.setAdmin(true);
            clazzAlumnusDAO.updateAdmin(clazzAlumnus);
        } else {
            throw new DataViolationException(403, "您不是管理员,无任命管理员权限");
        }
    }
}
