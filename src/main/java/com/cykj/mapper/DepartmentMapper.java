package com.cykj.mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import com.cykj.model.pojo.Department;

/**
 * @author abin
 * @date 2024/8/8 10:47
 */
public interface DepartmentMapper {
    List<Department> findAllWithPage(Department department);

    List<Department> findAllByDeptIsDeleted(Integer deptIsDeleted);


    Department findOneByDeptName(@Param("deptName")String deptName);

    int addOneDept(String deptName);

    int updateByDeptId(@Param("updated")Department updated,@Param("deptId")Integer deptId);

    int deleteByDeptId(@Param("deptId")Integer deptId);


}