package zqh.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import zqh.commons.util.SessionUtil;
import zqh.dto.AjaxResult;
import zqh.dto.RequestList;
import zqh.model.ClazzAlumnus;
import zqh.model.ClazzAlumnusApply;
import zqh.service.ClazzAlumnusApplyService;
import zqh.service.ClazzAlumnusService;

import javax.servlet.http.HttpSession;

@RestController
@CrossOrigin
@RequestMapping("/clazz/alumnus/admin")
public class ClazzAlumnusAdminController {

    @Autowired
    private ClazzAlumnusService clazzAlumnusService;

    @Autowired
    private ClazzAlumnusApplyService clazzAlumnusApplyService;

    @PostMapping("/apply/list")
    public AjaxResult listApply(@RequestBody RequestList<ClazzAlumnusApply> requestList, HttpSession session) {
        Integer alumnus = SessionUtil.getUserAlumnus(session);
        ClazzAlumnus clazzAlumnus = new ClazzAlumnus();
        clazzAlumnus.setAlumnus(alumnus);
        clazzAlumnus.setClazz(requestList.getKey().getClazz());
        if (clazzAlumnusService.isAdmin(clazzAlumnus) ){
            return AjaxResult.success(clazzAlumnusApplyService.list(requestList));
        } else {
            return AjaxResult.fail(602, "班级管理员才能进行此操作!");
        }
    }

    @PostMapping("/apply/approve")
    public AjaxResult approveApply(@RequestBody ClazzAlumnusApply clazzAlumnusApply, HttpSession session) {
        Integer alumnus = SessionUtil.getUserAlumnus(session);
        ClazzAlumnus clazzAlumnus = new ClazzAlumnus();
        clazzAlumnus.setAlumnus(alumnus);
        clazzAlumnus.setClazz(clazzAlumnusApply.getClazz());
        if (clazzAlumnusService.isAdmin(clazzAlumnus) ){
            clazzAlumnusApplyService.approveApply(clazzAlumnusApply);
            return AjaxResult.success("已确认通过此申请!");
        } else {
            return AjaxResult.fail(602, "班级管理员才能进行此操作!");
        }
    }

    @PostMapping("/apply/disapprove")
    public AjaxResult disapproveApply(@RequestBody ClazzAlumnusApply clazzAlumnusApply, HttpSession session) {
        Integer alumnus = SessionUtil.getUserAlumnus(session);
        ClazzAlumnus clazzAlumnus = new ClazzAlumnus();
        clazzAlumnus.setAlumnus(alumnus);
        clazzAlumnus.setClazz(clazzAlumnusApply.getClazz());
        if (clazzAlumnusService.isAdmin(clazzAlumnus) ){
            clazzAlumnusApplyService.disapproveApply(clazzAlumnusApply);
            return AjaxResult.success("已确认通过此申请!");
        } else {
            return AjaxResult.fail(602, "班级管理员才能进行此操作!");
        }
    }

    @PutMapping("/appoint")
    public AjaxResult appointAdmin(@RequestBody ClazzAlumnus clazzAlumnus, HttpSession session) {
        Integer alumnus = SessionUtil.getUserAlumnus(session);
        clazzAlumnusService.appointAdmin(clazzAlumnus, alumnus);
        return AjaxResult.success("任命管理员成功!");
    }

}
