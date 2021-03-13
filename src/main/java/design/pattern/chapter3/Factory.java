package design.pattern.chapter3;

public abstract class Factory {

	public LgProduct create(String model) {
		// return new LgProduct();
		return createProduct(model);
	}

	abstract public LgProduct createProduct(String model);
}
