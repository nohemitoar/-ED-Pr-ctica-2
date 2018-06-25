package Dades;

public class NodeF<F extends Comparable<F>, C extends Comparable<C>> {
	F element;
	ExcursioContractada<F, C> rel, relAnt;
	NodeF<F, C> seg,ant;

	public NodeF(F val) {
		element=val;
		rel=null;
		seg=null;
		ant=null;
	}

	public F getElement() {
		return element;
	}

	public ExcursioContractada<F, C> getSegRelC() {
		return rel;
	}

	public void setSegC(ExcursioContractada<F, C> seg) {
		this.rel = seg;
	}

	public NodeF<F, C> getSeg() {
		return seg;
	}

	public void setSeg(NodeF<F, C> seg) {
		this.seg = seg;
	}	
	
	public ExcursioContractada<F, C> getRelAntC() {
		return relAnt;
	}

	public void setRelAntC(ExcursioContractada<F, C> ant) {
		this.relAnt = ant;
	}	
	
	public NodeF<F, C> getAnt() {
		return ant;
	}

	public void setAnt(NodeF<F, C> ant) {
		this.ant = ant;
	}	
}
