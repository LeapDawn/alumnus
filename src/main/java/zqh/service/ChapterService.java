package zqh.service;

import zqh.dto.RequestList;
import zqh.dto.ResultModel;
import zqh.model.Chapter;

import java.util.List;

/**
 * 校友分会业务接口
 */
public interface ChapterService {

    /**
     * 新增校友分会信息
     * @param chapter
     */
    void save(Chapter chapter);

    /**
     * 更新校友分会信息
     * @param chapter  待修改信息
     * @param alumnus 修改人的校友ID
     */
    void updateContent(Chapter chapter, Integer alumnus);

    /**
     * 获取校友分会列表
     * @param requestList
     * @return
     */
    ResultModel<Chapter> list(RequestList<Chapter> requestList);

    /**
     * 根据名称获取校友分会
     * @param name
     * @return
     */
    Chapter selectOne(String name);

    /**
     * 根据成员查询所在校友分会信息
     * @param accout
     * @return
     */
    Chapter selectByAccount(String accout);
}
