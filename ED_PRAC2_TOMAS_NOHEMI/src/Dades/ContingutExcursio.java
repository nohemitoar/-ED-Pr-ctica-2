package Dades;

import Classes.*;
import Llistes.*;

public class ContingutExcursio {
	private LlistaGenerica<Activitat>[] ll_act;
	private Excursio excursio;
	private int codiExc, codiAct;
	
	public ContingutExcursio (int codiExc, int codiAct) {
		this.codiAct=codiAct;
		this.codiExc=codiExc;
		
	}
	
	public float importTotal() {
		float preuTotal=0;
		return preuTotal;
	}
	

}
