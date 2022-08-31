package cn.tannn.captcha;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CaptchaApplicationTests {

    @Test
    void contextLoads() {
        String test = "1";
        assertEquals(test,"1");
    }

}
