package com.expenses.summary.aop;

import com.expenses.summary.constants.ExpensesConstants;
import com.expenses.summary.entity.CustomerGroups;
import com.expenses.summary.repository.CustomerGroupsRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Configuration
@Aspect
@Slf4j
public class AOPBeforeExecution {

    @Autowired
    private CustomerGroupsRepository customerGroupsRepository;

    //private Logger log = LoggerFactory.getLogger(getClass());

    @Before("execution(* com.expenses.summary.controller.*.*(..))")
    public void addGroupIdToRequest() throws BadRequestException {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        log.info("AOP Before Method execution starts");
        if (Objects.nonNull(attributes)) {
            HttpServletRequest request = attributes.getRequest();
            String customerId = request.getHeader(ExpensesConstants.X_USER_INFO);
            log.info("customer id : {} ", customerId);
            Optional<CustomerGroups> customerGroups = customerGroupsRepository.findById(customerId);
            if (customerGroups.isEmpty()) {
                log.error("no groups found for customer : {} , throwing error ", customerId);
                //TODO replace with custom exception
                throw new BadRequestException();
            }
            List<String> groupIds = Arrays.asList(customerGroups.get().getGroupId().split(","));
            request.setAttribute(ExpensesConstants.X_USER_GROUPS, groupIds);
            //return request;
        }
        // return null;
    }
}
