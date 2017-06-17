/**
 * A tree which every node in it has unlimited amount of children
 * @author Daniel Margalit 
 * @author Noy Sujaz
 */
public class Trie {
	private Node root;
	
	public Trie(){
		root = new Node(0,' ',0,null);
	}

	public Node getRoot() {
		return root;
	}
}
