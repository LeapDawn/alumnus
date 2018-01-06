package zqh.service;

import zqh.dto.RequestList;
import zqh.dto.ResultModel;
import zqh.model.Alumnus;
import zqh.model.Clazz;
import zqh.model.ClazzAlumnus;

/**
 * 校友班级与校友关系业务接口
 */
public interface ClazzAlumnusService {

    /**
     * 加入校友班级
     * @param clazzAlumnus
     */
    void save(ClazzAlumnus clazzAlumnus);

    /**
     * 退出校友班级
     * @param clazzAlumnus
     */
    void delete(ClazzAlumnus clazzAlumnus);

    /**
     * 查询用户自身所在班级信息
     * @param alumnus
     */
    Clazz selectClazz(Integer alumnus);

    /**
     * 根据班级名称和成员状态获取成员列表
     * @param rl
     * @return
     */
    ResultModel<Alumnus> listAlumnus(RequestList<ClazzAlumnus> rl);

    /**
     * 判断校友是否为班级管理员
     * @param clazzAlumnus
     * @return
     */
    boolean isAdmin(ClazzAlumnus clazzAlumnus);

    /**
     * 判断校友是否已经加入了班级
     * @param alumnus
     * @return
     */
    ClazzAlumnus hasJoined(Integer alumnus);

    /**
     * 任命某个校友为管理员
     * @param clazzAlumnus 待任命信息
     * @param adminId 进行该项操作的管理员ID
     */
    void appointAdmin(ClazzAlumnus clazzAlumnus, Integer adminId);
}
