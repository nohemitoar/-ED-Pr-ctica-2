package Llistes;

import java.util.Arrays;
import java.util.Iterator;
import Classes.*;
import Dades.*;
import Excepcions.*;

public class LlistaGenericaExcursions implements TADLlistaGenerica<Excursio>, Iterable<Excursio> {
	private Node<Excursio> primer;
	private Node<Excursio> ultim;
	
	public LlistaGenericaExcursions() {
		primer=null;
		ultim=null;
	}

	public void inserir (Excursio exc) throws LlistaPlena {
		if (primer == null) {
			Node<Excursio> aux = new Node<Excursio>(exc);
			primer = aux;
			ultim = aux;
		} else {
			Node<Excursio> nodeNou = new Node<Excursio>(exc);
			Node<Excursio> nodeActual = primer;
			Node<Excursio> nodeAnterior = null;
			
			Excursio excNova = nodeNou.getElement();
			Excursio excActual = nodeActual.getElement();
			
			while (excActual.compareTo(excNova) < 0 && nodeActual!=ultim) {
				nodeAnterior=nodeActual;
				nodeActual=nodeActual.getSeg();
				excActual=nodeActual.getElement();			
			}
			
			if (nodeActual==primer) {
				if (primer == ultim) {
					if (excActual.compareTo(excNova) < 0) {
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
				if (excActual.compareTo(excNova)<0) {
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
	
	
	
	public Excursio[] consultar(int codi) throws LlistaBuida{
		if (primer==null) return null;
		
		Node<Excursio> nodeActual = primer;
		Excursio excActual = nodeActual.getElement();
		Excursio[] ll_exc= new Excursio[2];
		
		while(nodeActual!=ultim) {
			if(codi==excActual.getCodi()) {
				ll_exc[0]=excActual;
				
				nodeActual=nodeActual.getSeg();
				excActual=nodeActual.getElement();
				while(nodeActual!=ultim) {
					if(codi==excActual.getCodi()) {
						ll_exc[1]=excActual;
						return ll_exc;
					}
					nodeActual=nodeActual.getSeg();
					excActual=nodeActual.getElement();
				}
				if (codi == excActual.getCodi()) {
					ll_exc[1]=excActual;
					return ll_exc;
				}
				return ll_exc;
			}
			nodeActual=nodeActual.getSeg();
			excActual=nodeActual.getElement();
		}
		if (codi == excActual.getCodi()) {
			ll_exc[0] = excActual;
			return ll_exc;
		}
		else return null;
	}
	
	public boolean esborrarElement (Excursio exc) throws LlistaBuida {
		return false;
		
	}
	
	public Excursio existeix (Excursio exc) {
		if (primer==null) return null;
		
		Node<Excursio> nodeActual = primer;
		Excursio excActual = nodeActual.getElement();
		while(excActual.compareTo(exc)<0 && nodeActual!=ultim) {
			if (exc.equals(excActual)) return excActual;
			nodeActual=nodeActual.getSeg();
			excActual=nodeActual.getElement();
		}
		
		if (exc.equals(excActual)) return excActual;	
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
		return "LlistaGenericaExcursios [primer=" + primer + ", ultim=" + ultim + "]";
	}

	@Override
	public Iterator<Excursio> iterator() {
		IteratorLlistaGenerica<Excursio> elementI=new IteratorLlistaGenerica<Excursio>(primer, ultim);
		return elementI;
	}
}
