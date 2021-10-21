package ca.nomosnow.sportorganizationeventcreatedepartmentservice.configuration.resttemplate;

import ca.nomosnow.sportorganizationeventcreatedepartmentservice.utils.UserContextInterceptor;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Configuration
public class NomosnowRestTemplateConfiguration {
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
