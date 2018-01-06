package zqh.dao;

import org.apache.ibatis.annotations.Param;
import zqh.model.Clazz;

import java.util.List;

public interface ClazzDAO {
    int delete(String name);

    int insert(Clazz record);

    Clazz selectOne(String name);

    Integer count(@Param("clazz") Clazz clazz);

    List<Clazz> selectList(@Param("clazz") Clazz clazz, @Param("skip") int skip, @Param("rows") int rows);

    int updateContent(Clazz record);
}