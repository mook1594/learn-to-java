package design.pattern.chapter10;

public class I7 extends Decorate {
	public Component base;

	public I7(Component concrete) {
		this.base = concrete;
	}

	@Override
	public String product() {
		return this.base.product() + ", i7";
	}

	@Override
	public int price() {
		return this.base.price() + 475_000;
	}
}
