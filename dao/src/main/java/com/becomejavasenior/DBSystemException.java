package com.becomejavasenior;

/**
 * DBSystemException
 *
 * @author  Andrey Radionov <andyomsk@gmail.com>
 * @version 0.1
 */

public class DBSystemException extends DBException {

    public DBSystemException() {
    }

    public DBSystemException(String reason) {
        super(reason);
    }

    public DBSystemException(String reason, Throwable cause) {
        super(reason, cause);
    }
}
