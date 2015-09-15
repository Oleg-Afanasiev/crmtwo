package com.becomejavasenior;

import com.becomejavasenior.impl.DealImpl;

class abcd {
    public static void main(String[] args) {
        DaoManager daoManager = DaoManager.getInstance();
        DealDAO dealDAO = daoManager.getDealDAO();
        Deal deal = dealDAO.getById(1);
        //dealDAO.insertOrUpdate();
        Deal newDeal = new DealImpl();
        System.out.println(newDeal);
    }
}