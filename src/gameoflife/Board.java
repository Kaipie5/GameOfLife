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
    private static boolean gabbaRayCalculateNextStateOfCell(int row, int col) {
        return false;
    }
    private Board gabbaRay() {
        return this;
    }
    private Board meteorStrike() {
        return this;
    }
    
    public String generateBoardString() {
        return null;
    }
    
}
