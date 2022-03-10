package com.illiaiievliev.learning.dao.exceptions;

public class NoSuchElementDAOException extends DAOException {
    public NoSuchElementDAOException() {
    }

    public NoSuchElementDAOException(String message) {
        super(message);
    }

    public NoSuchElementDAOException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchElementDAOException(Throwable cause) {
        super(cause);
    }

    public NoSuchElementDAOException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}