package com.example.seckill.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * 公共返回对象枚举
 *  Getter -- get方法
 *  ToString -- toString方法
 *  AllArgsConstructor -- 全参构造
 */
@Getter
@ToString
@AllArgsConstructor
public enum RespBeanEnum {
    //通用
    SUCCESS(200,"SUCCESS"),
    ERROR(500,"服务端异常"),
    //登录模块
    LOGIN_ERROR(500210,"用户名或密码不正确"),
    MOBILE_ERROR(5000211,"手机号码格式不正确"),
    BIND_ERROR(500212,"参数校验异常"),
    //秒杀模块5005xx
    EMPTY_STOCK(500500,"库存不足"),
    REPEAT_ERROR(500501,"每人限购一件")
    ;
    private final Integer code;
    private final String message;
}
