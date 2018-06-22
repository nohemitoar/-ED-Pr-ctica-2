package Llistes;

import java.util.Arrays;
import java.util.Iterator;
import Classes.*;
import Dades.*;
import Excepcions.*;

public class LlistaGenericaTuristes implements TADLlistaGenerica<Turista>, Iterable<Turista> {
	private Node<Turista> primer;
	private Node<Turista> ultim;
	
	public LlistaGenericaTuristes() {
		primer=null;
		ultim=null;
	}

	public void inserir (Turista tur) throws LlistaPlena {
		if (primer == null) {
			Node<Turista> aux = new Node<Turista>(tur);
			primer = aux;
			ultim = aux;
		} else {
			Node<Turista> nodeNou = new Node<Turista>(tur);
			Node<Turista> nodeActual = primer;
			Node<Turista> nodeAnterior = null;
			
			Turista turNou = nodeNou.getElement();
			Turista turActual = nodeActual.getElement();
			
			while (turActual.compareTo(turNou) < 0 && nodeActual!=ultim) {
				nodeAnterior=nodeActual;
				nodeActual=nodeActual.getSeg();
				turActual=nodeActual.getElement();			
			}
			
			if (nodeActual==primer) {
				if (primer == ultim) {
					if (turActual.compareTo(turNou) < 0) {
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
				if (turActual.compareTo(turNou)<0) {
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
	
	
	
	public Turista[] consultar(int codi) throws LlistaBuida{
		if (primer==null) return null;
		
		Node<Turista> nodeActual = primer;
		Turista turActual = nodeActual.getElement();
		Turista[] ll_tur= new Turista[2];
		
		while(nodeActual!=ultim) {
			if(codi==turActual.getId()) {
				ll_tur[0]=turActual;
				
				nodeActual=nodeActual.getSeg();
				turActual=nodeActual.getElement();
				while(nodeActual!=ultim) {
					if(codi==turActual.getId()) {
						ll_tur[1]=turActual;
						return ll_tur;
					}
					nodeActual=nodeActual.getSeg();
					turActual=nodeActual.getElement();
				}
				if (codi == turActual.getId()) {
					ll_tur[1]=turActual;
					return ll_tur;
				}
				return ll_tur;
			}
			nodeActual=nodeActual.getSeg();
			turActual=nodeActual.getElement();
		}
		if (codi == turActual.getId()) {
			ll_tur[0] = turActual;
			return ll_tur;
		}
		else return null;
	}
	
	public boolean esborrarElement (Turista act) throws LlistaBuida {
		return false;
		
	}
	
	public Turista existeix (Turista act) {
		if (primer==null) return null;
		
		Node<Turista> nodeActual = primer;
		Turista turActual = nodeActual.getElement();
		while(turActual.compareTo(act)<0 && nodeActual!=ultim) {
			if (act.equals(turActual)) return turActual;
			nodeActual=nodeActual.getSeg();
			turActual=nodeActual.getElement();
		}
		
		if (act.equals(turActual)) return turActual;	
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
		return "LlistaGenericaTuristas [primer=" + primer + ", ultim=" + ultim + "]";
	}

	@Override
	public Iterator<Turista> iterator() {
		IteratorLlistaGenerica<Turista> elementI=new IteratorLlistaGenerica<Turista>(primer, ultim);
		return elementI;
	}
}

