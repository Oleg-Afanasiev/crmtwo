package com.becomejavasenior;

import com.becomejavasenior.impl.jdbc.*;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Dmytro Tsapko on 8/29/2015.
 */
public class DaoManager {

    private static BasicDataSource connectionPool;
    private static ThreadLocal<DaoManager> daoManagerThreadLocal = new ThreadLocal<>();;
    private Connection connection;

    {
        DaoProperties daoProperties = new DaoProperties();
        connectionPool = new BasicDataSource();
        connectionPool.setUsername(daoProperties.getProperty("user"));
        connectionPool.setPassword(daoProperties.getProperty("password"));
        connectionPool.setDriverClassName(daoProperties.getProperty("driver"));
        connectionPool.setUrl(daoProperties.getProperty("url"));
        connectionPool.setInitialSize(50);

    }

    public static synchronized DaoManager getInstance() {
        if(daoManagerThreadLocal.get() == null){
            daoManagerThreadLocal.set(new DaoManager());
        }
        return daoManagerThreadLocal.get();
    }


    private DaoManager() {
        try {
            connection = connectionPool.getConnection();
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new DAOException("Can't get connection from pool.", e);
        }
    }


    public DealDAO getDealDAO() {
        return new DealDAOImpl(connection);
    }

    public DealStatusDAO getDealStatusDAO() {
        return new DealStatusDAOImpl(connection);
    }

    public TaskDAO getTaskDAO() {
        return new TaskDAOImpl(connection);
    }

    public TaskPeriodDAO getTaskPeriodDAO() {
        return new TaskPeriodDAOImpl(connection);
    }

    public TaskTypeDAO getTaskTypeDAO() {
        return new TaskTypeDAOImpl(connection);
    }

    public CommentDAO getCommentDAO() {
        return new CommentDAOImpl(connection);
    }

    public CompanyDAO getCompanyDAO() {
        return new CompanyDAOImpl(connection);
    }

    public ContactDAO getContactDAO() {
        return new ContactDAOImpl(connection);
    }

    public FileDAO getFileDAO() {
        return new FileDAOImpl(connection);
    }

    public PhoneDAO getPhoneDAO() {
        return new PhoneDAOImpl(connection);
    }

    public TagDAO getTagDAO() {
        return new TagDAOImpl(connection);
    }

    public UserDAO getUserDAO() {
        return new UserDAOImpl(connection);
    }

    public RoleDAO getRoleDAO() {
        return new RoleDAOImpl(connection);
    }

    public void closeConnection() {

        try{
            this.connection.commit();
            this.connection.close();
        }catch (SQLException e){
            throw new DAOException("Can't close connection", e);
        }
    }
}
