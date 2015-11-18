/*
 * 		Project: 	Word Search (CS 360 Fall 2015, Project 3)
 * 		File:		Main.java
 * 		Author:		Jacob A. Zarobsky
 * 		Date:		Nov 5, 2015
 *
 * 		This file is the main entry point for the program.
 *      The program reads in a word search and then solves
 *      the word search.
 */

public class Main {
    public static void main(String[] args) {
        new WordSearch("puzzle.txt", "words.txt").run();
    }

    public static void exitWithError(String errorMessage) {
        // Print the error in red.
        System.err.println(errorMessage);

        // Return a number other than 0.
        System.exit(1);
    }
}
