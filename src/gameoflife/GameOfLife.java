
package gameoflife;

import java.util.Arrays;

/**
 *
 * @author kaimcconnell
 */
public class GameOfLife {

    
    //Constants
    static int BOARD_HEIGHT = 30;
    static int BOARD_WIDTH = 115;
    static double LIFE_LIKELIHOOD_FOR_CELL = 0.30;
    static int EXPOSURE = 2;
    static int OVERCROWD = 3;
    static int PAUSE_MILLIS = 100;
    static double GABBA_RAY_CHANCE = 0.01;
    static boolean GABBA_RAY_ON = true;
    static double METEOR_CHANCE = 0.1;
    static boolean METEOR_ON = true;
    static int METEOR_SIZE = 10;

    public static void main(String[] args) {
        
        boolean[][] board = initBoard();
        boolean[][] previousBoard = board;
        boolean[][] oldestBoard = board;
        
        int stabilityTimer = 0;
        boolean notStable = true;
        
        while (notStable) {
            String boardString = generateBoardString(board);
            System.out.println(boardString);
            
            try {
                Thread.sleep(PAUSE_MILLIS);
            } catch (InterruptedException e) {

            }
            
            oldestBoard = previousBoard;
            previousBoard = board;
            board = calculateNextState(board);
            
            if (stabilityTimer >= 3) {
                
                if (Arrays.deepEquals(board, previousBoard) || Arrays.deepEquals(board, oldestBoard)) {
                    
                    notStable = false;
                    System.out.println("STABLE");
                    
                }
            }
            
            stabilityTimer++;
            
        }
    }
    
    private static boolean[][] initBoard() {
        //buffer of 1 around entirety of 2d array
        boolean[][] board = new boolean[BOARD_HEIGHT + 1][BOARD_WIDTH + 1];
        for (int row = 1; row < BOARD_HEIGHT; row++){
            
            for (int col = 1; col < BOARD_WIDTH; col++) {
                
                double rand = Math.random();
                
                if (rand <= LIFE_LIKELIHOOD_FOR_CELL) {
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
        double rand = Math.random();
        if (numAlive == OVERCROWD && !board[row][col]) {
            return true;
        } else if (numAlive >= EXPOSURE && numAlive <= OVERCROWD && board[row][col]) {
            return true;
        } else if (GABBA_RAY_ON && rand <= GABBA_RAY_CHANCE && !board[row][col]) {
            return true;
        } else {
            return false;
        }      
    }
    
    private static boolean[][] meteorStrike(boolean[][] board) {
        boolean[][] meteoredBoard = board;
        int max = (BOARD_HEIGHT - METEOR_SIZE);
        int min = (1 + METEOR_SIZE);
        int meteorRow = (int)(Math.random() * ((max - min) + 1)) + min;
        int meteorCol = (int)(Math.random() * ((max - min) + 1)) + min;
        for (int row = meteorRow - METEOR_SIZE; row <= meteorRow + METEOR_SIZE; row++) {
            for (int col = meteorCol - METEOR_SIZE; col <= meteorCol + METEOR_SIZE; col++) {
                meteoredBoard[row][col] = false;
            }
        }
        return meteoredBoard;
    }
    
    private static boolean[][] calculateNextState(boolean[][] board) {
        boolean[][] newState = new boolean[BOARD_HEIGHT + 1][BOARD_WIDTH + 1];
        for (int row = 1; row < BOARD_HEIGHT; row++) {
            
            for (int col = 1; col < BOARD_WIDTH; col++) {
                
                newState[row][col] = calculateNextStateOfCell(board, row, col);
                
            }   
        }
        double rand = Math.random();
        if (rand <= METEOR_CHANCE) {
            System.out.println("METEORSTRIKE");
            newState = meteorStrike(newState);
        }
        return newState;
    }
    
    private static String generateBoardString(boolean[][] board) {
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
