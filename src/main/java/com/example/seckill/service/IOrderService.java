package com.example.seckill.service;

import com.example.seckill.pojo.Order;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.seckill.pojo.User;
import com.example.seckill.vo.GoodsVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zwh
 * @since 2022-08-03
 */
public interface IOrderService extends IService<Order> {

    Order seckill(User user, GoodsVo goodsVo);
}
