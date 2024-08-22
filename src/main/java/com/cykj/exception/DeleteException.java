package com.cykj.exception;

/**
 * 删除异常
 * @author abin
 * @date 2024/8/11 17:54
 */
public class DeleteException extends CurdException{


    public DeleteException(String message, Integer id) {
        super(message, id);
    }

    @Override
    public String getMessage() {
        return "删除失败，编号为" + getId() + "，原因：" + super.getMessage();
    }
}
