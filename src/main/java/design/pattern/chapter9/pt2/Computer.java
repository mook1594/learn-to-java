package design.pattern.chapter9.pt2;

import design.pattern.chapter9.pt1.Disk;
import design.pattern.chapter9.pt1.Memory;

public class Computer {
	public Monitor monitor;
	public String name = "구성";

	public void setMonitor(Monitor monitor) {
		this.monitor = monitor;
	}
}
