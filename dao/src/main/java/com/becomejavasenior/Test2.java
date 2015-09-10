package com.becomejavasenior;

import com.becomejavasenior.deal.Deal;
import com.becomejavasenior.deal.DealDAO;
import com.becomejavasenior.deal.DealImpl;
import com.becomejavasenior.task.Task;
import com.becomejavasenior.task.TaskDAO;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Collection;
import java.util.Set;

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

        ContactDAO dao = DaoFactoryDMTS.getContactDAO();

        Contact contact = dao.getById(1);
        Set<File> files = contact.getFiles();
        System.out.println(files);
        File t = files.iterator().next();
        System.out.println(t);
        files.remove(t);
        System.out.println(files);
        contact.setFiles(files);
        dao.saveOrUpdate(contact);


        DaoFactoryDMTS.closeConnection();


    }
}
