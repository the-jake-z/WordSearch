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

    private ArrayList<String> dictionary;

    public void setPuzzleSource(String pSource) { puzzleSource = pSource; }
    public String getPuzzleSource() { return puzzleSource;}

    public void setWordSource(String wSource) { wordSource = wSource; }
    public String getWordSource() { return wordSource; }

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
                // Do something with the count.
            }
            else
            {
                StringTokenizer tokenizer =
                    new StringTokenizer(line, delimiters);

                while(tokenizer.hasMoreTokens())
                {
                    System.out.println(tokenizer.nextToken());
                }
            }
        });

        fileReader.setFilePath(getWordSource());

        fileReader.forEachLine((String line, Integer lineNumber) ->
        {
            getDictionary().add(line);
        });

        // Sort the dictionary so it's ready for use.
        Collections.sort(dictionary);
    }
}
