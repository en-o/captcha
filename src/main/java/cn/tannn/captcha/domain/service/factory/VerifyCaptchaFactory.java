package cn.tannn.captcha.domain.service.factory;

import cn.tannn.captcha.domain.enums.CaptchaType;
import cn.tannn.captcha.domain.service.VerifyCaptcha;
import cn.tannn.captcha.domain.service.impl.ComparisonVerifyCaptcha;
import cn.tannn.captcha.domain.service.impl.MathVerifyCaptcha;

/**
 * 验证码验证工厂
 *
 * @author tnnn
 * @version V1.0
 * @date 2023-02-26 18:22
 */
public class VerifyCaptchaFactory {
    VerifyCaptcha verifyCaptcha;

    public VerifyCaptchaFactory(CaptchaType type) throws IllegalAccessException {
        switch (type) {
            case MATH -> this.verifyCaptcha = new MathVerifyCaptcha();
            case CIRCLE, LINE, SHEAR -> this.verifyCaptcha = new ComparisonVerifyCaptcha();
            case null, default -> throw new IllegalAccessException("非法的验证码");
        }
    }

    /**
     * 验证证码正确与否
     * @param question 问题
     * @param answer   回答
     * @return true 正确
     */
    public Boolean verifyCaptcha(String question, String answer) {
        return this.verifyCaptcha.verifyCaptcha(question, answer);
    }
}
