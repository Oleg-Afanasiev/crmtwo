package com.becomejavasenior;

/**
 * Created by Dmytro Tsapko on 8/29/2015.
 */
public class DAOException extends RuntimeException {
    public DAOException(String message, Throwable e) {
        super(message, e);
    }

    public DAOException(String message) {
        super(message);
    }
}
