/*
 * 		Project: 	Word Search (CS 360 Fall 2015, Project 3)
 * 		File:		WordSearch.java
 * 		Author:		Jacob A. Zarobsky
 * 		Date:		Nov 5, 2015
 *
 * 		This file runs the WordSearch and stores all
 *      necessary data for the search.
 */

public class WordSearch {
    // Private Properties
    private String puzzleSource;
    private String wordSource;
    private Graph graph;
    private PrefixTree tree;

    // Accessors
    public void setPuzzleSource(String pSource) { puzzleSource = pSource; }
    public String getPuzzleSource() { return puzzleSource; }

    public void setWordSource(String wSource) { wordSource = wSource; }
    public String getWordSource() { return wordSource; }

    public void setGraph(Graph g) {graph = g;}
    public Graph getGraph() { return graph; }

    public void setTree(PrefixTree t) { tree = t; }
    public PrefixTree getTree() {
        // Lazy instantiation
        if(tree == null) tree = new PrefixTree();
        return tree;
    }

    // Constructor
    public WordSearch(String puzzleSource, String wordSource) {
        setPuzzleSource(puzzleSource);
        setWordSource(wordSource);
    }

    // Where the action happens.
    public void run() {
        initalizeSources();

        graph.setTree(getTree());

        graph.forEachVertex((Integer row, Integer col) -> {
            // i = dx values. -1 -> left, 0 -> current, 1 -> right
            // j = dy values. -1 -> up, 0 -> current, 1 -> down
            for(int i = -1; i < 2; i++) {
                for(int j = -1; j < 2; j++) {
                    // If we didn't move up, and didn't move down, then
                    // skip this iteration.
                    if( i == 0 && j == 0) continue;
                    // Depth first search in one direction.
                    graph.dfs(row, col, i, j);
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
                setGraph(new Graph(size));
            } else {
                // Split the string based on spaces.
                String[] letters = line.split(" ");

                // Add a vertex for every letter in the line.
                for(int i = 0; i < letters.length; i++)
                    getGraph().addVertex(lineNumber - 2, i,
                        letters[i].charAt(0));
            }
        });

        fileReader.setFilePath(getWordSource());

        // Read in the dictionary.
        fileReader.forEachLine((String line, Integer lineNumber) -> {
            getTree().insert(line);
        });
    }
}
