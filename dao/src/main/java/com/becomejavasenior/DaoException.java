package com.becomejavasenior;

/**
 * Dao Exception
 *
 * @author  Andrey Radionov <andyomsk@gmail.com>
 * @version 0.1
 */

public class DaoException extends RuntimeException{
    private static long serialVersionUID = 1L;

    public DaoException() {
        super();
    }

    public DaoException(String message) {
        super(message);
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoException(Throwable cause) {
        super( cause );
    }
}