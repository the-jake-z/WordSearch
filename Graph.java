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
import java.util.ArrayList;
import java.util.function.BiConsumer;
import java.util.Collections;
import java.util.HashSet;

public class Graph
{
    private Vertex[][] verticies;
    public HashSet<String> dictionary;

    public void setVerticies(Vertex[][] v) { verticies = v; }
    public Vertex[][] getVerticies() { return verticies; }

    public void setDictionary(HashSet<String> d) { dictionary = d; }
    public HashSet<String> getDictionary() { return dictionary; }

    public Graph(int rows, int columns)
    {
        verticies = new Vertex[rows][columns];
    }

    public void addVertex(int row, int column, String letter)
    {
        verticies[row][column] = new Vertex(letter);
    }

    public void forEachVertex(BiConsumer<Integer, Integer> consumer)
    {
        for(int i = 0; i < verticies.length; i++)
        {
            for(int j = 0; j < verticies[i].length; j++)
            {
                consumer.accept(i, j);
            }
        }
    }

    public void populateEdges()
    {
        forEachVertex((Integer row, Integer column) -> {
            Vertex v = verticies[row][column];
            // Add the "North Edge"
            if(row > 0)
                v.addEdge(verticies[row-1][column], Direction.NORTH);

            // Add the "North East Edge"
            if(row > 0 && column < verticies[row].length - 1)
                v.addEdge(verticies[row-1][column+1], Direction.NORTH_EAST);

            // Add the "East Edge"
            if(column < verticies[row].length - 1)
                v.addEdge(verticies[row][column+1], Direction.EAST);

            // Add the South East Edge
            if(column < verticies[row].length - 1 && row < verticies.length - 1)
                v.addEdge(verticies[row+1][column+1], Direction.SOUTH_EAST);

            // Add the "South Edge"
            if(row < verticies.length - 1)
                v.addEdge(verticies[row+1][column], Direction.SOUTH);

            // Add the "South West Edge"
            if(row < verticies.length - 1 &&  column > 0)
                v.addEdge(verticies[row+1][column-1], Direction.SOUTH_WEST);

            // Add the "West Edge"
            if(column > 0)
                v.addEdge(verticies[row][column-1], Direction.WEST);

            // Add the Northwest Edge
            if(column > 0 && row > 0)
                v.addEdge(verticies[row-1][column-1], Direction.NORTH_WEST);
        });
    }

    // Private internal class used for the DFS stack.
    class DFSStackItem
    {
        private Vertex vertex;
        private byte direction;
        private String currentString;

        public void setVertex(Vertex v) { vertex = v; }
        public Vertex getVertex() { return vertex; }

        public void setDirection(byte d) { direction = d; }
        public byte getDirection() { return direction; }

        public void setCurrentString(String s) { currentString = s; }
        public String getCurrentString() { return currentString; }

        public DFSStackItem(Vertex v, byte d, String s)
        {
            setVertex(v);
            setDirection(d);
            setCurrentString(s);
        }
    }

    public void depthFirstSearch(int row, int column)
    {
        LinkedList<DFSStackItem> stack = new LinkedList<DFSStackItem>();
        Vertex startVertex = verticies[row][column];

        stack.push(new DFSStackItem(
                startVertex, Direction.ANY, startVertex.getLetter()));

        while(!stack.isEmpty())
        {
            DFSStackItem item = stack.pop();
            Vertex v = item.getVertex();

            for(Edge e : v.getEdges())
            {
                Vertex toVertex = e.getToVertex();
                byte d = item.getDirection();

                if(e.getDirection() == d || d == Direction.ANY)
                {
                    String newString = item.getCurrentString() + toVertex.getLetter();

                    if(newString.length() > 3 &&
                        getDictionary().contains(newString))
                    {
                        System.out.printf(
                            "%s (%d,%d,%s)\n",
                            newString,
                            column + 1, // because not zero index
                            row + 1, // because not zero index.
                            Direction.toString(d));
                    }

                    stack.push(new DFSStackItem(toVertex, e.getDirection(),
                        newString));
                }
            }
        }
    }
}
