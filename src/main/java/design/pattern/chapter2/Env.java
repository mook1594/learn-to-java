package design.pattern.chapter2;

public class Env extends Config {

	public void setting() {

	}

	public static Config getInstance() {
		if(instance == null) {
			instance = new Env();
		}
		return instance;
	}
}
