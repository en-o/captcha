package cn.tannn.captcha.domain.vo;

import cn.tannn.captcha.domain.enums.CaptchaType;
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
     *  验证码base64
     */
    private String base64;

    /**
     * 验证码数据（要回传
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
