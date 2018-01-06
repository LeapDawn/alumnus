package zqh.service;

import zqh.dto.RequestList;
import zqh.dto.ResultModel;
import zqh.model.ChapterApply;

/**
 * 申请校友分会相关业务
 */
public interface ChapterApplyService {

    /**
     * 提交建立新校友分会的申请
     * @param chapterApply
     */
    void apply(ChapterApply chapterApply);

    /**
     * 通过申请
     * @param chapterApply
     */
    void approveApply(ChapterApply chapterApply);

    /**
     * 不通过申请
     * @param chapterApply
     */
    void disapproveApply(ChapterApply chapterApply);

    /**
     * 获取校友最近的一条申请记录
     * @param alumnus
     * @return
     */
    ChapterApply getMyApply(Integer alumnus);

    /**
     * 获取校友分会申请列表
     * @param rl
     * @return
     */
    ResultModel<ChapterApply> list(RequestList<Integer> rl);
}
