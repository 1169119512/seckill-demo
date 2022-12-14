package com.example.seckill.controller;


import com.example.seckill.pojo.User;
import com.example.seckill.vo.RespBean;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

/**
 * 1000线程循环10次测试三次
 * Windows 优化前QPS:1148
 * Linux   优化前QPS:46.4
 *
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zwh
 * @since 2022-07-30
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/info")
    public RespBean info(User user){
        return RespBean.success(user);
    }


}
