package Llistes;

import java.util.Iterator;
import Dades.*;
import Excepcions.*;

public class LlistaGenerica<E extends Comparable<E>> implements TADLlistaGenerica<E>, Iterable<E> {
	private Node<E> primer;
	private int num;
	
	public LlistaGenerica() {
		primer=null;
		num=0;
	}

	public boolean inserir (E e) {	
		boolean hiCap=false;
		if (esBuida()) {
			primer = new Node<E>(e, null);
			num++;
			hiCap=true;
			
		} else if (primer.getElement().compareTo(e) != 0) {
		
			Node<E> nodeNou ;
			Node<E> nodeActual = primer;
			Node<E> nodeAnterior = null;
			
			if (primer.getElement().compareTo(e) == 1) {
				nodeNou = new Node<E>(e, primer);
				primer = nodeNou;
				num++;
				hiCap=true;
			} else {
				nodeAnterior = nodeActual;
				nodeActual = nodeAnterior.getSeg();
				while ((nodeActual!=null) && (nodeActual.getElement().compareTo(e) <= 0)) {
					nodeAnterior=nodeActual;
					nodeActual=nodeAnterior.getSeg();
				}  
				if (nodeAnterior.getElement().compareTo(e)!=0) {
					nodeNou = new Node<E>(e, nodeActual);
					nodeAnterior.setSeg(nodeNou);
					num++;
					hiCap=true;
				} 
			}
		}
		return hiCap;
		/*if(existeixElement(e)) return false;
		if(esBuida()||primer.getElement().compareTo(e) > 0) primer = new Node<>(e, primer);
		else {
			Node<E> aux = primer;
			while (aux.getSeg()!=null && aux.getSeg().getElement().compareTo(e) < 0) aux=aux.getSeg();
			aux.setSeg(new Node<>(e, aux.getSeg()));
		}
		num++;
		return true;*/
	}
	
	public E consultar(int index) throws LlistaBuida{
		if (esBuida()) throw new LlistaBuida();
		
		Node<E> nodeActual = primer;
		
		if(index < getNumEle()){
			for (int i=0; i<index; i++)	nodeActual=nodeActual.getSeg();
		}
		return nodeActual.getElement();
	}
	
	public boolean esborrarElement (E e) throws LlistaBuida {
		boolean esborrat=false;
		if (esBuida()) throw new LlistaBuida();
		else {
			Node<E> nodeActual = primer;
			Node<E> nodeAnterior = null;
			
			if(nodeActual.getElement().compareTo(e)==0) {
				nodeActual=nodeActual.getSeg();
				num--;
				esborrat=true;
			} else {
				nodeAnterior=nodeActual;
				nodeActual=nodeAnterior.getSeg();
				
				while ((nodeActual!=null) && (nodeActual.getElement().compareTo(e) <= 0)) {
					nodeAnterior=nodeActual;
					nodeActual=nodeActual.getSeg();
				}
				
				if (nodeActual.getElement().compareTo(e)==0) {
					nodeAnterior.setSeg(nodeActual.getSeg());
					num--;
					esborrat=true;
				}
			}
		} 
		return esborrat;
	}
	
	public boolean existeixElement (E e) {
		boolean existeix=false;
		if (esBuida()) existeix=false;
		
		Node<E> nodeActual = primer;
		E eActual = nodeActual.getElement();
		
		while(eActual.compareTo(e)<0 && nodeActual!=null) {
			if (e.equals(eActual)) existeix=true;
			nodeActual=nodeActual.getSeg();
			eActual=nodeActual.getElement();
		}
		
		if (e.equals(eActual)) existeix=true;
		return existeix;
	}
	
	public int getIndex(E e) {
		int ind=0;
		Node<E> aux = primer;
		while (aux != null && !aux.getElement().equals(e)) {
			aux=aux.getSeg();
			ind++;
		}
		if (aux!=null) return ind;
		else return -1;
	}
	
	public TADLlistaGenerica<E> getLlista() throws LlistaBuida, LlistaPlena{
		TADLlistaGenerica<E> aux = new LlistaGenerica<E>();
		
		if (esBuida()) throw new LlistaBuida();
		else {
			Node<E> nodeNou = null;
			Node<E> nodeActual = primer;
			
			nodeNou=nodeActual.getSeg();
			while (nodeNou!=null) {
				aux.inserir(nodeActual.getElement());
				nodeActual=nodeActual.getSeg();
				nodeNou=nodeActual.getSeg();
			}
		}
		return aux;
	}
	
	public boolean esBuida() {
		return primer==null;
	}

	public int getNumEle() {
		return num;
	}
	
	@Override
	public String toString() {
		return "LlistaGenericaEs [primer=" + primer + "]";
	}

	@Override
	public Iterator<E> iterator() {
		IteratorLlistaGenerica<E> elementI=new IteratorLlistaGenerica<E>(this);
		return elementI;
	}

}
