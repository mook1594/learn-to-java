package design.pattern.chapter5;

public class Storage {
	
	private int size;

	public Storage() {
		this.size = 0;
	}

	public Storage(int size) {
		this.size = size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getSize() {
		return size;
	}
}
