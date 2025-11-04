package com.expenses.summary.filter;

import com.expenses.summary.constants.ExpensesConstants;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class RequestUUIDLoggingFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            log.info("inside RequestUUIDLoggingFilter ");
            HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
            String uuid = httpRequest.getHeader(ExpensesConstants.X_USER_UUID);
            MDC.put(ExpensesConstants.UUID, uuid); // Put UUID into MDC
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            log.info("post execution RequestUUIDLoggingFilter");
            MDC.remove(ExpensesConstants.UUID);
        }
    }
}
