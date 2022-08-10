package com.example.seckill.mapper;

import com.example.seckill.pojo.Goods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.seckill.vo.GoodsVo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zwh
 * @since 2022-08-03
 */
public interface GoodsMapper extends BaseMapper<Goods> {

    /**
     *
     * 获取商品列表
     */
    List<GoodsVo> findGoodsVo();

    GoodsVo findGoodsVoById(Long goodsId);
}
