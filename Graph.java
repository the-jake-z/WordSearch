import java.util.function.BiConsumer;

public class Graph {
    private char[][] letters;
    private PrefixTree tree;
    private StringBuilder stringBuilder;

    public void setLetters(char[][] c) { letters = c; }
    public char[][] getLetters() { return letters; }

    public StringBuilder getStringBuilder() {
        if(stringBuilder == null) stringBuilder = new StringBuilder();

        return stringBuilder;
    }

    public void setStringBuilder(StringBuilder sb) { stringBuilder = sb; }

    public Graph(int size) {
        setLetters(new char[size][size]);
    }

    public void setTree(PrefixTree t) { tree = t; }
    public PrefixTree getTree() { return tree; }

    public void addVertex(int row, int col, char c) {
        letters[row][col] = c;
    }

    public int getSize() { return letters.length; }

    public void forEachVertex(BiConsumer<Integer, Integer> lambda) {
        for(int i = 0; i < getSize(); i++) {
            for(int j = 0; j < getSize(); j++ ) {
                lambda.accept(i, j);
            }
        }
    }

    public void solve(int row, int col, int dx, int dy) {
        StringBuilder sb = getStringBuilder();
        char[][] letters  = getLetters();

        sb.setLength(0);

        int currentRow = row;
        int currentCol = col;
        char currentChar = letters[row][col];

        Node cursor = tree.getRoot(currentChar);

        while(cursor != null) {
            sb.append(currentChar);

            if(cursor.getEndOfWord() && sb.length() > 3) {
                System.out.printf(
                       "%s (%d,%d,%s)\n",
                       sb.toString(), col + 1, row + 1, direction(dx, dy));
            }

            currentRow += dx;
            currentCol += dy;

            if(!(currentRow >= 0 && currentRow < getSize()
                && currentCol >= 0 && currentCol < getSize())) break;

            currentChar = letters[currentRow][currentCol];
            cursor = tree.lookup(cursor, currentChar);
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
