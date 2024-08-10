package com.cykj.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * 响应数据传输对象(向浏览器返回固定格式的响应数据)
 * @author abin
 * @date 2024/6/22 21:07
 */

@Data
@ToString
@AllArgsConstructor
public class ResponseDTO {

    /**
     * 自定义状态码
     */
    private Integer code;

    /**
     * 返回消息
     */
    private String msg;

    /**
     * 总数(分页查询)
     */
    private Integer count;

    /**
     * 返回数据
     */
    private Object data;


    public static ResponseDTO success(String msg, int count, Object data){
        return new ResponseDTO(0, msg, count, data);
    }

    public static ResponseDTO success(int count, Object data){
        return success("操作成功", count, data);
    }

    public static ResponseDTO success(String msg, Object data){
        return new ResponseDTO(0, msg, null, data);
    }

    public static ResponseDTO success(String msg){
        return new ResponseDTO(0, msg, null, null);
    }


    public static ResponseDTO fail(int code, String msg){
        return new ResponseDTO(code, msg, 0, null);
    }


    public static ResponseDTO fail(String msg){
        return new ResponseDTO(-1, msg, 0, null);
    }
}
