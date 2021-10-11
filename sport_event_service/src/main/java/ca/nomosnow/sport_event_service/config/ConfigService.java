package ca.nomosnow.sport_event_service.config;


import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


/**
 * Method to pass configuration from config server by using prefix
 */
@Component
@Getter
@Setter
public class ConfigService {
    // todo
    private String property;

    @Value("${redis.server}")
    private String redisServer="";

    @Value("${redis.port}")
    private String redisPort="";
}
