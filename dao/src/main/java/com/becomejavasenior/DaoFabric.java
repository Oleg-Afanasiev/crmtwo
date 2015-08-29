package com.becomejavasenior;

import com.becomejavasenior.deal.DealDAO;
import com.becomejavasenior.deal.DealDAOImpl;
import com.becomejavasenior.deal.DealStatusDAO;
import com.becomejavasenior.deal.DealStatusDAOImpl;
import com.becomejavasenior.task.*;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Dmytro Tsapko on 8/29/2015.
 */
public class DaoFabric {
    DataSource dataSource;
    Connection connection;

    public DaoFabric(){
        try {
            Class.forName("org.postgresql.Driver");
            this.connection =
                    DriverManager.getConnection("jdbc:postgresql://192.168.1.200:5432/crmtwo","postgres", "postgres");
        } catch (ClassNotFoundException e) {
            throw new DAOException("Can't load driver", e);
        } catch (SQLException e) {
            throw new DAOException("Can't get Connection", e);
        }
    }

    public DaoFabric(DataSource dataSource) throws SQLException {
        this.dataSource = dataSource;
        try {
            this.connection = dataSource.getConnection();
        } catch (SQLException e) {
            throw new DAOException("Can't get Connection", e);
        }

    }

    public static DealDAO getDealDAO(){
        return new DealDAOImpl();
    }

    public static DealStatusDAO getDealStatusDAO(){
        return new DealStatusDAOImpl();
    }

    public static TaskDAO getTaskDAO(){
        return new TaskDAOImpl();
    }

    public static TaskPeriodDAO getTaskPeriodDAO(){
        return new TaskPeriodDAOImpl();
    }

    public static TaskTypeDAO getTaskTypeDAO(){
        return new TaskTypeDAOImpl();
    }

    public static CommentDAO getCommentDAO(){
        return new CommentDAOImpl();
    }

    public static CompanyDAO getCompanyDAO(){
        return new CompanyDAOImpl();
    }

    public static ContactDAO getContactDAO(){
        return new ContactDAOImpl();
    }

    public static FileDAO getFileDAO(){
        return new FileDAOImpl();
    }

    public static PhoneDAO getPhoneDAO(){
        return new PhoneDAOImpl();
    }

    public static TagDAO getTagDAO(){
        return new TagDAOImpl();
    }

    public static UserDAO getUserDAO(){
        return new UserDAOImpl();
    }

}
