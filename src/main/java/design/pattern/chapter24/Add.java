package design.pattern.chapter24;

public class Add implements Expression{
	private Expression left;
	private Expression right;

	public Add(Expression left, Expression right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public String interpret() {
		return this.left.interpret() + this.right.interpret();
	}
}
