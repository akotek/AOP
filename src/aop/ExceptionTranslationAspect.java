package aop;

import exceptions.EndGameException;
import exceptions.KeyboardException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class ExceptionTranslationAspect {

    private static Logger logger = LogManager.getLogger("exceptionLogger");

    @Around("execution(* SpaceWars.run())")
    public Object translateException(ProceedingJoinPoint pjp) throws Throwable {

        // Translates 3rd party exception
        // To program exception

        try {
            return pjp.proceed();
        } catch (KeyboardException e) {
            logger.error("An {} exception has thrown, converting to application {} exception",
                    e.getClass().getName(), EndGameException.class.getName());
            throw new EndGameException(e);
        }
    }
}
