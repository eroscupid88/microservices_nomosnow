package ca.nomosnow.sportorganizationeventcreatedepartmentservice;

import ca.nomosnow.sportorganizationeventcreatedepartmentservice.configuration.location.LocationConfiguration;
import ca.nomosnow.sportorganizationeventcreatedepartmentservice.configuration.messagesource.NomosnowMessageSourceConfiguration;
import ca.nomosnow.sportorganizationeventcreatedepartmentservice.configuration.redis.NomosnowRedisConfiguration;
import ca.nomosnow.sportorganizationeventcreatedepartmentservice.configuration.resttemplate.NomosnowRestTemplateConfiguration;
import ca.nomosnow.sportorganizationeventcreatedepartmentservice.utils.UserContextInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootApplication
@EnableEurekaClient
@RefreshScope
@Import({LocationConfiguration.class,
        NomosnowMessageSourceConfiguration.class,
        NomosnowRestTemplateConfiguration.class,
        NomosnowRedisConfiguration.class})
public class SportOrganizationEventCreateDepartmentServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SportOrganizationEventCreateDepartmentServiceApplication.class, args);
    }

    private static final Logger logger = LoggerFactory.getLogger(SportOrganizationEventCreateDepartmentServiceApplication.class);






}
