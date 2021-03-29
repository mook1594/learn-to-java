package design.pattern.chapter18;

public class UserA extends Observer {

	public UserA(String name) {
		this.name = name;
	}

	@Override
	public void update() {
		System.out.println("갱신 - " + this.name);
	}
}
