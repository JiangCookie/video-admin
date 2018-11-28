package com.jyh.videoadmin.common.enums;

/**
 * @author JYH
 * 2018/11/24 11:04
 */
public enum ResponseCode {
    SUCCESS(0,"成功"),
    ERROR(1,"失败"),
    ILLEGAL_ARGUMENT(2,"ILLEGAL_ARGUMENT"),
    NEED_LOGIN(3,"NEED_LOGIN");

    private int code;
    private String name;
    private ResponseCode(int code,String name){
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
