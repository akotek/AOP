package aop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AuditAspect {

    private static Logger logger = LogManager.getLogger("auditLogger");

    @Pointcut("execution(void Action.do*(..)) " + "|| execution(* SpaceShip.get*(..))")
    public void auditEvents(){}

    @After("auditEvents()")
    public void logAuditEvents(JoinPoint jp){
        logger.debug("{} Event has occurred by {}",
                jp.getSignature().getName(), jp.getTarget().getClass());
    }
}