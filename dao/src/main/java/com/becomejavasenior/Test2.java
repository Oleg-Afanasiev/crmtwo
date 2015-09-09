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
        TaskDAO dao = DaoFactoryDMTS.getTaskDAO();

        Task task = dao.getById(1);
        System.out.println(task);
        User u = task.getResponsibleUser();
        System.out.println(u);



        DaoFactoryDMTS.closeConnection();


    }
}
