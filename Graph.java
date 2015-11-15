/*
 * 		Project: 	Word Search (CS 360 Fall 2015, Project 3)
 * 		File:		Graph.java
 * 		Author:		Jacob A. Zarobsky
 * 		Date:		Nov 5, 2015
 *
 * 		This file stores all the data for the graph used to solve the
 *      puzzle. It also contains methods to traverse the graph.
 */

import java.util.LinkedList;
import java.util.function.BiConsumer;
import java.util.HashSet;
import java.util.Map;
import java.util.Iterator;

public class Graph {
    // 2D Array allows direct access to any vertex.
    private Vertex[][] verticies;
    // Hash Set of all Dictionary terms. Allows for O(1) search.
    private HashSet<String> dictionary;

    // Accessors
    public void setVerticies(Vertex[][] v) { verticies = v; }
    public Vertex[][] getVerticies() { return verticies; }

    public void setDictionary(HashSet<String> d) { dictionary = d; }
    public HashSet<String> getDictionary() { return dictionary; }

    // Constructor
    public Graph(int rows, int columns) {
        verticies = new Vertex[rows][columns];
    }

    // Convenience
    public void addVertex(int row, int column, char letter) {
        verticies[row][column] = new Vertex(letter);
    }

    // Convenience
    public void forEachVertex(BiConsumer<Integer, Integer> consumer) {
        for(int i = 0; i < verticies.length; i++) {
            for(int j = 0; j < verticies[i].length; j++)
                consumer.accept(i, j);
        }
    }

    // This method links each vertex to all adjacent verticies.
    public void populateEdges() {
        forEachVertex((Integer row, Integer column) -> {
            Vertex v = verticies[row][column];
            // If we are in a row after the first.
            if(row > 0)
                v.addEdge(verticies[row-1][column], Direction.NORTH);

            // If we are in a row after the first, and a column before
            // the last.
            if(row > 0 && column < verticies[row].length - 1)
                v.addEdge(verticies[row-1][column+1], Direction.NORTH_EAST);

            // If we are in a column before the last.
            if(column < verticies[row].length - 1)
                v.addEdge(verticies[row][column+1], Direction.EAST);

            // If we are in a row that isn't the last one and we
            // aren't in the last column.
            if(column < verticies[row].length - 1 && row < verticies.length - 1)
                v.addEdge(verticies[row+1][column+1], Direction.SOUTH_EAST);

            // If we are in a row that is less than the last row.
            if(row < verticies.length - 1)
                v.addEdge(verticies[row+1][column], Direction.SOUTH);

            // If we are in a row less than the last row and we aren't
            // in the first column.
            if(row < verticies.length - 1 &&  column > 0)
                v.addEdge(verticies[row+1][column-1], Direction.SOUTH_WEST);

            // If we are not in the first column.
            if(column > 0)
                v.addEdge(verticies[row][column-1], Direction.WEST);

            //  If we are not in the first column and not in the first row
            if(column > 0 && row > 0)
                v.addEdge(verticies[row-1][column-1], Direction.NORTH_WEST);
        });
    }

    // Performs a valid depth first search at each vertex.
    public void depthFirstSearch(int row, int column) {
        // Inititalize a stack.
        LinkedList<DFSStackItem> stack = new LinkedList<DFSStackItem>();
        // Use the 2D array to get direct access to our start vertex.
        Vertex startVertex = verticies[row][column];

        // Some useful variables.
        String newString =  startVertex.getLetter() + "";
        Vertex toVertex = null;
        Direction d = Direction.ANY;

        // Push our start vertex.
        stack.push(new DFSStackItem(startVertex, d, newString));

        while(!stack.isEmpty())
        {
            DFSStackItem item = stack.pop();
            Vertex vertex = item.getVertex();

            // This will be used the first time only.
            if(item.getDirection() == Direction.ANY) {

                Iterator iterator = vertex.getEdges().entrySet().iterator();
                while(iterator.hasNext()) {
                    Map.Entry pair = (Map.Entry)iterator.next();
                    toVertex = (Vertex)pair.getValue();
                    newString = item.getCurrentString() + toVertex.getLetter();
                    d = (Direction) pair.getKey();
                    stack.push(new DFSStackItem(toVertex, d,
                        newString));
                }
                continue; // Done with this iteration. Go on to next one.
                // Note: this could be easily done with an if/else block
                // as well, however identations/formatting looked terrible
                // becaue there are really long lines. Continue seemed like the
                // next best option.
            }

            if(vertex.getEdges().containsKey(item.getDirection())) {
                toVertex = vertex.getEdges().get(item.getDirection());
                newString = item.getCurrentString() + toVertex.getLetter();

                // If we find a word, print it ASAP.
                if(newString.length() > 3 &&
                    getDictionary().contains(newString)) {
                    System.out.printf(
                        "%s (%d,%d,%s)\n",
                        newString, column + 1, row + 1, item.getDirection());
                }

                stack.push(new DFSStackItem(toVertex, item.getDirection(),
                    newString));
            }
        }
    }

    // Private internal class used for the DFS stack.
    class DFSStackItem {
        // The Vertex we are going to do our DFS at.
        private Vertex vertex;
        // The current direction we're traveling.
        private Direction direction;
        // The sequence of characters thus far.
        private String currentString;

        // Accessors
        public void setVertex(Vertex v) { vertex = v; }
        public Vertex getVertex() { return vertex; }

        public void setDirection(Direction d) { direction = d; }
        public Direction getDirection() { return direction; }

        public void setCurrentString(String s) { currentString = s; }
        public String getCurrentString() { return currentString; }

        // Constructor
        public DFSStackItem(Vertex v, Direction d, String s) {
            setVertex(v);
            setDirection(d);
            setCurrentString(s);
        }
    }
}
