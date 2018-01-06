package zqh.service;

import zqh.dto.RequestList;
import zqh.dto.ResultModel;
import zqh.model.Clazz;

/**
 * 校友班级业务接口
 */
public interface ClazzService {

    /**
     * 新增校友班级信息
     * @param clazz
     */
    void save(Clazz clazz);

    /**
     * 更新校友班级信息
     * @param clazz  待修改信息
     * @param alumnus 修改人的校友ID
     */
    void updateContent(Clazz clazz, Integer alumnus);

    /**
     * 获取校友班级列表
     * @param requestList
     * @return
     */
    ResultModel<Clazz> list(RequestList<Clazz> requestList);

    /**
     * 根据名称获取校友班级
     * @param name
     * @return
     */
    Clazz selectOne(String name);

    /**
     * 根据成员查询所在校友班级信息
     * @param accout
     * @return
     */
    Clazz selectByAccount(String accout);
}
