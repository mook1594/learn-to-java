package design.pattern.chapter17;

public abstract class Chain {
	protected Chain next;
	public void setNext(Chain chain) {
		this.next = chain;
	}

	abstract String execute(String event);
}
