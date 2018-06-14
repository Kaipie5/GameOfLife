
package gameoflife;


/**
 *
 * @author kaimcconnell
 */
public class Main {
    
    //Constants
    static int BOARD_HEIGHT = 25;
    static int BOARD_WIDTH = 115;
    
    static double LIFE_LIKELIHOOD_FOR_CELL = 0.30;
    
    static int EXPOSURE = 2;
    static int OVERCROWD = 3;
    
    static int PAUSE_MILLIS = 100;
    
    
    static double GABBA_RAY_CHANCE = 0.01;
    static boolean GABBA_RAY_ON = true;
    
    static double METEOR_CHANCE = 0.01;
    static boolean METEOR_ON = true;
    static int METEOR_SIZE = 10;

    public static void main(String[] args) {
        
        GameOfLife newGame = new GameOfLife(BOARD_HEIGHT, BOARD_WIDTH, 
                LIFE_LIKELIHOOD_FOR_CELL, EXPOSURE, OVERCROWD, PAUSE_MILLIS, 
                GABBA_RAY_CHANCE, GABBA_RAY_ON, METEOR_CHANCE, METEOR_ON, METEOR_SIZE);
        
        newGame.run();
        
    }
}
