package design.pattern.chapter18;

public class UserB extends Observer {

	public UserB(String name) {
		this.name = name;
	}

	@Override
	public void update() {
		System.out.println("갱신 - " + this.name);
	}
}
