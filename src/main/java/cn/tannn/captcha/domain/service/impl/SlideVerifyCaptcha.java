package cn.tannn.captcha.domain.service.impl;

import cn.tannn.captcha.domain.service.VerifyCaptcha;
import cn.tannn.captcha.infrastructure.exception.CaptChaExceptionMsg;
import cn.tannn.captcha.infrastructure.exception.CaptChaUtilException;
import org.apache.commons.lang3.StringUtils;

/**
 * 滑动验证码比对验证
 * @author tnnn
 * @version V1.0
 * @date 2023-02-26 18:17
 */
public class SlideVerifyCaptcha implements VerifyCaptcha {

    /**
     * 拼图验证码允许偏差
     * todo 这个也要改为动态
     **/
    private final static Integer ALLOW_DEVIATION = 3;


    @Override
    public Boolean verifyCaptcha(String question, String answer) {
        // 根据移动距离判断验证是否成功
        if (StringUtils.isNotBlank(question) && StringUtils.isNotBlank(answer)) {
            if (Math.abs(Integer.parseInt(question) - Integer.parseInt(answer)) > ALLOW_DEVIATION) {
                throw new CaptChaUtilException(CaptChaExceptionMsg.SLIDE_EXPIRES);
            }else {
                return true;
            }
        }
        throw new CaptChaUtilException("滑动验证码参数异常");

    }
}
