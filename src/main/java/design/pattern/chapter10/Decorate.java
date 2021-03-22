package design.pattern.chapter10;

public abstract class Decorate implements Component {
	@Override
	public abstract String product();

	@Override
	public abstract int price();
}
