package aop;

import org.aspectj.lang.JoinPoint;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import exceptions.UnhandledException;

@Aspect
public class ExceptionHandlerAspect {

    private static Logger logger = LogManager.getLogger("exceptionLogger");

    @Pointcut("execution(void SpaceWars.*(..))")
    public void UnhandledExceptionScope() {}

    @AfterThrowing(value="UnhandledExceptionScope()", throwing="t")
    public void handleException(JoinPoint jp, Throwable t) {

        // Intercept unhandled exceptions in application,
        // Log it and throw generic UnhandledException(msg)

        logger.error("Error in service {} with message {}", jp.getSignature(), t.getMessage());
        throw new UnhandledException(t.getMessage());
    }
}