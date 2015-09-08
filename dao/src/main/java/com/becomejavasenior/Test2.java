package com.becomejavasenior;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

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
        Company company = new CompanyImpl();
        CompanyDAO cd = DaoFactoryDMTS.getCompanyDAO();

        Company c = cd.getById(2);
        System.out.println(c);
        User u = c.getResponsibleUser();
        System.out.println(u);



        DaoFactoryDMTS.closeConnection();


    }
}
