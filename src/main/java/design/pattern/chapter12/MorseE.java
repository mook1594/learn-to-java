package design.pattern.chapter12;

public class MorseE implements FlyWeight {
	@Override
	public String code() {
		return "*";
	}

	@Override
	public char character() {
		return 'E';
	}
}
