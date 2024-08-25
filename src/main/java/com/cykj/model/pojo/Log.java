package com.cykj.model.pojo;

import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 日志信息
 * @author abin
 * @date 2024/8/8 10:47
 */
@Data
@NoArgsConstructor
public class Log {
    /**
     * 日志ID
     */
    private Integer logId;

    /**
     * 管理员ID
     */
    private Integer logStaffId;

    /**
     * 时间
     */
    private Date logTime;

    /**
     * 操作
     */
    private String logOperation;

    /**
     * 关联的工作人员账号
     */
    private String staffAccount;
}