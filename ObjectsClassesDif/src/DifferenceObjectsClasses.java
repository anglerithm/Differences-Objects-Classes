public class DifferenceObjectsClasses {
    public static void main(String[] args) throws Exception {
        Scratcher sessionOne = new Scratcher(); // creating a new Scratcher class instance (sessionOne)

        /*sessionOne object has been created
        * Use methods from the Scratcher class on the class instance
        * Class instance will store values declared in Scratcher.java
        * Methods can change the value of explicit parameters (i.e
        */

        // In the Scratcher class, methods are invoked when a user presses a key
        // You can still invoke methods on the class instance without startGame()

        // This is for all methods except for accessors
        System.out.println(sessionOne.getGems());
        System.out.println(sessionOne.getMultiplier());
        System.out.println(sessionOne.getGameState());
        // With the scratcher class, you can essentially think of accessors like debugging

        // Simply invoke startGame method on the current object sessionOne
        sessionOne.startGame();
    }
}