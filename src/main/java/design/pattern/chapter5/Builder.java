package design.pattern.chapter5;

public abstract class Builder {

	protected BuildAlgorism algorism;

	public Builder setAlgorism(BuildAlgorism algorism) {
		this.algorism = algorism;
		return this;
	}

	public Computer getInstance() {
		return this.algorism.getInstance();
	}

	public abstract Builder build();
}
