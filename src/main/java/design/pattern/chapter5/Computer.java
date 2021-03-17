package design.pattern.chapter5;

import java.util.ArrayList;
import java.util.List;

public class Computer {

	private CPU cpu;
	private List<Memory> ram;
	private List<Storage> storage;

	public Computer() {

	}

	public int memory() {
		return ram.stream()
			.mapToInt(r -> r.getSize())
			.sum();
	}

	public int storage() {
		return storage.stream()
			.mapToInt(s -> s.getSize())
			.sum();
	}
}
