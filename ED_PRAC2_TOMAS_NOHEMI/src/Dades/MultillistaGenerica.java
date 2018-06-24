package Dades;

import Llistes.LlistaGenerica;
import Llistes.LlistaGenericaJava;

public class MultillistaGenerica<F extends Comparable<F>, C extends Comparable<C>> implements TADMultillistaGenerica<F, C> {
	
	//variables multillista
	private NodeF<F, C> punt_F;
	private NodeC<F, C> punt_C;
	
	/**
	 * Constructor de la multillista
	 */
	public MultillistaGenerica() {
		punt_F=null;
		punt_C=null;
	}
	
	//metodes privats
	/**
	 * Metode que serveix per obtenir el node f
	 * @param f node que es vol obtenir
	 * @return
	 */
	private NodeF<F, C> getFila(F f){
		NodeF<F, C> aux = punt_F;
		while (aux!=null) {
			if (aux.getElement().equals(f)) return aux;
			else aux=aux.getSeg();
		}
		return aux;
	}
	
	/**
	 * Metode que serveix per obtenir el node c
	 * @param c node que es vol obtenir
	 * @return
	 */
	private NodeC<F, C> getColumna(C c){
		NodeC<F, C> aux = punt_C;
		while (aux!=null) {
			if (aux.getElement().equals(c)) return aux;
			else aux=aux.getSeg();
		}
		return aux;
	}
	
	/**
	 * Metode que serveix per saber quina relacio te el node f amb les columnes
	 * @param f node que es vol comprovar la relacio
	 * @param c node que es vol aconseguir
	 * @return
	 */
	private NodeC<F, C> relEnFila(NodeF<F, C> f, C c){
		ExcursioContractada<F, C> aux = null;
		while (aux!=null) {
			if (aux.getC().equals(c)) return aux.getNodeC();
			else aux=aux.getSegC();
		}		
		return null;
	}
	
	//Metodes multillista
	public boolean afegirRelacio(F f, C c) {
		NodeF<F, C> nodeF=getFila(f);
		
		if (nodeF==null) {
			nodeF=new NodeF<F, C>(f);
			nodeF.setSeg(punt_F);
			punt_F=nodeF;
		}
		
		NodeC<F, C> aux=relEnFila(nodeF, c);
		
		if (aux!=null) return false;
		else {
			NodeC<F, C> nodeC=getColumna(c);
			if (nodeC==null) {
				nodeC = new NodeC<F, C>(c);
				nodeC.setSeg(punt_C);
				punt_C=nodeC;
			}
			
			ExcursioContractada<F, C> novaRel= new ExcursioContractada<F, C>(nodeF, nodeC);
			ExcursioContractada<F, C> relSegF = nodeC.getSegRelF();
			ExcursioContractada<F, C> relSegC = nodeF.getSegRelC();
			novaRel.setSegF(relSegF);
			novaRel.setSegC(relSegC);
			
			nodeF.setSegC(novaRel);
			nodeC.setSegF(novaRel);
			
			return true;
		}
	}
	
	public boolean existeixRelacio(F f, C c) {
		NodeF<F, C> nodeF = getFila(f);
		if(nodeF==null) return false;
		else {
			NodeC<F, C> nodeC = relEnFila(nodeF, c);
			if (nodeC==null) return false;
			else return true;
		}
	}
	
	public TADLlistaGenerica<C> consultarRelAmbF(F f, boolean LlJava){
		TADLlistaGenerica<C> ll_c=null;
		NodeF<F, C> nodeF= getFila(f);
		if(nodeF!=null && nodeF.getSegRelC()!=null) {
			if(LlJava) ll_c = new LlistaGenericaJava<C>();
			else ll_c = new LlistaGenerica<C>();
		}
		return ll_c;
	}
	
	public TADLlistaGenerica<F> consultarRelAmbC(C c, boolean LlJava){
		TADLlistaGenerica<F> ll_f=null;
		NodeC<F, C> nodeC= getColumna(c);
		if(nodeC!=null && nodeC.getSegRelF()!=null) {
			if(LlJava) ll_f = new LlistaGenericaJava<F>();
			else ll_f = new LlistaGenerica<F>();
		}
		return ll_f;
	}
}
