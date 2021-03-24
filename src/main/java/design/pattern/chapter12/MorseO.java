package design.pattern.chapter12;

public class MorseO implements FlyWeight {
	@Override
	public String code() {
		return "---";
	}

	@Override
	public char character() {
		return 'O';
	}
}
