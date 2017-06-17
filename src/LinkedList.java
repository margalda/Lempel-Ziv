/**
 * A Doubly LinkedList
 * @author Daniel Margalit
 * @author Noy Sujaz
 */
public class LinkedList {
	private Link first;
	private int length;
	private Link tail;
	
	public LinkedList() {
		first = null;
		length = 0;
	}
	
	public boolean isEmpty() {
		return (first == null);
	}
	
	public void add(Object _data){
		if (isEmpty()){
			first = new Link(_data, null, null);
			tail = first;
		}
		else {
			tail.add(_data);
			tail = tail.getNext();
		}
		length = length+1;
	}
	
	public Link getFirst(){
		return first;
	}
	public Link getTail(){
		return tail;
	}

	public int getLength() {
		return length;
	}

	public void RemoveTail(){
		tail = tail.getPrev();
		tail.setNext(null);
		length = length-1;
	}
}
