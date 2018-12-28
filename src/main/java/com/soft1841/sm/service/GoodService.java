package com.soft1841.sm.service;

import com.soft1841.sm.entity.Good;

import java.util.List;

/**
 * 商品的业务逻辑接口
 * @auther
 * 2018年.12.26
 */
public interface GoodService {
    /**
     * 查询所有商品
     * @return
     */
    List<Good> getAllGoods();
}
