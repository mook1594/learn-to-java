package design.pattern.chapter13;

public class Proxy implements Subject {

	private RealSubject subject;

	public Proxy(RealSubject subject) {
		this.subject = subject;
	}

	@Override
	public String action1() {
		return "프록시 액션 1으로 대체";
	}

	@Override
	public String action2() {
		String msg = this.subject.action2();
		if(msg == null || msg.isEmpty()) {
			return "실제 객체의 문자열이 없음";
		}
		return msg;
	}

	@Override
	public String action3() {
		return this.subject.action3();
	}
}
