package design.pattern.chapter19;

public class User extends Colleague {
	protected String name;

	public User(String name) {
		this.name = name;
	}

	public String userName() {
		return this.name;
	}

	public void send(String data) {
		// 중개 서버로 메시지를 전송
		this.mediator.mediate(data, this.name);
	}

	public void message(String data) {

	}
}
