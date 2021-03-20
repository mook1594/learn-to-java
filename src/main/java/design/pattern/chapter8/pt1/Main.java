package design.pattern.chapter8.pt1;

public class Main {
	public static void main(String... args) {
		Hello korean = new Korean();
		Hello english = new English();

		korean.greeting();
		english.greeting();
	}
}
