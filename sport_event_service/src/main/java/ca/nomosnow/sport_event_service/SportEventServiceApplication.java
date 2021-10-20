package ca.nomosnow.sport_event_service;

import ca.nomosnow.sport_event_service.configuration.location.LocationConfiguration;
import ca.nomosnow.sport_event_service.configuration.messagesource.NomosnowMessageSourceConfiguration;
import ca.nomosnow.sport_event_service.configuration.redis.NomosnowRedisConfiguration;
import ca.nomosnow.sport_event_service.utils.UserContextInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

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
@EnableRedisRepositories
//@EnableCaching
@Import({
        NomosnowMessageSourceConfiguration.class,
        NomosnowRedisConfiguration.class,
        LocationConfiguration.class})
public class SportEventServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(SportEventServiceApplication.class, args);
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
