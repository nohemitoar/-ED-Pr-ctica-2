package Llistes;

import java.util.Arrays;
import java.util.Iterator;
import Classes.*;
import Dades.*;
import Excepcions.*;

public class LlistaGenericaActivitats implements TADLlistaGenerica<Activitat>, Iterable<Activitat> {
	private Node<Activitat> primer;
	private Node<Activitat> ultim;
	
	public LlistaGenericaActivitats() {
		primer=null;
		ultim=null;
	}

	public void inserir (Activitat act) throws LlistaPlena {
		if (primer == null) {
			Node<Activitat> aux = new Node<Activitat>(act);
			primer = aux;
			ultim = aux;
		} else {
			Node<Activitat> nodeNou = new Node<Activitat>(act);
			Node<Activitat> nodeActual = primer;
			Node<Activitat> nodeAnterior = null;
			
			Activitat actNova = nodeNou.getElement();
			Activitat actActual = nodeActual.getElement();
			
			while (actActual.compareTo(actNova) < 0 && nodeActual!=ultim) {
				nodeAnterior=nodeActual;
				nodeActual=nodeActual.getSeg();
				actActual=nodeActual.getElement();			
			}
			
			if (nodeActual==primer) {
				if (primer == ultim) {
					if (actActual.compareTo(actNova) < 0) {
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
				if (actActual.compareTo(actNova)<0) {
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
	
	
	
	public Activitat[] consultar(int codi) throws LlistaBuida{
		if (primer==null) return null;
		
		Node<Activitat> nodeActual = primer;
		Activitat actActual = nodeActual.getElement();
		Activitat[] ll_act= new Activitat[2];
		
		while(nodeActual!=ultim) {
			if(codi==actActual.getCodi()) {
				ll_act[0]=actActual;
				
				nodeActual=nodeActual.getSeg();
				actActual=nodeActual.getElement();
				while(nodeActual!=ultim) {
					if(codi==actActual.getCodi()) {
						ll_act[1]=actActual;
						return ll_act;
					}
					nodeActual=nodeActual.getSeg();
					actActual=nodeActual.getElement();
				}
				if (codi == actActual.getCodi()) {
					ll_act[1]=actActual;
					return ll_act;
				}
				return ll_act;
			}
			nodeActual=nodeActual.getSeg();
			actActual=nodeActual.getElement();
		}
		if (codi == actActual.getCodi()) {
			ll_act[0] = actActual;
			return ll_act;
		}
		else return null;
	}
	
	public boolean esborrarElement (Activitat act) throws LlistaBuida {
		return false;
		
	}
	
	public Activitat existeix (Activitat act) {
		if (primer==null) return null;
		
		Node<Activitat> nodeActual = primer;
		Activitat actActual = nodeActual.getElement();
		while(actActual.compareTo(act)<0 && nodeActual!=ultim) {
			if (act.equals(actActual)) return actActual;
			nodeActual=nodeActual.getSeg();
			actActual=nodeActual.getElement();
		}
		
		if (act.equals(actActual)) return actActual;	
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
		return "LlistaGenericaActivitats [primer=" + primer + ", ultim=" + ultim + "]";
	}

	@Override
	public Iterator<Activitat> iterator() {
		IteratorLlistaGenerica<Activitat> elementI=new IteratorLlistaGenerica<Activitat>(primer, ultim);
		return elementI;
	}
}
