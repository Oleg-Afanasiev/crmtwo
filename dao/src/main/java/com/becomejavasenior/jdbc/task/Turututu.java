package com.becomejavasenior.jdbc.task;

import com.becomejavasenior.jdbc.DaoManager;
import com.becomejavasenior.jdbc.deal.Deal;

/**
 * Created by user on 9/11/2015.
 */
public class Turututu {
    public static void main(String[] args) {
        DaoManager dm = DaoManager.getInstance();
        Deal d = dm.getDealDAO().getById(10);
        System.out.println(d);
    }
}
