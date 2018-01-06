package zqh.service;

import zqh.commons.exception.ExcelException;
import zqh.dto.RequestList;
import zqh.dto.ResultModel;
import zqh.dto.UploadResultModel;
import zqh.model.Alumnus;

import java.io.InputStream;

public interface AlumnusService {

    /**
     * 新增校友信息
     * @param alumnus
     */
     void save(Alumnus alumnus);

    /**
     * 更新校友信息
     * @param alumnus
     */
     void update(Alumnus alumnus);

    /**
     * 审核校友信息，更改其生效状态（用户自注册时提交的校友信息）
     * 0审核通过，1未审核，2审核不通过
     * @param alumnusId
     */
     void updateInvalid(Integer alumnusId, Integer invalid);

    /**
     * 根据姓名和身份证号码获取校友信息
     * @param alumnus 包含校友姓名和身份证信息的实体
     * @return 数据库中对应校友信息的编号
     */
    Integer selectOneByNameAndCard(Alumnus alumnus);

    /**
     * 根据主键获取校友信息
     * @param id
     * @return
     */
    Alumnus selectOne(Integer id);

    /**
     * 删除校友信息
     * @param ids
     * @return
     */
     int delete(int ids);

    /**
     * 获取校友信息列表
     * @param rl
     * @return
     */
    ResultModel<Alumnus> listValid(RequestList<Alumnus> rl);

    /**
     * 获取待审核校友信息列表
     * @param rl
     * @return
     */
    ResultModel<Alumnus> listInvalid(RequestList rl);

    /**
     * excel录入校友信息
     * @param input
     * @return
     */
     UploadResultModel importExcel(InputStream input, boolean special) throws ExcelException;
}
