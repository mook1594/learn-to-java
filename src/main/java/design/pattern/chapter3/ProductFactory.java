package design.pattern.chapter3;

public class ProductFactory extends Factory {

	@Override
	public LgProduct createProduct(String model) {
		return new LgProduct();
	}
}
