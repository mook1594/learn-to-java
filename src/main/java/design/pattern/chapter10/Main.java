package design.pattern.chapter10;

public class Main {
	public static void main(String... args) {
		Product1 product1 = new Product1();

		Decorate spec = new I7(product1);
		spec = new SSD256(spec);

		System.out.println("스펙= " + spec.product());
		System.out.println("가격= " + spec.price());
	}
}
