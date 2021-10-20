package ca.nomosnow.sportorganizationservice;

import ca.nomosnow.sportorganizationservice.configuration.messagesource.NomosnowMessageSourceConfiguration;
import ca.nomosnow.sportorganizationservice.utils.UserContextInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootApplication
@EnableEurekaClient
@RefreshScope
@Import({
//        SportEventCommandHandlersConfiguration.class,
        NomosnowMessageSourceConfiguration.class})
public class SportOrganizationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SportOrganizationServiceApplication.class, args);
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
