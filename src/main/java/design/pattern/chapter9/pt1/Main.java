package design.pattern.chapter9.pt1;

public class Main {

	public static void main(String... args) {
		Computer computer = new Computer();
		computer.setMonitor(new Monitor());

		System.out.println(computer.name);
		System.out.println(computer.monitor.name);
		System.out.println(computer.memory.name);
		System.out.println(computer.disk.name);
	}
}
