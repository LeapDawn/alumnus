package zqh.dao;

import org.apache.ibatis.annotations.Param;
import zqh.model.Alumnus;
import zqh.model.Chapter;
import zqh.model.ChapterAlumnus;

import java.util.List;

public interface ChapterAlumnusDAO {

    int insert(ChapterAlumnus record);

    int updateAdmin(ChapterAlumnus record);

    int deleteByChapterAndAlumnus(ChapterAlumnus record);

    ChapterAlumnus selectByChapterAndAlumnus(ChapterAlumnus record);

    ChapterAlumnus selectByAlumnus(ChapterAlumnus record);

    List<Alumnus> selectList(@Param("chapterAlumnus") ChapterAlumnus chapterAlumnus, @Param("skip")int skip, @Param("rows")int rows);

    Integer count(@Param("chapterAlumnus") ChapterAlumnus chapterAlumnus);
}