package Aplicacio;

import Dades.*;
import Excepcions.*;
import Llistes.*;

import java.io.*;
import java.util.*;
import Classes.*;

public class Main {
	//Variables globals
	static TADLlistaGenerica<Activitat> ll_act; 
	static TADLlistaGenerica<Turista> ll_tur;
	static TADLlistaGenerica<Excursio> ll_exc;
	static TADMultillistaGenerica<Turista, Excursio> multillista;
	static TADLlistaGenerica<ContingutExcursio> ll_cont;
	
	static boolean llJava=false;
	static int codiExc, codiAct, numTur, idTur, totalExcCon, totalAct, totalTur;
	static float preuTotal, preuAct;
	static String nomExc, nomAct, nomTur, cognomTur;
	
	static Scanner teclat = new Scanner (System.in);
	static BufferedReader fitxer1 = null;
	static BufferedReader fitxer2 = null;
	
	/********************METODES ADDICIONALS********************/
	/*
	 * Metode a partir del codi d'una excursio mostra quines activitats inclou i 
	 * quin es l'import total de l'excursio.
	 */
	static public void activitatsIncloses(int codiExc) {
		TADLlistaGenerica<Activitat> acts;
		ContingutExcursio cont=null;
		if (llJava) acts= new LlistaGenericaJava<Activitat>();
		else acts= new LlistaGenerica<Activitat>();
		try {
			cont=ll_cont.consultar(codiExc);
			acts=cont.getLl_act();
			acts.toString();
		} catch (LlistaBuida e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Metode a partir del codi d'una activitat mostra quines excursions ofereixen
	 * l'activitat i quin es l'import total de cada excursio.
	 */
	static public TADLlistaGenerica<Excursio> excursionsImportTotal(int codiAct) {
		TADLlistaGenerica<Excursio> excs = null;
		Excursio e;
		if (llJava) excs= new LlistaGenericaJava<Excursio>();
		else excs= new LlistaGenerica<Excursio>();
		
		
		
		
		
		return excs;
	}
	
	/*
	 * Metode a partir del codi d'una activitat mostra aquelles que l'han 
	 * realitzat menys de X turistes.
	 */
	static public void excursionsRealitzades(int codiAct, int numTur) {
		
		
		
	}
	
	/*
	 * Metode a partir del codi d'una excursio mostra quins turistes l'han 
	 * realitzat, sumar-los i el nombre total de turistes.
	 */
	static public int numeroTuristes(int codiExc) {
		return 1;
	}
	
	/*
	 * Metode a partir de l'id d'un turista, mostra quines excursions ha 
	 * contractat
	 */
	static public void excursionsContractades(int idTur) {
		
	}
	
	/*
	 * Metode a partir de l'identificador d'un turista mostra l'impor total de 
	 * les excursions que ha realitzat.
	 */
	static public void importExcursionsRealitzades(int idTur) {
		
	}
	
	/*
	 * Metode que calcula el preu total d'una excursio
	 */
	static public float calcularPreuTotal(int codiExc) throws LlistaBuida {
		float preu=0;
		ContingutExcursio cont;
		TADLlistaGenerica<Activitat> ll_acts;
		Activitat act;
		cont=ll_cont.consultar(codiExc);
		ll_acts=cont.getLl_act();
		for (int i=0; i<ll_acts.getNumEle(); i++) {
			act=ll_acts.consultar(i);
			preu+=act.getPreu();
		}
		return preu;
	}
	
	
	/************************METODES PROGRAMA***********************/
	//Metode que controla les opcions introduides pel usuari --FET--
	public static int OpcioValida (int opcionsPossibles) {
		int valor = -1;
		boolean valid = false; 
		
		while (!valid) {
			if ((opcionsPossibles==2)||(opcionsPossibles==12)){
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
	
	//Metode per comprovar l'introduccio dels fitxers --FET--
	public static void comprovarFitxers() {
		String nomFitxer1 = "";
		String nomFitxer2 = "";
		boolean existeix1 = false;
		boolean existeix2 = false;
		
		while ((!existeix1) || (!existeix2)) {
			System.out.println("Indica el nom dels dos fitxers: primer el fitxer que conté la informacio de les excursio i el segon les excursions contractades");
			nomFitxer1 = teclat.nextLine();
			nomFitxer2 = teclat.nextLine();
			
			try {
				fitxer1 = new BufferedReader (new FileReader("src/doc/"+nomFitxer1+".csv"));
				existeix1 = true;
			}	catch(FileNotFoundException e) {
				System.out.println("\nNo existeix el Fitxer 1");
			}
			try {
				fitxer2 = new BufferedReader (new FileReader("src/doc/"+nomFitxer2+".csv"));
				existeix2 = true;
			}	catch(FileNotFoundException e) {
				System.out.println("\nNo existeix el Fitxer 2");
			}		
		}
	}
	
	//Metode per carregar les dades dels fitxers a les TADs --FET--
	public static void carregarDades(BufferedReader fitxer1, BufferedReader fitxer2) throws IOException, LlistaBuida, LlistaPlena, EmptyFileException {
		Excursio exc;
		Turista tur;
		boolean fiLinea=false;
		String fi;
		
		String liniaF1 = fitxer1.readLine();
		if (liniaF1==null) throw new EmptyFileException();
		while(liniaF1!=null) {
			
			codiExc=Integer.parseInt(liniaF1.split(";")[0]);
			nomExc=liniaF1.split(";")[1];
			exc = new Excursio(codiExc, nomExc);
			ll_exc.inserir(exc);
			
			int i = 2;
			while (!fiLinea) {
				fi=liniaF1.split(";")[i];
				if (fi!=null) {
					codiAct=Integer.parseInt(liniaF1.split(";")[i]);
					i++;
					nomAct=liniaF1.split(";")[i];
					i++;
					preuAct=Float.parseFloat(liniaF1.split(";")[i]);
					i++;
					ll_act.inserir(new Activitat(codiAct, nomAct, preuAct));
				} else fiLinea=true;
				liniaF1=fitxer1.readLine();
			}
			
			ll_cont.inserir(new ContingutExcursio(exc, ll_act, llJava));
		}
		
		String liniaF2 = fitxer2.readLine();
		if (liniaF2==null) throw new EmptyFileException();
		while (liniaF2!=null) {
			
			codiExc=Integer.parseInt(liniaF2.split(";")[0]);
			nomExc=liniaF2.split(";")[1];
			exc=new Excursio(codiExc, nomExc);
			
			idTur=Integer.parseInt(liniaF2.split(";")[2]);
			nomTur=liniaF2.split(";")[3];
			cognomTur=liniaF2.split(";")[4];
			tur=new Turista(idTur, cognomTur, nomTur);
			ll_tur.inserir(tur);
			
			multillista.afegirRelacio(tur, exc);
			
			liniaF2 = fitxer2.readLine();
		}
		fitxer1.close();
		fitxer2.close();
	}

	//Metode que mostra les opcions del primer menu --FET--
	public static void menu1() {
		System.out.println("Quina implementació vols fer servir?");
		System.out.println("\t 1. Llista Generica.");
		System.out.println("\t 2. Llista Generica Java.");
		int op = OpcioValida(2);
		switch (op) {
		case 1:
			ll_act = new LlistaGenerica<Activitat>();
			ll_tur = new LlistaGenerica<Turista>();
			ll_exc = new LlistaGenerica<Excursio>();
			multillista = new MultillistaGenerica<Turista, Excursio>();
			llJava=false;
			break;

		case 2:
			ll_act = new LlistaGenericaJava<Activitat>();
			ll_tur = new LlistaGenericaJava<Turista>();
			ll_exc = new LlistaGenericaJava<Excursio>();
			multillista = new MultillistaGenerica<Turista, Excursio>();
			llJava = true;
			break;
		default: break;
		}
	}
	
	//Metode que mostra les opcions del segon menu --FET--
	public static void menu2() {
		System.out.println("Quina consulta vols fer?");
		System.out.println("\t 1. Mirar quines activitats conte una excursio en concret i l'import total d'aquesta");
		System.out.println("\t 2. Mirar quines excursions ofereixen una activitat en concret i l'import total de les excursions");
		System.out.println("\t 3. Mirar quina activitat han realitzat menys d'un numero determinat de turistes");
		System.out.println("\t 4. Mirar quins turistes han realitzat una excursio en concret.");
		System.out.println("\t 5. Mirar quines excursions ha contractar un turista en concret.");
		System.out.println("\t 6. Mirar el import total de les excursions que ha realitzat un turista en concret.");
		System.out.println("\t 7. Nombre total d'activitats en el sistema.");
		System.out.println("\t 8. Nombre total de turistes en el sistema.");
		System.out.println("\t 9. Nombre total d'excursions contractades.");
		System.out.println("\t 10. Esborrar una excursio en concret.");
		System.out.println("\t 11. Esborrar una activitat en concret.");
		System.out.println("\t 12. Esborrar un turista en concret.");
	
	}
	
	/***************************MAIN***************************/
	public static void main (String[] args) throws IOException, LlistaPlena, LlistaBuida{
		long tempsInicial=0;
		long tempsCarregarDades=0;
		boolean seguir=true;
		boolean esborrat=false;
		int op=0;
		String resposta;
		
		//Comprova l'existencia del fitxer i l'obre
		comprovarFitxers();
		
		//Guardar les dades en les TADS corresponents
		tempsCarregarDades=System.nanoTime();
		try {
			carregarDades(fitxer1, fitxer2);
		} catch (EmptyFileException e1) {
			e1.printStackTrace();
		}
		long tempsFinal=System.nanoTime();
		long tempsTotal=tempsFinal-tempsCarregarDades;
		System.out.printf("El temps de carregar dades ha estat de: %d ns", tempsTotal);
		
		//Bucle principal del programa
		while (!seguir) {
			menu1();
			menu2();
			op = OpcioValida(12);
			
			//Opcions de consultes
			switch (op) {
			case 1:
				System.out.println("Introdueix el codi de l'excursio.");
				codiExc=Integer.parseInt(teclat.nextLine());
				tempsInicial=System.nanoTime();
				activitatsIncloses(codiExc);
				try {
					preuTotal=calcularPreuTotal(codiExc);
				} catch (LlistaBuida e) {
					e.printStackTrace();
				}
				break;
				
			case 2:
				System.out.println("Introdueix el codi de l'activitat.");
				codiAct=Integer.parseInt(teclat.nextLine());
				tempsInicial=System.nanoTime();
				ll_exc=excursionsImportTotal(codiAct);
				try {
					preuTotal=calcularPreuTotal(codiExc);
				} catch (LlistaBuida e) {
					e.printStackTrace();
				}
				break;
				
			case 3:
				System.out.println("Introdueix el codi de l'activitat.");
				codiAct=Integer.parseInt(teclat.nextLine());
				System.out.println("Introdueix el numero de turistes.");
				numTur=Integer.parseInt(teclat.nextLine());
				tempsInicial=System.nanoTime();
				excursionsRealitzades(codiAct, numTur);
				
				break;
				
			case 4:
				System.out.println("Introdueix el codi de l'excursio.");
				codiExc=Integer.parseInt(teclat.nextLine());
				tempsInicial=System.nanoTime();
				numeroTuristes(codiExc);
				break;
				
			case 5:
				System.out.println("Introdueix l'identificador del turista.");
				idTur=Integer.parseInt(teclat.nextLine());
				tempsInicial=System.nanoTime();
				excursionsContractades(idTur);
				break;
				
			case 6:
				System.out.println("Introdueix l'identificador del turista.");
				idTur=Integer.parseInt(teclat.nextLine());
				tempsInicial=System.nanoTime();
				importExcursionsRealitzades(idTur);
				break;
				
			case 7:
				tempsInicial=System.nanoTime();
				totalAct =ll_act.getNumEle();
				break;
				
			case 8:
				tempsInicial=System.nanoTime();
				totalTur=ll_tur.getNumEle();
				break;
				
			case 9:
				tempsInicial=System.nanoTime();
				totalExcCon=ll_exc.getNumEle();
				break;
				
			case 10:
				System.out.println("Introdueix el codi de l'excursio.");
				codiExc=Integer.parseInt(teclat.nextLine());
				tempsInicial=System.nanoTime();
				esborrat=ll_exc.esborrarElement(ll_exc.consultar(codiExc));
				if(esborrat) System.out.println("S'ha pogut esborrar l'excursio.");
				else System.out.println("No s'ha put esborrar l'excursio.");
				break;
				
			case 11:
				System.out.println("Introdueix el codi de l'activitat.");
				codiAct=Integer.parseInt(teclat.nextLine());
				tempsInicial=System.nanoTime();
				esborrat=ll_act.esborrarElement(ll_act.consultar(codiAct));
				if(esborrat) System.out.println("S'ha pogut esborrar l'activitat.");
				else System.out.println("No s'ha put esborrar l'activitat.");
				break;
				
			case 12:
				System.out.println("Introdueix l'identificador del turista.");
				idTur=Integer.parseInt(teclat.nextLine());
				tempsInicial=System.nanoTime();
				esborrat=ll_tur.esborrarElement(ll_tur.consultar(idTur));
				if(esborrat) System.out.println("S'ha pogut esborrar el turista.");
				else System.out.println("No s'ha put esborrar el turista.");
				break;
				
			default:
				break;
			}
			
			long tempsFinalFunc=System.nanoTime();
			long tempsTotalProg=tempsFinalFunc-tempsInicial;
			System.out.printf("El temps total d'execucio del programa ha estat de: %d ns", tempsTotalProg);
			
			System.out.println("Vols canviar la implementacio o fer una altra consulta? S/N");
			resposta=teclat.nextLine();
			if ((resposta=="N")||(resposta=="n")) seguir=false;
		}	
	}
}
