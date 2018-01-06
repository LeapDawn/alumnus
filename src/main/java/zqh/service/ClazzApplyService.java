package zqh.service;

import zqh.dto.RequestList;
import zqh.dto.ResultModel;
import zqh.model.ClazzApply;

/**
 * 申请校友班级相关业务
 */
public interface ClazzApplyService {

    /**
     * 提交建立新校友班级的申请
     * @param clazzApply
     */
    void apply(ClazzApply clazzApply);

    /**
     * 通过申请
     * @param clazzApply
     */
    void approveApply(ClazzApply clazzApply);

    /**
     * 不通过申请
     * @param clazzApply
     */
    void disapproveApply(ClazzApply clazzApply);

    /**
     * 获取校友最近的一条申请记录
     * @param alumnus
     * @return
     */
    ClazzApply getMyApply(Integer alumnus);

    /**
     * 获取校友班级申请列表
     * @param rl
     * @return
     */
    ResultModel<ClazzApply> list(RequestList<Integer> rl);
}
