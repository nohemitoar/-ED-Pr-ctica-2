package Llistes;

import java.util.Arrays;
import java.util.Iterator;
import Classes.*;
import Dades.*;
import Excepcions.*;

public class LlistaGenerica<E> implements TADLlistaGenerica<E>, Iterable<E> {
	private Node<E> primer;
	private Node<E> ultim;
	
	public LlistaGenerica() {
		primer=null;
		ultim=null;
	}

	public void inserir (E e) throws LlistaPlena {
		if (primer == null) {
			Node<E> aux = new Node<E>(e);
			primer = aux;
			ultim = aux;
		} else {
			Node<E> nodeNou = new Node<E>(e);
			Node<E> nodeActual = primer;
			Node<E> nodeAnterior = null;
			
			E eNou = nodeNou.getElement();
			E eActual = nodeActual.getElement();
			
			while (eActual.compareTo(eNou) < 0 && nodeActual!=ultim) {
				nodeAnterior=nodeActual;
				nodeActual=nodeActual.getSeg();
				eActual=nodeActual.getElement();			
			}
			
			if (nodeActual==primer) {
				if (primer == ultim) {
					if (eActual.compareTo(eNou) < 0) {
						ultim=nodeNou;
						primer.setSeg(nodeNou);
					} else { 
						primer=nodeNou;
						nodeNou.setSeg(ultim);
					}
				} else {
					nodeNou.setSeg(primer);
					primer=nodeNou;
				}
			} else if (nodeActual == ultim) {
				if (eActual.compareTo(eNou)<0) {
					ultim.setSeg(nodeNou);
					ultim=nodeNou;
				} else {
					nodeAnterior.setSeg(nodeNou);
					nodeNou.setSeg(nodeActual);
				}
			} else {
				nodeAnterior.setSeg(nodeNou);
				nodeNou.setSeg(nodeActual);
			}
		}
	}
	
	
	
	public E[] consultar(int codi) throws LlistaBuida{
		if (primer==null) return null;
		
		Node<E> nodeActual = primer;
		E eActual = nodeActual.getElement();
		E[] ll_e= new E[2];
		
		while(nodeActual!=ultim) {
			if(codi==eActual.getCodi()) {
				ll_e[0]=eActual;
				
				nodeActual=nodeActual.getSeg();
				eActual=nodeActual.getElement();
				while(nodeActual!=ultim) {
					if(codi==eActual.getCodi()) {
						ll_e[1]=eActual;
						return ll_e;
					}
					nodeActual=nodeActual.getSeg();
					eActual=nodeActual.getElement();
				}
				if (codi == eActual.getCodi()) {
					ll_e[1]=eActual;
					return ll_e;
				}
				return ll_e;
			}
			nodeActual=nodeActual.getSeg();
			eActual=nodeActual.getElement();
		}
		if (codi == eActual.getCodi()) {
			ll_e[0] = eActual;
			return ll_e;
		}
		else return null;
	}
	
	public boolean esborrarElement (E e) throws LlistaBuida {
		return false;
		
	}
	
	public E existeix (E e) {
		if (primer==null) return null;
		
		Node<E> nodeActual = primer;
		E eActual = nodeActual.getElement();
		while(eActual.compareTo(e)<0 && nodeActual!=ultim) {
			if (e.equals(eActual)) return eActual;
			nodeActual=nodeActual.getSeg();
			eActual=nodeActual.getElement();
		}
		
		if (e.equals(eActual)) return eActual;	
		else return null;
	}
	
	public boolean esPlena() {
		return false;
	} 
	
	public boolean esBuida() {
		return primer==null;
	}

	@Override
	public String toString() {
		return "LlistaGenericaEs [primer=" + primer + ", ultim=" + ultim + "]";
	}

	@Override
	public Iterator<E> iterator() {
		IteratorLlistaGenerica<E> elementI=new IteratorLlistaGenerica<E>(primer, ultim);
		return elementI;
	}
}
