package Dades;

import java.util.Iterator;

public class IteratorLlistaGenerica<E> implements Iterator<E> {
	
	private Node<E> actual, ultim;
	
	public IteratorLlistaGenerica(Node<E> primer, Node<E> ultim){
		this.ultim = ultim;
		actual = primer;
	}

	@Override
	public boolean hasNext(){
		return (ultim.getSeg() != actual);
	}

	@Override
	public E next() {
		Node<E> aux = actual;
		actual = actual.getSeg();
		return aux.getElement();
	}

}
