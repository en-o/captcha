package cn.tannn.redis.domain.service;

import cn.tannn.captcha.domain.CaptchaVO;
import cn.tannn.captcha.infrastructure.util.IpUtil;
import cn.tannn.redis.infrastructure.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.Optional;

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
    public void storageImageCaptcha(CaptchaVO captcha, ServerHttpRequest request) {
        // GuavaCache.imageCaptchaCache.put(captcha.getCode(),captcha.getCode());
        String key = IpUtil.getPoxyIpEnhance(request);
        String redisFolderKey = RedisUtil.storageImageCaptchaRedisFolder(key);
        redisTemplate.boundHashOps(redisFolderKey).put(key,
                captcha);
        // 设置过期时间（秒
        redisTemplate.expire(redisFolderKey, Duration.ofMillis(captcha.getOvertime()));
    }

    @Override
    public Optional<CaptchaVO> loadImageCaptcha(ServerHttpRequest request) {
        String key = IpUtil.getPoxyIpEnhance(request);
        String redisFolderKey = RedisUtil.storageImageCaptchaRedisFolder(key);
        try {
            BoundHashOperations<String, Object, CaptchaVO> find = redisTemplate
                    .boundHashOps(redisFolderKey);
            CaptchaVO captchaVO = find.get(key);
            return Optional.ofNullable(captchaVO);
        }catch (Exception e){
            e.printStackTrace();
            return Optional.empty();
        }
    }
}