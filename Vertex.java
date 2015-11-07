/*
 * 		Project: 	Word Search (CS 360 Fall 2015, Project 3)
 * 		File:		Vertex.java
 * 		Author:		Jacob A. Zarobsky
 * 		Date:		Nov 6, 2015
 *
 * 		This file runs the WordSearch and stores all
 *      necessary data for the search.
 */

 public class Vertex
 {
     private String data;
     private ArrayList<Edge> edges;

     public void setData(String d) { data = d; }
     public String getData() { return data;}

     public void setEdges(ArrayList<Edge> e) { edges = e; }
     public ArrayList<Edge> getEdges()
     {
         if(edges == null)
         {
             edges = new ArrayList<Edge>();
         }

         return edges;
     }

     public Vertex(String data)
     {
        setData(data);
     }

     public void addEdge(Vertex toVertex, Direction d)
     {
         getEdges().add(new Vertex(toVertex, d));
     }
 }
