package ca.nomosnow.sportorganizationeventcreatedepartmentservice.configuration.location;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@Configuration
public class LocationConfiguration {
    @Bean
    public LocaleResolver localeResolvers() {
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        //set CANADA as default locate
        localeResolver.setDefaultLocale(Locale.CANADA);
        return localeResolver;
    }
}
