/*
 * 		Project: 	Word Search (CS 360 Fall 2015, Project 3)
 * 		File:		Edge.java
 * 		Author:		Jacob A. Zarobsky
 * 		Date:		Nov 6, 2015
 *
 * 		This class stores all the data associated with an edge.
 */

 public class Edge
 {
     // The direction to get to the to vertex from whoever
     // initalized us.
     private Direction direction;

     // The vertex this edge takes us to.
     private Vertex toVertex;

     // Property Accessors
     public void setDirection(Direction d) { direction = d; }
     public Direction getDirection() { return direction; }

     public void setToVertex(Vertex v) { toVertex = v; }
     public Vertex getToVertex() { return toVertex; }

     // Constructor
     public Edge(Vertex toVertex, Direction d )
     {
         setToVertex(toVertex);
         setDirection(d);
     }
 }
