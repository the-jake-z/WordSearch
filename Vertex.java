/*
 * 		Project: 	Word Search (CS 360 Fall 2015, Project 3)
 * 		File:		Vertex.java
 * 		Author:		Jacob A. Zarobsky
 * 		Date:		Nov 6, 2015
 *
 * 		This file stores all the data on a vertex.
 */

import java.util.ArrayList;

 public class Vertex
 {
     // The letter of this vertex.
     private String letter;
     // The edges leaving out of this vertex.
     private ArrayList<Edge> edges;
     // Property Accessors.
     public void setLetter(String d) { letter = d; }
     public String getLetter() { return letter; }

     public void setEdges(ArrayList<Edge> e) { edges = e; }
     public ArrayList<Edge> getEdges()
     {
         // Lazy instantiation.
         if(edges == null)
             edges = new ArrayList<Edge>();

         return edges;
     }

     // Constructor
     public Vertex(String letter)
     {
        setLetter(letter);
     }

     // Convenience method.
     public void addEdge(Vertex toVertex, Direction d)
     {
         getEdges().add(new Edge(toVertex, d));
     }
 }
