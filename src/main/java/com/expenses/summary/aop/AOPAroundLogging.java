package com.expenses.summary.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StopWatch;

@Aspect
@Configuration
//@Slf4j
public class AOPAroundLogging {

    private Logger log = LoggerFactory.getLogger(getClass());


    @Around("execution(* com.expenses.summary.controller.*.*(..))")
    public Object logExecutionTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object result = proceedingJoinPoint.proceed();
        stopWatch.stop();
        log.info("time taken to execute method : {} , total time in milli seconds : {}  ", proceedingJoinPoint.getSignature().getName(), stopWatch.getTotalTimeMillis());
        return result;
    }
}
