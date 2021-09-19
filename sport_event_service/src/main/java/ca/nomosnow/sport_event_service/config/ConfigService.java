package ca.nomosnow.sport_event_service.config;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "someprefix")
@Getter
@Setter
/**
 * Method to pass configuration from config server by using prefix
 */
public class ConfigService {
    private String property;
}
