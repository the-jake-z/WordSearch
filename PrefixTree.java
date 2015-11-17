import java.util.HashMap;

public class PrefixTree {
    private HashMap<Character, Node> roots;

    public Node getRoot(char c) {
        if(roots == null) {
            roots = new HashMap<Character, Node>();
        }

        if(!roots.containsKey(c)) {
            roots.put(c, new Node(c));
        }

        return roots.get(c);
    }

    public void insert(String s) {

        Node current = getRoot(s.charAt(0));

        for(int i = 1; i < s.length(); i++) {
            char c = s.charAt(i);
            if(current.getChildren().containsKey(c))
                current = current.getChildren().get(c);
            else {
                Node newNode = new Node(c);
                current.getChildren().put(c, newNode);
                current = newNode;
            }

            if(i == (s.length() - 1)) current.setEndOfWord(true);
        }
    }

    public Node lookup(Node n, char c) {
        if(n.getChildren().containsKey(c)) return n.getChildren().get(c);

        return null;
    }
}
