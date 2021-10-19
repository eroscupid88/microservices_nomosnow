package ca.nomosnow.sportorganizationeventcreatedepartmentservice.utils;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;


// this class make sure correlation_id is inject into any HTTP base service requests
// that are executed from Rest Template
public class UserContextInterceptor implements ClientHttpRequestInterceptor {
    @Override
    public ClientHttpResponse intercept(
            HttpRequest request,
            byte[] body,
            ClientHttpRequestExecution execution)
            throws IOException {

        HttpHeaders headers = request.getHeaders();
        headers.add(UserContext.CORRELATION_ID, UserContext.getCorrelationId());
        headers.add(UserContext.AUTH_TOKEN, UserContext.getAuthToken());

        return execution.execute(request, body);
    }
}