package com.zemoso.bookmanagement.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class PointCutDeclaration {

    private Logger logger = Logger.getLogger(getClass().getName());

    @Before("execution(* com.zemoso.bookmanagement.controller.*.*(..))")
    public void before(JoinPoint theJoinPoint) {
        // display method we are calling
        String theMethod = theJoinPoint.getSignature().toShortString();
        logger.info("Before advice: calling method: " + theMethod);

        Object[] args = theJoinPoint.getArgs();

        // display args
        for (Object tempArg : args) {
            logger.info("Before advice: argument: " + tempArg);
        }
    }

    // class within the given package
    @Before("within(com.zemoso.bookmanagement.controller.*)")
    public void within(JoinPoint joinPoint) {
        String theMethod = joinPoint.getSignature().toLongString();
        logger.info("Before advice: calling from within(com.zemoso.bookmanagement.controller.*): " + theMethod);
    }

    @Around("execution(* com.zemoso.bookmanagement.controller.*.*(..))")
    public Object around(ProceedingJoinPoint theJoinPoint) throws Throwable {
        // display method we are calling
        String theMethod = theJoinPoint.getSignature().toShortString();
        logger.info("Around advice: calling method: " + theMethod);
        Object[] args = theJoinPoint.getArgs();

        // display args
        for (Object tempArg : args) {
            logger.info("Around advice: argument: " + tempArg.getClass());
        }
        Object result = theJoinPoint.proceed(args);
        logger.info("Around advice: Returning " + result);
        return result;

    }

    @After("execution(* com.zemoso.bookmanagement.controller.*.*(..))")
    public void after(JoinPoint theJoinPoint) {
        // display method we are calling
        String theMethod = theJoinPoint.getSignature().toShortString();
        logger.info("After advice: calling method: " + theMethod);

        Object[] args = theJoinPoint.getArgs();

        // display args
        for (Object tempArg : args) {
            logger.info("After advice: argument: " + tempArg);
        }
    }

}
