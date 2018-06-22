package Dades;

import Classes.*;
import Llistes.*;

public class ContingutExcursio implements TADMultillistaGenerica<Activitat, Excursio>{
	private int codiAct, codiExc;

	
	public ContingutExcursio (int codiAct, int codiExc) {
		this.codiAct=codiAct;
		this.codiExc=codiExc;
	}
	
	public int getCodiAct() {
		return codiAct;
	}
	
	public int getCodiExc() {
		return codiExc;
	}

}
