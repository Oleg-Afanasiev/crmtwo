package com.becomejavasenior;

/**
 * Dao Factory interface
 *
 * @author  Andrey Radionov <andyomsk@gmail.com>
 * @version 0.1
 */

public interface DaoFactory {

    ContactDao getContactDao();

    //CompanyDao getCompanyDao();
    //DealDao getDealDao();
    //TaskDao getTaskDao();

}
