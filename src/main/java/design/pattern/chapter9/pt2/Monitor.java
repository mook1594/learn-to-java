package design.pattern.chapter9.pt2;

import java.util.List;

public class Monitor {
	public List<Monitor> screen;
	public String name = "모니터";

	public void addMonitor(Monitor monitor) {
		screen.add(monitor);
	}

	public void show() {
		screen.stream()
			.forEach(s -> System.out.println(s));
	}
}
