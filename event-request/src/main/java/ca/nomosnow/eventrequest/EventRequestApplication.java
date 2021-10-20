package ca.nomosnow.eventrequest;

import ca.nomosnow.eventrequest.configuration.location.LocationConfiguration;
import ca.nomosnow.eventrequest.configuration.messagesource.NomosnowMessageSourceConfiguration;
import io.eventuate.tram.spring.jdbckafka.TramJdbcKafkaConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableDiscoveryClient
@RefreshScope
@Import({TramJdbcKafkaConfiguration.class,
        NomosnowMessageSourceConfiguration.class,
        LocationConfiguration.class})
public class EventRequestApplication {
    public static void main(String[] args) {
        SpringApplication.run(EventRequestApplication.class, args);
    }

}
