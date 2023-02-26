package cn.tannn.redis.infrastructure.util;

import cn.tannn.redis.infrastructure.dict.RedisCaptchaConstant;

/**
 * @author tnnn
 * @version V1.0
 * @date 2022-11-21 13:38
 */
public class RedisUtil {

    /**
     * 验证码的存储key
     * @param key key
     * @return folderName:key
     */
    public static String storageCaptchaRedisFolder(String key) {
        return RedisCaptchaConstant.CAPTCHA_CACHE_KEY +":" + key;
    }
}
