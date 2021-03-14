package design.pattern.chapter4.usa;

import design.pattern.chapter4.abstracts.DoorProduct;
import design.pattern.chapter4.abstracts.Factory;
import design.pattern.chapter4.abstracts.TireProduct;

public class USAFactory extends Factory {
	@Override
	public USATireProduct createTire() {
		return new USATireProduct();
	}

	@Override
	public USADoorProduct createDoor() {
		return new USADoorProduct();
	}
}
