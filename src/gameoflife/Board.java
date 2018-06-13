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
    
    public Board(int height, int width, int exposure, int overcrowd) {
        this.height = height;
        this.width = width;
        this.exposure = exposure;
        this.overcrowd = overcrowd;
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
