package cn.tannn.captcha.interfaces;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import cn.hutool.captcha.generator.MathGenerator;
import cn.jdevelops.annotation.mapping.PathRestController;
import cn.jdevelops.result.result.ResultVO;
import cn.tannn.captcha.domain.CaptchaVO;
import cn.tannn.captcha.infrastructure.dict.CaptchaConstant;
import cn.tannn.redis.infrastructure.service.RedisService;
import org.springframework.web.bind.annotation.GetMapping;

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
     * @return CaptchaVO of ResultVO
     */
    @GetMapping("/image/math")
    public ResultVO<CaptchaVO> imageMathCaptcha() {
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
        redisService.storageImageCaptcha(captcha.getCode(),captcha.getCode());
        return ResultVO.successForData(build);
    }

}
