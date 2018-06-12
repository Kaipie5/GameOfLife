
package gameoflife;

/**
 *
 * @author kaimcconnell
 */
public class GameOfLife {

    
    //Constants
    static int BOARDHEIGHT = 50;
    static int BOARDWIDTH = 50;
    static double STARTWEIGHT = 0.30;
    static int EXPOSURE = 2;
    static int OVERCROWD = 3;
    static int PAUSE_MILLIS = 1000;

    public static void main(String[] args) {
        
        boolean[][] board = initBoard();
        printBoard(board);
        
        while (true) {
            try {
                Thread.sleep(PAUSE_MILLIS);
            } catch (InterruptedException e) {

            }
            board = nextState(board);
            printBoard(board);
        }
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
    
    private static int calculateAliveNeighbors(boolean[][] board, int rowIndex, int colIndex) {
        int numAlive = 0;
        for (int row = rowIndex - 1; row <= rowIndex + 1; row++) {
            
            for (int col = colIndex - 1; col <= colIndex + 1; col++) {
                
                if (row == rowIndex && col == colIndex) continue;
                
                if (board[row][col]) {
                    numAlive++;
                }
            }
        }
        return numAlive;
    }
    
    private static boolean calculateAliveOrDead(boolean[][] board, int row, int col) {
        
        int numAlive = calculateAliveNeighbors(board, row, col);
        
        if (numAlive < EXPOSURE && board[row][col]) {
            return false;
        }
        if (numAlive > OVERCROWD && board[row][col]) {
            return false;
        }
        if (numAlive == OVERCROWD && !board[row][col]) {
            return true;
        }
        if (numAlive >= EXPOSURE && numAlive <= OVERCROWD && board[row][col]) {
            return true;
        } else {
            return false;
        }
        
    }
    
    private static boolean[][] nextState(boolean[][] board) {
        boolean[][] newState = new boolean[BOARDHEIGHT + 1][BOARDWIDTH + 1];
        for (int row = 1; row < BOARDHEIGHT; row++) {
            for (int col = 1; col < BOARDWIDTH; col++) {
                newState[row][col] = calculateAliveOrDead(board, row, col);
            }
        }
        return newState;
    }
    
    private static void printBoard(boolean[][] board) {
        for (int row = 1; row < BOARDHEIGHT; row++) {
            for (int col = 1; col < BOARDWIDTH; col++) {
                if (board[row][col]) {
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println("");
        }
        for (int row = 1; row < BOARDHEIGHT; row++) {
            System.out.print("-");
        }
        System.out.println("");
    }
    
    
}
