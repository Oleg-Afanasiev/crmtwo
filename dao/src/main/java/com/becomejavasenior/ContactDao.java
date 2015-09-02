package com.becomejavasenior;

import java.sql.SQLException;
import java.util.List;

/**
 * Interface for Contact DAO
 *
 * @author  Andrey Radionov <andyomsk@gmail.com>
 * @version 0.1
 */

public interface ContactDao {

    Contact getById(int id) throws SQLException;

    List<Contact> getAll() throws SQLException;

    boolean delete(int id) throws SQLException;

    Contact create(Contact contact) throws SQLException;

    void update(Contact contact) throws SQLException;

}
