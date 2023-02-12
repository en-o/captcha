package cn.tannn.exception;

import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 全局异常
 * @author tnnn
 */
@Configuration
public class ResourcesConfig {
    @Bean
    public WebProperties.Resources getResources(){
        return new WebProperties.Resources();
    }
}
