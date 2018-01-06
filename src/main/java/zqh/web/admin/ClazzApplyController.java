package zqh.web.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import zqh.dto.AjaxResult;
import zqh.dto.RequestList;
import zqh.dto.ResultModel;
import zqh.model.ClazzApply;
import zqh.service.ClazzApplyService;

@RestController
@CrossOrigin
@RequestMapping("/admin/clazz/apply")
public class ClazzApplyController {

    @Autowired
    private ClazzApplyService clazzApplyService;

    @PostMapping("/list")
    public AjaxResult list(@RequestBody RequestList<Integer> requestList) {
        ResultModel<ClazzApply> list = clazzApplyService.list(requestList);
        return AjaxResult.success(list);
    }

    @PutMapping("/approve")
    public AjaxResult checkValid(@RequestBody ClazzApply clazzApply) {
        clazzApplyService.approveApply(clazzApply);
        return AjaxResult.success("该校友班级已确认审核通过！");
    }

    @PutMapping("/disapprove")
    public AjaxResult checkInvalid(@RequestBody ClazzApply clazzApply) {
        clazzApplyService.disapproveApply(clazzApply);
        return AjaxResult.success("该校友班级已确认审核未通过！");
    }

}
