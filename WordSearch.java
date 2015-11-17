/*
 * 		Project: 	Word Search (CS 360 Fall 2015, Project 3)
 * 		File:		WordSearch.java
 * 		Author:		Jacob A. Zarobsky
 * 		Date:		Nov 5, 2015
 *
 * 		This file runs the WordSearch and stores all
 *      necessary data for the search.
 */

import java.util.HashSet;

public class WordSearch {
    // Private Properties
    private String puzzleSource;
    private String wordSource;
    private CharGraph charGraph;
    private HashSet<String> dictionary;

    // Accessors
    public void setPuzzleSource(String pSource) { puzzleSource = pSource; }
    public String getPuzzleSource() { return puzzleSource; }

    public void setWordSource(String wSource) { wordSource = wSource; }
    public String getWordSource() { return wordSource; }

    public void setCharGraph(CharGraph c) {charGraph = c;}
    public CharGraph getCharGraph() { return charGraph; }

    public void setDictionary(HashSet<String> dict) { dictionary = dict; }
    public HashSet<String> getDictionary() {
        // Lazy instantiation
        if(dictionary == null) dictionary = new HashSet<String>();
        return dictionary;
    }

    // Constructor
    public WordSearch(String puzzleSource, String wordSource) {
        setPuzzleSource(puzzleSource);
        setWordSource(wordSource);
    }

    // Where the action happens.
    public void run() {
        initalizeSources();

        charGraph.setDictionary(getDictionary());

        int size = getCharGraph().getCharacters().length;

        charGraph.forEachIndex((Integer row, Integer column) -> {
            for(int i = -1; i < 2; i++) {
                for(int j = -1; j < 2; j++) {
                    if( i == 0 && j == 0) continue;
                    getCharGraph().depthFirstSearch(row, column, i, j);
                }
            }
        });
    }

    // Load up our graph and our dictionary.
    private void initalizeSources() {
        final FileReader fileReader = new FileReader(getPuzzleSource());
        final String delimiters = " ";
        fileReader.forEachLine((String line, Integer lineNumber) -> {
            // The first line of this file contains the size of the puzzle
            // we need to solve. Treat it differntly than all the rest.
            if(lineNumber == 1) {
                // Get the size of the puzzle.
                int size = new Integer(line);
                // Initalize a new square graph.
                setCharGraph(new CharGraph(size));
            } else {
                // Split the string based on spaces.
                String[] letters = line.split(" ");

                // Add a vertex for every letter in the line.
                for(int i = 0; i < letters.length; i++)
                    charGraph.setCharacter(lineNumber - 2, i,
                        letters[i].charAt(0));
            }
        });

        fileReader.setFilePath(getWordSource());

        // Read in the dictionary.
        fileReader.forEachLine((String line, Integer lineNumber) -> {
            getDictionary().add(line);
        });
    }
}
