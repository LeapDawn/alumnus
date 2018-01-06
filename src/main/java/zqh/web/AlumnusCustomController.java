package zqh.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import zqh.commons.util.SessionUtil;
import zqh.dto.AjaxResult;
import zqh.dto.RequestList;
import zqh.dto.ResultModel;
import zqh.model.Account;
import zqh.model.Alumnus;
import zqh.service.AccountService;
import zqh.service.AlumnusService;

import javax.servlet.http.HttpSession;

@RestController
@CrossOrigin
@RequestMapping("/alumnus")
public class AlumnusCustomController {
    @Autowired
    private AlumnusService alumnusService;

    @Autowired
    private AccountService accountService;

    @PostMapping("/addition")
    public AjaxResult save(@RequestBody Alumnus alumnus, HttpSession session) {
        Account account = SessionUtil.getUser(session);
        if (account != null && account.getAlumnus() == null) {
            alumnus.setSpecial(false);
            alumnus.setInvalid(1);
            alumnusService.save(alumnus);
            // 将数据库返回的新主键存储在account中
            account.setAlumnus(alumnus.getId());
            accountService.updateAlumnus(account);
            return AjaxResult.success("绑定校友信息成功,等待管理员审核");
        } else {
            return AjaxResult.fail(2, "您已经绑定校友信息了！");
        }
    }

    @PutMapping("/update")
    public AjaxResult update(@RequestBody Alumnus alumnus,  HttpSession session) {
        Account account = SessionUtil.getUser(session);
        alumnus.setId(account.getAlumnus());
        alumnusService.update(alumnus);
        Integer invalid = (Integer)session.getAttribute("invalid");
        // 审核不通过的情况下，重新提交信息，将审核状态重置为未审核
        if (invalid != null && invalid == 2) {
            alumnusService.updateInvalid(alumnus.getId(), 1);
            session.setAttribute("invalid", 1);
        }
        return AjaxResult.success("更新个人校友信息成功");
    }

    @RequestMapping(value = "/list/valid", method = {RequestMethod.GET, RequestMethod.POST})
    public AjaxResult listValid(@RequestBody RequestList<Alumnus> requestList) {
        ResultModel<Alumnus> list = alumnusService.listValid(requestList);
        return AjaxResult.success(list);
    }

    @RequestMapping("/item")
    public AjaxResult getAlumnus(@RequestParam("id") Integer id){
        return AjaxResult.success(alumnusService.selectOne(id));
    }
}
