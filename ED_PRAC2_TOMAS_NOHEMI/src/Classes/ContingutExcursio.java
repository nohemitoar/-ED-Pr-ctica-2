package Classes;

import Dades.TADLlistaGenerica;

import Llistes.*;

public class ContingutExcursio implements Comparable<ContingutExcursio> {
	private TADLlistaGenerica<Activitat> ll_act;
	private Excursio exc;
	
	public ContingutExcursio (Excursio exc, TADLlistaGenerica<Activitat> ll, boolean llJava) {
		this.exc=exc;
		if(llJava) ll_act = new LlistaGenericaJava<Activitat>();
		else ll_act = new LlistaGenerica<Activitat>();
		ll_act=ll;
	}

	public TADLlistaGenerica<Activitat> getLl_act() {
		return ll_act;
	}

	public void setLl_act(TADLlistaGenerica<Activitat> ll_act) {
		this.ll_act = ll_act;
	}

	public Excursio getExc() {
		return exc;
	}

	public void setExc(Excursio exc) {
		this.exc = exc;
	}

	@Override
	public int compareTo(ContingutExcursio o) {
		return exc.compareTo(o.getExc());
	} 
}
