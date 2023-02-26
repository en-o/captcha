package cn.tannn.captcha.domain.service.impl;

import cn.hutool.captcha.generator.MathGenerator;
import cn.tannn.captcha.domain.service.VerifyCaptcha;
import cn.tannn.captcha.infrastructure.exception.CaptChaUtilException;
import org.apache.commons.lang3.StringUtils;

/**
 * 常规比对验证
 * 包含： 线段，圆圈，扭曲
 * @author tnnn
 * @version V1.0
 * @date 2023-02-26 18:17
 */
public class ComparisonVerifyCaptcha implements VerifyCaptcha {

    @Override
    public Boolean verifyCaptcha(String question, String answer) {
        if (StringUtils.isNotBlank(question) && StringUtils.isNotBlank(answer)) {
            return question.equals(answer);
        }
        throw new CaptChaUtilException("验证码参数异常");
    }
}
