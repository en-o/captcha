package cn.tannn.redis.domain.service;

import cn.tannn.captcha.domain.vo.CaptchaVO;
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
        String key = IpUtil.getPoxyIpEnhance(request);
        String redisFolderKey = RedisUtil.storageCaptchaRedisFolder(key);
        redisTemplate.boundHashOps(redisFolderKey).put(key,
                captcha);
        // 设置过期时间（秒
        redisTemplate.expire(redisFolderKey, Duration.ofMillis(captcha.getOvertime()));
    }

    @Override
    public Optional<CaptchaVO> loadImageCaptcha(ServerHttpRequest request) {
        String key = IpUtil.getPoxyIpEnhance(request);
        return loadImageCaptcha(key);
    }

    @Override
    public Optional<CaptchaVO> loadImageCaptcha(String ip) {
        String redisFolderKey = RedisUtil.storageCaptchaRedisFolder(ip);
        try {
            BoundHashOperations<String, Object, CaptchaVO> find = redisTemplate
                    .boundHashOps(redisFolderKey);
            CaptchaVO captchaVO = find.get(ip);
            return Optional.ofNullable(captchaVO);
        }catch (Exception e){
            LOG.error("加载验证码信息失败",e);
            return Optional.empty();
        }
    }

    @Override
    public void deleteImageCaptcha(ServerHttpRequest request) {
        String key = IpUtil.getPoxyIpEnhance(request);
        deleteImageCaptcha(key);
    }

    @Override
    public void deleteImageCaptcha(String ip) {
        try {
            String redisFolderKey = RedisUtil.storageCaptchaRedisFolder(ip);
            redisTemplate.boundHashOps(redisFolderKey).delete(ip);
        }catch (Exception e){
            LOG.error("删除过期验证码失败",e);
            e.printStackTrace();
        }
    }
}
