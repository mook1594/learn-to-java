package design.pattern.chapter19;

public abstract class Colleague {
	protected Server mediator;

	// 중개 객체 설정
	// concreateMediator에 의해서 호출
	public void setMediator(Server mediator) {
		this.mediator = mediator;
	}
}
