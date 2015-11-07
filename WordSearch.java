/*
 * 		Project: 	Word Search (CS 360 Fall 2015, Project 3)
 * 		File:		WordSearch.java
 * 		Author:		Jacob A. Zarobsky
 * 		Date:		Nov 5, 2015
 *
 * 		This file runs the WordSearch and stores all
 *      necessary data for the search.
 */

import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Collections;

public class WordSearch
{
    private String puzzleSource;
    private String wordSource;
    private Graph graph;
    private ArrayList<String> dictionary;

    public void setPuzzleSource(String pSource) { puzzleSource = pSource; }
    public String getPuzzleSource() { return puzzleSource;}

    public void setWordSource(String wSource) { wordSource = wSource; }
    public String getWordSource() { return wordSource; }

    public void setGraph(Graph g) { graph = g; }
    public Graph getGraph() { return graph; }

    public void setDictionary(ArrayList<String> dict) { dictionary = dict; }
    public ArrayList<String> getDictionary() {
        if(dictionary == null)
        {
            dictionary = new ArrayList<String>();
        }
        return dictionary;
    }

    public WordSearch(String puzzleSource, String wordSource)
    {
        setPuzzleSource(puzzleSource);
        setWordSource(wordSource);
    }

    public void run()
    {
        initalizeSources();
    }

    private void initalizeSources()
    {
        final FileReader fileReader = new FileReader(getPuzzleSource());
        final String delimiters = " ";
        fileReader.forEachLine((String line, Integer lineNumber) ->
        {
            if(lineNumber == 1)
            {
                int size = new Integer(line);
                setGraph(new Graph(size, size));
            }
            else
            {
                StringTokenizer tokenizer =
                    new StringTokenizer(line, delimiters);

                int count = 0;
                while(tokenizer.hasMoreTokens())
                {
                    graph.addVertex(lineNumber - 2, count,
                        tokenizer.nextToken());
                    count++;
                }
            }
        });

        // Add in all the edges that we didn't do.
        graph.populateEdges();

        for(int i = 0; i < graph.getVerticies().length; i++)
        {
            for(int j = 0; j < graph.getVerticies()[i].length; j++)
            {
                Vertex v = graph.getVerticies()[i][j];
                for(Edge e : v.getEdges())
                {
                    System.out.println(v.getLetter() + ": " + e.getToVertex().getLetter());
                }
            }
        }

        fileReader.setFilePath(getWordSource());

        fileReader.forEachLine((String line, Integer lineNumber) ->
        {
            getDictionary().add(line);
        });

        // Sort the dictionary so it's ready for use.
        Collections.sort(dictionary);
    }
}
