package zqh.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import zqh.commons.util.SessionUtil;
import zqh.dto.AjaxResult;
import zqh.model.Account;
import zqh.model.Alumnus;
import zqh.service.AccountService;
import zqh.service.AlumnusService;

import javax.servlet.http.HttpSession;

@RestController
@CrossOrigin
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AlumnusService alumnusService;

    @PostMapping("/register")
    public AjaxResult addition(@RequestBody Account account, HttpSession session){
        accountService.save(account);
        session.setAttribute("user", account);
        return AjaxResult.success("注册成功");
    }

    @PutMapping("/password")
    // account.accounts用于存储旧密码,account.password用于存储新密码
    public AjaxResult updatePassWord(@RequestBody Account account, HttpSession session){
        Account user = SessionUtil.getUser(session);
        if (user != null && !user.getPassword().equals(account.getAccounts())){
            return AjaxResult.fail(304,"原密码错误");
        } else{
            account.setAccounts(user.getAccounts());
            accountService.updatePassword(account);
            // 更新session中的密码
            user.setPassword(account.getPassword());
            return AjaxResult.success("修改密码成功");
        }
    }

    @PostMapping("/login")
    public AjaxResult login(@RequestBody Account account, HttpSession session){
        Account login = accountService.login(account);
        session.setAttribute("user", login);
        if (login.getAlumnus() != null) {
            Alumnus alumnus = alumnusService.selectOne(login.getAlumnus());
            session.setAttribute("invalid", alumnus.getInvalid());
        }
        return AjaxResult.success("登陆成功");
    }

    @GetMapping("/logout")
    public AjaxResult logout(HttpSession session){
        session.removeAttribute("user");
        return AjaxResult.success("退出登陆成功");
    }

    @PutMapping("/bind")
    public AjaxResult bind(@RequestBody Alumnus alumnus, HttpSession session) {
        Account user = SessionUtil.getUser(session);
        if (user != null && user.getAccounts() != null){
            accountService.bind(user.getAccounts(), alumnus);
            return AjaxResult.success("成功认证校友信息");
        } else {
            return AjaxResult.fail(1,"");
        }
    }

}
