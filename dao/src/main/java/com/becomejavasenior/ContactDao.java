package com.becomejavasenior;

import java.sql.SQLException;
import java.util.List;

/**
 * Interface for Contact DAO
 *
 * @author  Andrey Radionov <andyomsk@gmail.com>
 * @version 0.2
 */

public interface ContactDao {

    Contact getById(int id) throws DBException;

    List<Contact> getAll() throws DBException;

    boolean delete(int id) throws DBException;

    Contact create(Contact contact) throws DBException;

    void update(Contact contact) throws DBException;

}
