package design.pattern.chapter21;

public class Memento {
	protected Hello obj;

	public Memento(Hello obj) {
		this.obj = obj; // clone
	}

	public Hello getObj() {
		return this.obj;
	}
}
