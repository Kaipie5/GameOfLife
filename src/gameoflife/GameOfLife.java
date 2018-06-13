
package gameoflife;

import java.util.Arrays;

/**
 *
 * @author kaimcconnell
 */
public class GameOfLife {

    //Remove buffer on board (also remove buffer for meteor)
    
    //Constants
    static int BOARD_HEIGHT = 25;
    static int BOARD_WIDTH = 115;
    
    static double LIFE_LIKELIHOOD_FOR_CELL = 0.30;
    
    public static int EXPOSURE = 2;
    public static int OVERCROWD = 3;
    
    static int PAUSE_MILLIS = 100;
    
    
    static double GABBA_RAY_CHANCE = 0.001;
    static boolean GABBA_RAY_ON = true;
    
    static double METEOR_CHANCE = 0.01;
    static boolean METEOR_ON = true;
    static int METEOR_SIZE = 10;

    public static void main(String[] args) {
        
        Board board = new Board(BOARD_HEIGHT, BOARD_WIDTH, LIFE_LIKELIHOOD_FOR_CELL);
        Board previousBoard = new Board(BOARD_HEIGHT, BOARD_WIDTH, LIFE_LIKELIHOOD_FOR_CELL);
        Board antepenultimateBoard = new Board(BOARD_HEIGHT, BOARD_WIDTH, LIFE_LIKELIHOOD_FOR_CELL);
        
        int stabilityTimer = 0;
        boolean notStable = true;
        
        while (notStable) {
            String boardString = board.generateBoardString();
            System.out.println(boardString);
            
            try {
                Thread.sleep(PAUSE_MILLIS);
            } catch (InterruptedException e) {

            }
            
            antepenultimateBoard.setCells(previousBoard.getCells());
            previousBoard.setCells(board.getCells());
            board.calculateNextState();
            
            if (GABBA_RAY_ON) {
                board.gabbaRay();
            }
            
            double rand = Math.random();
            if (rand <= METEOR_CHANCE) {
                System.out.println("METEORSTRIKE");
                board.meteorStrike();
            }
            
            if (stabilityTimer >= 3) {
                
                if (board.boardCompare(previousBoard) || board.boardCompare(antepenultimateBoard)) {
                    
                    notStable = false;
                    System.out.println("STABLE");
                    
                }
            }
            
            stabilityTimer++;
            
        }
    }
    
    
}
