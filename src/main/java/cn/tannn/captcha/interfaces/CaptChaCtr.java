package cn.tannn.captcha.interfaces;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import cn.hutool.captcha.generator.MathGenerator;
import cn.jdevelops.annotation.mapping.PathRestController;
import cn.jdevelops.result.result.ResultVO;
import cn.tannn.captcha.domain.CaptchaVO;
import cn.tannn.captcha.infrastructure.dict.CaptchaConstant;
import cn.tannn.redis.domain.service.RedisService;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Mono;


/**
 *  验证码的生成接口
 *
 * @author tnnn
 * @version V1.0
 * @date 2022-09-01 10:07
 */
@PathRestController("captcha")
public class CaptChaCtr {

    private final RedisService redisService;

    public CaptChaCtr(RedisService redisService) {
        this.redisService = redisService;
    }

    /**
     * 算术图形验证码
     * （后期考虑传入用户code，进行ip+code的唯一key区分。目前就用IP）
     * @return CaptchaVO of ResultVO
     */
    @GetMapping("/math")
    public Mono<ResultVO<CaptchaVO>> imageMathCaptcha(ServerHttpRequest request) {
        ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(160, 45, 4, 1);
        // 自定义验证码内容为四则运算方式
        captcha.setGenerator(new MathGenerator(1));
        // 重新生成code
        captcha.createCode();
        CaptchaVO build = CaptchaVO.builder()
                .base64(captcha.getImageBase64Data())
                .overtime(CaptchaConstant.IMAGE_CAPTCHA_CACHE_TIMEOUT)
                .captcha(captcha.getCode())
                .build();
        // 算术验证码好像没必要存储
        redisService.storageImageCaptcha(build,request);
        return Mono.justOrEmpty(ResultVO.successForData(build))
                .onErrorResume(e -> Mono.empty())
                .switchIfEmpty(Mono.just(ResultVO.fail("获取验证码失败，请重新获取！")));
    }

}
