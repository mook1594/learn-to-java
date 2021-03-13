package design.pattern.chapter3;

public class Index {

	public static void main(String... args) {
		Factory factory = new ProductFactory();
		LgProduct product = factory.create("LG");
		product.name();
	}
}
