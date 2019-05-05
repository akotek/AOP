package aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LoggingAspect {

//    private Logger logger =
    public LoggingAspect() {
        initLogger();
    }

    private void initLogger() {

        // logging config:

    }
    //    @Pointcut("execution(public * HumanShip.do*(..))")
//    public void logOnPattern(){}

    @Pointcut("@annotation(annotations.Loggable)")
    public void loggableMethods(){}

    @Before("loggableMethods()")
    public void logMethod(JoinPoint jp){
        System.out.println("testing on method " + jp.getSignature().getName());
    }

}
