/*
 * 		Project: 	Word Search (CS 360 Fall 2015, Project 3)
 * 		File:		Node.java
 * 		Author:		Jacob A. Zarobsky
 * 		Date:		Nov 5, 2015
 *
 * 		This file stores the data associated with a Node in the PrefixTree.
 */

import java.util.HashMap;

public class Node {
    // Properties
    private HashMap<Character, Node> children;
    private char letter;
    private boolean endOfWord;

    // Accessors
    public HashMap<Character, Node> getChildren() { return children; }
    public void setChildren(HashMap<Character, Node> c) { children = c; }

    public char getLetter() { return letter; }
    public void setLetter(char l) { letter = l; }

    public boolean getEndOfWord() { return endOfWord; }
    public void setEndOfWord(boolean eow) { endOfWord = eow; }

    // Constructor
    public Node(char letter) {
        setLetter(letter);
        setChildren(new HashMap<Character, Node>());
    }
}
