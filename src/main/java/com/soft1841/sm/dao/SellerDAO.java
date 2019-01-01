package com.soft1841.sm.dao;

import com.soft1841.sm.entity.Seller;

import java.sql.SQLException;
import java.util.List;

/**
 * 后台DAO 接口
 * @auther
 * 2018.12.26
 */
public interface SellerDAO {
    /**
     * 根据工号查询收银员
     * @param number
     * @return
     * @throws SQLException
     */
    Seller getSellerByNumber(String number)throws SQLException;


    /**
     * 查询所有收银员
     * @return
     * @throws SQLException
     */
    List<Seller> selectSeller() throws SQLException;
}
