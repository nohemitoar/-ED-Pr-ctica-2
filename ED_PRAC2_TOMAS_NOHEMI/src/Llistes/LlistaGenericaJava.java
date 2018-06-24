package Llistes;

import java.util.LinkedList;
import java.util.Iterator;

import Dades.TADLlistaGenerica;
import Excepcions.*;

public class LlistaGenericaJava<E> implements TADLlistaGenerica<E>{
	private LinkedList<E> llista = new LinkedList<E>();

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean inserir(E e) {
		// TODO Auto-generated method stub
		return llista.add(e);
	}

	@Override
	public E consultar(int index) throws LlistaBuida {
		// TODO Auto-generated method stub
		E aux = null;
		if (llista.isEmpty()) throw new LlistaBuida();
		else aux=llista.get(index);
		return aux;
	}

	@Override
	public boolean esborrarElement(E e) throws LlistaBuida {
		// TODO Auto-generated method stub
		boolean esborrat = false;
		if (llista.isEmpty()) throw new LlistaBuida();
		else {
			esborrat = llista.remove(e);
		}
		return esborrat;
	}

	@Override
	public boolean existeixElement (E e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean esBuida() {
		// TODO Auto-generated method stub
		return llista.isEmpty();
	}

	@Override
	public int getNumEle() {
		// TODO Auto-generated method stub
		return llista.size();
	}
	
	
}
