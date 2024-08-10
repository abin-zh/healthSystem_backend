package com.cykj.mapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.cykj.model.pojo.Staff;

/**
 * 工作人员(管理员)映射类
 * @author abin
 * @date 2024/8/8 10:47
 */
public interface StaffMapper {

    Staff findOneByStaffAccount(@Param("staffAccount")String staffAccount);
    List<Staff> findAllWithPage();

    Staff findOneByStaffId(@Param("staffId")Integer staffId);

}