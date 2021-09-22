package ca.nomosnow.sport_event_service;

import ca.nomosnow.sport_event_service.model.SportEvent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

/**
 * Start Standalone SpringBootApplication with SportEventService
 */
@SpringBootApplication
@EnableEurekaClient
public class SportEventServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SportEventServiceApplication.class, args);
    }
    // internationalization enable multiple formats and languages
    @Bean
    /**
     * locateResolver method create default Locate.CANADA as location
     */
    public LocaleResolver localeResolver() {
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

}
