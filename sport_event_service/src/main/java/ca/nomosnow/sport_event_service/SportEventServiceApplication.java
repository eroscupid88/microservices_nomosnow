package ca.nomosnow.sport_event_service;

import ca.nomosnow.sport_event_service.config.ConfigService;
import ca.nomosnow.sport_event_service.model.SportEvent;
import ca.nomosnow.sport_event_service.utils.UserContextInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

/**
 * Start Standalone SpringBootApplication with SportEventService
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableDiscoveryClient
@RefreshScope
public class SportEventServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SportEventServiceApplication.class, args);
    }

    @Autowired
    ConfigService configService;

    @Bean
    public LocaleResolver localeResolvers() {
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        //set CANADA as default locate
        localeResolver.setDefaultLocale(Locale.CANADA);

        return localeResolver;
    }
    // internationalization enable multiple formats and languages

    /**
     * messageSource method take messages_properties into messageSource Object
     * RequestHeader set up language for message (use in message_${location}.properties file)
     */
    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        // does throw error if messages isn't found
        messageSource.setUseCodeAsDefaultMessage(true);
        //set base name of the languages properties files
        messageSource.setBasenames("messages");
        return messageSource;
    }


//    set up redis
    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        String hostname = configService.getRedisServer();
        int port = Integer.parseInt(configService.getRedisPort());
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(hostname, port);
        return new JedisConnectionFactory(redisStandaloneConfiguration);
    }

    RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        return template;
    }

    /**
     * Loadbalancer
     * @return restTemplate
     */
    @Primary
//    @LoadBalanced
    @Bean
    RestTemplate getRestTemplate() {
        RestTemplate template = new RestTemplate();
        List interceptors = template.getInterceptors();
        interceptors.add(new UserContextInterceptor());
        template.setInterceptors( interceptors);

        return template;
    }

}
