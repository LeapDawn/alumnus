package zqh.service;

import zqh.dto.RequestList;
import zqh.dto.ResultModel;
import zqh.model.Alumnus;
import zqh.model.Chapter;
import zqh.model.ChapterAlumnus;

import java.util.List;

/**
 * 校友分会与校友关系业务接口
 */
public interface ChapterAlumnusService {

    /**
     * 加入校友分会
     * @param chapterAlumnus
     */
    void save(ChapterAlumnus chapterAlumnus);

    /**
     * 退出校友分会
     * @param chapterAlumnus
     */
    void delete(ChapterAlumnus chapterAlumnus);

    /**
     * 查询用户自身所在分会信息
     * @param alumnus
     */
    Chapter selectChapter(Integer alumnus);

    /**
     * 根据分会名称和成员状态获取成员列表
     * @param rl
     * @return
     */
    ResultModel<Alumnus> listAlumnus(RequestList<ChapterAlumnus> rl);

    /**
     * 判断校友是否为分会管理员
     * @param chapterAlumnus
     * @return
     */
    boolean isAdmin(ChapterAlumnus chapterAlumnus);

    /**
     * 判断校友是否已经加入了分会
     * @param alumnus
     * @return
     */
    ChapterAlumnus hasJoined(Integer alumnus);

    /**
     * 任命某个校友为管理员
     * @param chapterAlumnus 待任命信息
     * @param adminId 进行该项操作的管理员ID
     */
    void appointAdmin(ChapterAlumnus chapterAlumnus, Integer adminId);
}
