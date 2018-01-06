package zqh.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zqh.commons.exception.DataViolationException;
import zqh.dao.ClazzDAO;
import zqh.dto.RequestList;
import zqh.dto.ResultModel;
import zqh.model.Clazz;
import zqh.model.ClazzAlumnus;
import zqh.service.ClazzAlumnusService;
import zqh.service.ClazzService;

import java.util.Date;
import java.util.List;

@Service("clazzService")
public class ClazzServiceImpl implements ClazzService {

    @Autowired
    private ClazzDAO clazzDAO;

    @Autowired
    private ClazzAlumnusService clazzAlumnusService;

    @Override
    public void save(Clazz clazz) {
        clazz.setAddtime(new Date());
        clazzDAO.insert(clazz);
    }

    @Override
    public void updateContent(Clazz clazz, Integer alumnus) {
        // 验证修改人是否班级管理员
        ClazzAlumnus clazzAlumnus = new ClazzAlumnus();
        clazzAlumnus.setClazz(clazz.getName());
        clazzAlumnus.setAlumnus(alumnus);
        if (clazzAlumnusService.isAdmin(clazzAlumnus)){
            clazzDAO.updateContent(clazz);
        } else {
            throw new DataViolationException(701, "班级管理员才能修改班级信息");
        }
    }


    @Override
    public ResultModel<Clazz> list(RequestList<Clazz> requestList) {
        Clazz key = requestList.getKey();
        int total = clazzDAO.count(key);
        ResultModel<Clazz> resultModel = new ResultModel<>(total, requestList.getRows(), requestList.getPage());
        List<Clazz> list = clazzDAO.selectList(key, (resultModel.getCurrentPage() - 1) * resultModel.getRows(), resultModel.getRows());
        resultModel.setData(list);
        return resultModel;
    }

    @Override
    public Clazz selectOne(String name) {
        return clazzDAO.selectOne(name);
    }

    @Override
    public Clazz selectByAccount(String accout) {
        // TODO 获取可以放到关系业务中实现？通过调用selectOne
        return null;
    }
}
