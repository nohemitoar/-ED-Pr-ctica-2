package Dades;

import Classes.*;

public class NodeRelacio<E> {
	private Turista turista;
	private Excursio excursio;
	private NodeRelacio<Turista> segTurista;
	private NodeRelacio<Excursio> segExcursio;
	
	public NodeRelacio(Turista tur, Excursio exc) {
		turista = tur;
		excursio = exc;
		segTurista = null;
		segExcursio = null;
	}
	
	public Turista getTurista() {
		return turista;
	}
	
	public Excursio getExcursio() {
		return excursio;
	}
	
	public NodeRelacio<Turista> getSegTurista() {
		return segTurista;
	}
	
	public void setSegTurista(NodeRelacio<Turista> segTurista) {
		this.segTurista = segTurista;
	}
	
	public NodeRelacio<Excursio> getSegExcursio() {
		return segExcursio;
	}
	
	public void setSegExcursio(NodeRelacio<Excursio> segExcursio) {
		this.segExcursio = segExcursio;
	}
}
