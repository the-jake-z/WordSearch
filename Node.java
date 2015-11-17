import java.util.HashMap;

public class Node {
    private HashMap<Character, Node> children;
    private char letter;
    private boolean endOfWord;

    public HashMap<Character, Node> getChildren() { return children; }
    public void setChildren(HashMap<Character, Node> c) { children = c; }

    public char getLetter() { return letter; }
    public void setLetter(char l) { letter = l; }

    public boolean getEndOfWord() { return endOfWord; }
    public void setEndOfWord(boolean eow) { endOfWord = eow; }

    public Node(char letter) {
        setLetter(letter);
        setChildren(new HashMap<Character, Node>());
    }
}
