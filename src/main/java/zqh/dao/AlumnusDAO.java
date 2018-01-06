package zqh.dao;

import org.apache.ibatis.annotations.Param;
import zqh.model.Alumnus;

import java.util.List;

public interface AlumnusDAO {
    int delete(int ids);

    int insert(Alumnus record);

    Alumnus selectById(Integer id);

    Alumnus selectOpenById(Integer id);

    List<Alumnus> selectValidList(@Param("alumnus") Alumnus record,
                             @Param("skip") int skip, @Param("rows") int rows);

    int countValid(@Param("alumnus") Alumnus record);

    List<Alumnus> selectInvalidList(@Param("skip") int skip, @Param("rows") int rows);

    int countInvalid();

    int update(Alumnus record);

    int updateInvalid(@Param("id") Integer id, @Param("invalid") Integer invalid);

    Integer selectOneByNameAndCard(Alumnus alumnus);
}