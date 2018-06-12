
package gameoflife;

/**
 *
 * @author kaimcconnell
 */
public class GameOfLife {

    
    //Constants
    static int BOARDHEIGHT = 20;
    static int BOARDWIDTH = 20;
    static double STARTWEIGHT = 0.30;

    public static void main(String[] args) {
        // TODO code application logic here
    }
    
    private boolean[][] initBoard() {
        //buffer of 1 around entirety of 2d array
        boolean[][] board = new boolean[BOARDHEIGHT + 1][BOARDWIDTH + 1];
        for (int row = 1; row < BOARDHEIGHT; row++){
            for (int col = 1; col < BOARDWIDTH; col++) {
                double rand = Math.random();
                if (rand < STARTWEIGHT) {
                    board[row][col] = true;
                } else {
                    board[row][col] = false;
                }
            }
        }
        return board;
    }
    
    private int calculateAliveNeighbors(int row, int col) {
        return 0;
    }
    
    private boolean calculateAliveOrDead(int row, int col) {
        return true;
    }
    
    
    
}
