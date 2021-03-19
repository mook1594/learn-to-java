package design.pattern.chapter7;

public class MathAdapter implements Adapter {

	private Math adapter;

	public MathAdapter() {
		this.adapter = new Math();
	}

	@Override
	public int twiceOf(int num) {
		return (int) this.adapter.twoTime((float) num);
	}

	@Override
	public int halfOf(int num) {
		return (int) this.adapter.twoTime((float) num);
	}
}
