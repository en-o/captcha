package cn.tannn.captcha.infrastructure.cache;

import cn.tannn.redis.infrastructure.dict.RedisCaptchaConstant;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.TimeUnit;

/**
 * guava的缓存
 *
 * @author tn
 * @date 2021-09-13 16:07
 */
public class GuavaCache {

    /**
     *  图形验证 cache
     *  redis已经用上，后期将她优化到redis中去
     */
    public static Cache<String, Object> imageCaptchaCache = CacheBuilder.newBuilder()
            // 设置并发级别为cpu核心数，默认为4
            .concurrencyLevel(Runtime.getRuntime().availableProcessors())
            // 设置缓存在写入1分钟后失效 默认不失效
            .expireAfterWrite(RedisCaptchaConstant.CAPTCHA_CACHE_TIMEOUT, TimeUnit.MILLISECONDS)
            // 设置初始容量为500
            .initialCapacity(5000)
            //设置缓存最大容量为100，超过100之后就会按照LRU最近虽少使用算法来移除缓存项
            .maximumSize(10000)
            .build();

}
