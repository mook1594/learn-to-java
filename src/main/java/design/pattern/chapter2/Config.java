package design.pattern.chapter2;

public class Config {
	protected static Config instance = null;

	public static Config getInstance() {
		if(instance == null) {
			return new Config();
		}
		return instance;
	}

	protected Config() { }

	@Override
	protected Object clone() {
		throw new UnsupportedOperationException("clone 방지");
	}
}
