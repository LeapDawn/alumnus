package zqh.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zqh.commons.exception.DataViolationException;
import zqh.dao.AccountDAO;
import zqh.model.Account;
import zqh.model.Alumnus;
import zqh.service.AccountService;
import zqh.service.AlumnusService;

@Service("accountService")
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDAO accountDAO;

    @Autowired
    private AlumnusService alumnusService;

    @Override
    public void save(Account account) {
        accountDAO.insert(account);
    }

    @Override
    public void updatePassword(Account account) {
        accountDAO.updatePassword(account);
    }

    @Override
    public Account login(Account account) {
        Account login = accountDAO.selectOne(account.getAccounts());
        if (login == null) {
            throw new DataViolationException(301, "用户名不存在");
        }
        if (!login.getPassword().equals(account.getPassword())) {
            throw new DataViolationException(302, "密码错误");
        }
        return login;
    }

    @Override
    public void bind(String account, Alumnus alumnus) {
        Integer id = alumnusService.selectOneByNameAndCard(alumnus);
        if (id == null) {
            alumnus.setInvalid(1);
            alumnus.setSpecial(false);
            alumnusService.save(alumnus);
            throw new DataViolationException(303, "校友信息库中不存在您的信息，已提交数据等待管理员审核");
        } else {
            Account record = new Account();
            record.setAccounts(account);
            record.setAlumnus(id);
            this.updateAlumnus(record);
        }
    }

    @Override
    public void updateAlumnus(Account account) {
        accountDAO.updateAlumnus(account);
    }
}
