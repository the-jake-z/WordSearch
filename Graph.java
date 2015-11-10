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
import java.util.function.*;
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

    public void populateEdges()
    {
        // Traverse the rows.
        for(int i = 0; i < verticies.length; i++)
        {
            // Traverse the columns.
            for(int j = 0; j < verticies[i].length; j++)
            {
                Vertex v = verticies[i][j];

                // Add the "North Edge"
                if(i > 0)
                    v.addEdge(verticies[i-1][j], Direction.NORTH);

                // Add the "North East Edge"
                if(i > 0 && j < verticies[i].length - 1)
                    v.addEdge(verticies[i-1][j+1], Direction.NORTH_EAST);

                // Add the "East Edge"
                if(j < verticies[i].length - 1)
                    v.addEdge(verticies[i][j+1], Direction.EAST);

                // Add the South East Edge
                if(j < verticies[i].length - 1 && i < verticies.length - 1)
                    v.addEdge(verticies[i+1][j+1], Direction.SOUTH_EAST);

                // Add the "South Edge"
                if(i < verticies.length - 1)
                    v.addEdge(verticies[i+1][j], Direction.SOUTH);

                // Add the "South West Edge"
                if(i < verticies.length - 1 &&  j > 0)
                    v.addEdge(verticies[i+1][j-1], Direction.SOUTH_WEST);

                // Add the "West Edge"
                if(j > 0)
                    v.addEdge(verticies[i][j-1], Direction.WEST);

                // Add the Northwest Edge
                if(j > 0 && i > 0)
                    v.addEdge(verticies[i-1][j-1], Direction.NORTH_WEST);
            }
        }
    }

    // Private internal class used for the DFS stack.
    class DFSStackItem
    {
        private Vertex vertex;
        private Direction direction;
        private String currentString;

        public void setVertex(Vertex v) { vertex = v; }
        public Vertex getVertex() { return vertex; }

        public void setDirection(Direction d) { direction = d; }
        public Direction getDirection() { return direction; }

        public void setCurrentString(String s) { currentString = s; }
        public String getCurrentString() { return currentString; }

        public DFSStackItem(Vertex v, Direction d, String s)
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

        for(int i = 0; i < verticies.length; i++)
        {
            for(int j = 0; j < verticies[i].length; j++)
            {
                verticies[i][j].setSeen(false);
            }
        }

        stack.push(new DFSStackItem(
                startVertex, Direction.ANY, startVertex.getLetter()));

        while(!stack.isEmpty())
        {
            DFSStackItem item = stack.pop();
            Vertex v = item.getVertex();

            for(Edge e : v.getEdges())
            {
                if(e.getDirection() == item.getDirection() ||
                    item.getDirection() == Direction.ANY)
                {
                    Vertex toVertex = e.getToVertex();

                    if(!toVertex.getSeen())
                    {
                        String newString = String.format("%s%s", item.getCurrentString(),
                            toVertex.getLetter());

                        if(newString.length() > 3)
                        {
                            if(getDictionary().contains(newString))
                            {
                                System.out.printf("%s %s %d %d\n", newString, item.getDirection(),
                                    row, column);
                            }
                        }

                        toVertex.setSeen(true);
                        stack.push(new DFSStackItem(toVertex, e.getDirection(),
                            newString));
                    }
                }
            }
        }
    }
}
