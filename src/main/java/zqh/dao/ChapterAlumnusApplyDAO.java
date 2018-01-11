package zqh.dao;

import org.apache.ibatis.annotations.Param;
import zqh.model.Alumnus;
import zqh.model.ChapterAlumnusApply;

import java.util.List;

public interface ChapterAlumnusApplyDAO {
    int insert(ChapterAlumnusApply record);

    ChapterAlumnusApply selectByAlumnus(Integer alumnus);

    ChapterAlumnusApply selectById(Integer id);

    List<Alumnus> selectList(@Param("apply") ChapterAlumnusApply apply, @Param("skip") int skip, @Param("rows")int rows);

    Integer count(@Param("apply") ChapterAlumnusApply apply);

    int updateStatus(ChapterAlumnusApply record);
}