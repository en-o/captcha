package cn.tannn.captcha.domain.service.impl;

import cn.hutool.captcha.generator.MathGenerator;
import cn.tannn.captcha.domain.service.VerifyCaptcha;
import cn.tannn.captcha.infrastructure.exception.CaptChaUtilException;
import org.apache.commons.lang3.StringUtils;

/**
 * 算术验证
 *
 * @author tnnn
 * @version V1.0
 * @date 2023-02-26 18:17
 */
public class MathVerifyCaptcha implements VerifyCaptcha {

    @Override
    public Boolean verifyCaptcha(String question, String answer) {
        if (StringUtils.isNotBlank(question) && StringUtils.isNotBlank(answer)) {
            return new MathGenerator().verify(question, answer);
        }
        throw new CaptChaUtilException("算术验证码参数异常");
    }
}
