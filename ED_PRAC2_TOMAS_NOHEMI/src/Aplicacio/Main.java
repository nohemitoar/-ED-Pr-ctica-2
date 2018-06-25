package Aplicacio;

import Dades.*;
import Excepcions.*;
import Llistes.*;

import java.io.*;
import java.util.*;
import Classes.*;

public class Main {
	//Variables globals
	static TADLlistaGenerica<Activitat> ll_act=null; 
	static TADLlistaGenerica<Turista> ll_tur=null;
	static TADLlistaGenerica<Excursio> ll_exc=null;
	static TADMultillistaGenerica<Turista, Excursio> multillista;
	static TADLlistaGenerica<ContingutExcursio> ll_cont=null;
	
	static boolean llJava=false;
	static int codiExc, codiAct, numTur, idTur, totalExcCon, totalAct, totalTur;
	static float preuTotal, preuAct;
	static String nomExc, nomAct, nomTur, cognomTur;
	
	static Scanner teclat = new Scanner (System.in);
	static BufferedReader fitxer1 = null;
	static BufferedReader fitxer2 = null;
	
	/********************METODES ADDICIONALS********************/
	
	static public void mostrarLlista(TADLlistaGenerica<Activitat> ll) throws LlistaBuida {
		Activitat aux;
		if (llJava) {
			//aux = new LlistaGenericaJava<Activitat>();
			ll = new LlistaGenericaJava<Activitat>();
		} else {
			//aux = new LlistaGenerica<Activitat>();
			ll = new LlistaGenerica<Activitat>();
		}
		for (int i=0; i<ll.getNumEle(); i++) {
			aux=ll.consultar(i);
			System.out.println("Codi: " + aux.getCodi() + " Nom: " + aux.getNom());
		}
	}
	
	/**
	 * Metode a partir del codi d'una excursio mostra quines activitats inclou i 
	 * quin es l'import total de l'excursio.
	 * @param codiExc
	 * @throws LlistaBuida
	 */
	static public TADLlistaGenerica<Activitat> activitatsIncloses(int codiExc) throws LlistaBuida, LlistaPlena{
		TADLlistaGenerica<Activitat> acts;
		ContingutExcursio cont;
		Excursio exc;
		if (llJava) {
			acts= new LlistaGenericaJava<Activitat>();
		} else {
			acts= new LlistaGenerica<Activitat>();
		}
	
		if (ll_cont.esBuida()) throw new LlistaBuida();
		else {
			exc = ll_exc.consultar(ll_exc.getIndex(new Excursio(codiExc, "")));
			cont=ll_cont.consultar(ll_cont.getIndex(new ContingutExcursio(exc, null, llJava)));
			System.out.println("Exc " + cont.getExc() + "Llista " + cont.getLl_act());
			/*for (int i=0; i<ll_cont.getNumEle(); i++) {
				cont=ll_cont.consultar(i);
				if (cont.compareTo(ll_cont.consultar(ll_cont.getIndex(new ContingutExcursio(exc, null, llJava))))==0) {
					acts=cont.getLl_act();
					return acts;
				}
			}*/
			return null;
		}
	}
	
	/**
	 * Metode a partir del codi d'una activitat mostra quines excursions ofereixen
	 * l'activitat i quin es l'import total de cada excursio.
	 * @param codiAct
	 * @return
	 * @throws LlistaBuida
	 */
	static public TADLlistaGenerica<Excursio> excursionsImportTotal(int codiAct) throws LlistaBuida, LlistaPlena{
		TADLlistaGenerica<Excursio> excs, incloses;
		TADLlistaGenerica<Activitat> acts=null;
		Excursio exc;
		Activitat act;
		
		if (llJava) {
			excs= new LlistaGenericaJava<Excursio>();
			acts= new LlistaGenericaJava<Activitat>();
			incloses = new LlistaGenericaJava<Excursio>();
		} else {
			excs= new LlistaGenerica<Excursio>();
			acts= new LlistaGenerica<Activitat>();
			incloses = new LlistaGenerica<Excursio>();
		}
		
		if (ll_act.esBuida()) throw new LlistaBuida();
		else {
			act=ll_act.consultar(ll_act.getIndex(new Activitat(codiAct, "", 0)));
			for (int i=0; i<ll_exc.getNumEle(); i++) {
				excs=ll_exc.getLlista();
				for (int j=0; j<excs.getNumEle(); j++) {
					exc=excs.consultar(j);
					acts = activitatsIncloses(exc.getCodi());
					for(int k=0; k<acts.getNumEle(); k++) {
						if (acts.consultar(k).equals(act)) incloses.inserir(exc);
					}
				}
			}
			
			return incloses;
		}	
	}
	
	/**
	 * Metode a partir del codi d'una activitat mostra aquelles excursions que 
	 * l'han realitzat menys de X turistes.
	 * @param codiAct
	 * @param numTur
	 */
	static public TADLlistaGenerica<Excursio> excursionsRealitzades(int codiAct, int numTur) throws LlistaBuida, LlistaPlena {
		TADLlistaGenerica<Turista> turs;
		TADLlistaGenerica<Excursio> excs, realitzades;
		Excursio exc;
		
		if (llJava) {
			excs= new LlistaGenericaJava<Excursio>();
			turs= new LlistaGenericaJava<Turista>();
			realitzades= new LlistaGenericaJava<Excursio>();
		} else {
			excs= new LlistaGenerica<Excursio>();
			turs= new LlistaGenerica<Turista>();
			realitzades= new LlistaGenerica<Excursio>();
		}
		
		excs=excursionsImportTotal(codiAct);
		for (int i=0; i<excs.getNumEle(); i++) {
			exc=excs.consultar(i);
			turs=numeroTuristes(exc.getCodi());
			if (numTur>turs.getNumEle()) realitzades.inserir(exc);
		}
		return realitzades;
	}
	
	/**
	 * Metode a partir del codi d'una excursio mostra quins turistes l'han 
	 * realitzat, sumar-los i el nombre total de turistes.
	 * @param codiExc
	 * @throws LlistaBuida
	 */
	static public TADLlistaGenerica<Turista> numeroTuristes(int codiExc) throws LlistaBuida {
		TADLlistaGenerica<Turista> turs;
		Excursio exc = null;
		
		if (llJava) turs= new LlistaGenericaJava<Turista>();
		else turs= new LlistaGenerica<Turista>();
		
		if (ll_cont.esBuida()) throw new LlistaBuida();
		else {
			exc = ll_exc.consultar(ll_exc.getIndex(new Excursio(codiExc, "")));
			turs=multillista.consultarRelAmbC(exc, llJava);
			System.out.println("El numero de turistes ha estat de" + turs.getNumEle());
			return turs;
		}
	}
	

	/**
	 * Metode a partir de l'id d'un turista, mostra quines excursions ha 
	 * contractat
	 * @param idTur
	 * @throws LlistaBuida
	 */
	static public void excursionsContractades(int idTur) throws LlistaBuida {
		TADLlistaGenerica<Excursio> excs;	
		
		if (llJava) excs = new LlistaGenericaJava<Excursio>();
		else excs = new LlistaGenerica<Excursio>();
		
		if (ll_tur.esBuida()) throw new LlistaBuida();
		else {
			excs=multillista.consultarRelAmbF(ll_tur.consultar(ll_tur.getIndex(new Turista(idTur, "", ""))), llJava);
			for (int i=0; i<excs.getNumEle(); i++) {
				System.out.println(excs.toString());
			}
			
		}	
	}
	
	
	/**
	 * Metode a partir de l'identificador d'un turista mostra l'import total de 
	 * les excursions que ha realitzat.
	 * @param idTur
	 * @throws LlistaBuida
	 */
	static public void importExcursionsRealitzades(int idTur) throws LlistaBuida {
		TADLlistaGenerica<Excursio> excs;
		Turista tur = null;
		Excursio exc = null;
		float preu=0;
		
		if (llJava) excs = new LlistaGenericaJava<Excursio>();
		else excs = new LlistaGenerica<Excursio>();
		
		if (ll_tur.esBuida()) throw new LlistaBuida();
		else {
			tur=ll_tur.consultar(idTur);
			excs=multillista.consultarRelAmbF(tur, llJava);
		
			for (int i=0; i<excs.getNumEle(); i++) {
				exc=excs.consultar(i);
				preu+=calcularPreuTotal(exc.getCodi());
			}
			
			System.out.println("L'import total del turista" + tur.getNom() + tur.getCognom() + " ha estat de " + preu);
		}
	}
	
	
	/**
	 * Metode que calcula el preu total d'una excursio
	 * @param codiExc
	 * @return
	 * @throws LlistaBuida
	 */
	static public float calcularPreuTotal(int codiExc) throws LlistaBuida {
		float preu=0;
		ContingutExcursio cont;
		TADLlistaGenerica<Activitat> acts;
		Activitat act;
		if (ll_cont.esBuida()) throw new LlistaBuida();
		else {
			cont=ll_cont.consultar(codiExc);
			acts=cont.getLl_act();
			for (int i=0; i<acts.getNumEle(); i++) {
				act=acts.consultar(i);
				preu+=act.getPreu();
			}
			return preu;
		}
	}
	
	
	/************************METODES PROGRAMA***********************/
	//Metode que controla les opcions introduides pel usuari 
	
	/**
	 * Metode per saber si la opcio introduida es correcte
	 * @param opcionsPossibles
	 * @return
	 */
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
	
	/**
	 * Metode per comprovar l'introduccio dels fitxers 
	 */
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
	
	/**
	 * Metode per carregar les dades dels fitxers a les TADs
	 * @param fitxer1
	 * @param fitxer2
	 * @throws IOException
	 * @throws LlistaBuida
	 * @throws LlistaPlena
	 * @throws EmptyFileException
	 */
	public static void carregarDades(BufferedReader fitxer1, BufferedReader fitxer2, long tempsCarregarDades) throws IOException, LlistaBuida, LlistaPlena, EmptyFileException {
		Excursio exc;
		Turista tur;
		String[] fiLinia;
		
		tempsCarregarDades=System.nanoTime();
		String liniaF1 = fitxer1.readLine();
		
		if (liniaF1==null) throw new EmptyFileException();
		while(liniaF1!=null) {
			if (liniaF1.indexOf(";") > -1) {
				fiLinia=liniaF1.split(";");
				
				codiExc=Integer.parseInt(fiLinia[0]);
				nomExc=fiLinia[1];
				exc = new Excursio(codiExc, nomExc);
				ll_exc.inserir(exc);
				
				for(int i=2;i<fiLinia.length;i+=3) {
					codiAct=Integer.parseInt(fiLinia[i]);
					nomAct=fiLinia[i+1];
					preuAct=Float.parseFloat(fiLinia[i+2]);
					ll_act.inserir(new Activitat(codiAct, nomAct, preuAct));
				}
				
				ll_cont.inserir(new ContingutExcursio(exc, ll_act, llJava));
				liniaF1=fitxer1.readLine();
			}
		}
		
		String liniaF2 = fitxer2.readLine();
		if (liniaF2==null) throw new EmptyFileException();
		while (liniaF2!=null) {
			if (liniaF2.indexOf(";") > -1) {
				fiLinia=liniaF2.split(";");
				
				codiExc=Integer.parseInt(fiLinia[0]);
				nomExc=fiLinia[1];
				exc=new Excursio(codiExc, nomExc);
				
				idTur=Integer.parseInt(fiLinia[2]);
				nomTur=fiLinia[3];
				cognomTur=fiLinia[4];
				tur=new Turista(idTur, cognomTur, nomTur);
				ll_tur.inserir(tur);
				
				multillista.afegirRelacio(tur, exc);
				
				liniaF2 = fitxer2.readLine();
			}
		}
		fitxer1.close();
		fitxer2.close();
	}

	
	/**
	 * Metode que mostra les opcions del primer menu 
	 */
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
			ll_cont = new LlistaGenerica<ContingutExcursio>();
			multillista = new MultillistaGenerica<Turista, Excursio>();
			llJava=false;
			break;

		case 2:
			ll_act = new LlistaGenericaJava<Activitat>();
			ll_tur = new LlistaGenericaJava<Turista>();
			ll_exc = new LlistaGenericaJava<Excursio>();
			ll_cont = new LlistaGenericaJava<ContingutExcursio>();
			multillista = new MultillistaGenerica<Turista, Excursio>();
			llJava = true;
			break;
		default: break;
		}
	}
	
	/**
	 * Metode que mostra les opcions del segon menu 
	 */
	public static void menu2() {
		System.out.println("\n Quina consulta vols fer?");
		System.out.println("\t 1. Mirar quines activitats conte una excursio en concret i l'import total d'aquesta");
		System.out.println("\t 2. Mirar quines excursions ofereixen una activitat en concret i l'import total de les excursions");
		System.out.println("\t 3. Mirar quina activitat han realitzat menys d'un numero determinat de turistes");
		System.out.println("\t 4. Mirar quins turistes han realitzat una excursio en concret.");
		System.out.println("\t 5. Mirar quines excursions ha contractat un turista en concret.");
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
		boolean esborrat=false;
		int op=0;
		Excursio excAux;
		Activitat actAux;
		TADLlistaGenerica<Excursio> ll_auxExc;
		TADLlistaGenerica<Activitat> ll_auxAct;
		
		menu1();
		//Bucle principal del programa
			
		if(llJava) {
			ll_auxExc = new LlistaGenericaJava<Excursio>();
			ll_auxAct = new LlistaGenericaJava<Activitat>();
		} else {
			ll_auxExc = new LlistaGenerica<Excursio>();
			ll_auxAct = new LlistaGenerica<Activitat>();
		}
		
		//Comprova l'existencia dels fitxer i l'obre
		comprovarFitxers();
		
		//Guardar les dades en les TADS corresponents
		try {
			carregarDades(fitxer1, fitxer2, tempsCarregarDades);
		} catch (EmptyFileException e1) {
			e1.printStackTrace();
		}
		long tempsFinal=System.nanoTime();
		long tempsTotal=tempsFinal-tempsCarregarDades;
		System.out.printf("El temps de carregar dades ha estat de: %d ns", tempsTotal);
		
		menu2();
		op = OpcioValida(12);
		
		//Opcions de consultes
		switch (op) {
		case 1:
			System.out.println("Introdueix el codi de l'excursio.");
			codiExc=Integer.parseInt(teclat.nextLine());
			tempsInicial=System.nanoTime();
			try {
				ll_auxAct=activitatsIncloses(codiExc);
				for (int i=0; i<ll_auxAct.getNumEle(); i++) {
					actAux=ll_auxAct.consultar(i);
					preuTotal+=actAux.getPreu();
					mostrarLlista(ll_act);
					System.out.println("El preu total de l'excursio es de " + preuTotal + " €");
				}
				
			} catch (LlistaBuida e) {
				e.printStackTrace();
			}
			break;
			
		case 2:
			System.out.println("Introdueix el codi de l'activitat.");
			codiAct=Integer.parseInt(teclat.nextLine());
			tempsInicial=System.nanoTime();
			try {
				ll_auxExc=excursionsImportTotal(codiAct);
				for (int i=0; i<ll_auxExc.getNumEle(); i++) {
					excAux=ll_auxExc.consultar(i);
					preuTotal+=calcularPreuTotal(excAux.getCodi());
					System.out.println("Excursio: " + excAux.getCodi() + excAux.getNom() + " el preu es de: " + preuTotal );
				}
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
			System.out.println("El numero total d'activitats en el sistema es de " + totalAct);
			break;
			
		case 8:
			tempsInicial=System.nanoTime();
			totalTur=ll_tur.getNumEle();
			System.out.println("El numero total de turistes en el sistema es de " + totalTur);
			break;
			
		case 9:
			tempsInicial=System.nanoTime();
			totalExcCon=ll_cont.getNumEle();
			System.out.println("El numero total d'excursions contractades en el sistema es de " + totalExcCon);
			break;
			
		case 10:
			System.out.println("Introdueix el codi de l'excursio.");
			codiExc=Integer.parseInt(teclat.nextLine());
			tempsInicial=System.nanoTime();
			excAux=ll_exc.consultar(ll_exc.getIndex(new Excursio(codiExc, "")));
			
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
	}
}
