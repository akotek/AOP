import oop.ex2.GameGUI;
import java.awt.*;
import java.util.Random;

/**
 * The DrunkardShip class represents a spaceship type in the SpaceWars game, that acts completely randomly.
 * You can't quite know what to expect with this spaceship.
 */


public class DrunkardShip extends SpaceShip implements Action{


    /* counter for amount of rounds the ship should move in some direction */
    private int directionCounter;

    /* ship's current turn value */
    private int turnValue;

    /* random object to assist getting random numbers */
    private Random random = new Random();

    /* Constant - array of possible directions for ship */
    private static final int [] TURN_VALUES = {NO_TURN_VALUE, RIGHT_TURN_VALUE, LEFT_TURN_VALUE};
    /* Constant - maximal range for random numbers */
    private static final int MAX_RANDOM_RANGE = 100;
    /* Constant - range for random numbers */
    private int randomRange;
    /* Constant - default image of spaceship */
    private static final Image WITHOUT_SHIELD_IMAGE = GameGUI.ENEMY_SPACESHIP_IMAGE;
    /* Constant - image of spaceship with a shield */
    private static final Image WITH_SHIELD_IMAGE = GameGUI.ENEMY_SPACESHIP_IMAGE_SHIELD;

    /**
     * Initializes a new DrunkardShip in the SpaceWars game.
     */
    public DrunkardShip(){
        super(WITHOUT_SHIELD_IMAGE, WITH_SHIELD_IMAGE);
        randomRange = random.nextInt(MAX_RANDOM_RANGE); // get a starting random range
        directionCounter = 0; // start value for amount of times the ship will move in a random direction
        turnValue = TURN_VALUES[random.nextInt(TURN_VALUES.length)]; // get an initial random turn value
    }

    /**
     * Operates spaceship teleportation.
     * @param game the game object to which this ship belongs.
     */
    public boolean doTeleport(SpaceWars game){
//        if(getRandomBoolean())
//            return teleport();
        return false;
    }

    /**
     * Returns the fitting turn value for the ship according to it's type.
     * @param game the game object to which this ship belongs.
     * @return the fitting turn value for the ship according to it's type.
     */
    public int getTurnValue(SpaceWars game){
        if(directionCounter <= randomRange)
            directionCounter++;
        else {
            randomRange = random.nextInt(MAX_RANDOM_RANGE);
            turnValue = TURN_VALUES[random.nextInt(TURN_VALUES.length)];
            directionCounter = 0;
        }
        return turnValue;
    }

    /**
     * Returns the fitting acceleration value for the ship according to it's type.
     * @param game the game object to which this ship belongs.
     * @return the fitting acceleration value (true/false) for the ship according to it's type.
     */
    public boolean getAccelerationValue(SpaceWars game){
        return getRandomBoolean();
    }

    /**
     * Operates spaceship shield activation.
     * @param game the game object to which this ship belongs.
     */
    public void doShield(SpaceWars game){
        if(getRandomBoolean())
            shieldOn();
        else
            shieldOff();
    }

    /**
     * Operates spaceship firing.
     * @param game the game object to which this ship belongs.
     */
    public void doShoot(SpaceWars game){
        if(getRandomBoolean())
            fire(game);
    }

    /*
     * Returns a random boolean value.
     */
    private boolean getRandomBoolean(){
        int randomNum = random.nextInt(MAX_RANDOM_RANGE);
        return randomNum <= randomRange;
    }
}
