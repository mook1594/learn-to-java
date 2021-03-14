package design.pattern.chapter4.korea;

import design.pattern.chapter4.abstracts.Factory;

public class KoreaFactory  extends Factory {

	public KoreaFactory() {

	}

	@Override
	public KoreaTireProduct createTire() {
		return new KoreaTireProduct();
	}

	@Override
	public KoreaDoorProduct createDoor() {
		return new KoreaDoorProduct();
	}
}
