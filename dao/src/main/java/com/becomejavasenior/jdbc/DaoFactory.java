package com.becomejavasenior.jdbc;

import com.becomejavasenior.jdbc.deal.DealDAO;
import com.becomejavasenior.jdbc.deal.DealDAOImpl;
import com.becomejavasenior.jdbc.deal.DealStatusDAO;
import com.becomejavasenior.jdbc.deal.DealStatusDAOImpl;
import com.becomejavasenior.jdbc.task.*;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Dmytro Tsapko on 8/29/2015.
 */
public class DaoFactory {
    private static DataSource dataSource;

    private static Connection connection;

    public static void startConnection(){
        try {
            Class.forName("org.postgresql.Driver");
            connection =
                    DriverManager.getConnection("jdbc:postgresql://192.168.1.200:5432/crmtwo","postgres", "postgres");
        } catch (ClassNotFoundException e) {
            throw new DAOException("Can't load driver", e);
        } catch (SQLException e) {
            throw new DAOException("Can't get Connection", e);
        }
    }
    public DaoFactory(){

    }

    public DaoFactory(DataSource dataSource) throws SQLException {
        this.dataSource = dataSource;
        try {
            this.connection = dataSource.getConnection();
        } catch (SQLException e) {
            throw new DAOException("Can't get Connection", e);
        }

    }

    public static DealDAO getDealDAO(){
        return new DealDAOImpl(connection);
    }

    public static DealStatusDAO getDealStatusDAO(){
        return new DealStatusDAOImpl(connection);
    }

    public static TaskDAO getTaskDAO(){
        return new TaskDAOImpl(connection);
    }

    public static TaskPeriodDAO getTaskPeriodDAO(){
        return new TaskPeriodDAOImpl(connection);
    }

    public static TaskTypeDAO getTaskTypeDAO(){
        return new TaskTypeDAOImpl(connection);
    }

    public static CommentDAO getCommentDAO(){
        return new CommentDAOImpl(connection);
    }

    public static CompanyDAO getCompanyDAO(){
        return new CompanyDAOImpl(connection);
    }

    public static ContactDAO getContactDAO(){
        return new ContactDAOImpl(connection);
    }

    public static FileDAO getFileDAO(){
        return new FileDAOImpl(connection);
    }

    public static PhoneDAO getPhoneDAO(){
        return new PhoneDAOImpl(connection);
    }

    public static TagDAO getTagDAO(){
        return new TagDAOImpl(connection);
    }

    public static UserDAO getUserDAO(){
        return new UserDAOImpl(connection);
    }

    public static DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public static Connection getConnection() {
        return connection;
    }

    public static void closeConnection(){
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
