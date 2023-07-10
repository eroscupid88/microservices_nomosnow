package ca.nomosnow.eventrequest;

import ca.nomosnow.eventrequest.configuration.location.LocationConfiguration;
import ca.nomosnow.eventrequest.configuration.messagesource.NomosnowMessageSourceConfiguration;
import ca.nomosnow.eventrequest.configuration.redis.NomosnowRedisConfiguration;
import io.eventuate.tram.spring.jdbckafka.TramJdbcKafkaConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;
import io.microservices.canvas.extractor.spring.annotations.ServiceDescription;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@RefreshScope
@Import({TramJdbcKafkaConfiguration.class,
        NomosnowRedisConfiguration.class,
        NomosnowMessageSourceConfiguration.class,
        LocationConfiguration.class})
@ServiceDescription(description="Manages EventRequests", capabilities = "EventRequest Management")
public class EventRequestServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(EventRequestServiceApplication.class, args);
    }

}
