import oop.ex2.SpaceShipPhysics;
import javax.swing.*;
import java.awt.*;
import java.lang.*;

/**
 * The SpecialShip class represents a spaceship type in the SpaceWars game, that attempts to defend the
 * closest ship to it by trying to circle it and fire around it all the time. grantedly, not all ships are
 * cooperative with that plan.
 */

public class SpecialShip extends SpaceShip{

    /* ship's current turn value */
    private int turnValue;
    /* counter for amount of rounds the ship should move in some direction */
    private int directionCounter;
    /* current maximal value of rounds that the ship should move in some direction */
    private int currentMaxValue;
    /* Constant - default image of spaceship */
    private static final Image WITHOUT_SHIELD_IMAGE = new
            ImageIcon(SpecialShip.class.getResource("bubblesNoShield.gif"), "").getImage();
    private static final Image WITH_SHIELD_IMAGE = new
            ImageIcon(SpecialShip.class.getResource("bubblesNoShield.gif"), "").getImage();

    /**
     * Initializes a new SpecialShip in the SpaceWars game.
     */
    public SpecialShip(){
        super(WITHOUT_SHIELD_IMAGE, WITH_SHIELD_IMAGE);
        directionCounter = 0; // start value for amount of times the ship will move in a set direction
    }

    /**
     * Returns the fitting turn value for the ship according to it's type.
     * @param game the game object to which this ship belongs.
     * @return the fitting turn value for the ship according to it's type.
     */
    public int getTurnValue(SpaceWars game){
        SpaceShipPhysics closestShipPhysics = game.getClosestShipTo(this).getPhysics(); // closest ship
        if(directionCounter == 0)
            /* the currentMaxValue will be the amount of radians between the ship and its nearest ship */
            currentMaxValue = (int)getPhysics().angleTo(closestShipPhysics);

        changeTurnValue(); // change turnValue towards the ship

        if (directionCounter < Math.abs(currentMaxValue))
            directionCounter++;
        else
            directionCounter = 0;

        return turnValue;
    }

    /**
     * Returns the fitting acceleration value for the ship according to it's type.
     * @param game the game object to which this ship belongs.
     * @return the fitting acceleration value (true/false) for the ship according to it's type.
     */
    public boolean getAccelerationValue(SpaceWars game){
        return true;
    }

    /**
     * Operates spaceship firing.
     * @param game the game object to which this ship belongs.
     */
    public void doShoot(SpaceWars game){
        fire(game);
    }

    /*
     * change the turnValue so that the ship will always be directed towards the nearest ship.
     */
    private void changeTurnValue(){
        turnValue = NO_TURN_VALUE;
        if(currentMaxValue < 0)
            turnValue = RIGHT_TURN_VALUE;
        else if (currentMaxValue > 0)
            turnValue = LEFT_TURN_VALUE;
    }
}
