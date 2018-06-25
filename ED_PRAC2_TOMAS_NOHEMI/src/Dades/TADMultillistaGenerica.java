package Dades;

public interface TADMultillistaGenerica<F, C> {
	
	/**
	 * Metode que permet trobar si hi ha una relacio o no entre els elements
	 * de les files i les columnes
	 * @param f es l'element de les files de la multillista
	 * @param c es l'element de les columnes de la multillista
	 * @return si existeix o no la relacio
	 */
	public boolean existeixRelacio (F f, C c);
	
	/**
	 * Metode que serveix per afegir un element a la multillista
	 * @param f es l'element de les files de la multillista
	 * @param c es l'element de les columnes de la multillista
	 * @return si s'ha afegit o no
	 */
	public boolean afegirRelacio(F f, C c);
	
	/**
	 * Metode que serveix per esborrar un element de la multillista
	 * @param f es l'element de les files de la multillista
	 * @param c es l'element de les columnes de la multillista
	 * @return si s'ha esborrat o no
	 * @throws Throwable
	 */
	public boolean esborrarRelacio(F f, C c);
	
	/**
	 * Metode que serveix per trobar un element de les files a partir de l'element
	 * de les columnes
	 * @param c es l'element de les columnes de la multillista
	 * @return
	 */
	public TADLlistaGenerica<C> consultarRelAmbF(F f, boolean LJava);
	
	/**
	 * Metode que serveix per trobar un element de les columnes a partir de 
	 * l'element de les files
	 * @param f es l'element de les files de la multillista
	 * @return
	 */
	public TADLlistaGenerica<F> consultarRelAmbC(C c, boolean LJava);
	
}
