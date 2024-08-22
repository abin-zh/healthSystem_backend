package com.cykj.service;

import com.cykj.model.dto.ResponseDTO;
import com.cykj.model.pojo.Log;
import com.cykj.model.vo.PageVO;

/**
 * @author abin
 * @date 2024/8/8 10:47
 */
public interface LogService {

    /**
     * 获取日志列表(分页、模糊)
     * @param vo 分页及日志查询信息
     * @return
     */
    ResponseDTO getLogs(PageVO<Log> vo);
}
