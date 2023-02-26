package cn.tannn.captcha.domain.service;


/**
 * 验证 验证码正确性
 * @author tan
 * @date 2023-02-26 18:15:43
 */
public interface VerifyCaptcha {

    /**
     * 验证证码正确与否
     * @param question 问题
     * @param answer   回答
     * @return true 正确
     */
     Boolean verifyCaptcha(String question, String answer);
}
