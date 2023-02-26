package cn.tannn.captcha.domain.vo;

import cn.tannn.captcha.domain.enums.CaptchaType;
import cn.tannn.captcha.domain.slide.SlideCaptcha;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

/**
 * 验证码vo
 *
 * @author tn
 * @date 2021-09-13 15:59
 */
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CaptchaVO {
    /**
     *  验证码base64(画布)
     */
    private String base64;

    /**
     * 滑动验证特有字段
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private SlideCaptcha slide;

    /**
     * 验证码数据
     */
    private String captcha;

    /**
     *  过期时间（毫秒）
     */
    private Long overtime;

    /**
     *  验证码类型
     */
    private CaptchaType captchaType;

}
