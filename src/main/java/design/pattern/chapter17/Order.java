package design.pattern.chapter17;

public class Order extends Chain {

	@Override
	public String execute(String event){
		if(event == "order") {
			return "주문을 처리합니다.";
		}
		return this.next.execute(event);
	}
}
