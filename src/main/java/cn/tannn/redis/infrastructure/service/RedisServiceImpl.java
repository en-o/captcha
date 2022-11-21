package cn.tannn.redis.infrastructure.service;

import cn.tannn.captcha.infrastructure.dict.CaptchaConstant;
import cn.tannn.redis.infrastructure.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * redis
 *
 * @author tnnn
 * @version V1.0
 * @date 2022-11-21 13:25
 */
@Service
public class RedisServiceImpl implements RedisService{


    private static final Logger LOG = LoggerFactory.getLogger(RedisServiceImpl.class);

    /**
     * reids
     */
    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public void storageImageCaptcha(String key, String value) {
        // GuavaCache.imageCaptchaCache.put(captcha.getCode(),captcha.getCode());
        String redisFolder = RedisUtil.storageImageCaptchaRedisFolder(key);
        redisTemplate.boundHashOps(redisFolder).put(key,
                value);
        // 设置过期时间（秒
        redisTemplate.expire(redisFolder, CaptchaConstant.IMAGE_CAPTCHA_CACHE_TIMEOUT, TimeUnit.MILLISECONDS);
    }
}
