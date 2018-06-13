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
public class Cell {
    private boolean alive;
    private Coordinate location;
    private final Coordinate[] neighbors;
    public Cell(Coordinate location) {
        neighbors = new Coordinate[8];
        populateNeighbors();
        this.location = location;
        alive = false;
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
        int index = 0;
        for (int row = -1; row <= 1; row++) {
            for (int col = -1; col <= 1; col++) {
                
                if (row == location.getXCoord() && col == location.getYCoord()) continue;
                
                neighbors[index] = new Coordinate(location.getXCoord() + row, location.getYCoord() + col);
                index++;
            }
        }
    }
    
}
