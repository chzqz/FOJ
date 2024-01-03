package icu.chzqz.foj.exception;

/**
 * 请求失败异常
 */
public class RequestFailException extends Exception{
    public RequestFailException() {
        super();
    }

    public RequestFailException(String message) {
        super(message);
    }

    public RequestFailException(String message, Throwable cause) {
        super(message, cause);
    }

    public RequestFailException(Throwable cause) {
        super(cause);
    }

    protected RequestFailException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
