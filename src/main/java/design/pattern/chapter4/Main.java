package design.pattern.chapter4;

import design.pattern.chapter4.korea.KoreaDoorProduct;
import design.pattern.chapter4.korea.KoreaFactory;
import design.pattern.chapter4.korea.KoreaTireProduct;
import design.pattern.chapter4.usa.USADoorProduct;
import design.pattern.chapter4.usa.USAFactory;
import design.pattern.chapter4.usa.USATireProduct;

public class Main {

	public static void main(String... args) {
		KoreaFactory koreaFactory = new KoreaFactory();

		KoreaTireProduct ham = koreaFactory.createTire();
		ham.makeAssemble();

		KoreaDoorProduct bread = koreaFactory.createDoor();
		bread.makeAssemble();

		USAFactory usaFactory = new USAFactory();

		USATireProduct ham2 = usaFactory.createTire();
		ham.makeAssemble();

		USADoorProduct bread2 = usaFactory.createDoor();
		bread.makeAssemble();
	}
}
