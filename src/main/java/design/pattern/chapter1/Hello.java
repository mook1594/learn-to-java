package design.pattern.chapter1;

public class Hello {
	public String greeting(LanguageType type) {
		Language lang = Factory.getInstance(type);
		return lang.text();
	}

	public static void main(String... args) {
		Hello hello = new Hello();
		System.out.println(hello.greeting(LanguageType.KOREAN));
		System.out.println(hello.greeting(LanguageType.ENGLISH));
	}
}
