package cn.tannn.redis.infrastructure.dict;

/**
 * redis key
 *
 * @author tnnn
 * @version V1.0
 * @date 2022-11-21 13:26
 */
public class RedisCaptchaConstant {

    /**
     * 验证码缓存key
     */
    public static final String CAPTCHA_CACHE_KEY = "CAPTCHA_CACHE";


    /**
     * 图像验证码过期时间 （毫秒
     * 1分钟
     */
    public static final Long CAPTCHA_CACHE_TIMEOUT = 60000L;


}
