package design.pattern.chapter5;

public class Main {

	public static void main(String... args) {
		BuildAlgorism algorism = new ProductModel();

		Factory factory = new Factory(algorism);

		Computer computer = factory.build().getInstance();

	}
}
