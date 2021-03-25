package design.pattern.chapter13;

public class RealSubject implements Subject {
	public RealSubject() {
		// 객체 생성
	}

	@Override
	public String action1() {
		return "작업 처리1";
	}

	@Override
	public String action2() {
		return "작업 처리2";
	}

	@Override
	public String action3() {
		return "작업 처리3";
	}
}
