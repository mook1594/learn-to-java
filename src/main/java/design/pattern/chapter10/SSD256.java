package design.pattern.chapter10;

public class SSD256 extends Decorate {

	public Component base;
	public SSD256(Component component) {
		this.base = component;
	}

	@Override
	public String product() {
		return this.base.product() + ", ssd256";
	}

	@Override
	public int price() {
		return this.base.price() + 11_000;
	}
}
