import annotations.Loggable;
import oop.ex2.GameGUI;
import oop.ex2.SpaceShipPhysics;
import java.awt.*;

/**
 * The BasherShip class represents a spaceship type in the SpaceWars game, that always attempts to
 * collide with other ships. It will always accelerate, and will constantly turn towards the closest ship.
 * If the distance between the nearest ship and the BasherShip are within a certain
 * range, the BasherShip will attempt to turn on its shields.
 */


public class BasherShip extends SpaceShip{

    /* Constant - default image of spaceship */
    private static final Image WITHOUT_SHIELD_IMAGE = GameGUI.ENEMY_SPACESHIP_IMAGE;
    /* Constant - image of spaceship with a shield */
    private static final Image WITH_SHIELD_IMAGE = GameGUI.ENEMY_SPACESHIP_IMAGE_SHIELD;
    /* Constant - within this distance from another spaceship the runner feels threatened */
    private static final double TURN_ON_SHIELD_DISTANCE = 0.19;

    private SpaceShipPhysics closestShipPhysics;

    /**
     * Initializes a new BasherShip in the SpaceWars game.
     */
    public BasherShip(){
        super(WITHOUT_SHIELD_IMAGE, WITH_SHIELD_IMAGE);
    }

    /**
     * Returns the fitting turn value for the ship according to it's type.
     * @param game the game object to which this ship belongs.
     * @return the fitting turn value for the ship according to it's type.
     */
    @Loggable
    public int getTurnValue(SpaceWars game){
        closestShipPhysics = game.getClosestShipTo(this).getPhysics(); // closest ship
        int turnValue = NO_TURN_VALUE;

        if(getPhysics().angleTo(closestShipPhysics) < 0)
            turnValue = RIGHT_TURN_VALUE;
        else if(getPhysics().angleTo(closestShipPhysics) > 0)
            turnValue = LEFT_TURN_VALUE;

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
     * Operates spaceship shield activation.
     * @param game the game object to which this ship belongs.
     */
    public void doShield(SpaceWars game){

        if(getPhysics().distanceFrom(closestShipPhysics) <= TURN_ON_SHIELD_DISTANCE)
            shieldOn();
        else
            shieldOff();
    }
}
