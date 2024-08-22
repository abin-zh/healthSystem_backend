package com.cykj.model.dto;

import com.cykj.model.pojo.Department;
import com.cykj.model.pojo.MedicalItem;
import com.cykj.model.pojo.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * 项目编辑时需要的信息
 * @author abin
 * @date 2024/8/12 15:51
 */

@Data
@AllArgsConstructor
public class EditNeedDTO<T> {

    /**
     * 所有的部门
     */
    private List<Department> depts;

    /**
     * 所有的角色
     */
    private List<Role> roles;

    /**
     * 未使用
     */
    private List<T> fromItems;

    /**
     * 已使用
     */
    private List<T> toItems;

    private List<T> toAllItems;

}
