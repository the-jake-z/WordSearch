/*
 * 		Project: 	Word Search (CS 360 Fall 2015, Project 3)
 * 		File:		Graph.java
 * 		Author:		Jacob A. Zarobsky
 * 		Date:		Nov 5, 2015
 *
 * 		This file stores the graph in a 2D Array of Characters.
 *      Using a stringbuilder and a PrefixTree, it searches
 *      the puzzle for a list of words.
 */

import java.util.function.BiConsumer;
import java.util.LinkedList;

public class Graph {

    // Properties
    private char[][] letters;
    private PrefixTree tree;
    private StringBuilder stringBuilder;

    // Accessors
    public void setLetters(char[][] c) { letters = c; }
    public char[][] getLetters() { return letters; }

    public StringBuilder getStringBuilder() {
        // Lazy instantiation
        if(stringBuilder == null) stringBuilder = new StringBuilder();

        return stringBuilder;
    }

    public void setStringBuilder(StringBuilder sb) { stringBuilder = sb; }

    public void setTree(PrefixTree t) { tree = t; }
    public PrefixTree getTree() { return tree; }

    // Constructor
    public Graph(int size) {
        setLetters(new char[size][size]);
    }

    // Convenience
    public void addVertex(int row, int col, char c) {
        letters[row][col] = c;
    }

    // Convenience
    public int getSize() { return letters.length; }

    // Convenience
    public void forEachVertex(BiConsumer<Integer, Integer> lambda) {
        for(int i = 0; i < getSize(); i++) {
            for(int j = 0; j < getSize(); j++ ) {
                lambda.accept(i, j);
            }
        }
    }

    public void dfs(int row, int col, int dx, int dy) {
        StringBuilder sb = getStringBuilder();
        char[][] letters  = getLetters();

        // Initalize a stack.
        // This stack holds valid moves. It should potentially
        // only hold 1 value at a time.
        LinkedList<Node> stack = new LinkedList<Node>();

        // Clear the string builder.
        sb.setLength(0);

        // Do some initalization.
        int currentRow = row, currentCol = col;

        // Get our root node we're going to use.
        Node current = tree.getRoot(letters[row][col]);
        stack.push(current);

        // While the stack is not empty (there is valid moves) and
        // the current item is not null
        while(!stack.isEmpty() && (current = stack.pop()) != null) {
            // Append the current character to the string builder.
            sb.append(current.getLetter());

            // We found a word! Yay!
            if(current.getEndOfWord() && sb.length() > 3) {
                System.out.printf("%s (%d,%d,%s)\n", sb.toString(),
                        col + 1, row + 1, direction(dx, dy));
            }

            if(canMoveAgain(currentRow + dx, currentCol + dy)) {
                // Increment these two
                currentRow += dx; currentCol += dy;
                // Update the current character.
                stack.push(tree.lookup(current,
                    letters[currentRow][currentCol]));
            }
        }
    }

    // Determines if you can move again given a row and column
    private boolean canMoveAgain(int row, int col) {
        return row >= 0 && row < getSize() && col >= 0 && col < getSize();
    }

    // Builds a direction string based on the dx and dy values.
    private String direction(int dx, int dy) {
      StringBuilder direction = new StringBuilder();

      if(dx == -1) direction.append("n");
      if(dx == 1) direction.append("s");

      if(dy == -1) direction.append("w");
      if(dy == 1) direction.append("e");

      return direction.toString();
  }
}
