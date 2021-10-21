package ca.nomosnow.sportorganizationeventcreatedepartmentservice.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class UserContextFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(UserContextFilter.class);


    /**
     *  doFilter method to retrived value of HTTPServlet header to UserContext which will be store in local thread
     * @param servletRequest HTTPServlet header request
     * @param servletResponse HTTPServlet header response
     * @param filterChain view into the invocation chain of a filtered request for a resource
     * @throws IOException exception
     * @throws ServletException servlet exception
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {


        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        UserContext.setCorrelationId(  httpServletRequest.getHeader(UserContext.CORRELATION_ID) );
        UserContext.setUserId(httpServletRequest.getHeader(UserContext.USER_ID));
        UserContext.setAuthToken(httpServletRequest.getHeader(UserContext.AUTH_TOKEN));
        UserContext.setOrgId(httpServletRequest.getHeader(UserContext.ORGANIZATION_ID));



        logger.debug("UserContextFilter Correlation id: {}", UserContextHolder.getContext().getCorrelationId());

        filterChain.doFilter(httpServletRequest, servletResponse);
    }

    /**
     * method Called by the web container to indicate to a filter that it is being placed into service.
     * @param filterConfig A filter configuration object used by a servlet container to pass information to a filter during initialization
     * @throws ServletException exception
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
                // todo
    }

    /**
     * Method Called by the web container to indicate to a filter that it is being taken out of service.
     */
    @Override
    public void destroy() {
                // todo
    }
}
