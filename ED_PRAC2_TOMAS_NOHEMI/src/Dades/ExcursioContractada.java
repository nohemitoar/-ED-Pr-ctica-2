package Dades;

public class ExcursioContractada<F extends Comparable<F>, C extends Comparable<C>>{
	private NodeF<F, C> f;
	private NodeC<F, C> c;
	private ExcursioContractada<F, C> antF;
	private ExcursioContractada<F, C> antC;
	private ExcursioContractada<F, C> segF;
	private ExcursioContractada<F, C> segC;
	
	public ExcursioContractada() {
		f=null;
		c=null;
		antF=null;
		antC=null;
		segF=null;
		segC=null;
	}
	
	public ExcursioContractada(NodeF<F, C> f, NodeC<F, C> c) {
		this.f=f;
		this.c=c;
		antF=null;
		antC=null;
		segF=null;
		segC=null;
	}

	public F getF() {
		return f.getElement();
	}

	public NodeF<F, C> getNodeF() {
		return f;
	}

	public C getC() {
		return c.getElement();
	}

	public NodeC<F, C> getNodeC() {
		return c;
	}
	
	public ExcursioContractada<F, C> getAntF() {
		return antF;
	}

	public void setAntF(ExcursioContractada<F, C> antF) {
		this.antF = antF;
	}

	public ExcursioContractada<F, C> getAntC() {
		return antC;
	}

	public void setAntC(ExcursioContractada<F, C> antC) {
		this.antC = antC;
	}
	
	public ExcursioContractada<F, C> getSegF() {
		return segF;
	}

	public void setSegF(ExcursioContractada<F, C> segF) {
		this.segF = segF;
	}

	public ExcursioContractada<F, C> getSegC() {
		return segC;
	}

	public void setSegC(ExcursioContractada<F, C> segC) {
		this.segC = segC;
	}
	
}
