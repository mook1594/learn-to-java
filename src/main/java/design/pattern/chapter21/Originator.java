package design.pattern.chapter21;

import com.sun.org.apache.xpath.internal.operations.Or;

public class Originator {
	protected Hello state;

	// 메멘토
	// 메멘토의 객체를 생성해 반환
	public Memento create() {
		return new Memento(this.state);
	}

	// 복원한다.
	public void restore(Memento memento) {
		this.state = memento.getObj();
	}

	// 상태 불러옴
	public Hello getState() {
		return this.state;
	}

	// 상태 설정
	public void setState(Hello state) {
		this.state = state;
	}
}
