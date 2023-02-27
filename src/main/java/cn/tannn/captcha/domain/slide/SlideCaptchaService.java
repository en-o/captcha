package cn.tannn.captcha.domain.slide;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.util.UUID;

/**
 * 滑动验证码
 * @author tnnn
 */
@Service
public class SlideCaptchaService {

    /**
     * 获取验证码拼图（生成的抠图和带抠图阴影的大图及抠图坐标）
     **/
    public SlideCaptcha getCaptcha(ServerHttpRequest request) {
        SlideCaptcha slideCaptcha = new SlideCaptcha();
        // 参数校验
         SlideCaptchaUtils.checkCaptcha(slideCaptcha);
        // 获取资源图
        BufferedImage canvasImage = SlideCaptchaUtils.getBufferedImage();
        //调整原图到指定大小
        canvasImage = SlideCaptchaUtils.imageResize(canvasImage,
                slideCaptcha.getCanvasWidth(),
                slideCaptcha.getCanvasHeight());
        //随机生成阻塞块坐标
        int blockX = SlideCaptchaUtils.getNonceByRange(slideCaptcha.getBlockWidth()
                , slideCaptcha.getCanvasWidth() - slideCaptcha.getBlockWidth() - 10);
        int blockY = SlideCaptchaUtils.getNonceByRange(10, slideCaptcha.getCanvasHeight() -
                slideCaptcha.getBlockHeight() + 1);
        //阻塞块
        BufferedImage blockImage = new BufferedImage(slideCaptcha.getBlockWidth(),
                slideCaptcha.getBlockHeight(),
                BufferedImage.TYPE_4BYTE_ABGR);
        //新建的图像根据轮廓图颜色赋值，源图生成遮罩
        SlideCaptchaUtils.cutByTemplate(canvasImage, blockImage,
                slideCaptcha.getBlockWidth(),
                slideCaptcha.getBlockHeight(),
                slideCaptcha.getBlockRadius(),
                blockX,
                blockY);
        // 移动横坐标
        String nonceStr = UUID.randomUUID().toString().replaceAll("-", "");
        //设置返回参数
        slideCaptcha.setNonceStr(nonceStr);
        slideCaptcha.setBlockY(blockY);
        // 返回给前端的时候这个参数一定要隐藏，存储的时候要用而已
        slideCaptcha.setBlockX(blockX);
        slideCaptcha.setBlockSrc(SlideCaptchaUtils.toBase64(blockImage, "png"));
        slideCaptcha.setCanvasSrc(SlideCaptchaUtils.toBase64(canvasImage, "png"));
        return slideCaptcha;
    }
}
