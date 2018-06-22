package Classes;

public class Activitat implements Comparable<Activitat>{
	private int codi;
	private String nom;
	private float preu;
	
	public Activitat (int codi, String nom, float preu) {
		this.codi=codi;
		this.nom=nom;
		this.preu=preu;
	}

	public int getCodi() {
		return codi;
	}

	public String getNom() {
		return nom;
	}

	public float getPreu() {
		return preu;
	}

	@Override
	public String toString() {
		return "Activitat [codi=" + codi + ", nom=" + nom + ", preu=" + preu + ", getCodi()=" + getCodi() + ", getNom()="
				+ getNom() + ", getPreu()=" + getPreu()	+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}

	@Override
	public boolean equals(Object obj) {
		boolean iguals = false;
		if (nom==obj) iguals = true;
		return iguals;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
	@Override
	public int compareTo(Activitat act) {
		return nom.compareTo(act.nom);
	}
}