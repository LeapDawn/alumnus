package zqh.dao;

import org.apache.ibatis.annotations.Param;
import zqh.model.Alumnus;
import zqh.model.ClazzAlumnus;

import java.util.List;

public interface ClazzAlumnusDAO {

    int insert(ClazzAlumnus record);

    int updateAdmin(ClazzAlumnus record);

    int deleteByClazzAndAlumnus(ClazzAlumnus record);

    ClazzAlumnus selectByClazzAndAlumnus(ClazzAlumnus record);

    ClazzAlumnus selectByAlumnus(ClazzAlumnus record);

    List<Alumnus> selectList(@Param("clazzAlumnus") ClazzAlumnus clazzAlumnus, @Param("skip") int skip, @Param("rows") int rows);

    Integer count(@Param("clazzAlumnus") ClazzAlumnus clazzAlumnus);
}