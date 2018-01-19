package zqh.service;

import zqh.dto.RequestList;
import zqh.dto.ResultModel;
import zqh.model.Alumnus;
import zqh.model.ClazzAlumnusApply;

/**
 * 申请加入校友班级相关申请
 */
public interface  ClazzAlumnusApplyService {

    /**
     * 申请加入某个校友班级
     * @param apply
     */
    void apply(ClazzAlumnusApply apply);

    /**
     * 通过申请
     * @param apply
     */
    void approveApply(ClazzAlumnusApply apply);

    /**
     * 不通过申请
     * @param apply
     */
    void disapproveApply(ClazzAlumnusApply apply);

    /**
     * 获取校友最近的一条申请加入班级记录
     * @param alumnus
     * @return
     */
    ClazzAlumnusApply getMyApply(Integer alumnus);

    /**
     * 获取加入班级申请列表
     * @param rl
     * @return
     */
    ResultModel<Alumnus> list(RequestList<ClazzAlumnusApply> rl);
}
