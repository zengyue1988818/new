package com.soft1841.sm.service;

/**
 * 前台业务逻辑接口
 * @auther
 * 2018.12.26
 */
public interface QianTaiService {
    /**
     *
     * @param number
     * @param password
     * @return
     */
    boolean qiantailogin(String number, String password);
}
