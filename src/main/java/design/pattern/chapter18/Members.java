package design.pattern.chapter18;

import java.util.ArrayList;
import java.util.List;

public class Members implements Subject {

	List<Observer> objs = new ArrayList<>();

	@Override
	public void addObserver(Observer o) {
		objs.add(o);
	}

	@Override
	public void deleteObserver(Observer o) {
		objs.remove(o);
	}

	@Override
	public void notiObserver() {
		objs.stream()
			.forEach(observer -> observer.update());
	}
}
