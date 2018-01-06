package zqh.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import zqh.commons.util.SessionUtil;
import zqh.dto.AjaxResult;
import zqh.dto.RequestList;
import zqh.dto.ResultModel;
import zqh.model.Clazz;
import zqh.model.ClazzApply;
import zqh.service.ClazzApplyService;
import zqh.service.ClazzService;

import javax.servlet.http.HttpSession;

@RestController
@CrossOrigin
@RequestMapping("/clazz")
public class ClazzController {

    @Autowired
    private ClazzService clazzService;

    @Autowired
    private ClazzApplyService clazzApplyService;

    @PostMapping("/apply")
    public AjaxResult addition(@RequestBody ClazzApply clazzApply, HttpSession session) {
        Integer alumnus = SessionUtil.getUserAlumnus(session);
        clazzApply.setAlumnus(alumnus);
        clazzApplyService.apply(clazzApply);
        return AjaxResult.success("申请校友班级成功，等待管理员审核！");
    }

    @PutMapping("/update")
    public AjaxResult update(@RequestBody Clazz clazz, HttpSession session) {
        Integer alumnus = SessionUtil.getUserAlumnus(session);
        clazzService.updateContent(clazz, alumnus);
        return AjaxResult.success("修改校友班级内容介绍成功");
    }

    @PostMapping("/list")
    public AjaxResult list(@RequestBody RequestList<Clazz> requestList) {
        Clazz key = requestList.getKey();
        ResultModel<Clazz> list = clazzService.list(requestList);
        return AjaxResult.success(list);
    }

    @GetMapping("/item")
    public AjaxResult getByName(@RequestParam("item") String name) {
        return AjaxResult.success(clazzService.selectOne(name));
    }

    @GetMapping("/apply/status")
    public AjaxResult getByAdder(HttpSession session) {
        Integer alumnus = SessionUtil.getUserAlumnus(session);
        return AjaxResult.success(clazzApplyService.getMyApply(alumnus));
    }

}
