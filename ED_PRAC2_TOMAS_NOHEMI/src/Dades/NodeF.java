package Dades;

public class NodeF<F extends Comparable<F>, C extends Comparable<C>> {
	F element;
	ExcursioContractada<F, C> rel;
	NodeF<F, C> seg;

	public NodeF(F val) {
		element=val;
		rel=null;
		seg=null;
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
}
