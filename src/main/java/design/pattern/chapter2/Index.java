package design.pattern.chapter2;

public class Index {
	public static void main(String... args) {
		Config config = Config.getInstance();
		Env config2 = (Env) Env.getInstance();
		config2.setting();
	}
}
