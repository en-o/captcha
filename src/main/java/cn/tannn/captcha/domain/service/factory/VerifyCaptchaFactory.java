package cn.tannn.captcha.domain.service.factory;

import cn.tannn.captcha.domain.enums.CaptchaType;
import cn.tannn.captcha.domain.service.VerifyCaptcha;
import cn.tannn.captcha.domain.service.impl.ComparisonVerifyCaptcha;
import cn.tannn.captcha.domain.service.impl.MathVerifyCaptcha;
import cn.tannn.captcha.domain.service.impl.SlideVerifyCaptcha;

import static cn.tannn.captcha.domain.enums.CaptchaType.*;

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
        // 打包之后不支持
//        switch (type) {
//            case MATH -> this.verifyCaptcha = new MathVerifyCaptcha();
//            case CIRCLE, LINE, SHEAR -> this.verifyCaptcha = new ComparisonVerifyCaptcha();
//            case SLIDE -> this.verifyCaptcha = new SlideVerifyCaptcha();
//            case null, default -> throw new IllegalAccessException("非法的验证码");
//        }

        if(type == null){
            throw new IllegalAccessException("非法的验证码");
        }else if(type == MATH){
            this.verifyCaptcha = new MathVerifyCaptcha();
        } else if (type == CIRCLE || type == LINE || type == SHEAR ) {
            this.verifyCaptcha = new ComparisonVerifyCaptcha();
        } else if (type == SLIDE ) {
            this.verifyCaptcha = new SlideVerifyCaptcha();
        }else {
            throw new IllegalAccessException("非法的验证码");
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
