package design.pattern.chapter8.pt2;

public class Main {
	public static void main(String... args) {

		Language language = new Message(new Korean());

		language.greeting();
	}
}
