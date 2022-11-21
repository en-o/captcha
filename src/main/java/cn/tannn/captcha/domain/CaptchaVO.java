package cn.tannn.captcha.domain;

import lombok.*;

/**
 * 验证码vo
 *
 * @author tn
 * @className CaptchaVO
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
    private Integer overtime;

}
