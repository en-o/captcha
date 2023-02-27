package cn.tannn;

import cn.jdevelops.result.result.ResultVO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tan
 */
@SpringBootApplication
@RestController
public class CaptchaApplication {

    public static void main(String[] args) {
        SpringApplication.run(CaptchaApplication.class, args);
    }


    @GetMapping
    public ResultVO<String> hi(){
        return ResultVO.success("hi");
    }

}
