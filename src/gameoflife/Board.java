/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife;
import static gameoflife.GameOfLife.*;

/**
 *
 * @author kaimcconnell
 */
public class Board {
    private Cell[][] cells;
    private final int height;
    private final int width;
    
    public Board(int height, int width, double lifeLikelihoodForCell) {
        this.height = height;
        this.width = width;
        cells = new Cell[height][width];
        
        for (int row = 0; row < height; row++){
            
            for (int col = 0; col < width; col++) {
                
                Coordinate c = new Coordinate(row, col);
                Cell newCell = new Cell(c);
                
                double rand = Math.random();
                
                if (rand <= lifeLikelihoodForCell) {
                    newCell.bringToLife();
                }
                cells[row][col] = newCell;
            }
        }
    }

    public Cell[][] getCells() {
        return cells;
    }

    public void setCells(Cell[][] cells) {
        this.cells = cells;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
    
    
    private int calculateAliveNeighborsOfCurrentCell(int rowIndex, int colIndex) {
        int numAlive = 0;
        for (int row = rowIndex - 1; row <= rowIndex + 1; row++) {
            
            for (int col = colIndex - 1; col <= colIndex + 1; col++) {
                
                if (row == rowIndex && col == colIndex) continue;
                
                if (row >= 0 && col >=0 && row < height && col < width) {
                    if (cells[row][col].isAlive()) {
                        numAlive++;
                    }
                }  
            }
        }
        return numAlive;
    }
    private boolean calculateNextStateOfCell(int row, int col) {
        int numAlive = calculateAliveNeighborsOfCurrentCell(row, col);
        
        if (numAlive == OVERCROWD && !cells[row][col].isAlive()) {
            return true;
        } else if (numAlive >= EXPOSURE && numAlive <= OVERCROWD && cells[row][col].isAlive()) {
            return true;
        }  else {
            return false;
        }    
    } 
    public void calculateNextState() {
        Cell[][] newState = new Cell[height][width];
        for (int row = 0; row < BOARD_HEIGHT; row++) {
            
            for (int col = 0; col < BOARD_WIDTH; col++) {
                
                boolean aliveOrDead = calculateNextStateOfCell(row, col);
                
                newState[row][col].setAlive(aliveOrDead);
                
            }   
        }
        
        cells = newState;
        
    }
    private boolean gabbaRayCalculateNextStateOfCell(int row, int col) {
        double rand = Math.random();
        if (GABBA_RAY_ON && rand <= GABBA_RAY_CHANCE && !cells[row][col].isAlive()) {
            return true;
        } else {
            return cells[row][col].isAlive();
        }
    }
    private void gabbaRay() {
        Cell[][] newState = new Cell[height][width];
        for (int row = 0; row < height; row++) {
            
            for (int col = 0; col < width; col++) {
                
                boolean aliveOrDead = gabbaRayCalculateNextStateOfCell(row, col);
                newState[row][col].setAlive(aliveOrDead);
                
            }   
        }
        cells = newState;
    }
    private void meteorStrike() {
        Cell[][] meteoredBoard = new Cell[height][width];
        int meteorRow = (int)(Math.random() * ((height) + 1));
        int meteorCol = (int)(Math.random() * ((width) + 1));
        for (int row = meteorRow - METEOR_SIZE; row <= meteorRow + METEOR_SIZE; row++) {
            for (int col = meteorCol - METEOR_SIZE; col <= meteorCol + METEOR_SIZE; col++) {
                if (row >= 0 && col >=0 && row < height && col < width) {
                    
                    meteoredBoard[row][col].die();
                }  
            }
        }
        cells = meteoredBoard;
    }
    
    public String generateBoardString() {
        String boardString = "";
        for (int row = 0; row < height; row++) {
            
            for (int col = 0; col < width; col++) {
                
                if (cells[row][col].isAlive()) {
                    boardString += "*";
                } else {
                    boardString += " ";
                }
            }
            boardString += "\n";
        }
        
        for (int col = 0; col < width; col++) {
            boardString += "-";
        }
        
        boardString += "\n";
        return boardString;
    }
    
}
