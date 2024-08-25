package com.cykj.model.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 工作人员(管理员)信息
 * @author abin
 * @date 2024/8/8 10:47
 */
@Data
@NoArgsConstructor
public class Staff {
    /**
     * 工作人员ID
     */
    private Integer staffId;

    /**
     * 账号
     */
    private String staffAccount;

    /**
     * 密码
     */
    private String staffPassword;

    /**
     * 确认密码
     */
    private String staffCPassword;

    /**
     * 姓名
     */
    private String staffName;

    /**
     * 所属角色ID
     */
    private Integer staffRoleId;

    /**
     * 所属科室ID
     */
    private Integer staffDeptId;

    /**
     * 使用状态 (1: 活跃, 0: 非活跃)
     */
    private Integer staffIsStatus;

    /**
     * 是否删除 (1: 已删除, 0: 未删除)
     */
    private Integer staffIsDeleted;

    /**
     * 工作人员邮箱
     */
    private String staffEmail;

    /**
     * 工作人员头像
     */
    private String staffAvatar;

    /**
     * 关联的科室名称
     */
    private String deptName;

    /**
     * 关联的角色名称
     */
    private String roleName;
}