package zqh.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zqh.commons.exception.DataViolationException;
import zqh.dao.ChapterAlumnusApplyDAO;
import zqh.dto.RequestList;
import zqh.dto.ResultModel;
import zqh.model.ChapterAlumnus;
import zqh.model.ChapterAlumnusApply;
import zqh.service.ChapterAlumnusApplyService;
import zqh.service.ChapterAlumnusService;

import java.util.Date;
import java.util.List;

@Service("chapterAlumnusApply")
public class ChapterAlumnusApplyServiceImpl implements ChapterAlumnusApplyService {

    @Autowired
    private ChapterAlumnusApplyDAO chapterAlumnusApplyDAO;

    @Autowired
    private ChapterAlumnusService chapterAlumnusService;

    @Override
    public void apply(ChapterAlumnusApply apply) {
        apply.setStatus(0);
        apply.setDate(new Date());
        ChapterAlumnusApply myApply = this.getMyApply(apply.getAlumnus());
        if (myApply.getStatus() == 0) {
            throw new DataViolationException(601, "已有待审核的加入校友分会申请!");
        }
        chapterAlumnusApplyDAO.insert(apply);
    }

    @Override
    public void approveApply(ChapterAlumnusApply apply) {
        apply.setStatus(1);
        chapterAlumnusApplyDAO.updateStatus(apply);
        ChapterAlumnusApply record = chapterAlumnusApplyDAO.selectById(apply.getId());
        ChapterAlumnus chapterAlumnus = new ChapterAlumnus();
        chapterAlumnus.setChapter(record.getChapter());
        chapterAlumnus.setAlumnus(record.getAlumnus());
        chapterAlumnus.setAdmin(false);
        chapterAlumnusService.save(chapterAlumnus);
    }

    @Override
    public void disapproveApply(ChapterAlumnusApply apply) {
        apply.setStatus(2);
        chapterAlumnusApplyDAO.updateStatus(apply);
    }

    @Override
    public ChapterAlumnusApply getMyApply(Integer alumnus) {
        return chapterAlumnusApplyDAO.selectByAlumnus(alumnus);
    }

    @Override
    public ResultModel<ChapterAlumnusApply> list(RequestList<ChapterAlumnusApply> rl) {
        ChapterAlumnusApply key = rl.getKey();
        key = key != null ? key : new ChapterAlumnusApply();
        int total = chapterAlumnusApplyDAO.count(key);
        ResultModel<ChapterAlumnusApply> resultModel = new ResultModel<>(total, rl.getRows(), rl.getPage());
        List<ChapterAlumnusApply> list = chapterAlumnusApplyDAO.selectList(rl.getKey(),
                (resultModel.getCurrentPage() - 1) * resultModel.getRows(), resultModel.getRows());
        resultModel.setData(list);
        return resultModel;
    }
}
