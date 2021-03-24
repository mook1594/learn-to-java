package design.pattern.chapter12;

public class MorseL implements FlyWeight {
	@Override
	public String code() {
		return "*-**";
	}

	@Override
	public char character() {
		return 'L';
	}
}
