package Excepcions;

/**Excepció que saltarà quan el fitxer llegit estigui buit
 * @author Jeroni Molina
 * @author Roger Bosch
 */
public class EmptyFileException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**Constructor de la excepció
	 */
	public EmptyFileException(){
		System.out.println("ERROR! El fitxer està buit");
	}
}
