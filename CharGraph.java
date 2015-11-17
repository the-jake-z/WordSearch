import java.util.HashSet;
import java.util.function.BiConsumer;

public class CharGraph {
    private char[][] characters;
    private StringBuilder stringBuilder;
    private HashSet<String> dictionary;

    public char[][] getCharacters() { return characters; }
    public void setCharacters(char[][] c) { characters = c; }

    public StringBuilder getStringBuilder() {
        if(stringBuilder == null) stringBuilder = new StringBuilder();
        return stringBuilder;
    }

    public void setStringBuilder(StringBuilder s) { stringBuilder = s; }

    public HashSet<String> getDictionary() { return dictionary; }
    public void setDictionary(HashSet<String> d) { dictionary = d; }

    public void setCharacter(int row, int column, char c) {
        if(characters != null && characters[row] != null)
            characters[row][column] = c;
    }

    public CharGraph(int size) {
        characters = new char[size][size];
    }

    public void forEachIndex(BiConsumer<Integer, Integer> consumer) {
        for(int i = 0; i < getCharacters().length; i++) {
            for(int j = 0; j < getCharacters()[i].length; j++) {
                consumer.accept(i, j);
            }
        }
    }

    public int getSize() { return getCharacters().length; }

    // This modified version of depth first search starts at a given
    // row, column, and travels in strictly 1 direction.
    public void depthFirstSearch(int row, int column, int dx, int dy) {
        int currentRow = row, currentColumn = column;

        StringBuilder stringBuilder = getStringBuilder();
        char[][] characters = getCharacters();

        stringBuilder.setLength(0);

        while(currentRow >= 0 && currentRow < getSize()
            && currentColumn >= 0 && currentColumn < getSize())
        {
            stringBuilder.append(characters[currentRow][currentColumn]);

            if(stringBuilder.length() > 3 ) {
                String checkString = stringBuilder.toString();
                if(getDictionary().contains(checkString))
                {
                    System.out.printf(
                        "%s (%d,%d,%s)\n",
                        checkString, column + 1, row + 1, direction(dx, dy));
                }

                checkString = null;
            }

            currentRow += dx;
            currentColumn += dy;
        }
    }

    private String direction(int dx, int dy) {
        StringBuilder direction = new StringBuilder();

        if(dx == -1) direction.append("n");
        if(dx == 1) direction.append("s");

        if(dy == -1) direction.append("w");
        if(dy == 1) direction.append("e");

        return direction.toString();
    }
}
