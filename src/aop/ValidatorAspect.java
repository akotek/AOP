package aop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.util.Arrays;

@Aspect
public class ValidatorAspect {

    private static Logger stdLogger = LogManager.getLogger("stdoutLogger");

    @Pointcut("execution(void SpaceWars.main(String[])) && args(args)" ) void onGameLaunch(String[] args) {}

    @Before(value="onGameLaunch(args)", argNames="args") public void beforeGameLaunch(String[] args) {
        stdLogger.info("Starting (Before) validation of {} params...", args.length);
    }

    @Around("onGameLaunch(args)") public Object aroundGameLaunch(String[] args, ProceedingJoinPoint jp) {

        // Validating given params before game launch,
        // If params are invalid - stop execution of the game and log the error

        stdLogger.info("Around gameLaunch, validating given params {}", Arrays.toString(args));

        // Validate total given args:
        if (args == null || args.length < 2){
            String errMsg = "Bad input args for game for args, must be 2 spaceships " + Arrays.toString(args);
            stdLogger.error(errMsg);
            throw new IllegalArgumentException(errMsg);
        }

        // Validate spaceships args:
        for (String arg: args) {
            if (arg.length() > 1){
                throw new IllegalArgumentException("SpaceShips args must be of size 1");
            }
        }

        // Proceed game if all is valid
        try {
            return jp.proceed();
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return null;
    }
}
