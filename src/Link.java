/**
 * A single Link in a Doubly LinkedList
 * @author Daniel Margalit
 * @author Noy Sujaz
 */
public class Link {
	private Object data;;
	private Link next;
	private Link prev;
	
	public Link(Object _data, Link _next, Link _prev) {
		data = _data;
		next = _next;
		prev = _prev;
	
	}
	
	public void add(Object _data) {
		next = new Link(_data, null, this);
	}
	
	public Link getNext() {
		return next;
	}
	
	public Link getPrev() {
		return prev;
	}
	
	public Object getData() {
		return data;
	}

	public void setNext(Link _next) {
		next = _next;
		
	}

}
