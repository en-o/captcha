package cn.tannn.redis.infrastructure.util;

import cn.tannn.redis.infrastructure.dict.RedisConstant;

/**
 * @author tnnn
 * @version V1.0
 * @date 2022-11-21 13:38
 */
public class RedisUtil {

    /**
     * 图形验证码的存储key
     * @param key key
     * @return folderName:key
     */
    public static String storageImageCaptchaRedisFolder(String key) {
        return RedisConstant.IMAGE_CAPTCHA_CACHE_TIMEOUT_KEY +":" + key;
    }
}
