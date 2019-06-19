import oop.ex2.*;

import java.awt.*;

/**
 * The HumanShip class represents a spaceship type in the SpaceWars game, that is operated by a human
 * (computer user).
 */


public class HumanShip extends SpaceShip{

    /* Constant - default image of spaceship */
    private static final Image WITHOUT_SHIELD_IMAGE = GameGUI.SPACESHIP_IMAGE;
    /* Constant - image of spaceship with a shield */
    private static final Image WITH_SHIELD_IMAGE = GameGUI.SPACESHIP_IMAGE_SHIELD;

    /**
     * Initializes a new HumanShip in the SpaceWars game.
     */
    public HumanShip(){
        super(WITHOUT_SHIELD_IMAGE, WITH_SHIELD_IMAGE);
    }

    /**
     * Operates spaceship teleportation.
     * @param game the game object to which this ship belongs.
     */
    public boolean doTeleport(SpaceWars game){
        if(game.getGUI().isTeleportPressed())
            return teleport();
        return false;
    }

    /**
     * Returns the fitting acceleration value for the ship according to it's type.
     * @param game the game object to which this ship belongs.
     * @return the fitting acceleration value (true/false) for the ship according to it's type.
     */
    public boolean getAccelerationValue(SpaceWars game){
        return game.getGUI().isUpPressed();
    }

    /**
     * Returns the fitting turn value for the ship according to it's type.
     * @param game the game object to which this ship belongs.
     * @return the fitting turn value for the ship according to it's type.
     */
    public int getTurnValue(SpaceWars game){
        int turnValue = NO_TURN_VALUE; // turn value if user pressed both keys
        if(game.getGUI().isLeftPressed() && !game.getGUI().isRightPressed()){
            turnValue = LEFT_TURN_VALUE;
        }
        else if(game.getGUI().isRightPressed() && !game.getGUI().isLeftPressed()){
            turnValue = RIGHT_TURN_VALUE;
        }
        return turnValue;
    }

    /**
     * Operates spaceship shield activation.
     * @param game the game object to which this ship belongs.
     */
    public void doShield(SpaceWars game){
        if(game.getGUI().isShieldsPressed())
            shieldOn();
        else
            shieldOff();
    }

    /**
     * Operates spaceship firing.
     * @param game the game object to which this ship belongs.
     */
    public void doShoot(SpaceWars game){
        if(game.getGUI().isShotPressed())
            fire(game);
    }
}
