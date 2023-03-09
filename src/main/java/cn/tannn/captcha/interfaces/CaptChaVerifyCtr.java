package cn.tannn.captcha.interfaces;

import cn.jdevelops.annotation.mapping.PathRestController;
import cn.jdevelops.result.result.ResultVO;
import cn.tannn.captcha.domain.service.factory.VerifyCaptchaFactory;
import cn.tannn.captcha.domain.vo.CaptchaVO;
import cn.tannn.captcha.infrastructure.exception.CaptChaExceptionMsg;
import cn.tannn.captcha.infrastructure.exception.CaptChaUtilException;
import cn.tannn.redis.domain.service.RedisService;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


/**
 *  验证码的验证接口
 *
 * @author tnnn
 * @version V1.0
 * @date 2022-09-01 10:07
 */
@PathRestController("/verify/captcha")
public class CaptChaVerifyCtr {

    private final RedisService redisService;

    public CaptChaVerifyCtr(RedisService redisService) {
        this.redisService = redisService;
    }

    /**
     * 验证验证码的正确性
     * @param answer 用户回答的答案
     * @return ture or false
     */
    @GetMapping("/{answer}")
    public ResultVO<Boolean> imageMathCaptcha(@PathVariable("answer") String answer,
                                              ServerHttpRequest request) throws IllegalAccessException {
        CaptchaVO redisCaptcha = redisService.loadImageCaptcha(request)
                .orElseThrow(() -> new CaptChaUtilException(CaptChaExceptionMsg.EXPIRES));
        String question = redisCaptcha.getCaptcha();
        VerifyCaptchaFactory captchaFactory = new VerifyCaptchaFactory(redisCaptcha.getCaptchaType());
        Boolean aBoolean = captchaFactory.verifyCaptcha(question, answer);
        redisService.deleteImageCaptcha(request);
        return ResultVO.resultDataMsgForT(aBoolean,aBoolean,"验证");

    }


    /**
     * 验证验证码的正确性(指定IP，这个接口尽量不外暴露，做微服务的话这个接口抽离成fegin)
     * @param answer 用户回答的答案
     * @return ture or false
     */
    @GetMapping("/{answer}/{ip}")
    public ResultVO<Boolean> imageMathCaptcha(@PathVariable("answer") String answer,
                                              @PathVariable("ip") String ip) throws IllegalAccessException {
        CaptchaVO redisCaptcha = redisService.loadImageCaptcha(ip)
                .orElseThrow(() -> new CaptChaUtilException(CaptChaExceptionMsg.EXPIRES));
        String question = redisCaptcha.getCaptcha();
        VerifyCaptchaFactory captchaFactory = new VerifyCaptchaFactory(redisCaptcha.getCaptchaType());
        Boolean aBoolean = captchaFactory.verifyCaptcha(question, answer);
        redisService.deleteImageCaptcha(ip);
        return ResultVO.resultDataMsgForT(aBoolean,aBoolean,"验证");

    }
}
