package com.soft1841.sm.service.impl;

import com.soft1841.sm.dao.SellerDAO;
import com.soft1841.sm.entity.Seller;
import com.soft1841.sm.service.SellerService;
import com.soft1841.sm.utils.DAOFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 后台的业务逻辑接口的实现类
 * @auther
 * 2018.12.26
 */
public class SellerServiceImpl implements SellerService {
    private SellerDAO sellerDAO =  DAOFactory.getSellerDAOInstance();

    @Override
    public boolean login(String number, String password) {
        Seller seller = null;
        try {
            seller = sellerDAO.getSellerByNumber(number);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //根据工号查找成功
        if (seller != null) {
            //比较密码
            if (password.equals(seller.getPassword())) {
                return true;
            }
        }

        return false;
    }

    @Override
    public List<Seller> getAllSellers() {
        List<Seller> adminList = new ArrayList<>();
        try {
            adminList = sellerDAO.selectSeller();
        } catch (SQLException e) {
            System.err.println("查询所有管理员出现异常!");
        }
        return adminList;
    }
}
