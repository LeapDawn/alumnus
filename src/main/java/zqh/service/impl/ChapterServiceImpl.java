package zqh.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zqh.commons.exception.DataViolationException;
import zqh.dao.ChapterDAO;
import zqh.dto.RequestList;
import zqh.dto.ResultModel;
import zqh.model.Chapter;
import zqh.model.ChapterAlumnus;
import zqh.service.ChapterAlumnusService;
import zqh.service.ChapterService;

import java.util.Date;
import java.util.List;

@Service("chapterService")
public class ChapterServiceImpl implements ChapterService {

    @Autowired
    private ChapterDAO chapterDAO;

    @Autowired
    private ChapterAlumnusService chapterAlumnusService;

    @Override
    public void save(Chapter chapter) {
        chapter.setAddtime(new Date());
        chapterDAO.insert(chapter);
    }

    @Override
    public void updateContent(Chapter chapter, Integer alumnus) {
        // 验证修改人是否分会管理员
        ChapterAlumnus chapterAlumnus = new ChapterAlumnus();
        chapterAlumnus.setChapter(chapter.getName());
        chapterAlumnus.setAlumnus(alumnus);
        if (chapterAlumnusService.isAdmin(chapterAlumnus)){
            chapterDAO.updateContent(chapter);
        } else {
            throw new DataViolationException(701, "分会管理员才能修改分会信息");
        }
    }


    @Override
    public ResultModel<Chapter> list(RequestList<Chapter> requestList) {
        Chapter key = requestList.getKey();
        int total = chapterDAO.count(key);
        ResultModel<Chapter> resultModel = new ResultModel<>(total, requestList.getRows(), requestList.getPage());
        List<Chapter> list = chapterDAO.selectList(key, (resultModel.getCurrentPage() - 1) * resultModel.getRows(), resultModel.getRows());
        resultModel.setData(list);
        return resultModel;
    }

    @Override
    public Chapter selectOne(String name) {
        return chapterDAO.selectOne(name);
    }

    @Override
    public Chapter selectByAccount(String accout) {
        // TODO 获取可以放到关系业务中实现？通过调用selectOne
        return null;
    }
}
