import oop.ex2.SpaceShipPhysics;
import java.lang.*;

/**
 * The AggressiveShip class represents a spaceship type in the SpaceWars game, that always attempts to
 * pursue other ships and tries to fire at them. It will always accelerate, and will constantly turn
 * towards the nearest ship. If its the angle to the nearest ship and the is less than a certain
 * range, the AggressiveShip will try to fire.
 */


public class AggressiveShip extends BasherShip{

    /* Constant - within this angle from another spaceship the aggressive ship will try to fire */
    private static final double MIN_ANGLE_TO_FIRE = 0.21;

    /**
     * Operates spaceship firing.
     * @param game the game object to which this ship belongs.
     */
    public void doShoot(SpaceWars game){
        SpaceShipPhysics closestShipPhysics = game.getClosestShipTo(this).getPhysics();
        if(Math.abs(getPhysics().angleTo(closestShipPhysics)) < MIN_ANGLE_TO_FIRE){
            fire(game);
        }
    }

    /**
     * Operates spaceship shield activation.
     * @param game the game object to which this ship belongs.
     */
    public void doShield(SpaceWars game){}
}
