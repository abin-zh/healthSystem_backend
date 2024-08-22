package com.cykj.exception;

/**
 * 插入异常
 * @author abin
 * @date 2024/8/12 17:06
 */
public class AddException extends CurdException{


    public AddException(String message, Integer id) {
        super(message, id);
    }

    @Override
    public String getMessage() {
        return "添加失败，编号为" + getId() + "，原因：" + super.getMessage();
    }
}
