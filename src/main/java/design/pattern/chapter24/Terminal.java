package design.pattern.chapter24;

public class Terminal implements Expression {
	private String n;

	public Terminal(String n){
		this.n = n;
	}

	@Override
	public String interpret() {
		return n;
	}
}
