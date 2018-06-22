package Dades;

import Classes.Turista;
import Llistes.LlistaGenericaExcursions;

public class ExcursioContractada implements TADMultillistaGenerica<Excursio, Turista>{
	private Turista turista;
	private LlistaGenericaExcursions[] ll_exc;

	@Override
	public boolean existeix(Excursio exc, Turista tur) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void afegirFila(Excursio exc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afegirCol(Turista tur) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Excursio[] getFila(Turista tur) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Turista[] getColumna(Excursio exc) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
