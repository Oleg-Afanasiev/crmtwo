package com.becomejavasenior;

import java.sql.SQLException;

/**
 * DBException
 *
 * @author  Andrey Radionov <andyomsk@gmail.com>
 * @version 0.1
 */

public class DBException extends Exception {

    public DBException() {
    }

    public DBException(String reason) {
        super(reason);
    }

    public DBException(String reason, Throwable cause) {
        super(reason, cause);
    }
}