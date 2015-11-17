/*
 * 		Project: 	Word Search (CS 360 Fall 2015, Project 3)
 * 		File:		PrefixTree.java
 * 		Author:		Jacob A. Zarobsky
 * 		Date:		Nov 17, 2015
 *
 * 		This class implements a simple prefix tree.
 */

import java.util.HashMap;

public class PrefixTree {

    // HashMap of roots, allows us to access the start node
    // for any letter in constant time.
    private HashMap<Character, Node> roots;

    // Returns the given root for the character.
    public Node getRoot(char c) {
        // Lazy Instantiaion
        if(roots == null) roots = new HashMap<Character, Node>();

        if(!roots.containsKey(c))
            roots.put(c, new Node(c));

        return roots.get(c);
    }

    public void insert(String s) {
        // Get the start node.
        Node current = getRoot(s.charAt(0));

        // Iterate through the characters of the string, moving
        // and inserting as we go along.
        for(int i = 1; i < s.length(); i++) {
            char c = s.charAt(i);
            // There's already a node with that character, make that our
            // current node.
            if(current.getChildren().containsKey(c)) {
                current = current.getChildren().get(c);
            } else {
                Node newNode = new Node(c); // Create a new node.
                current.getChildren().put(c, newNode); // Add it to children.
                current = newNode; // Set it to be our current.
            }

            // If we're at the end of the word then set our flag that
            // we're at the end (so we know when looking up that it is a
            // valid word.)
            if(i == (s.length() - 1)) current.setEndOfWord(true);
        }
    }

    // Returns the child with the given key, if one exisits, otherwise null.
    public Node lookup(Node n, char c) {
        if(n.getChildren().containsKey(c)) return n.getChildren().get(c);
        return null;
    }
}
