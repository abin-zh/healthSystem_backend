package com.cykj.model.pojo;

import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
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
}