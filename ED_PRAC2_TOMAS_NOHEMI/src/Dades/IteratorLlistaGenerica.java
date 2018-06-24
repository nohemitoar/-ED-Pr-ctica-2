package Dades;

import java.util.Iterator;

import Excepcions.LlistaBuida;
import Llistes.LlistaGenerica;

public class IteratorLlistaGenerica<E extends Comparable<E>> implements Iterator<E> {
	private LlistaGenerica<E> ll;
	private int index;
	
	public IteratorLlistaGenerica(LlistaGenerica<E> ll){
		this.ll=ll;
		index=0;
	}

	@Override
	public boolean hasNext(){
		return (index<ll.getNumEle());
	}

	@Override
	public E next() {
		E aux = null;
		try {
			aux = ll.consultar(index);
		} catch (LlistaBuida e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		index++;
		return aux;
	}

}
