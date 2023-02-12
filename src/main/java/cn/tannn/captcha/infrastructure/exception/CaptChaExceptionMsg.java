package cn.tannn.captcha.infrastructure.exception;

/**
 * 错误消息
 *
 * @author tnnn
 * @version V1.0
 * @date 2023-02-12 15:20
 */
public enum CaptChaExceptionMsg {


    /**
     * 验证码已过期
     */
    EXPIRES(3001,"验证码已过期，请重新获取！")
    ;

    private final int code;
    /**
     * 消息
     */
    private final String message;

    CaptChaExceptionMsg(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
