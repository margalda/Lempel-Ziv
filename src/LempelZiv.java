/**
 * LZ implementation.
 * @author Daniel Margalit
 * @author Noy Sujaz
 */
public class LempelZiv implements LZ {
	/**
	 * @param lastV the last Node getMaxPrefix returned
	 * @param maxDepth Node with largest depth (longest prefix)
	 * @param pairs A Doubly LinkedList of pairs representing the input from the last encode
	 * @param tree The tree we used in the last encode
	 */
	Node lastV;
	Node maxDepth;
	LinkedList pairs;
	Trie tree;
	
	public void encode(int[] input) {
		char[] tArr = new char[input.length];
		for (int i = 0; i < tArr.length; i++)
			tArr[i] = (char)(input[i]+48);
		encode(new String(tArr));
	}
	
	public void encode(String input) {
		int index = 0;
		char temp;
		Pair pair;
		tree = new Trie();
		LinkedList tPairs = new LinkedList();
		maxDepth = tree.getRoot();
		
		while (input.length() > 0){
			index = index + 1;
			lastV = getMaxPrefix(tree.getRoot(), input);
			int depth = lastV.getDepth();
			if (depth > maxDepth.getDepth())
				maxDepth = lastV;
			if (input.length() > depth){
				temp = input.charAt(depth);
				Node newNode = new Node(index, temp,depth+1, lastV);
				lastV.add(newNode);
				if (newNode.getDepth() > maxDepth.getDepth()) //updating maxNode
					maxDepth = newNode;
				input = input.substring(depth+1);
				pair = new Pair(lastV.getIndex(),temp);
				tPairs.add(pair);
			}
			else{
				temp = '*';
				pair = new Pair(lastV.getIndex(),temp);
				tPairs.add(pair);
				break;
			}
			
		}
		pairs = tPairs;
		
	}
	/**
	 * Finds the longest prefix of text in the tree
	 * @param v The node from which we start the search
	 * @param text A prefix we want to turn into a pair
	 * @return The node whos value is the last char in text
	 */
	private Node getMaxPrefix(Node v, String text){
		int counter = 0;
		char c = text.charAt(counter);
		Node next = v.contains(c);
		if(text.length() <= 1 && next != null){
			next.setAppear(next.getAppear() + 1);
			return next;
		}
		while (next != null){
			next.setAppear(next.getAppear() + 1);
			v = next;
			counter = counter + 1;
			if (counter == text.length())
				break;
			c = text.charAt(counter);
			next = v.contains(c);
			v.setDepth(counter);
		}
		return v;
	}
	
	public Pair[] getPairs() {
		if (pairs.isEmpty())
			return null;
		Link pair = pairs.getFirst();
		int size = pairs.getLength();
		Pair[] ans = new Pair[size];
		for (int i = 0; i < size; i++){
			ans[i] = (Pair)pair.getData();
			pair = pair.getNext();
		}
		return ans;	
	}

	public String reconstruct(Pair[] pairs) {
		String ans = "";
		int index=1;
		String[] arr = new String[pairs.length+1];
		arr[0] = "";
		
		for (int j = 0; j< pairs.length; j++){
			int i = pairs[j].getIndex();
			char x = pairs[j].getValue();
			if (x != '*'){
				arr[index] = arr[i] + x;
				ans = ans + arr[i] + x;
			}
			else
				ans = ans + arr[i];

			index = index + 1;
		}
		
		return ans;
	}

	public String reconstruct() {
		Pair[] arr = getPairs();
		if (arr == null)
			return null;
		return reconstruct(arr);
	}

	public void add(String input) {
		char temp;
		Pair pair;
		int index = pairs.getLength();
		char tailVal = ((Pair)pairs.getTail().getData()).getValue();
		if (tailVal == '*'){ //remove the last pair and connect a new node with the first char of the input to v
			pairs.RemoveTail();
			Node oldV = lastV; 
			lastV = getMaxPrefix(lastV, input);
			int depth = lastV.getDepth();
			if (oldV.equals(lastV)){
				 temp = input.charAt(0);
				 Node newNode = new Node(index, temp, depth+1, lastV);
				 lastV.add(newNode);
				 if (newNode.getDepth() > maxDepth.getDepth()) //updating maxNode
						maxDepth = newNode;
				 pair = new Pair(lastV.getIndex(),temp);
				 pairs.add(pair);//edited
			 }
			 else{
				 temp = '*';
				 pair = new Pair(lastV.getIndex(),temp);
				 pairs.add(pair);
			 }
			 input = input.substring(1);
		}
		
		while (input.length() > 0){ //regular encode, with the old index and tree
			index = index + 1;
			lastV = getMaxPrefix(tree.getRoot(), input);
			 int depth = lastV.getDepth();
			 if (input.length() > depth){
				 temp = input.charAt(depth);
				 Node newNode = new Node(index, temp, depth+1, lastV);
				 lastV.add(newNode);
				 if (newNode.getDepth() > maxDepth.getDepth()) //updating maxNode
						maxDepth = newNode;
				 input = input.substring(depth+1);
				 pair = new Pair(lastV.getIndex(),temp);
				 pairs.add(pair);
			 }
			 else{
				 temp = '*';
				 pair = new Pair(lastV.getIndex(),temp);
				 pairs.add(pair);
				 break;
			 }
		}
	}

	public int codes(String prefix) {
		Node currNode = tree.getRoot();
		for(int i = 0; i < prefix.length(); i++)
			currNode = currNode.contains(prefix.charAt(i));
		
		if(currNode == null)
			return 0;
		else
			return currNode.getAppear();
	}

	public int maxCodeLength() {
		return maxDepth.getDepth();
	}

	public String maxCode() {
		Node currNode = maxDepth;
		String ans = "";
		while (currNode.getParent() != null){
			ans = currNode.getValue() + ans;
			currNode = currNode.getParent();
		}	
		return ans;
	}

	public int[] randomBinarySeries(int length, double ratio) {
		int[] ans = new int[length];
		for (int i = 0; i < length; i++){
			double random = Math.random();
			if (random < ratio)
				ans[i] = 0;
			else
				ans[i] = 1;
		}
		
		return ans;
	}

}
