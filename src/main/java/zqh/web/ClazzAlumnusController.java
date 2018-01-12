package zqh.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import zqh.commons.util.SessionUtil;
import zqh.dto.AjaxResult;
import zqh.dto.RequestList;
import zqh.dto.ResultModel;
import zqh.model.Alumnus;
import zqh.model.ClazzAlumnus;
import zqh.model.ClazzAlumnusApply;
import zqh.service.ClazzAlumnusApplyService;
import zqh.service.ClazzAlumnusService;

import javax.servlet.http.HttpSession;

@RestController
@CrossOrigin
@RequestMapping("/clazz/alumnus")
public class ClazzAlumnusController {

    @Autowired
    private ClazzAlumnusService clazzAlumnusService;

    @Autowired
    private ClazzAlumnusApplyService clazzAlumnusApplyService;

    @PostMapping("/apply")
    public AjaxResult apply(@RequestBody ClazzAlumnusApply clazzAlumnusApply, HttpSession session) {
        Integer alumnus = SessionUtil.getUserAlumnus(session);
        clazzAlumnusApply.setAlumnus(alumnus);
        clazzAlumnusApplyService.apply(clazzAlumnusApply);
        return AjaxResult.success("申请加入校友班级成功，等待管理员审核！");
    }

    @DeleteMapping("/leave")
    public AjaxResult leave(@RequestParam("clazz") String clazz, HttpSession session) {
        Integer alumnus = SessionUtil.getUserAlumnus(session);
        ClazzAlumnus clazzAlumnus = new ClazzAlumnus();
        clazzAlumnus.setClazz(clazz);
        clazzAlumnus.setAlumnus(alumnus);
        clazzAlumnusService.delete(clazzAlumnus);
        return AjaxResult.success("成功退出校友班级");
    }

    @GetMapping("/mine")
    public AjaxResult query(HttpSession session) {
        Integer alumnus = SessionUtil.getUserAlumnus(session);
        return AjaxResult.success(clazzAlumnusService.selectClazz(alumnus));
    }

    @PostMapping("/member")
    public AjaxResult memberByAdminOrAccount(@RequestBody RequestList<ClazzAlumnus> requestList) {
        ResultModel<Alumnus> alumnusResultModel = clazzAlumnusService.listAlumnus(requestList);
        return AjaxResult.success(alumnusResultModel);
    }

    @GetMapping("/apply/mine")
    public AjaxResult myAppaly(HttpSession session){
        Integer alumnus = SessionUtil.getUserAlumnus(session);
        return AjaxResult.success(clazzAlumnusApplyService.getMyApply(alumnus));
    }

    @GetMapping("/joined")
    public AjaxResult hasJoined(HttpSession session) {
        Integer alumnus = SessionUtil.getUserAlumnus(session);
        return AjaxResult.success(clazzAlumnusService.hasJoined(alumnus));
    }

    @GetMapping("/isAdmin")
    public AjaxResult isAdmin(@RequestParam("clazz") String clazz, HttpSession session) {
        Integer alumnus = SessionUtil.getUserAlumnus(session);
        ClazzAlumnus clazzAlumnus = new ClazzAlumnus();
        clazzAlumnus.setClazz(clazz);
        clazzAlumnus.setAlumnus(alumnus);
        return AjaxResult.success(clazzAlumnusService.isAdmin(clazzAlumnus));
    }
}
