package design.pattern.chapter5;

public abstract class BuildAlgorism {
	protected Computer composite;

	public abstract BuildAlgorism setCpu();
	public abstract BuildAlgorism setMemory(int size);
	public abstract BuildAlgorism setStorage(int size);

	public Computer getInstance() {
		return composite;
	}
}
