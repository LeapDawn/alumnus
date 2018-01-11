package zqh.dao;

import org.apache.ibatis.annotations.Param;
import zqh.model.ClazzApply;

import java.util.List;

public interface ClazzApplyDAO {

    ClazzApply selectByName(String name);

    ClazzApply selectByAdder(Integer alumnus);

    ClazzApply selectById(Integer id);

    List<ClazzApply> selectListByStatus(@Param("status") Integer status, @Param("skip") int skip, @Param("rows") int rows);

    Integer countByStatus(@Param("status") Integer status);

    int insert(ClazzApply record);

    int updateStatus(ClazzApply record);
}