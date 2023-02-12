package cn.tannn.captcha.interfaces;

import cn.jdevelops.annotation.mapping.PathRestController;
import cn.jdevelops.result.result.ResultVO;
import cn.tannn.captcha.infrastructure.exception.CaptChaExceptionMsg;
import cn.tannn.captcha.infrastructure.exception.CaptChaUtilException;
import cn.tannn.captcha.infrastructure.util.CaptChaUtil;
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
     * 验证算术验证码的正确性
     * @param answer 用户回答的答案
     * @return ture or false
     */
    @GetMapping("/math/{answer}")
    public ResultVO<Boolean> imageMathCaptcha(@PathVariable("answer") String answer,
                                              ServerHttpRequest request) {
        String question = redisService.loadImageCaptcha(request)
                .orElseThrow(() -> new CaptChaUtilException(CaptChaExceptionMsg.EXPIRES))
                .getCaptcha();
        Boolean aBoolean = CaptChaUtil.verifyCaptcha(question, answer);
        return ResultVO.resultDataMsgForT(aBoolean,aBoolean,"验证");

    }
}
