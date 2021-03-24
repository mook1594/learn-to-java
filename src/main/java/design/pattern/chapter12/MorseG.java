package design.pattern.chapter12;

public class MorseG implements FlyWeight {

	public MorseG() {

	}

	@Override
	public String code() {
		return "**-*";
	}

	@Override
	public char character() {
		return 'G';
	}
}
