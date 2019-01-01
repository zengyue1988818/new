package com.soft1841.sm.service;

import com.soft1841.sm.entity.Seller;

import java.util.List;

/**
 * 后台业务逻辑接口
 * @auther
 * 2018.12.25
 */
public interface SellerService {
    /**
     *管理员登录
     * @param number
     * @param password
     * @return
     */
    boolean login(String number, String password);


    /**
     * 查询所有管理员
     * @return
     */
    List<Seller> getAllSellers();
}
