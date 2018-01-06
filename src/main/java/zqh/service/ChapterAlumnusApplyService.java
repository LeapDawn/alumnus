package zqh.service;

import zqh.dto.RequestList;
import zqh.dto.ResultModel;
import zqh.model.ChapterAlumnusApply;

/**
 * 申请加入校友分会相关申请
 */
public interface ChapterAlumnusApplyService {

    /**
     * 申请加入某个校友分会
     * @param apply
     */
    void apply(ChapterAlumnusApply apply);

    /**
     * 通过申请
     * @param apply
     */
    void approveApply(ChapterAlumnusApply apply);

    /**
     * 不通过申请
     * @param apply
     */
    void disapproveApply(ChapterAlumnusApply apply);

    /**
     * 获取校友最近的一条申请加入分会记录
     * @param alumnus
     * @return
     */
    ChapterAlumnusApply getMyApply(Integer alumnus);

    /**
     * 获取加入分会申请列表
     * @param rl
     * @return
     */
    ResultModel<ChapterAlumnusApply> list(RequestList<ChapterAlumnusApply> rl);
}
