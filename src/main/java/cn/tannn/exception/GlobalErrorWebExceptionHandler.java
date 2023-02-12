package cn.tannn.exception;

import cn.jdevelops.result.result.ResultVO;
import cn.tannn.captcha.infrastructure.exception.CaptChaUtilException;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;


/**
 * webflux 全局异常
 */
@Component
public class GlobalErrorWebExceptionHandler extends
        AbstractErrorWebExceptionHandler {

    public GlobalErrorWebExceptionHandler(ErrorAttributes errorAttributes, WebProperties.Resources resourceProperties,
                                          ApplicationContext applicationContext, ServerCodecConfigurer configurer) {
        super(errorAttributes, resourceProperties, applicationContext);
        this.setMessageWriters(configurer.getWriters());
    }


    @Override
    protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
        return RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse);
    }

    private Mono<ServerResponse> renderErrorResponse(final ServerRequest request) {

        // 设置浏览器状态码
//        return ServerResponse.status(HttpStatus.BAD_REQUEST)
//                .contentType(MediaType.APPLICATION_JSON_UTF8)
//                .body(BodyInserters.fromObject(errorPropertiesMap));
        // 浏览器状态码一直为200
        /**
         * 这个方法我们能获得异常的一些详细信息，比如异常类型，异常的消息描述，异常的TRACK信息等。这样有助于我们对异常做针对性的处理
         */
//        Map<String, Object> errorPropertiesMap = getErrorAttributes(request,
//                ErrorAttributeOptions.of(ErrorAttributeOptions.Include.BINDING_ERRORS,
//                        ErrorAttributeOptions.Include.EXCEPTION,
//                        ErrorAttributeOptions.Include.STACK_TRACE,
//                        ErrorAttributeOptions.Include.MESSAGE));
        Throwable error = getError(request);
        ResultVO<Object> fail;
        if(error instanceof CaptChaUtilException){
           fail = ResultVO.fail(((CaptChaUtilException) error).getCode(), error.getMessage());
        }else {
            fail = ResultVO.fail(error.getMessage());
        }
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(fail));
    }


}
