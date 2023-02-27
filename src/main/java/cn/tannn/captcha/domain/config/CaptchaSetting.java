package cn.tannn.captcha.domain.config;

import cn.tannn.captcha.infrastructure.exception.CaptChaUtilException;
import cn.tannn.redis.domain.service.RedisServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.security.SecureRandom;
import java.util.Objects;

/**
 * 验证码配置文件
 *
 * @author tnnn
 * @version V1.0
 * @date 2023-02-27 15:34
 */
@Component
@ConfigurationProperties(prefix = "jdevelops.captcha")
public class CaptchaSetting {
    private static final Logger LOG = LoggerFactory.getLogger(CaptchaSetting.class);

    private final String HTTP_URL = "http";
    private final String DEFAULT_IMAGES = "img/slide/test-2.png";

    /**
     * 滑动验证码的资源图集，随机不同的图片(可网络，可本地)
     * 本地: "E:\\测试图片\\" (文件夹下不允许再有文件夹)
     * 网络: "http(s)://test.com/image/" (文件夹下不允许再有文件夹)
     * 默认: 内置的一张图
     */
    String slide;


    @Override
    public String toString() {
        return "CaptchaSetting{" +
                "slide='" + slide + '\'' +
                '}';
    }

    public String getSlide() {
        return slide;
    }

    public void setSlide(String slide) {
        this.slide = slide;
    }

    public BufferedImage sildeFile(){
        try {
            if(Objects.isNull(slide)||slide.equalsIgnoreCase(DEFAULT_IMAGES)){
                ClassPathResource resource = new ClassPathResource(DEFAULT_IMAGES);
                return ImageIO.read(resource.getFile());
            }else if(slide.startsWith(HTTP_URL)){
                URL url = new URL(slide);
                return ImageIO.read(url.openStream());
            }else {
                File file = new File(slide);
                if(file.isDirectory()){
                    File[] files = file.listFiles();
                    //创建随机对象
                    SecureRandom random = new SecureRandom();
                    //随机数组索引，nextInt(len-1)表示随机整数[0,(len-1)]之间的值
                    int arrIdx = random.nextInt(files.length-1);
                    file = files[arrIdx];
                }
                return ImageIO.read(file);
            }

        } catch (Exception e) {
            LOG.error("获取拼图资源失败");
            throw new CaptChaUtilException("请设置正确的滑动资源图集",e);
        }
    }
}
