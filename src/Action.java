/**
 * The Action interface represents actions that a spaceship type in the SpaceWars game can perform.
 */
public interface Action {

    /**
     * Operates spaceship teleportation.
     * @param game the game object to which this ship belongs.
     */
    boolean doTeleport(SpaceWars game);

    /**
     * Operates spaceship shield activation.
     * @param game the game object to which this ship belongs.
     */
    void doShield(SpaceWars game);

    /**
     * Operates spaceship firing.
     * @param game the game object to which this ship belongs.
     */
    void doShoot(SpaceWars game);
}
