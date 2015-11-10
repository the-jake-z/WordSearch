/*
 * 		Project: 	Word Search (CS 360 Fall 2015, Project 3)
 * 		File:		PuzzleSolver.java
 * 		Author:		Jacob A. Zarobsky
 * 		Date:		Nov 5, 2015
 *
 * 		This file is the main entry point for the program.
 *      The program reads in a file and then finds the words
 *      hidden inside of that file.
 */

 import java.util.ArrayList;
 import java.util.Collections;
 import java.util.List;
 import java.util.HashSet;

 public class PuzzleSolver
 {
     private Graph graph;
     private HashSet<String> dictionary;

     public void setGraph(Graph g) { graph = g; }
     public Graph getGraph() { return graph; }

     public void setDictionary(HashSet<String> d) { dictionary = d; }
     public HashSet<String> getDictionary() { return dictionary; }

     public PuzzleSolver(Graph graph, HashSet<String> dictionary)
     {
         setGraph(graph);
         setDictionary(dictionary);
     }

     public void solve()
     {
         graph.setDictionary(getDictionary());
         boolean seenValue = true;
         // For every vertex.
         for(int i = 0; i < getGraph().getVerticies().length; i ++)
         {
             for(int j = 0; j < getGraph().getVerticies()[i].length; j++)
             {
                 getGraph().depthFirstSearch(i, j);
             }
         }
     }

 }
