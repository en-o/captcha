package cn.tannn.redis.domain.service;

import cn.tannn.captcha.domain.CaptchaVO;
import org.springframework.http.server.reactive.ServerHttpRequest;

import java.util.Optional;

/**
 * redis
 *
 * @author tnnn
 * @version V1.0
 * @date 2022-11-21 13:21
 */
public interface RedisService {

    /**
     * 存储图形验证码
     * ip 作为key（保证相对唯一性）
     * @param captcha CaptchaVO
     * @param request 请求信息
     */
    void storageImageCaptcha(CaptchaVO captcha, ServerHttpRequest request);

    /**
     * 加载图形验证码
     * ip 作为key（保证相对唯一性）
     * @param request 请求信息
     * @return captcha 无数据为空
     */
    Optional<CaptchaVO> loadImageCaptcha(ServerHttpRequest request);


    /**
     * 删除图形验证码
     * ip 作为key（保证相对唯一性）
     * @param request 请求信息
     */
    void deleteImageCaptcha(ServerHttpRequest request);
}
