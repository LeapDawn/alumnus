package zqh.web.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import zqh.dto.AjaxResult;
import zqh.model.Administrator;
import zqh.service.AdminService;

import javax.servlet.http.HttpSession;

@RestController
@CrossOrigin
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/addition")
    public AjaxResult addition(@RequestBody Administrator administrator){
        adminService.save(administrator);
        return AjaxResult.success("新增管理员用户信息成功");
    }

    @PutMapping("/update")
    public AjaxResult update(@RequestBody Administrator administrator, HttpSession session) {
        Administrator admin = (Administrator) session.getAttribute("admin");
        if (admin == null || !admin.getAccount().equals(administrator.getAccount())) {
            return AjaxResult.fail(203, "只允许修改用户自己的信息");
        }
        adminService.update(administrator);
        return AjaxResult.success("修改管理员用户信息成功");
    }

    @PutMapping("/password/update")
    public AjaxResult updatePassword(@RequestBody Administrator administrator, HttpSession session) {
        Administrator admin = (Administrator) session.getAttribute("admin");
        if (admin != null) {
            if (!admin.getPassword().equals(administrator.getAccount())) {
                return AjaxResult.fail(204,"密码错误");
            } else {
                administrator.setAccount(admin.getAccount());
                adminService.updatePassword(administrator);
                return AjaxResult.success("修改管理员用户信息成功");
            }
        } else {
            return AjaxResult.fail(2,"");
        }
    }


    @PostMapping("/login")
    public AjaxResult login(@RequestBody Administrator administrator, HttpSession session){
        Administrator admin = adminService.login(administrator);
        session.setAttribute("admin", admin);
        return AjaxResult.success("登陆成功");
    }

    @GetMapping("/detail")
    public AjaxResult detail(HttpSession session){
        Administrator admin = (Administrator) session.getAttribute("admin");
        admin.setPassword("");
        return AjaxResult.success(admin);
    }

    @GetMapping("/logout")
    public AjaxResult logout(HttpSession session){
        session.removeAttribute("admin");
        return AjaxResult.success("退出登录成功");
    }
}
