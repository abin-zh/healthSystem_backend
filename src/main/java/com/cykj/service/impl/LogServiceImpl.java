package com.cykj.service.impl;

import com.cykj.model.dto.ResponseDTO;
import com.cykj.model.pojo.Log;
import com.cykj.model.vo.PageVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import com.cykj.mapper.LogMapper;
import com.cykj.service.LogService;

import java.util.List;

/**
 * @author abin
 * @date 2024/8/8 10:47
*/
@Service
public class LogServiceImpl implements LogService{

    private LogMapper logMapper;

    @Autowired
    public LogServiceImpl(LogMapper logMapper) {
        this.logMapper = logMapper;
    }

    @Override
    public ResponseDTO getLogs(PageVO<Log> vo) {
        Page<Object> page = PageHelper.startPage(vo.getPageNo(), vo.getPageSize());
        List<Log> logs = logMapper.findAllWithPage(vo.getData());
        PageInfo<Log> logPageInfo = new PageInfo<>(logs);
        return ResponseDTO.success((int) logPageInfo.getTotal(), logs);
    }
}
