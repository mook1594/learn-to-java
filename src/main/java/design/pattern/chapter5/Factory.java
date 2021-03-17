package design.pattern.chapter5;

public class Factory extends Builder {

	public Factory(BuildAlgorism algorism) {
		this.algorism = algorism;
	}

	@Override
	public Factory build() {
		this.algorism.setCpu();
		this.algorism.setMemory(1);
		this.algorism.setStorage(1);

		return this;
	}
}
