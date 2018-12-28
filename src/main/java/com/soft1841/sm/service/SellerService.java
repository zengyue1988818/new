package com.soft1841.sm.service;

/**
 * 后台业务逻辑接口
 * @auther
 * 2018.12.25
 */
public interface SellerService {
    /**
     *
     * @param number
     * @param password
     * @return
     */
    boolean login(String number, String password);
}
