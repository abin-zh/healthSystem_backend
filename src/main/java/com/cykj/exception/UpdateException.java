package com.cykj.exception;

/**
 * 更新异常
 * @author abin
 * @date 2024/8/12 22:51
 */
public class UpdateException extends CurdException{


    public UpdateException(String message, Integer id) {
        super(message, id);
    }

    @Override
    public String getMessage() {
        return "修改失败，编号为" + getId() + "，原因：" + super.getMessage();
    }
}
