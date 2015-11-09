/*
 * 		Project: 	Word Search (CS 360 Fall 2015, Project 3)
 * 		File:		Graph.java
 * 		Author:		Jacob A. Zarobsky
 * 		Date:		Nov 5, 2015
 *
 * 		This file stores all the data for the graph used to solve the
 *      puzzle. It also contains methods to traverse the graph.
 */

public class Graph
{
    private Vertex[][] verticies;

    public void setVerticies(Vertex[][] v) { verticies = v; }
    public Vertex[][] getVerticies() { return verticies; }

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

    public Tree depthFirstSearch(Vertex startVertex)
    {
        LinkedList<Node> stack = new LinkedList<Node>();

        for(int i = 0; i < verticies.length; i++)
        {
            for(int j = 0; j < verticies[i].length; j++)
            {
                vertex[i][j].setSeen(false);
            }
        }

        stack.push(startVertex);

        while(!stack.isEmpty())
        {
            Node node = stack.pop();
            Vertex v = node.getVertex();

            for(Edge e : v.getEdges())
            {
                Vertex toVertex = e.getToVertex();
                if(!toVertex.getSeen())
                {
                    toVertex.setSeen(true);
                    Node childNode = new Node(node, toVertex);
                    node.addChild(childNode);
                    stack.push(childNode);
                }
            }
        }
    }
}
