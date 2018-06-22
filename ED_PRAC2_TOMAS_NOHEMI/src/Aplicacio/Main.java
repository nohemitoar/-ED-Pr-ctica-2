package Aplicacio;

import Dades.*;
import Excepcions.*;
import java.io.*;
import java.util.*;

public class Main {
	//Variables globals
	static Scanner teclat = new Scanner (System.in);
	static TADLlistaGenerica llista;
	static TADMultillistaGenerica multillista;
	
	//Mètode que controla les opcions introduides pel usuari
	public static int OpcioValida (int opcionsPossibles) {
		int valor = -1;
		boolean valid = false; 
		
		while (!valid) {
			if (opcionsPossibles==2) {
				try {
					valor = Integer.parseInt(teclat.nextLine());
					if (1<=valor && valor<=opcionsPossibles) valid = true;
					else System.out.println("L'opcio no es valida. Introdueix un valor valid.");
				} 	catch (NumberFormatException e) {
					System.out.println("L'opcio no es valida. Introdueix un numero.");
				}
			}
			else {
				try {
					valor=Integer.parseInt(teclat.nextLine());
					valid=true;
				}	catch (NumberFormatException e){
					System.out.println("Has de introduir un numero.");
				}
			}
		}	
		return valor;
	}
	
	
	public int excursionsImportTotal(int codi) {
		return 1;
	}
	
	public void excursionsRealitzades(int codi, int tur) {
		
	}
	
	public float activitatsIncloses(int codi) {
		
		return;
	}
	
	public turistas[] numeroTuristes(int codi) {
		
	}
	
	public void excursionsContractades(int id) {
		
	}
	
	public void importExcursionsRealitzades(int id) {
		
	}
	
	public float calcularPreuTotal() {
		
	}
	
	//MAIN
	public static void main (String[] args) throws IOException{
		long tempsInicial=System.nanoTime();
		
		//Comprova l'existencia del fitxer i l'obre
		BufferedReader fitxer1 = null;
		BufferedReader fitxer2 = null;
		String nomFitxer1 = "";
		String nomFitxer2 = "";
		boolean existeix1 = false;
		boolean existeix2 = false;
		
		while ((!existeix1) || (!existeix2)) {
			System.out.println("Indica el nom dels dos fitxers:");
			nomFitxer1 = teclat.nextLine();
			nomFitxer2 = teclat.nextLine();
			
			try {
				fitxer1 = new BufferedReader (new FileReader(nomFitxer1));
				existeix1 = true;
			}	catch(FileNotFoundException e) {
				System.out.println("\nNo existeix el Fitxer1");
			}
			try {
				fitxer2 = new BufferedReader (new FileReader(nomFitxer2));
				existeix2 = true;
			}	catch(FileNotFoundException e) {
				System.out.println("\nNo existeix el Fitxer2");
			}		
		}
		
		System.out.println("Quina implementació vols fer servir?");
		System.out.println("\t 1. Llista Generica.");
		System.out.println("\t 2. Llista Generica Java.");
		int op = OpcioValida(2);
		
		switch (op) {
		case 1:
			
			break;

		case 2:
			
			break;
		default: break;
		}
		
		
	}
}
