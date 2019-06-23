package aop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.HashMap;
import java.util.Map;

@Aspect
public class SingletonAspect {

    private static Logger logger = LogManager.getLogger("stdoutLogger");

    private Map<Class<?>, Object> singletons = new HashMap<>();

    @Pointcut("call(*.SpaceWars.new(..))" + " || call(*.SomeSingletonClass.new(..))")
    public void singletonsScope(){}

  	@Around("singletonsScope()")
  	public Object aroundSingletons(ProceedingJoinPoint jp) throws Throwable {

        // Singleton design pattern implementation using aspects:
        // Intercept instance creations and store in mapping,
        // if already exists: return it

        Class<?> singClass = jp.getSignature().getDeclaringType();
        logger.info("Creating singleton for class: {}", singClass);

        Object singObj = this.singletons.get(singClass);
        if (singObj == null) {
            logger.info("First time singleton creation, adding to map");
            singObj = jp.proceed();
            this.singletons.put(singClass, singObj);
            }
        logger.debug("Object: " + singObj);
        return singObj;
    }
}
