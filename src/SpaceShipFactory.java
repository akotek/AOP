

/**
 * The SpaceShipFactory class represents a class that assists creating the spaseship objects from the
 * input arguments in order to operate the SpaceWars game.
 */

public class SpaceShipFactory {

    /* Constant - represents the value that represents a Human ship */
    private static final String HUMAN_SHIP = "h";
    /* Constant - represents the value that represents a Drunkard ship */
    private static final String DRUNKARD_SHIP = "d";
    /* Constant - represents the value that represents a Runner ship */
    private static final String RUNNER_SHIP = "r";
    /* Constant - represents the value that represents an Aggressive ship */
    private static final String AGGRESSIVE_SHIP = "a";
    /* Constant - represents the value that represents a Basher ship */
    private static final String BASHER_SHIP = "b";
    /* Constant - represents the value that represents a Special ship */
    private static final String SPECIAL_SHIP = "s";

    /**
     * Returns an array of spaceships according to the input arguments.
     * @param args array of inputs.
     * @return array of spaceships.
     */
    public static SpaceShip[] createSpaceShips(String[] args) {
        SpaceShip [] spaceShipsArray = new SpaceShip[args.length]; // returned value
        int index = 0; // index to indicate the current free spot in the array

        for(String arg : args){
            switch (arg){
                case(HUMAN_SHIP): spaceShipsArray[index] = new HumanShip();
                    index++;
                    break;
                case(DRUNKARD_SHIP): spaceShipsArray[index] = new
                        DrunkardShip();
                    index++;
                    break;
                case(RUNNER_SHIP): spaceShipsArray[index] = new RunnerShip();
                    index++;
                    break;
                case(AGGRESSIVE_SHIP): spaceShipsArray[index] = new
                        AggressiveShip();
                    index++;
                    break;
                case(BASHER_SHIP): spaceShipsArray[index] = new BasherShip();
                    index++;
                    break;
                case(SPECIAL_SHIP): spaceShipsArray[index] = new SpecialShip();
                    index++;
                    break;
                default: break;
            }
        }
        return spaceShipsArray;
    }
}
