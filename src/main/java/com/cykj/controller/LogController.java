package com.cykj.controller;
import com.cykj.model.dto.ResponseDTO;
import com.cykj.model.pojo.Log;
import com.cykj.model.vo.PageVO;
import com.cykj.service.LogService;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

/**
* 日志控制层
*
* @author abin
*/
@RestController
@RequestMapping("/log")
public class LogController {

    private LogService logService;

    @Autowired
    public LogController(LogService logService) {
        this.logService = logService;
    }

    @PostMapping("search")
    public ResponseDTO search(PageVO<Log> vo){
        return logService.getLogs(vo);
    }
}
