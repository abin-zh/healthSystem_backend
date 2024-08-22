package com.cykj.exception;

import lombok.Getter;

/**
 * 增删改查异常
 * @author abin
 * @date 2024/8/12 22:52
 */

@Getter
public class CurdException extends RuntimeException{


    /**
     * 错误原因
     */
    private String message;

    /**
     * 删除的内容ID
     */
    private Integer id;


    public CurdException(String message, Integer id) {
        super(message);
        this.message = message;
        this.id = id;
    }

}
