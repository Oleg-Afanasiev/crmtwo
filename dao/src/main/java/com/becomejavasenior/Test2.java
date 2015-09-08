package com.becomejavasenior;

import com.becomejavasenior.deal.Deal;
import com.becomejavasenior.deal.DealDAO;
import com.becomejavasenior.deal.DealImpl;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Collection;

/**
 * Created by user on 9/3/2015.
 */
public class Test2 {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        DaoFactoryDMTS.startConnection();



//        TagDAO pd = DaoFactoryDMTS.getTagDAO();
//        Tag p = new TagImpl();
//        p.setName("edwww");
//        System.out.println(p);
//        pd.saveOrUpdate(p);
//        System.out.println(p);
//        pd.delete(p);
//        System.out.println(p);
        Deal deal = new DealImpl();
        DealDAO dao = DaoFactoryDMTS.getDealDAO();

        Deal deal1 = dao.getById(2);
        System.out.println(deal1);
        User u = deal1.getResponsibleUser();
        System.out.println(u);



        DaoFactoryDMTS.closeConnection();


    }
}
