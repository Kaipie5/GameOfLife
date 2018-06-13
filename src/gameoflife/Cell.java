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
public class Cell {
    private boolean alive;
    private Coordinate location;
    private final ArrayList<Coordinate> neighbors;
    private final int height;
    private final int width;
    public Cell(Coordinate location, int height, int width) {
        this.height = height;
        this.width = width;
        neighbors = new ArrayList<>();
        this.location = location;
        alive = false;
        populateNeighbors();
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
    public void bringToLife() {
        alive = true;
    }
    public void die() {
        alive = false;
    }

    public Coordinate getLocation() {
        return location;
    }

    public void setLocation(Coordinate location) {
        this.location = location;
    }
    
    private void populateNeighbors() {
        for (int row = -1; row <= 1; row++) {
            for (int col = -1; col <= 1; col++) {
                
                if (row == location.getXCoord() && col == location.getYCoord()) continue;
                if ((location.getXCoord() + row) < 0 || (location.getYCoord() + col) < 0 
                        || (location.getXCoord() + row) >  height|| (location.getYCoord() + col) > width) continue;
                
                neighbors.add(new Coordinate(location.getXCoord() + row, location.getYCoord() + col));
            }
        }
    }
    
    public int calculateAliveNeighbors(Board board) { 
        int numAlive = 0;
        for (int index = 0; index < neighbors.size(); index++) {
            if (board.getCells()[neighbors.get(index).getXCoord()][neighbors.get(index).getYCoord()].isAlive()) {
                numAlive++;
            }
        }
        return numAlive;
    }
    
}
