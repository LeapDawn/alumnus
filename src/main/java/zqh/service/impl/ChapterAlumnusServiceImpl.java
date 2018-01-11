package zqh.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zqh.commons.exception.DataViolationException;
import zqh.dao.ChapterAlumnusDAO;
import zqh.dto.RequestList;
import zqh.dto.ResultModel;
import zqh.model.Alumnus;
import zqh.model.Chapter;
import zqh.model.ChapterAlumnus;
import zqh.service.ChapterAlumnusService;
import zqh.service.ChapterService;

import java.util.List;

@Service("chapterAlumnusService")
public class ChapterAlumnusServiceImpl implements ChapterAlumnusService {

    @Autowired
    private ChapterAlumnusDAO chapterAlumnusDAO;

    @Autowired
    private ChapterService chapterService;

    @Override
    public void save(ChapterAlumnus chapterAlumnus) {
        chapterAlumnusDAO.insert(chapterAlumnus);
    }

    @Override
    public void delete(ChapterAlumnus chapterAlumnus) {
        chapterAlumnusDAO.deleteByChapterAndAlumnus(chapterAlumnus);
    }

    @Override
    public Chapter selectChapter(Integer alumnus) {
        ChapterAlumnus chapterAlumnus = this.hasJoined(alumnus);
        if (chapterAlumnus == null) {
            throw new DataViolationException(402, "没有加入校友分会!");
        } else {
            return chapterService.selectOne(chapterAlumnus.getChapter());
        }
    }

    @Override
    public ResultModel<Alumnus> listAlumnus(RequestList<ChapterAlumnus> rl) {
        ChapterAlumnus key = rl.getKey();
        int total = chapterAlumnusDAO.count(key);
        ResultModel<Alumnus> resultModel = new ResultModel<>(total, rl.getRows(), rl.getPage());
        List<Alumnus> list = chapterAlumnusDAO.selectList(key, (resultModel.getCurrentPage() - 1) * resultModel.getRows(), resultModel.getRows());
        resultModel.setData(list);
        return resultModel;
    }

    @Override
    public boolean isAdmin(ChapterAlumnus chapterAlumnus) {
        ChapterAlumnus record = chapterAlumnusDAO.selectByChapterAndAlumnus(chapterAlumnus);
        return record == null ? false : record.getAdmin();
    }

    @Override
    public ChapterAlumnus hasJoined(Integer alumnus) {
        ChapterAlumnus record = new ChapterAlumnus();
        record.setAlumnus(alumnus);
        return chapterAlumnusDAO.selectByAlumnus(record);
    }

    @Override
    public void appointAdmin(ChapterAlumnus chapterAlumnus, Integer adminId) {
        ChapterAlumnus entity = new ChapterAlumnus();
        entity.setAlumnus(adminId);
        entity.setChapter(chapterAlumnus.getChapter());
        if (this.isAdmin(entity)){
            chapterAlumnus.setAdmin(true);
            chapterAlumnusDAO.updateAdmin(chapterAlumnus);
        } else {
            throw new DataViolationException(403, "您不是管理员,无任命管理员权限");
        }
    }
}
