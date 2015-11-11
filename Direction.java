/*
 * 		Project: 	Word Search (CS 360 Fall 2015, Project 3)
 * 		File:		Direction.java
 * 		Author:		Jacob A. Zarobsky
 * 		Date:		Nov 6, 2015
 *
 * 		This file stores readable direction values.
 */

 public class Direction
 {
    // Up
    public static final byte NORTH = 1;
    // Up, Right
    public static final byte NORTH_EAST = 2;
    // Right
    public static final byte EAST = 3;
    // Down, Right
    public static final byte SOUTH_EAST = 4;
    // Down
    public static final byte SOUTH = 5;
    // Down, Left
    public static final byte SOUTH_WEST = 6;
    // Left
    public static final byte WEST = 7;
    // Up, Left
    public static final byte NORTH_WEST = 8;

    public static final byte ANY = 9;

    public static String toString(byte d)
    {
        switch(d)
        {
            case NORTH:
                return "n";
            case NORTH_EAST:
                return "ne";
            case EAST:
                return "e";
            case SOUTH_EAST:
                return "se";
            case SOUTH:
                return "s";
            case SOUTH_WEST:
                return "sw";
            case WEST:
                return "w";
            case NORTH_WEST:
                return "nw";
            case ANY:
                return "n/a";
        }

        return "";
    }
 }
