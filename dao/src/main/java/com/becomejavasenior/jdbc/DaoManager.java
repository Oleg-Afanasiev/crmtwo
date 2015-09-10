package com.becomejavasenior.jdbc;

import com.becomejavasenior.jdbc.deal.DealDAO;
import com.becomejavasenior.jdbc.deal.DealDAOImpl;
import com.becomejavasenior.jdbc.deal.DealStatusDAO;
import com.becomejavasenior.jdbc.deal.DealStatusDAOImpl;
import com.becomejavasenior.jdbc.task.*;
import org.apache.commons.dbcp2.BasicDataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Dmytro Tsapko on 8/29/2015.
 */
public class DaoManager {

    private static BasicDataSource connectionPool;
    private static ThreadLocal<DaoManager> daoManagerThreadLocal;
    private Connection connection;

    {
        connectionPool = new BasicDataSource();
        connectionPool.setUsername("postgres");
        connectionPool.setPassword("postgres");
        connectionPool.setDriverClassName("org.postgresql.Driver");
        connectionPool.setUrl("jdbc:postgresql://192.168.1.200:5432/crmtwo");
        connectionPool.setInitialSize(10);

    }

    public static DaoManager getInstance(){ //todo what about synchronization of this method

        if (daoManagerThreadLocal == null) {
            daoManagerThreadLocal = new ThreadLocal<>();
            daoManagerThreadLocal.set(new DaoManager());
        }

        return daoManagerThreadLocal.get();
    }


//    public static void startConnection(){
//        try {
//            Class.forName("org.postgresql.Driver");
//            connection =
//                    DriverManager.getConnection("jdbc:postgresql://192.168.1.200:5432/crmtwo","postgres", "postgres");
//        } catch (ClassNotFoundException e) {
//            throw new DAOException("Can't load driver", e);
//        } catch (SQLException e) {
//            throw new DAOException("Can't get Connection", e);
//        }
//    }
    private DaoManager(){
        try {
            connection = connectionPool.getConnection();
        } catch (SQLException e) {
            throw new DAOException("Can't get connection from pool.", e);
        }
    }



    public  DealDAO getDealDAO(){
        return new DealDAOImpl(connection);
    }

    public  DealStatusDAO getDealStatusDAO(){
        return new DealStatusDAOImpl(connection);
    }

    public  TaskDAO getTaskDAO(){
        return new TaskDAOImpl(connection);
    }

    public  TaskPeriodDAO getTaskPeriodDAO(){
        return new TaskPeriodDAOImpl(connection);
    }

    public  TaskTypeDAO getTaskTypeDAO(){
        return new TaskTypeDAOImpl(connection);
    }

    public  CommentDAO getCommentDAO(){
        return new CommentDAOImpl(connection);
    }

    public  CompanyDAO getCompanyDAO(){
        return new CompanyDAOImpl(connection);
    }

    public  ContactDAO getContactDAO(){
        return new ContactDAOImpl(connection);
    }

    public  FileDAO getFileDAO(){
        return new FileDAOImpl(connection);
    }

    public  PhoneDAO getPhoneDAO(){
        return new PhoneDAOImpl(connection);
    }

    public  TagDAO getTagDAO(){
        return new TagDAOImpl(connection);
    }

    public  UserDAO getUserDAO(){
        return new UserDAOImpl(connection);
    }

}
