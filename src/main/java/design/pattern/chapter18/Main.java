package design.pattern.chapter18;

public class Main {
	public static void main(String... args) {
		Members members = new Members();

		UserA a = new UserA("Jiny");
		members.addObserver(a);

		UserB b = new UserB("Eric");
		members.addObserver(b);

		members.notiObserver();
	}
}
