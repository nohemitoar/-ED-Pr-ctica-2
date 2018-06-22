package Llistes;

import java.util.ArrayList;
import java.util.Iterator;

import Dades.TADLlistaGenerica;
import Excepcions.*;

public class LlistaGenericaJava<E> implements TADLlistaGenerica<E>{
	private ArrayList<E> llista = new ArrayList<E>();

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void inserir(E e) throws LlistaPlena {
		// TODO Auto-generated method stub
		
	}

	@Override
	public E[] consultar(int i) throws LlistaBuida {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean esborrarElement(E e) throws LlistaBuida {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public E existeix(E e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean esPlena() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean esBuida() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
