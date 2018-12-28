package com.soft1841.sm.dao;

import com.soft1841.sm.entity.QianTai;
import java.sql.SQLException;

/**
 * 前台DAO 接口
 * @auther
 * 2018.12.25
 */
public interface QianTaiDAO {
    /**
     *
     * @param number
     * @return
     * @throws SQLException
     */
     QianTai getQianTaiByNumber(String number)throws SQLException;

}
