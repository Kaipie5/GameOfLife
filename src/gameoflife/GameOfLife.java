
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
    static int EXPOSURE = 2;
    static int OVERCROWD = 3;

    public static void main(String[] args) {
        // TODO code application logic here
        boolean[][] board = initBoard();
    }
    
    private static boolean[][] initBoard() {
        //buffer of 1 around entirety of 2d array
        boolean[][] board = new boolean[BOARDHEIGHT + 1][BOARDWIDTH + 1];
        for (int row = 1; row < BOARDHEIGHT; row++){
            
            for (int col = 1; col < BOARDWIDTH; col++) {
                
                double rand = Math.random();
                
                if (rand <= STARTWEIGHT) {
                    board[row][col] = true;
                }
            }
        }
        return board;
    }
    
    private int calculateAliveNeighbors(boolean[][] board, int rowIndex, int colIndex) {
        int numAlive = 0;
        for (int row = rowIndex - 1; row < rowIndex + 1; row++) {
            
            for (int col = colIndex - 1; col < colIndex + 1; col++) {
                
                if (row == rowIndex && col == colIndex) continue;
                
                if (board[row][col]) {
                    numAlive++;
                }
            }
        }
        return numAlive;
    }
    
    private boolean calculateAliveOrDead(boolean[][] board, int row, int col) {
        
        int numAlive = calculateAliveNeighbors(board, row, col);
        
        if (numAlive >= EXPOSURE && numAlive <= OVERCROWD) {
            return true;
        } else {
            return false;
        }
    }
    
    private boolean[][] nextState(boolean[][] board) {
        boolean[][] newState = new boolean[BOARDHEIGHT + 1][BOARDWIDTH + 1];
        for (int row = 1; row < BOARDHEIGHT; row++) {
            for (int col = 1; col < BOARDWIDTH; col++) {
                newState[row][col] = calculateAliveOrDead(board, row, col);
            }
        }
        return newState;
    }
    
    
    
}
