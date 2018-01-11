package zqh.dao;

import org.apache.ibatis.annotations.Param;
import zqh.model.ChapterApply;

import java.util.List;

public interface ChapterApplyDAO {

    ChapterApply selectByAdder(Integer alumnus);

    ChapterApply selectByName(String name);

    ChapterApply selectById(Integer id);

    List<ChapterApply> selectListByStatus(@Param("status") Integer status, @Param("skip")int skip, @Param("rows")int rows);

    Integer countByStatus(@Param("status") Integer status);

    int insert(ChapterApply record);

    int updateStatus(ChapterApply record);
}