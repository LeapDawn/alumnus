package zqh.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import zqh.commons.util.SessionUtil;
import zqh.dto.AjaxResult;
import zqh.dto.RequestList;
import zqh.model.ChapterAlumnus;
import zqh.model.ChapterAlumnusApply;
import zqh.service.ChapterAlumnusApplyService;
import zqh.service.ChapterAlumnusService;

import javax.servlet.http.HttpSession;

@RestController
@CrossOrigin
@RequestMapping("/chapter/alumnus/admin")
public class ChapterAlumnusAdminController {

    @Autowired
    private ChapterAlumnusService chapterAlumnusService;

    @Autowired
    private ChapterAlumnusApplyService chapterAlumnusApplyService;

    @PostMapping("/apply/list")
    public AjaxResult listApply(@RequestBody RequestList<ChapterAlumnusApply> requestList, HttpSession session) {
        Integer alumnus = SessionUtil.getUserAlumnus(session);
        ChapterAlumnus chapterAlumnus = new ChapterAlumnus();
        chapterAlumnus.setAlumnus(alumnus);
        chapterAlumnus.setChapter(requestList.getKey().getChapter());
        if (chapterAlumnusService.isAdmin(chapterAlumnus) ){
            return AjaxResult.success(chapterAlumnusApplyService.list(requestList));
        } else {
            return AjaxResult.fail(602, "分会管理员才能进行此操作!");
        }
    }

    @PostMapping("/apply/approve")
    public AjaxResult approveApply(@RequestBody ChapterAlumnusApply chapterAlumnusApply, HttpSession session) {
        Integer alumnus = SessionUtil.getUserAlumnus(session);
        ChapterAlumnus chapterAlumnus = new ChapterAlumnus();
        chapterAlumnus.setAlumnus(alumnus);
        chapterAlumnus.setChapter(chapterAlumnusApply.getChapter());
        if (chapterAlumnusService.isAdmin(chapterAlumnus) ){
            chapterAlumnusApplyService.approveApply(chapterAlumnusApply);
            return AjaxResult.success("已确认通过此申请!");
        } else {
            return AjaxResult.fail(602, "分会管理员才能进行此操作!");
        }
    }

    @PostMapping("/apply/disapprove")
    public AjaxResult disapproveApply(@RequestBody ChapterAlumnusApply chapterAlumnusApply, HttpSession session) {
        Integer alumnus = SessionUtil.getUserAlumnus(session);
        ChapterAlumnus chapterAlumnus = new ChapterAlumnus();
        chapterAlumnus.setAlumnus(alumnus);
        chapterAlumnus.setChapter(chapterAlumnusApply.getChapter());
        if (chapterAlumnusService.isAdmin(chapterAlumnus) ){
            chapterAlumnusApplyService.disapproveApply(chapterAlumnusApply);
            return AjaxResult.success("已确认通过此申请!");
        } else {
            return AjaxResult.fail(602, "分会管理员才能进行此操作!");
        }
    }

    @PutMapping("/appoint")
    public AjaxResult appointAdmin(@RequestBody ChapterAlumnus chapterAlumnus, HttpSession session) {
        Integer alumnus = SessionUtil.getUserAlumnus(session);
        chapterAlumnusService.appointAdmin(chapterAlumnus, alumnus);
        return AjaxResult.success("任命管理员成功!");
    }

}
