package Dades;

import Excepcions.*;

public interface TADLlistaGenerica<E> extends Iterable<E> {
	
	/**
	 * Metode que serveix per inserir un element a la llista
	 * @param e element a inserir
	 * @throws LlistaPlena excepcio
	 */
	public boolean inserir (E e) throws LlistaPlena;
	
	/**
	 * Metode que serveix per consultar un element de la llista
	 * @param id del element
	 * @return el element
	 * @throws LlistaBuida excepcio
	 */
	public E consultar(int ind) throws LlistaBuida;
	
	/**
	 * Metode que serveix per esborrar un element de la llista
	 * @param e element a esborrar
	 * @return si l'ha pogut esborrar
	 * @throws LlistaBuida excepcio
	 */
	public boolean esborrarElement (E e) throws LlistaBuida;
	
	/**
	 * Metode per saber si existeix un element
	 * @param e element a buscar
	 * @return element
	 */
	public boolean existeixElement (E e);
	
	/**
	 * Metode per saber quin index te un element
	 * @param e element a buscar
	 * @return index del element
	 */
	public int getIndex(E e);
	
	/**
	 * Metode que serveix per a que retorni la llista
	 * @param llJava
	 * @return
	 * @throws LlistaBuida
	 * @throws Llistaplena
	 */
	public TADLlistaGenerica<E> getLlista() throws LlistaBuida, LlistaPlena;
	
	/**
	 * Metode que serveix per saber si la llista es buida
	 * @return si es buida o no
	 */
	public boolean esBuida();
	
	/**
	 * Metode que retorna el numero d'elements totals que 
	 * hi ha en la llista
	 * @return numero total
	 */
	public int getNumEle();

}
