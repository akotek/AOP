package aop;

import exceptions.EndGameException;
import exceptions.KeyboardException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class ExceptionTranslationAspect {

    @Around("execution(* SpaceWars.run())")
    public Object translateException(ProceedingJoinPoint pjp) throws Throwable {

        // Translates 3rd party exception
        // To program exception

        try {
            return pjp.proceed();
        } catch (KeyboardException e) {
            throw new EndGameException(e);
        }
    }
}
