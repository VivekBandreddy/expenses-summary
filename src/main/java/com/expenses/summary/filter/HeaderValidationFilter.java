package com.expenses.summary.filter;

import com.expenses.summary.configuration.properties.ExpensesConfig;
import com.expenses.summary.constants.ExpensesConstants;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
public class HeaderValidationFilter implements Filter {
    @Autowired
    private ExpensesConfig expensesConfig;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        List<String> mandatoryHeaders = expensesConfig.getMandatoryHeaders();
        log.info("mandatory headers : {} ", mandatoryHeaders);
        //List<String> missingHeaders =
        String s = mandatoryHeaders.parallelStream().filter(x -> validateHeaders(x, httpRequest.getHeader(x))).collect(Collectors.joining(", "));
        if (StringUtils.hasText(s)) {
            log.error("issue with mandatory headers : {} ", s);
            throw new BadRequestException(s);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private boolean validateHeaders(String key, String value) {

        if (Objects.isNull(value) || value.length() > 35) return true;

        switch (key) {
            case ExpensesConstants.X_USER_INFO:
                return isPatternMatches(ExpensesConstants.SAFE_STRING_PATTERN, value);
            case ExpensesConstants.X_USER_UUID:
                return isPatternMatches(ExpensesConstants.SAFE_STRING_ALPHA_NUMERICS, value);
            default:
                return false;
        }
    }

    private boolean isPatternMatches(String pattern, String value) {
        Pattern p = Pattern.compile(pattern);
        return !p.matcher(value).matches();
    }
}
