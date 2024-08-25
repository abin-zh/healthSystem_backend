package com.cykj.mapper;
import com.cykj.model.pojo.Log;
import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * 日志操作
 * @author abin
 * @date 2024/8/8 10:47
 */
public interface LogMapper {

    int addOneLog(@Param("logStaffId") int logStaffId, @Param("logOperation") String logOperation);

    List<Log> findAllWithPage(Log log);

}