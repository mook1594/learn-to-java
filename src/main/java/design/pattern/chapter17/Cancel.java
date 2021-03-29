package design.pattern.chapter17;

public class Cancel extends Chain{

	@Override
	public String execute(String event) {
		if(event == "cancel") {
			return "주문을 취소합니다.";
		}
		return this.next.execute(event);
	}
}
