package design.pattern.chapter17;

public class Handler {

	public String event(String event) {
		Order first = new Order();
		first.setNext(new Cancel());

		return first.execute(event);
	}

	public static void main(String... args) {
		Handler handler = new Handler();
		handler.event("order");
	}
}
