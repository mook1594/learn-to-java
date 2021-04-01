package design.pattern.chapter19;

import java.util.ArrayList;
import java.util.List;

public abstract class Mediator {
	protected List<User> colleague = new ArrayList<>();

	public void addColleague(User obj) {
		colleague.add(obj);
	}

	abstract public void createColleague();
}
