package com.soft1841.sm.dao.impl;

import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import com.soft1841.sm.dao.SellerDAO;
import com.soft1841.sm.entity.Seller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 后台登陆的DAO 实现类
 * @auther
 * 2018.12.27
 */
public class SellerDAOImpl implements SellerDAO {

    @Override
    public Seller getSellerByNumber(String number) throws SQLException {
        Entity entity =  Db.use().queryOne("SELECT * FROM t_cashier WHERE number = ? ",number );
        return convertSeller(entity);
    }

    @Override
    public List<Seller> selectSeller() throws SQLException {
        List<Entity> entityList = Db.use().query("SELECT * FROM t_cashier WHERE account = ?");
        List<Seller> sellerList = new ArrayList<>();
        for (Entity entity: entityList) {
            sellerList.add(convertSeller(entity));

        }
        return sellerList;
    }

    /**
     * 封装一个将Entity转换为Seller的方法
     * @param entity
     * @return
     */
    private Seller convertSeller(Entity entity){
        Seller seller = new Seller(entity.getLong("id"),entity.getStr("number"),
                entity.getStr("password"),entity.getStr("name"));
        return  seller;
    }
}
