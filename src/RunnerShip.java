import oop.ex2.GameGUI;
import oop.ex2.SpaceShipPhysics;
import java.awt.*;
import java.lang.*;

/**
 * The RunnerShip class represents a spaceship type in the SpaceWars game, that always attempts to
 * run away from the fight. It will always accelerate, and will constantly turn away from the closest ship.
 * If the distance and angle between the nearest ship and the RunnerShip are within a certain
 * range, the RunnerShip feels threatened and will attempt to teleport.
 */

public class RunnerShip extends SpaceShip{

    /* Constant - default image of spaceship */
    private static final Image WITHOUT_SHIELD_IMAGE = GameGUI.ENEMY_SPACESHIP_IMAGE;
    /* Constant - image of spaceship with a shield */
    private static final Image WITH_SHIELD_IMAGE = GameGUI.ENEMY_SPACESHIP_IMAGE_SHIELD;
    /* Constant - within this distance from another spaceship the runner feels threatened */
    private static final double THREATENED_DISTANCE = 0.25;
    /* Constant - within this distance from another spaceship the runner feels threatened */
    private static final double THREATENED_ANGLE = 0.23;

    /* The current SpaceShipPhysics object of the closest ship to this ship */
    private SpaceShipPhysics closestShipPhysics;

    /**
     * Initializes a new RunnerShip in the SpaceWars game.
     */
    public RunnerShip(){
        /* the runner always accelerates, thus using the second constructor to set the acceleration value.*/
        super(WITHOUT_SHIELD_IMAGE , WITH_SHIELD_IMAGE);
    }

    /**
     * Operates spaceship teleportation.
     * @param game the game object to which this ship belongs.
     */
    public boolean doTeleport(SpaceWars game){
        /* closest ship SpaceShipPhysics object */
        closestShipPhysics = game.getClosestShipTo(this).getPhysics();

        if(Math.abs(closestShipPhysics.angleTo(getPhysics())) < THREATENED_ANGLE &&
                getPhysics().distanceFrom(closestShipPhysics) < THREATENED_DISTANCE){
            teleport(); // teleports the ship if it feels threatened
            closestShipPhysics = game.getClosestShipTo(this).getPhysics(); // update closest ship
            return true;
        }
        return false;
    }

    /**
     * Returns the fitting turn value for the ship according to it's type.
     * @param game the game object to which this ship belongs.
     * @return the fitting turn value for the ship according to it's type.
     */
    public int getTurnValue(SpaceWars game){
        int turnValue = NO_TURN_VALUE;
        if(getPhysics().angleTo(closestShipPhysics) < 0){
            turnValue = LEFT_TURN_VALUE;
        }
        else if(getPhysics().angleTo(closestShipPhysics) > 0){
            turnValue = RIGHT_TURN_VALUE;
        }
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

}
