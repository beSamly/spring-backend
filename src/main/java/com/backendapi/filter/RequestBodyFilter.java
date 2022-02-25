package com.backendapi.filter;

import com.backendapi.appconfig.CachedBodyHttpServletRequest;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class RequestBodyFilter implements Filter {
    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
            throws IOException, ServletException {
        try {
            CachedBodyHttpServletRequest wrapper = new CachedBodyHttpServletRequest((HttpServletRequest) request);
            wrapper.setAttribute("requestBody", wrapper.getRequestBody());
            chain.doFilter(wrapper, response);
        } catch (Exception e) {
            chain.doFilter(request, response);
        }
    }
}
