package ca.nomosnow.sport_event_service.configuration.messagesource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

//@Configuration
public class NomosnowMessageSourceConfiguration {
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
}
