/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife;

/**
 *
 * @author kaimcconnell
 */
public class GameOfLife {
    
    private final int BOARD_HEIGHT;
    private final int BOARD_WIDTH;
    
    private final double LIFE_LIKELIHOOD_FOR_CELL;
    
    private final int EXPOSURE;
    private final int OVERCROWD;
    
    private final int PAUSE_MILLIS;
    
    
    private final double GABBA_RAY_CHANCE;
    private final boolean GABBA_RAY_ON;
    
    private final double METEOR_CHANCE;
    private final boolean METEOR_ON;
    private final int METEOR_SIZE;
    
    public GameOfLife(int BOARD_HEIGHT, int BOARD_WIDTH, double LIFE_LIKELIHOOD_FOR_CELL, 
            int EXPOSURE, int OVERCROWD, int PAUSE_MILLIS, double GABBA_RAY_CHANCE, 
            boolean GABBA_RAY_ON, double METEOR_CHANCE, boolean METEOR_ON, int METEOR_SIZE) {
        
        this.BOARD_HEIGHT = BOARD_HEIGHT;
        this.BOARD_WIDTH = BOARD_WIDTH;
        this.EXPOSURE = EXPOSURE;
        this.OVERCROWD = OVERCROWD;
        this.LIFE_LIKELIHOOD_FOR_CELL = LIFE_LIKELIHOOD_FOR_CELL;
        this.PAUSE_MILLIS = PAUSE_MILLIS;
        this.GABBA_RAY_ON = GABBA_RAY_ON;
        this.GABBA_RAY_CHANCE = GABBA_RAY_CHANCE;
        this.METEOR_CHANCE = METEOR_CHANCE;
        this.METEOR_ON = METEOR_ON;
        this.METEOR_SIZE = METEOR_SIZE;
        
    }
    public void run() {
        
        Board board = new Board(BOARD_HEIGHT, BOARD_WIDTH, EXPOSURE, OVERCROWD, 
                LIFE_LIKELIHOOD_FOR_CELL, GABBA_RAY_CHANCE, METEOR_SIZE);
        board.initBoard();
        
        Board previousBoard = new Board(BOARD_HEIGHT, BOARD_WIDTH, EXPOSURE, OVERCROWD,
                LIFE_LIKELIHOOD_FOR_CELL, GABBA_RAY_CHANCE, METEOR_SIZE);
        previousBoard.copyBoard(board);
        
        Board antepenultimateBoard = new Board(BOARD_HEIGHT, BOARD_WIDTH, EXPOSURE, OVERCROWD,
                LIFE_LIKELIHOOD_FOR_CELL, GABBA_RAY_CHANCE, METEOR_SIZE);
        antepenultimateBoard.copyBoard(board);
        
        Board anteAntepenultimateBoard = new Board(BOARD_HEIGHT, BOARD_WIDTH, EXPOSURE, OVERCROWD,
                LIFE_LIKELIHOOD_FOR_CELL, GABBA_RAY_CHANCE, METEOR_SIZE);
        anteAntepenultimateBoard.copyBoard(board);
        
        int stabilityTimer = 0;
        boolean notStable = true;
        
        while (notStable) {
            System.out.println(board);
            
            try {
                Thread.sleep(PAUSE_MILLIS);
            } catch (InterruptedException e) {

            }
            
            anteAntepenultimateBoard.copyBoard(antepenultimateBoard);
            antepenultimateBoard.copyBoard(previousBoard);
            previousBoard.copyBoard(board);
            board = board.calculateNextState();
            
            if (GABBA_RAY_ON) {
                board.gabbaRay();
            }
            
            double rand = Math.random();
            if (rand <= METEOR_CHANCE && METEOR_ON) {
                System.out.println("METEORSTRIKE");
                board.meteorStrike();
            }
            
            if (stabilityTimer >= 3) {
                
                if (board.equals(previousBoard) || board.equals(antepenultimateBoard)) {
                    
                    notStable = false;
                    System.out.println("STABLE");
                    
                }
            }
            
            stabilityTimer++;
            
        }
    }
}
