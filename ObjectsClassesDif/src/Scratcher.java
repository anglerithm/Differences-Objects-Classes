import java.util.Random;
import java.util.Scanner;

public class Scratcher 
{
    private int odds = 90; // win odds (%)
    private int oddsVal = 6; // affects odds when increasing or decreasing stakes
    private int currReward = 5; // gem currency (only valuable in game :))
    private int gemBank = 0; // total gems acquired through gameplay
    private int multiplier = 1; // affected by increasing or decreasing stakes
    private boolean gameState = false; // will be set to true with startGame()

    // CONSTRUCTORS

    /*
     * basic constructor with no game presets
     */
    public Scratcher() {}

    /*
     * constructor with a few options
     */
    public Scratcher(boolean resetFile)
    {
        if (resetFile)
        {
            odds = 90;
            currReward = 5;
            gemBank = 0;
            multiplier = 1;
            gameState = false;
        }
    }

    // ACCESSORS

    /*
     * gets the current odds of the game
     * @return the odds
     */
    public int getOdds()
    {
        return odds;
    }

    /*
     * gets the current reward val of the game
     * @return the reward val
     */
    public int getReward()
    {
        return currReward;
    }

    /*
     * gets the current multiplier val of the game
     * @return multiplier val
     */
    public int getMultiplier()
    {
        return multiplier;
    }

    /*
     * easily accessable gemBank val
     */
    public int getGems()
    {
        return gemBank;
    }

    /*
     * gets the current game state
     * @return the game state
     */
    public boolean getGameState()
    {
        return gameState;
    }

    // MUTATORS

    /*
     * provides message to player if their odds don't meet min or max
     */
    public void minMaxMessage()
    {
        while (gameState)
        {
            if (odds == 90)
            {
                System.out.println("maximum odds: 90%");
                System.out.println("your odds: " + odds + "%");
                return;
            }
            else if (odds == 6)
            {
                System.out.println("minimum odds: 6%");
                System.out.println("your odds: " + odds + "%");
                return;
            }
        }
    }

    /*
     * provides information for gameplay
     */
    public void infoMessage()
    {
        System.out.println("OPTIONS:");
        System.out.println("I - INCREASE STAKES");
        System.out.println("J - DECREASE STAKES");
        System.out.println("K - SHOW CURRENT STATS");
        System.out.println("L - TOTAL GEMS");
        System.out.println("E - SCRATCH!");

        System.out.println("DEBUG:");
        System.out.println("G - CURRENT GAME STATE");
    }

    /* 
     * increases the stakes by decreasing the odds
    */
    public void stakesPlus()
    {
        if (odds > 6) // val check for odds
        {
            odds -= oddsVal; // decrease odds
            multiplier += 1; // increase multiplier
            currReward = multiplier * currReward; // increase reward
        }
        else
        {
            minMaxMessage();
        }
    }

    /*
     * decreases the stakes by increasing the odds
     */
    public void stakesDec()
    {
        if (odds < 90) // val check for odds
        {
            odds += oddsVal; // increase odds
            // reset currReward to previous
            currReward = currReward/multiplier;
            multiplier -= 1; //decrease multiplier
        }
        else
        {
            minMaxMessage();
        }
    }

    /*
     * starts game loop
     */
    public void startGame() 
    {
        gameState = true;

        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();

        System.out.println("WELCOME TO GEMTOPIA!");
        minMaxMessage(); // based on odds
        infoMessage();   // print the controls

        while (gameState) {
            System.out.print("\nUSER_ENTRY: ");
            String input = scanner.nextLine().trim().toUpperCase();

            switch (input) {
                case "I":
                    if (odds == 6) 
                    {
                        System.out.println("MINIMUM ODDS: 6%");
                        break;
                    }
                    stakesPlus();
                    System.out.println("ODDS: " + odds + "%");
                    break;

                case "J":
                    if (odds == 90)
                    {
                        System.out.println("MAXIMUM ODDS: 90%");
                        break;
                    }
                    stakesDec();
                    System.out.println("ODDS: " + odds + "%");
                    break;

                case "K":
                    System.out.println("STATS:");
                    System.out.println("- ODDS: " + odds + "%");
                    System.out.println("- MULTIPLIER: x" + multiplier);
                    System.out.println("- GEMPOOL: " + currReward + " GEMS");
                    System.out.println("YOUR GEMS: " + gemBank + " GEMS");
                    break;

                case "E":
                    System.out.println("Scratching...");
                    int chance = rand.nextInt(100) + 1; // [1, 100]
                    if (chance <= odds) {
                        System.out.println("JACKPOT!");
                        System.out.println("YOUR REWARD: +" + currReward + " GEMS!");
                        gemBank += currReward;
                        System.out.println("TOTAL GEMS: " + gemBank);
                    } else {
                        System.out.println("YOU LOSE  -" + currReward + "GEMS :(");
                        if (gemBank - currReward < 0) // ensure gemBank isn't a negative val
                        {
                            gemBank = 0;
                        }
                        else
                        {
                            gemBank -= currReward;
                        }
                    }
                    break;

                case "G":
                    System.out.println("CURRENT GAME STATE:" + gameState);
                    break;

                case "X":
                    System.out.println("THANKS FOR PLAYING!");
                    gameState = false;
                    break;

                default:
                    System.out.println("INVALID ENTRY");
                    infoMessage();
            }
        }

        scanner.close(); // good practice
    }

}
