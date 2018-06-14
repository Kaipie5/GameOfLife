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
public class Board {
    private Cell[][] cells;
    private final int height;
    private final int width;
    private final int exposure;
    private final int overcrowd;
    private final double lifeLikelihoodOfCell;
    private final double gabbaRayChance;
    private final int meteorSize;
    
    public Board(int height, int width, int exposure, int overcrowd, double lifeLikelihoodOfCell, double gabbaRayChance, int meteorSize) {
        this.height = height;
        this.width = width;
        this.exposure = exposure;
        this.overcrowd = overcrowd;
        this.lifeLikelihoodOfCell = lifeLikelihoodOfCell;
        this.gabbaRayChance = gabbaRayChance;
        this.meteorSize = meteorSize;
        cells = new Cell[height][width];
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                Coordinate c = new Coordinate(row, col);
                cells[row][col] =  new Cell(c, height, width);
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
    
    public void initBoard() {
        for (int row = 0; row < height; row++){
            
            for (int col = 0; col < width; col++) {
                
                double rand = Math.random();
                
                if (rand <= lifeLikelihoodOfCell) {
                    cells[row][col].bringToLife();
                }
            }
        }
    }
    public String toString() {
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
    private boolean calculateNextStateOfCell(Cell cell) {
        int numAlive = cell.calculateAliveNeighbors(this);
        if (numAlive == overcrowd && !cell.isAlive()) {
            return true;
        } else if (numAlive >= exposure && numAlive <= overcrowd && cell.isAlive()) {
            return true;
        }  else {
            return false;
        }
    } 
    public Board calculateNextState() {
        Board newBoard = new Board(height, width, exposure, overcrowd, lifeLikelihoodOfCell, gabbaRayChance, meteorSize);
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                newBoard.getCells()[row][col].setAlive(calculateNextStateOfCell(cells[row][col]));
            }
        }
        return newBoard;
    }
    private boolean gabbaRayCalculateNextStateOfCell(int row, int col) {
        double rand = Math.random();
        if (rand <= gabbaRayChance && !cells[row][col].isAlive()) {
            return true;
        } else {
            return cells[row][col].isAlive();
        }
    }
    public void gabbaRay() {

        Cell[][] newState = cells;
        for (int row = 0; row < height; row++) {
            
            for (int col = 0; col < width; col++) {
                
                boolean aliveOrDead = gabbaRayCalculateNextStateOfCell(row, col);
                newState[row][col].setAlive(aliveOrDead);
                
            }   
        }
        cells = newState;
    }
    public void meteorStrike() {
        Cell[][] meteoredBoard = cells;
        int meteorRow = (int)(Math.random() * ((height) + 1));
        int meteorCol = (int)(Math.random() * ((width) + 1));
        for (int row = meteorRow - meteorSize; row <= meteorRow + meteorSize; row++) {
            for (int col = meteorCol - meteorSize; col <= meteorCol + meteorSize; col++) {
                if (row >= 0 && col >= 0 && row < height && col < width) {
                    
                    meteoredBoard[row][col].die();
                }  
            }
        }
        cells = meteoredBoard;
    }
    
    public void copyBoard(Board board) {
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                
                this.getCells()[row][col].setAlive(board.getCells()[row][col].isAlive());
                
            }
        }
    }
    
    public boolean equals(Board board) {
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if (!this.getCells()[row][col].isAlive() == board.getCells()[row][col].isAlive()) {
                    return false;
                }
            }
        }
        return true;
    }
    
}
