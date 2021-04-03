package design.pattern.chapter21;

import java.util.ArrayList;
import java.util.List;

public class CareTaker {
	protected List<Memento> stack;

	public CareTaker() {
		this.stack = new ArrayList<>();
	}

	public void push(Originator origin) {
		Memento memento = origin.create();

		this.stack.add(memento);
	}

	public Hello undo(Originator origin) {
		Memento memento = this.stack.get(0);
		this.stack.remove(0);

		origin.restore(memento);

		return origin.getState();
	}
}
