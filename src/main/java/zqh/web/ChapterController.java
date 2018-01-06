package zqh.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import zqh.commons.util.SessionUtil;
import zqh.dto.AjaxResult;
import zqh.dto.RequestList;
import zqh.dto.ResultModel;
import zqh.model.Chapter;
import zqh.model.ChapterApply;
import zqh.service.ChapterApplyService;
import zqh.service.ChapterService;

import javax.servlet.http.HttpSession;

@RestController
@CrossOrigin
@RequestMapping("/chapter")
public class ChapterController {

    @Autowired
    private ChapterService chapterService;

    @Autowired
    private ChapterApplyService chapterApplyService;

    @PostMapping("/apply")
    public AjaxResult addition(@RequestBody ChapterApply chapterApply, HttpSession session) {
        Integer alumnus = SessionUtil.getUserAlumnus(session);
        chapterApply.setAlumnus(alumnus);
        chapterApplyService.apply(chapterApply);
        return AjaxResult.success("申请校友分会成功，等待管理员审核！");
    }

    @PutMapping("/update")
    public AjaxResult update(@RequestBody Chapter chapter, HttpSession session) {
        Integer alumnus = SessionUtil.getUserAlumnus(session);
        chapterService.updateContent(chapter, alumnus);
        return AjaxResult.success("修改校友分会内容介绍成功");
    }

    @PostMapping("/list")
    public AjaxResult list(@RequestBody RequestList<Chapter> requestList) {
        Chapter key = requestList.getKey();
        ResultModel<Chapter> list = chapterService.list(requestList);
        return AjaxResult.success(list);
    }

    @GetMapping("/item")
    public AjaxResult getByName(@RequestParam("item") String name) {
        return AjaxResult.success(chapterService.selectOne(name));
    }

    @GetMapping("/apply/status")
    public AjaxResult getByAdder(HttpSession session) {
        Integer alumnus = SessionUtil.getUserAlumnus(session);
        return AjaxResult.success(chapterApplyService.getMyApply(alumnus));
    }

}
