package com.becomejavasenior;

/**
 * JDBC Dao Factory implementation
 *
 * @author  Andrey Radionov <andyomsk@gmail.com>
 * @version 0.1
 */

public class JdbcDaoFactory implements DaoFactory {

    public JdbcDaoFactory() {
    }

    public ContactDao getContactDao() {
        return new JdbcContactDao();
    }

    //public CompanyDao getCompanyDao();
    //public DealDao getDealDao();
    //public TaskDao getTaskDao();
}