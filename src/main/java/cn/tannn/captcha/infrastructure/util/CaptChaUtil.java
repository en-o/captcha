package cn.tannn.captcha.infrastructure.util;

import cn.hutool.captcha.generator.MathGenerator;
import cn.tannn.captcha.infrastructure.exception.CaptChaUtilException;
import org.apache.commons.lang3.StringUtils;

/**
 * 验证码
 *
 * @author tnnn
 * @version V1.0
 * @date 2022-11-21 13:51
 */
public class CaptChaUtil {

    /**
     * 验证图形验证码正确与否
     *
     * @param question 问题
     * @param answer   回答
     * @return true 正确
     */
    public static Boolean verifyCaptcha(String question, String answer) {
        if (StringUtils.isNotBlank(question) && StringUtils.isNotBlank(answer)) {
            return new MathGenerator().verify(question, answer);
        }
        throw new CaptChaUtilException("验证图形码参数异常");
    }

}
