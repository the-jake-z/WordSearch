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

        // If the root is not in the list, create a new one and add it.
        if(!roots.containsKey(c)) roots.put(c, new Node(c));

        return roots.get(c);
    }

    public void insert(String s) {
        // Get the start node.
        Node current = getRoot(s.charAt(0));

        // Iterate through the characters of the string, moving
        // and inserting as we go along. Start at 1 because root node
        // is already assigned to current.
        for(int i = 1; i < s.length(); i++) {
            char c = s.charAt(i);

            // Checks to see if there is a child node with that
            // character. Otherwise it creates one and assigns it
            // to the variable current.
            if(current.getChildren().containsKey(c))
                current = current.getChildren().get(c);
            else
                current.getChildren().put(c, (current = new Node(c)));

            // If we're at the end of the word then set our flag that
            // we're at the end. Allows for "subwords"
            if(i == (s.length() - 1)) current.setEndOfWord(true);
        }
    }

    // Returns the child with the given key, if one exisits, otherwise null.
    public Node lookup(Node n, char c) {
        if(n.getChildren().containsKey(c)) return n.getChildren().get(c);
        return null;
    }
}
