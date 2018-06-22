package Dades;

public interface TADMultillistaGenerica<F, C> {
	
	/**
	 * Metode que permet trobar si hi ha una relacio o no entre els elements
	 * de les files i les columnes
	 * @param f es l'element de les files de la multillista
	 * @param c es l'element de les columnes de la multillista
	 * @return si existeix o no la relacio
	 */
	public boolean existeix (F f, C c);
	
	/**
	 * Metode que serveix per afegir un element de les files a la multillista
	 * @param f es l'element de les files de la multillista
	 */
	public void afegirFila(F f);
	
	/**
	 * Metode que serveix per afegir un element de les columnes a la multillista
	 * @param c es l'element de les columnes de la multillista
	 */
	public void afegirCol(C c);
	
	/**
	 * Metode que serveix per trobar un element de les files a partir de l'element
	 * de les columnes
	 * @param c es l'element de les columnes de la multillista
	 * @return
	 */
	public F[] getFila(C c);
	
	/**
	 * Metode que serveix per trobar un element de les columnes a partir de 
	 * l'element de les files
	 * @param f es l'element de les files de la multillista
	 * @return
	 */
	public C[] getColumna(F f);
	
}
