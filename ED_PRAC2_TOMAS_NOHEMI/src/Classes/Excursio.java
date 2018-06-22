package Classes;

import Llistes.*;

public class Excursio implements Comparable<Excursio>{
	private LlistaGenerica<Activitat>[] ll_act;
	private int codi;
	private String nom; 
	
	public Excursio(int codi, String nom) {
		this.codi=codi;
		this.nom=nom;
	}

	public int getCodi() {
		return codi;
	}

	public String getNom() {
		return nom;
	}
	
	public LlistaGenerica<Activitat>[] getLl_act(){
		return ll_act;
	}

	@Override
	public String toString() {
		return "Excursio [codi=" + codi + ", nom=" + nom + "]";
	}

	public boolean equals(Object obj) {
		return nom.equals(obj);
	}

	@Override
	public int compareTo(Excursio e) {
		// TODO Auto-generated method stub
		return nom.compareTo(e.nom);
	}
	
	
	
}
