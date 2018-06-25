package Dades;

public class NodeC<F extends Comparable<F>, C extends Comparable<C>> {
	C element;
	ExcursioContractada<F, C> rel, relAnt;
	NodeC<F, C> seg, ant;

	public NodeC(C val) {
		element=val;
		rel=null;
		seg=null;
		ant=null;
	}

	public C getElement() {
		return element;
	}

	public ExcursioContractada<F, C> getSegRelF() {
		return rel;
	}

	public void setSegF(ExcursioContractada<F, C> seg) {
		this.rel = seg;
	}

	public NodeC<F, C> getSeg() {
		return seg;
	}

	public void setSeg(NodeC<F, C> seg) {
		this.seg = seg;
	}		
	
	public ExcursioContractada<F, C> getRelAntF() {
		return relAnt;
	}

	public void setRelAntF(ExcursioContractada<F, C> ant) {
		this.relAnt = ant;
	}	
	
	public NodeC<F, C> getAnt() {
		return ant;
	}

	public void setAnt(NodeC<F, C> ant) {
		this.ant = ant;
	}
}
