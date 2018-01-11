package zqh.dao;

import org.apache.ibatis.annotations.Param;
import zqh.model.Alumnus;
import zqh.model.ClazzAlumnusApply;

import java.util.List;

public interface ClazzAlumnusApplyDAO {
    int insert(ClazzAlumnusApply record);

    ClazzAlumnusApply selectByAlumnus(Integer alumnus);

    ClazzAlumnusApply selectById(Integer id);

    List<Alumnus> selectList(@Param("apply") ClazzAlumnusApply apply, @Param("skip") int skip, @Param("rows") int rows);

    Integer count(@Param("apply") ClazzAlumnusApply apply);

    int updateStatus(ClazzAlumnusApply record);
}