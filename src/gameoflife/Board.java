/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife;

import java.util.ArrayList;

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
    private final int lifeLikelihoodOfCell;
    
    public Board(int height, int width, int exposure, int overcrowd, int lifeLikelihoodOfCell) {
        this.height = height;
        this.width = width;
        this.exposure = exposure;
        this.overcrowd = overcrowd;
        this.lifeLikelihoodOfCell = lifeLikelihoodOfCell;
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                Coordinate c = new Coordinate(row, col);
                cells[row][col] = new Cell(c, height, width);
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
    public String generateBoardString() {
        return null;
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
    public void nextState() {
        Board newBoard = new Board(height, width, exposure, overcrowd, lifeLikelihoodOfCell);
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                calculateNextStateOfCell(cells[row][col]);
            }
        }
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
    
}
