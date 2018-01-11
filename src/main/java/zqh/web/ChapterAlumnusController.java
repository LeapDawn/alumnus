package zqh.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import zqh.commons.util.SessionUtil;
import zqh.dto.AjaxResult;
import zqh.dto.RequestList;
import zqh.dto.ResultModel;
import zqh.model.Alumnus;
import zqh.model.ChapterAlumnus;
import zqh.model.ChapterAlumnusApply;
import zqh.service.ChapterAlumnusApplyService;
import zqh.service.ChapterAlumnusService;

import javax.servlet.http.HttpSession;

@RestController
@CrossOrigin
@RequestMapping("/chapter/alumnus")
public class ChapterAlumnusController {

    @Autowired
    private ChapterAlumnusService chapterAlumnusService;

    @Autowired
    private ChapterAlumnusApplyService chapterAlumnusApplyService;

    @PostMapping("/apply")
    public AjaxResult apply(@RequestBody ChapterAlumnusApply chapterAlumnusApply, HttpSession session) {
        Integer alumnus = SessionUtil.getUserAlumnus(session);
        chapterAlumnusApply.setAlumnus(alumnus);
        chapterAlumnusApplyService.apply(chapterAlumnusApply);
        return AjaxResult.success("申请加入校友分会成功，等待管理员审核！");
}

    @DeleteMapping("/leave")
    public AjaxResult leave(@RequestParam("chapter") String chapter, HttpSession session) {
        Integer alumnus = SessionUtil.getUserAlumnus(session);
        ChapterAlumnus chapterAlumnus = new ChapterAlumnus();
        chapterAlumnus.setChapter(chapter);
        chapterAlumnus.setAlumnus(alumnus);
        chapterAlumnusService.delete(chapterAlumnus);
        return AjaxResult.success("成功退出校友分会");
    }

    @GetMapping("/mine")
    public AjaxResult query(HttpSession session) {
        Integer alumnus = SessionUtil.getUserAlumnus(session);
        return AjaxResult.success(chapterAlumnusService.selectChapter(alumnus));
    }

    @PostMapping("/member")
    public AjaxResult member(@RequestBody RequestList<ChapterAlumnus> requestList) {
        ResultModel<Alumnus> alumnusResultModel = chapterAlumnusService.listAlumnus(requestList);
        return AjaxResult.success(alumnusResultModel);
    }

    @GetMapping("/apply/mine")
    public AjaxResult myApply(HttpSession session){
        Integer alumnus = SessionUtil.getUserAlumnus(session);
        return AjaxResult.success(chapterAlumnusApplyService.getMyApply(alumnus));
    }

    @GetMapping("/joined")
    public AjaxResult hasJoined(HttpSession session) {
        Integer alumnus = SessionUtil.getUserAlumnus(session);
        return AjaxResult.success(chapterAlumnusService.hasJoined(alumnus));
    }

    @GetMapping("/isAdmin")
    public AjaxResult isAdmin(@RequestParam("chapter") String chapter, HttpSession session) {
        Integer alumnus = SessionUtil.getUserAlumnus(session);
        ChapterAlumnus chapterAlumnus = new ChapterAlumnus();
        chapterAlumnus.setChapter(chapter);
        chapterAlumnus.setAlumnus(alumnus);
        return AjaxResult.success(chapterAlumnusService.isAdmin(chapterAlumnus));
    }
}
