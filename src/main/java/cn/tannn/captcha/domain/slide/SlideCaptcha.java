package cn.tannn.captcha.domain.slide;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 滑动验证码
 * @author tnnn
 */
@Getter
@Setter
@ToString
public class SlideCaptcha {

    /**
     * 随机字符串
     **/
    private String nonceStr;
    /**
     * 验证值
     **/
    private String value;
    /**
     * 生成的画布的base64
     **/
    private String canvasSrc;
    /**
     * 画布宽度
     **/
    private Integer canvasWidth;
    /**
     * 画布高度
     **/
    private Integer canvasHeight;
    /**
     * 生成的阻塞块的base64
     **/
    private String blockSrc;
    /**
     * 阻塞块宽度
     **/
    private Integer blockWidth;
    /**
     * 阻塞块高度
     **/
    private Integer blockHeight;
    /**
     * 阻塞块凸凹半径
     **/
    private Integer blockRadius;
    /**
     * 阻塞块的横轴坐标
     **/
    private Integer blockX;
    /**
     * 阻塞块的纵轴坐标
     **/
    private Integer blockY;

}
