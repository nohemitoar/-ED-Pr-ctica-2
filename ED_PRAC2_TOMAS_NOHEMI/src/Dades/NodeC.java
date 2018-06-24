package Dades;

public class NodeC<F extends Comparable<F>, C extends Comparable<C>> {
	C element;
	ExcursioContractada<F, C> rel;
	NodeC<F, C> seg;

	public NodeC(C val) {
		element=val;
		rel=null;
		seg=null;
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
}
