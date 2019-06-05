package aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;


@Aspect
public class AnalysisAspect {

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
    public void checkIfThereWasCollision()
    {
        totalCollisions++;
    }

    @AfterReturning(pointcut = "execution(boolean SpaceWars.isEscPressed())", returning = "retVal")
    public void endTheGame(boolean retVal)
    {
        if (retVal) {
            time = System.nanoTime() - time;
            double timeInSec = (double) time / 1000000000;
            System.out.println("The game took: " + timeInSec);
            System.out.println("The total amount of shoots: " + totalShoots);
            System.out.println("The total amount of collisions: " + totalCollisions / 2);
        }

    }

}
