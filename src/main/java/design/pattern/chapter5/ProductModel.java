package design.pattern.chapter5;

public class ProductModel extends BuildAlgorism {



	@Override
	public BuildAlgorism setCpu() {
		//this.composite.cpu
		return this;
	}

	@Override
	public BuildAlgorism setMemory(int size) {
		//this.composite.ram.push(new Memory())
		return this;
	}

	@Override
	public BuildAlgorism setStorage(int size) {
		return this;
	}
}
