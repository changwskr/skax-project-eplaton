package com.kbstar.ksa.exception;

/**
 * New Business Exception class
 * 
 * This is a stub implementation to replace the missing KSA framework
 * BusinessException
 */
public class NewBusinessException extends Exception {

    private String errorCode;

    public NewBusinessException() {
        super();
    }

    public NewBusinessException(String message) {
        super(message);
    }

    public NewBusinessException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public NewBusinessException(Throwable cause) {
        super(cause);
    }

    public NewBusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}