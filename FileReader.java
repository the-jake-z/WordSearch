/*
* 		Project: 	Word Search (CS 360 Fall 2015, Project 3)
* 		File:		FileReader.java
* 		Author:		Jacob A. Zarobsky
* 		Date:		Nov 5, 2015
 *
 *      This class implements a file reader
 *      that uses a lamda to deal with
 *      each individual line.
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.function.BiConsumer;

public class FileReader
{
    // Properties
    private String filePath;
    private FileInputStream inputStream;
    private InputStreamReader inputStreamReader;
    private BufferedReader bufferedReader;
    private int currentLine = Integer.MIN_VALUE;

    // Accessors
    public void setFilePath(String f) { filePath = f; }
    public String getFilePath() { return filePath; }

    public void setInputStream(FileInputStream i) { inputStream = i; }
    public FileInputStream getInputStream() { return inputStream; }

    // I wish these would fit on one line without wrapping.
    public void setInputStreamReader(InputStreamReader i)
    {
        inputStreamReader = i;
    }

    public InputStreamReader getInputStreamReader()
    {
        return inputStreamReader;
    }

    public void setBufferedReader(BufferedReader r) { bufferedReader = r; }
    public BufferedReader getBufferedReader() { return bufferedReader; }

    private void setCurrentLine(int cl) { currentLine = cl; }
    private int getCurrentLine() { return currentLine; }

    // Convenience
    private void incrementLine() { currentLine ++; }

    // Constructor
    public FileReader(String path)
    {
        setFilePath(path);
    }

    // Uses a lamda to deal with every line. Thanks, Java 8
    public void forEachLine(BiConsumer<String, Integer> lambda)
    {
        try {
            // Create our input stream objects.
            setInputStream(new FileInputStream(getFilePath()));
            setInputStreamReader(new InputStreamReader
                    (getInputStream(), Charset.forName("UTF-8")));
            setBufferedReader(new BufferedReader(getInputStreamReader()));

            String line = null;
            BufferedReader reader = getBufferedReader();

            // Set our current line to 1.
            setCurrentLine(1);

            while((line = reader.readLine()) != null)
            {
                // Call the function that was passed in
                lambda.accept(line, new Integer(getCurrentLine()));
                incrementLine();
            }

            getInputStream().close();
            getInputStreamReader().close();
            getBufferedReader().close();
        }
        catch (UnsupportedEncodingException ex)
        {
            Main.exitWithError(
                "The encoding used was incompatible with the file.");
        }
        catch (FileNotFoundException ex)
        {
            Main.exitWithError("The file you entered was not found.");
        }
        catch (IOException ex)
        {
            Main.exitWithError(
                "There was an IO error while attempting to read the file.");
        }
    }
}
