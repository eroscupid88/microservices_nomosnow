package ca.nomosnow.sportorganizationservice.configuration.redis;


import ca.nomosnow.sportorganizationservice.configuration.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@Configuration
@EnableRedisRepositories
public class NomosnowRedisConfiguration {

    @Autowired
    ConfigService configService;
    //    set up redis
    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        String hostname = configService.getRedisServer();
        int port = Integer.parseInt(configService.getRedisPort());
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(hostname, port);
        return new JedisConnectionFactory(redisStandaloneConfiguration);
    }

    @Bean
    RedisTemplate<?, ?> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        return template;
    }

    @Bean
    public RedisCacheManager cacheManager() {
        RedisCacheManager rcm = RedisCacheManager.create(jedisConnectionFactory());
        rcm.setTransactionAware(true);
        return rcm;
    }
}
