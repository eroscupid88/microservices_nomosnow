package ca.nomosnow.sport_event_service;

import ca.nomosnow.sport_event_service.model.SportEvent;
import ca.nomosnow.sport_event_service.utils.UserContextInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
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
    // internationalization enable multiple formats and languages
    @Bean
    /**
     * locateResolver method create default Locate.CANADA as location
     */
    public LocaleResolver localeResolvers() {
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        //set CANADA as default locate
        localeResolver.setDefaultLocale(Locale.CANADA);

        return localeResolver;
    }
    // internationalization enable multiple formats and languages
    @Bean
    /**
     * messageSource method take messages_properties into messageSource Object
     * RequestHeader set up language for message (use in message_${location}.properties file)
     */
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        // does throw error if messages isn't found
        messageSource.setUseCodeAsDefaultMessage(true);
        //set base name of the languages properties files
        messageSource.setBasenames("messages");
        return messageSource;
    }

    /**
     * Loadbalancer
     * @return restTemplate
     */
    @LoadBalanced
    @Bean
    RestTemplate getRestTemplate() {
        RestTemplate template = new RestTemplate();
        List interceptors = template.getInterceptors();
        if (interceptors==null){
            template.setInterceptors(Collections.singletonList(
                    new UserContextInterceptor()));
        }else{
            interceptors.add(new UserContextInterceptor());
            template.setInterceptors( interceptors);
        }

        return template;
    }

}
