package zqh.web.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import zqh.dto.AjaxResult;
import zqh.dto.RequestList;
import zqh.dto.ResultModel;
import zqh.model.ChapterApply;
import zqh.service.ChapterApplyService;
import zqh.service.ClazzApplyService;

@RestController
@CrossOrigin
@RequestMapping("/admin/chapter/apply")
public class ChapterApplyController {

    @Autowired
    private ChapterApplyService chapterApplyService;

    @PostMapping("/list")
    public AjaxResult list(@RequestBody RequestList<Integer> requestList) {
        ResultModel<ChapterApply> list = chapterApplyService.list(requestList);
        return AjaxResult.success(list);
    }

    @PutMapping("/approve")
    public AjaxResult checkValid(@RequestBody ChapterApply chapterApply) {
        chapterApplyService.approveApply(chapterApply);
        return AjaxResult.success("该校友分会已确认审核通过！");
    }

    @PutMapping("/disapprove")
    public AjaxResult checkInvalid(@RequestBody ChapterApply chapterApply) {
        chapterApplyService.disapproveApply(chapterApply);
        return AjaxResult.success("该校友分会已确认审核未通过！");
    }

}
