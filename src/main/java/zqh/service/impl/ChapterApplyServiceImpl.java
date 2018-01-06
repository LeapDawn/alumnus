package zqh.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zqh.commons.exception.DataViolationException;
import zqh.dao.ChapterApplyDAO;
import zqh.dto.RequestList;
import zqh.dto.ResultModel;
import zqh.model.Chapter;
import zqh.model.ChapterAlumnus;
import zqh.model.ChapterApply;
import zqh.service.ChapterAlumnusService;
import zqh.service.ChapterApplyService;
import zqh.service.ChapterService;

import java.util.List;

@Service("chapterApplyService")
public class ChapterApplyServiceImpl implements ChapterApplyService {

    @Autowired
    private ChapterApplyDAO chapterApplyDAO;

    @Autowired
    private ChapterService chapterService;

    @Autowired
    private ChapterAlumnusService chapterAlumnusService;

    @Override
    public void apply(ChapterApply chapterApply) {
        ChapterApply myApply = this.getMyApply(chapterApply.getAlumnus());
        if (myApply != null && myApply.getStatus() == 1) {
            throw new DataViolationException(501,"已有待审核的建立校友分会申请");
        }
        chapterApply.setStatus(0);
        chapterApplyDAO.insert(chapterApply);
    }

    @Transactional
    @Override
    public void approveApply(ChapterApply chapterApply) {
        chapterApply.setStatus(1);
        chapterApplyDAO.updateStatus(chapterApply);
        chapterApply = chapterApplyDAO.selectById(chapterApply.getId());
        Chapter chapter = new Chapter();
        chapter.setAdder(chapterApply.getAlumnus());
        chapter.setContent(chapterApply.getContent());
        chapter.setName(chapterApply.getName());
        chapterService.save(chapter);
        // 将申请人加入校友分会并赋予管理员权限
        ChapterAlumnus chapterAlumnus = new ChapterAlumnus();
        chapterAlumnus.setAlumnus(chapter.getAdder());
        chapterAlumnus.setChapter(chapter.getName());
        chapterAlumnus.setAdmin(true);
        chapterAlumnusService.save(chapterAlumnus);
    }

    @Override
    public void disapproveApply(ChapterApply chapterApply) {
        chapterApply.setStatus(2);
        chapterApplyDAO.updateStatus(chapterApply);
    }

    @Override
    public ChapterApply getMyApply(Integer alumnus) {
        return chapterApplyDAO.selectByAdder(alumnus);
    }

    @Override
    public ResultModel<ChapterApply> list(RequestList<Integer> rl) {
        int total = chapterApplyDAO.countByStatus(rl.getKey());
        ResultModel<ChapterApply> resultModel = new ResultModel<>(total, rl.getRows(), rl.getPage());
        List<ChapterApply> list = chapterApplyDAO.selectListByStatus(rl.getKey(),
                (resultModel.getCurrentPage() - 1) * resultModel.getRows(), resultModel.getRows());
        resultModel.setData(list);
        return resultModel;
    }
}
