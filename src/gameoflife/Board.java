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
    private int height;
    private int width;
    
    public Board(int height, int width) {
        this.height = height;
        this.width = width;
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

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
    
    public void initBoard() {
        
    }
    public String generateBoardString() {
        return null;
    }
    private int calculateAliveNeighborsOfCurrentCell(int row, int col) {
        
        return 0;
    }
    private static boolean calculateNextStateOfCell(boolean[][] board, int row, int col) {
        return false;
    } 
    public void nextState() {
        
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
