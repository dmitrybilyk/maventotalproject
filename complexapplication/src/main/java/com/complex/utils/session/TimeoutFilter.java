package com.complex.utils.session;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TimeoutFilter implements Filter {
//    private String startPage = "https://accounts.google.com/ServiceLogin?hl=en&continue=http://www.google.com.ua";
//    private String startPage = "https://accounts.google.com/ServiceLogin?hl=en&continue=http://www.google.com.ua";

    public void init(FilterConfig filterConfig) throws ServletException {}
    public void destroy() {}

    public void doFilter(ServletRequest request, ServletResponse response,
        FilterChain filterChain) throws IOException, ServletException {
        if ((request instanceof HttpServletRequest) &&
            (response instanceof HttpServletResponse)) {
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            if (!isValidSession(httpServletRequest)) {
                String startPage = httpServletRequest.getContextPath()+"?errorMessage=session is expired";

                String timeoutUrl = startPage;
                //redirects to the first page
                httpServletResponse.sendRedirect(timeoutUrl);
                return;
            }
            filterChain.doFilter(request, response);
        }
    }

    private boolean isValidSession(HttpServletRequest httpServletRequest) {
        return (httpServletRequest.getRequestedSessionId() != null) &&
               httpServletRequest.isRequestedSessionIdValid();
    }
}