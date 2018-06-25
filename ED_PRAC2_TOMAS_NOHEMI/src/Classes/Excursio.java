package Classes;

public class Excursio implements Comparable<Excursio>{
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
	
	@Override
	public String toString() {
		return "Excursio [codi=" + codi + ", nom=" + nom + "]";
	}

	public boolean equals(Object obj) {
		return nom.equals(obj);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
	@Override
	public int compareTo(Excursio e) {
		// TODO Auto-generated method stub
		return nom.compareTo(e.nom);
	}
}
