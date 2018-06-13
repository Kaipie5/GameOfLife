/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife;

import java.util.*;

/**
 *
 * @author kaimcconnell
 */
public class Coordinate {
    private int xCoord;
    private int yCoord;
    
    public Coordinate(int xCoord, int yCoord){
        this.xCoord = xCoord;
        this.yCoord = yCoord;
    }
    
    //===================================================
    public int getXCoord() {
        return xCoord;
    }
    public int getYCoord() {
        return yCoord;
    }
    public void setXCoord(int xCoord) {
        if (xCoord < 0) return;
        this.xCoord = xCoord;
    }
    public void setYCoord(int yCoord) {
        if (yCoord < 0) return;
        this.yCoord = yCoord;
    }
    //===================================================
    
    public boolean equals(Coordinate c) {
        if (xCoord == c.getXCoord() && yCoord == c.getYCoord()) {
            return true;
        } else {
            return false;
        }
    }
    
    @Override
    public String toString() {
        return String.format("[x: %d y: %d]", xCoord, yCoord);
    }
}
