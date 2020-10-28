package com.Cinema_Management_System.utils.Exception;

/**
 * @Description class
 * @Auther GaoYi
 * @Date 2020/10/28 12:21 下午
 */
public class DeleteException extends Exception {
    public DeleteException() {
        super();
    }

    public DeleteException(String message) {
        super(message);
    }

    public DeleteException(String message, Throwable cause) {
        super(message, cause);
    }

    public DeleteException(Throwable cause) {
        super(cause);
    }

}
