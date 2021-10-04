package ca.nomosnow.sportorganizationservice;

import ca.nomosnow.sportorganizationservice.utils.UserContextInterceptor;
import org.apache.kafka.common.KafkaFuture;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import javax.xml.transform.Source;
import java.util.Collections;
import java.util.List;

@SpringBootApplication
@EnableEurekaClient
@RefreshScope
public class SportOrganizationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SportOrganizationServiceApplication.class, args);
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
        interceptors.add(new UserContextInterceptor());
        template.setInterceptors( interceptors);

        return template;
    }





}
