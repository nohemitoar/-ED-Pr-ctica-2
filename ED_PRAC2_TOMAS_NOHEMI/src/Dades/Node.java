package Dades;

public class Node<E> {
	private E element;
	private Node<E> seg;
	
	public Node(E e) {
		element=e;
		seg=null;
	}

	public E getElement() {
		return element;
	}
	
	public void setElement(E e) {
		element=e;
	}

	public Node<E> getSeg() {
		return seg;
	}

	public void setSeg(Node<E> seg) {
		this.seg = seg;
	}
}
