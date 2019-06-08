package aop;


import annotations.LogType;
import annotations.Loggable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

@Aspect
public class GameLoggingAspect {

    private static Logger gameLogger = LogManager.getLogger("gameLogger");

    private long time;
    private int totalShoots;
    private int totalCollisions;

    @Before("execution(void SpaceWars.run())")
    public void startTheGame()
    {
        time = System.nanoTime();
    }

    @After("execution(void SpaceWars.addShot(..))")
    public void spaceShipFired()
    {
        totalShoots++;
    }

    @After("execution(void SpaceShip.collidedWithAnotherShip())")
    public void checkIfThereWasCollision() { totalCollisions++; }

    @AfterReturning(pointcut = "execution(boolean SpaceWars.isEscPressed())", returning = "retVal")
    public void endTheGame(boolean retVal) {
        if (retVal) {
            time = System.nanoTime() - time;
            double timeInSec = (double) time / 1000000000;
            gameLogger.info("The game took: {} seconds", Math.round(timeInSec));
            gameLogger.info("The total amount of shoots: {}", totalShoots);
            gameLogger.info("The total amount of collisions: {}", totalCollisions / 2);
        }
    }

    @Pointcut("@annotation(annotations.Loggable)")
    public void loggableMethods(){}

    @After("loggableMethods()")
    public void logMethod(JoinPoint jp){

        // Determine which logging type was defined
        // Using java's reflections:

        MethodSignature signature = (MethodSignature) jp.getSignature();
        Method method = signature.getMethod();
        Loggable anno = method.getAnnotation(Loggable.class);

        String msg = String.format("Event of %s has occurred", jp.getSignature().getName());
        if (anno.value() == LogType.INFO){
            gameLogger.info(msg);
        } else if (anno.value() == LogType.DEBUG){
            gameLogger.debug(msg);
        } else {
            gameLogger.error(msg);
        }
    }
}
