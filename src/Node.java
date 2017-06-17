/**
 * A single Node in a Trie
 * @author Daniel Margalit
 * @author Noy Sujaz
 */
public class Node {
	/**
	 * @param children An array contains the Nodes connected to this Node
	 * @param index The index of the Node in the Trie
	 * @param value The value of the branch this Node connected with
	 * @param depth The depth of the Node in the Trie
	 * @param parent The Node this Node connected to
	 * @param appear Number of appearances of the Node's value in the Trie 
	 */
	private Node[] children;
	private int index; 
	private char value;
	private int depth;
	private Node parent;
	private int appear;
	
	public Node(int _index, char _value, int _depth, Node _parent){
		index = _index;
		value = _value;
		children = new Node[256];
		depth = _depth;
		parent = _parent;
		appear = 1;
	}
	
	public void setIndex(int _index) {
		index = _index;
	}
	
	public int getAppear(){
		return appear;
	}
	
	public void setAppear(int _appear){
		appear = _appear;
	}

	public Node[] getChildren() {
		return children;
	}

	public void setChar(char _value) {
		value = _value;
		
	}
	public void setDepth(int _depth) {
		depth = _depth;
	}
	
	
	public Node contains(char c) {
		return children[((int)c)%256];
	}
	
	public int getDepth() {
		return depth;
	}
	
	public int getIndex() {
		return index;
	}
	
	
	public void add(Node node) {
		children[((int)node.value)%256] = node;
	}
	
	public Node getParent(){
		return parent;
	}
	
	public char getValue() {
		return value;
	}
	
}
