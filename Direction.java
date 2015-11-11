/*
 * 		Project: 	Word Search (CS 360 Fall 2015, Project 3)
 * 		File:		Direction.java
 * 		Author:		Jacob A. Zarobsky
 * 		Date:		Nov 6, 2015
 *
 * 		This file stores readable direction values.
 */

 public enum Direction
 {
    NORTH ("n"),
    NORTH_EAST ("ne"),
    EAST ("e"),
    SOUTH_EAST ("se"),
    SOUTH ("s"),
    SOUTH_WEST ("sw"),
    WEST ("w"),
    NORTH_WEST ("nw"),
    ANY ("n/a");

    private final String display;

    private Direction(String s) { display = s; }

    public String toString() { return display; }
 }
