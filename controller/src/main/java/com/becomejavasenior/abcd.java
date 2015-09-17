package com.becomejavasenior;

import com.becomejavasenior.impl.DealImpl;

import java.math.BigDecimal;
import java.util.Date;

class abcd {
    public static void main(String[] args) {
        DaoManager daoManager = DaoManager.getInstance();
        DealDAO dealDAO = daoManager.getDealDAO();
        DealStatusDAO dealStatusDAO = daoManager.getDealStatusDAO();
        UserDAO userDAO = daoManager.getUserDAO();a

        Deal deal = dealDAO.getById(7);
        DealStatus dealStatus = dealStatusDAO.getById(2);
        User user = userDAO.getById(7);

        //dealDAO.insertOrUpdate();
        BigDecimal bd = new BigDecimal(750000);
        long nowTime = System.currentTimeMillis();
        Date nowDate = new Date(nowTime);
        Deal newDeal = new DealImpl();

        newDeal.setBudget(bd);
        newDeal.setCreated(nowDate);
        newDeal.setUpdated(nowDate);
        newDeal.setName("Новая сделка");
        newDeal.setResponsibleUser(user);

        newDeal.setDealStatus(dealStatus);

        deal.setName("новая сделка");
        dealDAO.insertOrUpdate(deal);

        System.out.println(deal);
        System.out.println(newDeal);
       // System.out.println(dealStatus);
        //System.out.println(user);
    }
}