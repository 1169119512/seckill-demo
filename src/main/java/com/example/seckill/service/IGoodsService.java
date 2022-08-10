package com.example.seckill.service;

import com.example.seckill.pojo.Goods;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.seckill.vo.GoodsVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zwh
 * @since 2022-08-03
 */
public interface IGoodsService extends IService<Goods> {
    public List<GoodsVo> findGoodsVo();

    GoodsVo findGoodsVoByGoodsId(Long goodsId);
}
