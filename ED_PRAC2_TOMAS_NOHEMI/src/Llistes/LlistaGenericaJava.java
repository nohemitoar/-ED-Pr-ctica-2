package Llistes;

import java.util.LinkedList;
import java.util.Iterator;

import Dades.TADLlistaGenerica;
import Excepcions.*;

public class LlistaGenericaJava<E> implements TADLlistaGenerica<E>{
	private LinkedList<E> llista = new LinkedList<E>();

	@Override
	public Iterator<E> iterator() {
		return llista.iterator();
	}

	@Override
	public boolean inserir(E e) {
		return llista.add(e);
	}

	@Override
	public E consultar(int index) throws LlistaBuida {
		E aux = null;
		if (llista.isEmpty()) throw new LlistaBuida();
		else aux=llista.get(index);
		return aux;
	}

	@Override
	public boolean esborrarElement(E e) throws LlistaBuida {
		boolean esborrat = false;
		if (llista.isEmpty()) throw new LlistaBuida();
		else {
			esborrat = llista.remove(e);
		}
		return esborrat;
	}

	@Override
	public boolean existeixElement (E e) {
		return llista.contains(e);
	}

	@Override
	public int getIndex(E e) {
		int ind=0;
		ind=llista.indexOf(e);
		return ind;
	}
	
	public TADLlistaGenerica<E> getLlista() throws LlistaBuida, LlistaPlena{
		TADLlistaGenerica<E> aux = new LlistaGenericaJava<E>();
		if(llista.isEmpty()) throw new LlistaBuida();
		else {
			for (int i=0; i<llista.size(); i++) {
				aux.inserir(llista.get(i));
			}
			return aux;
		}
	}
	
	@Override
	public boolean esBuida() {
		return llista.isEmpty();
	}

	@Override
	public int getNumEle() {
		return llista.size();
	}
}