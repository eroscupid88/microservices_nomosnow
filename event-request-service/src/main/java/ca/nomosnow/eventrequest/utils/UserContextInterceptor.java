package ca.nomosnow.eventrequest.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;


// this class make sure correlation_id is inject into any HTTP base service requests
// that are executed from Rest Template
public class UserContextInterceptor implements ClientHttpRequestInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(UserContextInterceptor.class);
    @Override
    public ClientHttpResponse intercept(
            HttpRequest request,
            byte[] body,
            ClientHttpRequestExecution execution)
            throws IOException {

        HttpHeaders headers = request.getHeaders();
        UserContextHolder.getContext();
        headers.add(UserContext.CORRELATION_ID, UserContext.getCorrelationId());
        UserContextHolder.getContext();
        headers.add(UserContext.AUTH_TOKEN, UserContext.getAuthToken());

        return execution.execute(request, body);
    }
}