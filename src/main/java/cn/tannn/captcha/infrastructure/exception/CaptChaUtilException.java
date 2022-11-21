package cn.tannn.captcha.infrastructure.exception;

import java.io.Serial;

/**
 * 验证码异常
 *
 * @author tnnn
 * @version V1.0
 * @date 2022-11-21 13:51
 */
public class CaptChaUtilException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 4129812562603997310L;

    private int code;
    private String msg;

    public CaptChaUtilException() {
        super();
    }

    public CaptChaUtilException(String message) {
        super(message);
        this.msg = message;
        this.code = 500;
    }

    public CaptChaUtilException(Integer code, String message) {
        super(message);
        this.code = code;
        this.msg = message;
    }

    public CaptChaUtilException(String message, Throwable cause) {
        super(message, cause);
        this.msg = message;
        this.code = 500;
    }

    public CaptChaUtilException(String message, Throwable cause, int code) {
        super(message, cause);
        this.msg = message;
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
