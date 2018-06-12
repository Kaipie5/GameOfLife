
package gameoflife;

/**
 *
 * @author kaimcconnell
 */
public class GameOfLife {

    
    //Constants
    static int BOARD_HEIGHT = 30;
    static int BOARD_WIDTH = 100;
    static double START_WEIGHT = 0.30;
    static int EXPOSURE = 2;
    static int OVERCROWD = 3;
    static int PAUSE_MILLIS = 100;

    public static void main(String[] args) {
        
        boolean[][] board = initBoard();
        
        while (true) {
            String boardString = printBoard(board);
            System.out.println(boardString);
            try {
                Thread.sleep(PAUSE_MILLIS);
            } catch (InterruptedException e) {

            }
            board = calculateNextState(board);
        }
    }
    
    private static boolean[][] initBoard() {
        //buffer of 1 around entirety of 2d array
        boolean[][] board = new boolean[BOARD_HEIGHT + 1][BOARD_WIDTH + 1];
        for (int row = 1; row < BOARD_HEIGHT; row++){
            
            for (int col = 1; col < BOARD_WIDTH; col++) {
                
                double rand = Math.random();
                
                if (rand <= START_WEIGHT) {
                    board[row][col] = true;
                }
            }
        }
        return board;
    }
    
    private static int calculateAliveNeighborsOfCurrentCell(boolean[][] board, int rowIndex, int colIndex) {
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
    
    private static boolean calculateNextStateOfCell(boolean[][] board, int row, int col) {
        
        int numAlive = calculateAliveNeighborsOfCurrentCell(board, row, col);
        
        if (numAlive == OVERCROWD && !board[row][col]) {
            return true;
        } else if (numAlive >= EXPOSURE && numAlive <= OVERCROWD && board[row][col]) {
            return true;
        } else {
            return false;
        }
        
//        if (numAlive < EXPOSURE && board[row][col]) {
//            return false;
//        }
//        if (numAlive > OVERCROWD && board[row][col]) {
//            return false;
//        }
        
        
    }
    
    private static boolean[][] calculateNextState(boolean[][] board) {
        boolean[][] newState = new boolean[BOARD_HEIGHT + 1][BOARD_WIDTH + 1];
        
        for (int row = 1; row < BOARD_HEIGHT; row++) {
            
            for (int col = 1; col < BOARD_WIDTH; col++) {
                
                newState[row][col] = calculateNextStateOfCell(board, row, col);
                
            }   
        }
        return newState;
    }
    
    private static String printBoard(boolean[][] board) {
        String boardString = "";
        for (int row = 1; row < BOARD_HEIGHT; row++) {
            
            for (int col = 1; col < BOARD_WIDTH; col++) {
                
                if (board[row][col]) {
                    boardString += "*";
                } else {
                    boardString += " ";
                }
            }
            boardString += "\n";
        }
        
        for (int col = 1; col < BOARD_WIDTH; col++) {
            boardString += "-";
        }
        
        boardString += "\n";
        return boardString;
    }
    
    
}
