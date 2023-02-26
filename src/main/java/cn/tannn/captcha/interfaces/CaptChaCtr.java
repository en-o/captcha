package cn.tannn.captcha.interfaces;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.captcha.ShearCaptcha;
import cn.hutool.captcha.generator.MathGenerator;
import cn.jdevelops.annotation.mapping.PathRestController;
import cn.jdevelops.result.result.ResultVO;
import cn.tannn.captcha.domain.enums.CaptchaType;
import cn.tannn.captcha.domain.slide.SlideCaptcha;
import cn.tannn.captcha.domain.slide.SlideCaptchaService;
import cn.tannn.captcha.domain.vo.CaptchaVO;
import cn.tannn.redis.domain.service.RedisService;
import cn.tannn.redis.infrastructure.dict.RedisCaptchaConstant;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Mono;

import java.util.Objects;


/**
 *  验证码的生成接口
 *
 * @author tnnn
 * @version V1.0
 * @date 2022-09-01 10:07
 */
@PathRestController("captcha")
public class CaptChaCtr {
    private static final String ERROR_MESSAGE = "获取验证码失败，请重新获取！";

    private final RedisService redisService;

    /**
     * 滑动验证码
     */
    private final SlideCaptchaService slideCaptchaService;

    public CaptChaCtr(RedisService redisService, SlideCaptchaService slideCaptchaService) {
        this.redisService = redisService;
        this.slideCaptchaService = slideCaptchaService;
    }

    /**
     * 算术图形验证码
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
                .overtime(RedisCaptchaConstant.CAPTCHA_CACHE_TIMEOUT)
                .captcha(captcha.getCode())
                .captchaType(CaptchaType.MATH)
                .build();
        // 存储，验证用
        redisService.storageImageCaptcha(build,request);
        return Mono.justOrEmpty(ResultVO.successForData(build))
                .onErrorResume(e -> Mono.empty())
                .switchIfEmpty(Mono.just(ResultVO.fail(ERROR_MESSAGE)));
    }


    /**
     * 线段干扰图形验证码
     * @return CaptchaVO of ResultVO
     */
    @GetMapping("/line")
    public Mono<ResultVO<CaptchaVO>> imageLineCaptcha(ServerHttpRequest request) {
        LineCaptcha captcha = CaptchaUtil.createLineCaptcha(160, 45, 4, 150);
        CaptchaVO build = CaptchaVO.builder()
                .base64(captcha.getImageBase64Data())
                .overtime(RedisCaptchaConstant.CAPTCHA_CACHE_TIMEOUT)
                .captcha(captcha.getCode())
                .captchaType(CaptchaType.LINE)
                .build();
        // 存储，验证用
        redisService.storageImageCaptcha(build,request);
        return Mono.justOrEmpty(ResultVO.successForData(build))
                .onErrorResume(e -> Mono.empty())
                .switchIfEmpty(Mono.just(ResultVO.fail(ERROR_MESSAGE)));
    }



    /**
     * 圆圈干扰图形验证码
     * @return CaptchaVO of ResultVO
     */
    @GetMapping("/circle")
    public Mono<ResultVO<CaptchaVO>> imageCircleCaptcha(ServerHttpRequest request) {
        CircleCaptcha captcha = CaptchaUtil.createCircleCaptcha(160, 45, 4, 20);
        CaptchaVO build = CaptchaVO.builder()
                .base64(captcha.getImageBase64Data())
                .overtime(RedisCaptchaConstant.CAPTCHA_CACHE_TIMEOUT)
                .captcha(captcha.getCode())
                .captchaType(CaptchaType.CIRCLE)
                .build();
        // 存储，验证用
        redisService.storageImageCaptcha(build,request);
        return Mono.justOrEmpty(ResultVO.successForData(build))
                .onErrorResume(e -> Mono.empty())
                .switchIfEmpty(Mono.just(ResultVO.fail(ERROR_MESSAGE)));
    }


    /**
     * 扭曲干扰图形验证码
     * @return CaptchaVO of ResultVO
     */
    @GetMapping("/shear")
    public Mono<ResultVO<CaptchaVO>> imageShearCaptcha(ServerHttpRequest request) {
        ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(160, 45, 4, 4);
        CaptchaVO build = CaptchaVO.builder()
                .base64(captcha.getImageBase64Data())
                .overtime(RedisCaptchaConstant.CAPTCHA_CACHE_TIMEOUT)
                .captcha(captcha.getCode())
                .captchaType(CaptchaType.SHEAR)
                .build();
        // 存储，验证用
        redisService.storageImageCaptcha(build,request);
        return Mono.justOrEmpty(ResultVO.successForData(build))
                .onErrorResume(e -> Mono.empty())
                .switchIfEmpty(Mono.just(ResultVO.fail(ERROR_MESSAGE)));
    }



    /**
     * 滑动验证码
     * @return CaptchaVO of ResultVO
     */
    @GetMapping("/slide")
    public Mono<ResultVO<CaptchaVO>> imageSlideCaptcha(ServerHttpRequest request) {
        SlideCaptcha captcha = slideCaptchaService.getCaptcha(request);
        // 缓存
        CaptchaVO build = CaptchaVO.builder()
                .overtime(RedisCaptchaConstant.CAPTCHA_CACHE_TIMEOUT)
                // getBlockX -  answer(SlideCaptcha.value) < 允许偏差
                .captcha(String.valueOf(Objects.isNull(captcha.getBlockX())?"0":captcha.getBlockX()))
                .captchaType(CaptchaType.SLIDE).build();
        // 存储，验证用
        redisService.storageImageCaptcha(build,request);
        // 这个不能返回给前端
        captcha.setBlockX(null);
        // 这个没必要存
        build.setSlide(captcha);
        return Mono.justOrEmpty(ResultVO.successForData(build))
                .onErrorResume(e -> Mono.empty())
                .switchIfEmpty(Mono.just(ResultVO.fail(ERROR_MESSAGE)));
    }


}
