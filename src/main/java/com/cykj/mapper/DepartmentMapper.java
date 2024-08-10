package com.cykj.mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import com.cykj.model.pojo.Department;

/**
 * @author abin
 * @date 2024/8/8 10:47
 */
public interface DepartmentMapper {
    List<Department> findAllByDeptNameWithPage(@Param("deptName")String deptName);

}