/*
 * 		Project: 	Word Search (CS 360 Fall 2015, Project 3)
 * 		File:		Vertex.java
 * 		Author:		Jacob A. Zarobsky
 * 		Date:		Nov 6, 2015
 *
 * 		This file stores all the data on a vertex.
 */

import java.util.EnumMap;

 public class Vertex
 {
     // The letter of this vertex.
     private String letter;
     // The edges leaving out of this vertex.
     private EnumMap<Direction, Vertex> edges;
     // Property Accessors.
     public void setLetter(String d) { letter = d; }
     public String getLetter() { return letter; }

     public void setEdges(EnumMap<Direction, Vertex> e) { edges = e; }
     public EnumMap<Direction, Vertex> getEdges()
     {
         // Lazy instantiation.
         if(edges == null)
             edges = new EnumMap<Direction, Vertex>(Direction.class);

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
         getEdges().put(d, toVertex);
     }
 }
