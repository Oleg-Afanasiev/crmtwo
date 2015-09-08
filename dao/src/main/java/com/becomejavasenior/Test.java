package com.becomejavasenior;

import java.util.Date;

/**
 * Created by user on 9/3/2015.
 */
public class Test {
    public static void main(String[] args) {
        DaoFactoryDMTS.startConnection();
        UserDAO ud = DaoFactoryDMTS.getUserDAO();

        User u = ud.getById(23);

        UserImpl d = new UserImpl();
        d.setIsDeleted(false);
        d.setRole_id(7);
        d.setUserName("dddwd");
        d.setLastName("dddwd");
        d.setLastName("dddwd");
        d.setFirstName("dddwd");
        d.setEmail("dddwd");
        d.setCreated(new Date());
        d.setUpdated(new Date());
        System.out.println(d);
        ud.saveOrUpdate(d);
        System.out.println("+++++++++++++++++++++++++++++++++++++++++");
        System.out.println(d);
        d.setEmail("OLOLOLO@ddddd");
        ud.saveOrUpdate(d);
        System.out.println(ud.getById(d.getId()));



        DaoFactoryDMTS.closeConnection();
    }
}
