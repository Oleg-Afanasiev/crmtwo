package com.becomejavasenior.jdbc.task;

import com.becomejavasenior.jdbc.DaoManager;
import com.becomejavasenior.jdbc.deal.Deal;

/**
 * Created by user on 9/11/2015.
 */
public class Turututu {
    public static void main(String[] args) {
//        DaoManager dm = DaoManager.getInstance();
//        Deal d = dm.getDealDAO().getById(10);
//        System.out.println(d);
//
//        dm.closeConnection();

        Thread[] threads = new Thread[50];
        for(int i = 0; i < 20; i++){
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    DaoManager dm = DaoManager.getInstance();
                    Deal d = dm.getDealDAO().getById(10);
                    System.out.println(d);

                    dm.closeConnection();
                }
            });
            threads[i] = thread;
        }
        System.out.println("GO! GO! GO!");
        for(int i = 0; i < 20; i++){
            threads[i].start();
        }

    }
}
