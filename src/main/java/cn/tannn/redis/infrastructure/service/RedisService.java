package cn.tannn.redis.infrastructure.service;

/**
 * redis
 *
 * @author tnnn
 * @version V1.0
 * @date 2022-11-21 13:21
 */
public interface RedisService {

    /**
     * 存储图形验证嘛
     * @param key key
     * @param value 值
     */
    void storageImageCaptcha(String key,String value);
}
