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

     public void setData(String d) { data = d; }
     public String getData() { return data;}

     public Vertex(String data)
     {
        setData(data);
     }
 }
