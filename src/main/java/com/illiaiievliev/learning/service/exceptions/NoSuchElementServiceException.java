package com.illiaiievliev.learning.service.exceptions;

public class NoSuchElementServiceException extends ServiceException{
    public NoSuchElementServiceException() {
        super();
    }

    public NoSuchElementServiceException(String message) {
        super(message);
    }

    public NoSuchElementServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchElementServiceException(Throwable cause) {
        super(cause);
    }

    protected NoSuchElementServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
