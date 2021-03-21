package design.pattern.chapter9.pt1;

public class Computer {
	public Monitor monitor;
	public Disk disk;
	public Memory memory;
	public String name = "구성";

	public void setMonitor(Monitor monitor) {
		this.monitor = monitor;
	}

	public void setDisk(Disk disk) {
		this.disk = disk;
	}

	public void setMemory(Memory memory) {
		this.memory = memory;
	}
}
