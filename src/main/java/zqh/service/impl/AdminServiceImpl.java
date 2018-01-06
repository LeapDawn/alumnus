package zqh.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zqh.commons.exception.DataViolationException;
import zqh.dao.AdministratorDAO;
import zqh.dao.AlumnusDAO;
import zqh.model.Administrator;
import zqh.service.AdminService;
import zqh.service.AlumnusService;

@Service("adminService")
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdministratorDAO adminDAO;

    @Override
    public void save(Administrator administrator) {
        //TODO 密码加密
        adminDAO.insert(administrator);
    }

    @Override
    public void update(Administrator administrator) {
        adminDAO.update(administrator);
    }

    @Override
    public Administrator login(Administrator administrator) {
        Administrator admin = adminDAO.selectOne(administrator.getAccount());
        if (admin == null) {
            throw new DataViolationException(201,"用户名不存在");
        }
        if (!admin.getPassword().equals(administrator.getPassword())){
            throw new DataViolationException(202, "密码错误");
        }
        return admin;
    }
}
