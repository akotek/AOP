import java.awt.Image;

import oop.ex2.*;

/**
 * The SpaceShip class is an abstract class that represents a spaceship in the SpaceWars game.
 * Every Spaceship that participates in the game will have the specified methods that the general
 * Spaceship has.
 */
public abstract class SpaceShip implements Action{

    /* The SpaceShipPhysics object of the ship. */
    private SpaceShipPhysics physics;

    /* The maximal energy level of the ship. */
    private int maximalEnergyLevel;

    /* The current energy level of the ship. */
    private int currentEnergyLevel;

    /* The health level of the ship. */
    private int healthLevel;

    /* The counter for how many rounds have passed since a shot has been fired. */
    private int roundsShotCounter;

    /* Represents if the ship fired a shot or not. */
    private boolean didFireShot;

    /* The ship's Image */
    private Image currentImage;

    /* The ship's Image with a shield */
    private final Image WITH_SHIELD_IMAGE;

    /* The ship's Image without a shield */
    private final Image WITHOUT_SHIELD_IMAGE;

    /* Represents if the ship has its shields up (true if it does). */
    private boolean shieldUp;

    /** constant value in order for ship to turn left  */
    protected static final int LEFT_TURN_VALUE = 1;

    /** constant value in order for ship to turn right  */
    protected static final int RIGHT_TURN_VALUE = -1;

    /** constant value in order for ship not to turn at all  */
    protected static final int NO_TURN_VALUE = 0;

    /* Constant - default maximal energy level value */
    private static final int DEFAULT_MAX_ENERGY_LEVEL = 210;
    /* Constant - default current energy level value */
    private static final int DEFAULT_CURRENT_ENERGY_LEVEL = 190;
    /* Constant - default maximal health level value */
    private static final int MAX_HEALTH_LEVEL = 22;
    /* Constant - firing shot energy cost */
    private static final int FIRING_SHOT_COST = 19;
    /* Constant - teleporting energy cost */
    private static final int TELEPORTING_COST = 140;
    /* Constant - getting hit energy cost */
    private static final int GETTING_HIT_ENERGY_COST = 10;
    /* Constant - getting hit health cost */
    private static final int GETTING_HIT_HEALTH_COST = 1;
    /* Constant - turning on shields energy cost */
    private static final int SHIELDS_COST = 3;
    /* Constant - bashing energy addition value */
    private static final int BASHING_VALUE = 18;
    /* Constant - amount of rounds to wait after firing a shot, until it is possible again */
    private static final int FIRING_SHOT_ROUNDS_HALT = 7;

    /**
     * Initializes a new SpaceShip in the SpaceWars game with the given withoutShieldImage as its
     * image without a shield and the given withShieldImage image with a shield.
     * @param withoutShieldImage the ship's Image without a shield.
     * @param withShieldImage the ship's Image with a shield.
     */
    public SpaceShip(Image withoutShieldImage, Image withShieldImage){
        this.WITHOUT_SHIELD_IMAGE = withoutShieldImage;
        this.WITH_SHIELD_IMAGE = withShieldImage;
        this.currentImage = WITHOUT_SHIELD_IMAGE;
        this.physics = new SpaceShipPhysics(); // new SpaceShipPhysics object, initializes location randomly
        this.healthLevel = MAX_HEALTH_LEVEL; // initial health level-set to maximum
        this.maximalEnergyLevel = DEFAULT_MAX_ENERGY_LEVEL; // initial maximal energy-set to maximum
        this.currentEnergyLevel = DEFAULT_CURRENT_ENERGY_LEVEL; // initial current energy-set to default
        this.shieldUp = false; // is shield up-set initially to false
        this.roundsShotCounter = 0; // counter of rounds since a shot has been fired-initialized to zero
        this.didFireShot = false; // boolean indicator if the spaceship fired a shot.
    }

    /**
     * Does the actions of this ship for this round.
     * This is called once per round by the SpaceWars game driver.
     *
     * @param game the game object to which this ship belongs.
     */
    public void doAction(SpaceWars game){
        doTeleport(game);
        getPhysics().move(getAccelerationValue(game), getTurnValue(game));
        doShield(game);
        shotCheck();
        doShoot(game);
        regenerate();
    }

    /**
     * Returns the fitting turn value for the ship according to it's type.
     * @param game the game object to which this ship belongs.
     * @return the fitting turn value for the ship according to it's type.
     */
    public abstract int getTurnValue(SpaceWars game);

    /**
     * Returns the fitting acceleration value for the ship according to it's type.
     * @param game the game object to which this ship belongs.
     * @return the fitting acceleration value (true/false) for the ship according to it's type.
     */
    public abstract boolean getAccelerationValue(SpaceWars game);

    /**
     * Operates spaceship teleportation.
     * @param game the game object to which this ship belongs.
     */
    public void doTeleport(SpaceWars game){}

    /**
     * Operates spaceship shield activation.
     * @param game the game object to which this ship belongs.
     */
    public void doShield(SpaceWars game){}

    /**
     * Operates spaceship firing.
     * @param game the game object to which this ship belongs.
     */
    public void doShoot(SpaceWars game){}


    /**
     * This method is called every time a collision with this ship occurs
     */
    public void collidedWithAnotherShip(){
        if(!shieldUp){
            updateAfterHit();
        }
        else{
            maximalEnergyLevel += BASHING_VALUE; // update maximal energy if shield was up
            currentEnergyLevel += BASHING_VALUE; // update current energy if shield was up
        }
    }

    /**
     * This method is called whenever a ship has died. It resets the ship's
     * attributes, and starts it at a new random position.
     */
    public void reset(){
        this.currentImage = WITHOUT_SHIELD_IMAGE;
        this.physics = new SpaceShipPhysics();
        this.healthLevel = MAX_HEALTH_LEVEL;
        this.maximalEnergyLevel = DEFAULT_MAX_ENERGY_LEVEL;
        this.currentEnergyLevel = DEFAULT_CURRENT_ENERGY_LEVEL;
        this.shieldUp = false;
        this.roundsShotCounter = 0;
        this.didFireShot = false;
    }

    /**
     * Checks if this ship is dead.
     *
     * @return true if the ship is dead. false otherwise.
     */
    public boolean isDead() {
        return healthLevel == 0;
    }

    /**
     * Gets the physics object that controls this ship.
     *
     * @return the physics object that controls the ship.
     */
    public SpaceShipPhysics getPhysics(){
        return this.physics;
    }

    /**
     * This method is called by the SpaceWars game object when ever this ship
     * gets hit by a shot.
     */
    public void gotHit(){
        if(!shieldUp){
            updateAfterHit();
        }
    }

    /*
    Updates the ship's status if it got hit (collision or shot at). health level and maximal energy
    level are reduced by constants, while if the maximal energy level is lower than the constant, it'll
    be zeroed out. In addition, if the current energy level exceeds the maximal energy level
    (after deduction), it's value will be reset to the maximal energy's value.
     */
    private void updateAfterHit(){
        healthLevel -= GETTING_HIT_HEALTH_COST;
        if(!isThereEnoughEnergy(maximalEnergyLevel, GETTING_HIT_ENERGY_COST))
            maximalEnergyLevel = 0;
        else
            maximalEnergyLevel -= GETTING_HIT_ENERGY_COST;
        if(currentEnergyLevel > maximalEnergyLevel)
            currentEnergyLevel = maximalEnergyLevel;
    }

    /**
     * Gets the image of this ship. This method should return the image of the
     * ship with or without the shield. This will be displayed on the GUI at
     * the end of the round.
     *
     * @return the image of this ship.
     */
    public Image getImage(){
        return this.currentImage;
    }

    /**
     * Attempts to fire a shot.
     *
     * @param game the game object.
     */
    public void fire(SpaceWars game) {
        if(isThereEnoughEnergy(currentEnergyLevel, FIRING_SHOT_COST) && !didFireShot){
            game.addShot(this.getPhysics());
            currentEnergyLevel -= FIRING_SHOT_COST;
            didFireShot = true; // set shot indicator to true in order to count the number of passed rounds
            roundsShotCounter++; // count the current round
        }
    }

    /**
     * Attempts to turn on the shield.
     */
    public void shieldOn(){
        if(isThereEnoughEnergy(currentEnergyLevel, SHIELDS_COST)) {
            shieldUp = true;
            currentEnergyLevel -= SHIELDS_COST;
            currentImage = WITH_SHIELD_IMAGE;
        }
        else {
            shieldUp = false;
            currentImage = WITHOUT_SHIELD_IMAGE;
        }
    }

    /**
     * Turns off the shield.
     */
    protected void shieldOff() {
        shieldUp = false;
        currentImage = WITHOUT_SHIELD_IMAGE;
    }

    /**
     * Attempts to teleport.
     */
    public void teleport() {
        if(isThereEnoughEnergy(currentEnergyLevel, TELEPORTING_COST)) {
            this.physics = new SpaceShipPhysics(); // initialize a new SpaceShipPhysics object
            currentEnergyLevel -= TELEPORTING_COST;
        }
    }

    /*
     * Returns true if there's enough energy (in the given energy parameter) to deduct a given cost
     * amount of energy, false otherwise.
     * @param energy The energy amount to measure.
     * @param cost The cost of the operation in energy units.
     * @return true if there's enough energy considering the cost, false otherwise.
     */
    private boolean isThereEnoughEnergy(int energy, int cost){
        return energy >= cost;
    }

    /*
     * Updates the shot firing status of the spaceship.
     */
    private void shotCheck(){
        if(didFireShot){
            roundsShotCounter++; // if a shot was fired update the shot counter.
            if(roundsShotCounter > FIRING_SHOT_ROUNDS_HALT){
                didFireShot = false;
                roundsShotCounter = 0;
            }
        }
    }

    /*
     * Regenerates the spaceship.
     */
    private void regenerate(){
        if(currentEnergyLevel < maximalEnergyLevel)
            currentEnergyLevel++;
    }
}
