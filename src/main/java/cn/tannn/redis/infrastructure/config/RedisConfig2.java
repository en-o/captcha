//package cn.tannn.redis.infrastructure.config;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.cache.annotation.CachingConfigurerSupport;
//import org.springframework.context.annotation.Bean;
//import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
//import org.springframework.data.redis.core.ReactiveRedisTemplate;
//import org.springframework.data.redis.serializer.RedisSerializationContext;
//import org.springframework.data.redis.serializer.RedisSerializer;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//import org.springframework.stereotype.Component;
//
//
///**
// * webflux
// */
//@Component
//public class RedisConfig2 extends CachingConfigurerSupport {
//
//    private static final Logger LOG = LoggerFactory.getLogger(RedisConfig.class);
//
//    @Bean
//    public RedisSerializationContext redisSerializationContext() {
//
//        RedisSerializationContext.RedisSerializationContextBuilder builder =
//                RedisSerializationContext.newSerializationContext();
//        builder.key(StringRedisSerializer.UTF_8);
//        builder.value(RedisSerializer.json());
//        builder.hashKey(StringRedisSerializer.UTF_8);
//        builder.hashValue(RedisSerializer.json());
//        return builder.build();
//    }
//
//
//    @Bean
//    public ReactiveRedisTemplate reactiveRedisTemplate(ReactiveRedisConnectionFactory connectionFactory,
//                                                       RedisSerializationContext serializationContext) {
//        ReactiveRedisTemplate reactiveRedisTemplate = new ReactiveRedisTemplate(connectionFactory,
//                serializationContext);
//        return reactiveRedisTemplate;
//    }
//}
