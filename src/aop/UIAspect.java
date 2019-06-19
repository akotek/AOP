package aop;

import oop.ex2.BangPhysics;
import oop.ex2.GameGUI;
import oop.ex2.Physics;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.awt.*;
import java.util.ArrayList;

@Aspect
public class UIAspect {

    private BangPhysics test = new BangPhysics();

    private boolean displayBang = false;

    private boolean displayModeBang = false;

    private int counterBang = 0;
    
    private boolean displayTeleport = false;

    private boolean displayModeTeleport = false;

    private int counterTeleport = 0;

    private boolean firstTime = true;
    

    @AfterReturning(pointcut = "execution(boolean SpaceShip.isDead())", returning = "retVal")
    public void isBangAnnotationNeedToDisplay(boolean retVal)
    {

        displayBang = retVal;

    }

    @AfterReturning(pointcut = "execution(boolean SpaceShip.teleport())", returning = "retVal")
    public void isTeleportAnnotationNeedToDisplay(boolean retVal)
    {

        displayTeleport = retVal;
    }


    @Around("execution(void SpaceWars.drawAllObjects(..)) &&" +  "args(annotations, physics)")
    public void addBangAnnotation(ProceedingJoinPoint jp, ArrayList<Image> annotations, ArrayList<Physics> physics) throws Throwable
    {

        if (annotations == null){
            annotations = new ArrayList<Image>();
        }
        if (physics == null){
            physics = new ArrayList<Physics>();
        }

        if ((displayBang && !displayModeBang) || (displayModeBang && counterBang < 50))
        {
            annotations.add(GameGUI.BANG_IMAGE);
            physics.add(test);

            displayModeBang = true;

            counterBang++;
        }

        if(counterBang >= 50 || !displayModeBang){
            counterBang = 0;
            displayModeBang = false;
        }

        if((displayTeleport && !displayModeTeleport) || (displayModeTeleport && counterTeleport < 50))
        {
            annotations.add(GameGUI.TELEPORT_IMAGE);
            physics.add(test);

            displayModeTeleport = true;

            counterTeleport++;
        }

        if(counterTeleport >= 50 || !displayModeTeleport){
            counterTeleport = 0;
            displayModeTeleport = false;
        }

        jp.proceed(new Object[] {annotations, physics});


    }








}
