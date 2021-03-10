package design.pattern.chapter1;

public class Factory {
	public static Language getInstance(LanguageType type) {

		if(LanguageType.KOREAN == type) {
			return new Korean();
		} else if(LanguageType.ENGLISH == type) {
			return new English();
		}
		throw new IllegalArgumentException("type error");
	}
}
