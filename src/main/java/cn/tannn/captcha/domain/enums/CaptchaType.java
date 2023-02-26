package cn.tannn.captcha.domain.enums;

/**
 * 验证码类型
 * @author tan
 * @date 2023-02-26 18:05:25
 */
public enum CaptchaType {

    /**
     * 算术
     */
    MATH("math","算术"),

    /**
     * 线段
     */
    LINE("line","线段"),


    /**
     * 圆圈
     */
    CIRCLE("circle","圆圈"),

    /**
     * 扭曲
     */
    SHEAR("shear","扭曲"),

    ;

    final String code;
    final String name;



    CaptchaType(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    /**
     * 根据code 获取 枚举
     * @param code code
     * @return
     */
    public static CaptchaType codeByEnum(String code) {
        for (CaptchaType captchaType : CaptchaType.values()) {
            if (captchaType.getCode().equals(code)) {
                return captchaType;
            }
        }
        return null;
    }
}
