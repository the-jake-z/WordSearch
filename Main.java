/*
 * 		Project: 	Word Search (CS 360 Fall 2015, Project 3)
 * 		File:		Main.java
 * 		Author:		Jacob A. Zarobsky
 * 		Date:		Nov 5, 2015
 *
 * 		This file is the main entry point for the program.
 *      The program reads in a file and then finds the words
 *      hidden inside of that file.
 */

public class Main
{
    public static void main(String[] args)
    {
        FileReader puzzleFile = new FileReader("puzzle.txt");
        FileReader wordsFile = new FileReader("words.txt");

        puzzleFile.forEachLine((String line, Integer lineNumber) ->
        {
            if(lineNumber == 1)
            {
                // Do something with the count.
            }

            System.out.println(line);
        });

        wordsFile.forEachLine((String line, Integer lineNumber) ->
        {
            System.out.println(line);
        });

    }

    public static void exitWithError(String errorMessage)
    {
        // Print the error in red.
        System.err.println(errorMessage);

        // Return a number other than 0.
        System.exit(1);
    }
}
