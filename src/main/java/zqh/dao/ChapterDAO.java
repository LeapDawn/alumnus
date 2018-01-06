package zqh.dao;

import org.apache.ibatis.annotations.Param;
import zqh.model.Chapter;

import java.util.List;

public interface ChapterDAO {
    int delete(String name);

    int insert(Chapter record);

    Chapter selectOne(String name);

    Integer count(@Param("chapter") Chapter chapter);

    List<Chapter> selectList(@Param("chapter") Chapter chapter, @Param("skip") int skip, @Param("rows") int rows);

    int updateContent(Chapter record);
}