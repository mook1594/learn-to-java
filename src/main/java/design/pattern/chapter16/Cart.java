package design.pattern.chapter16;

public class Cart implements Visitable {

	String name;
	int price;
	int num;

	public Cart(String name, int price, int num) {
		this.name = name;
		this.price = price;
		this.num = num;
	}

	@Override
	public String accept(Visitor visitor) {
		// 방문자의 주문을 호출
		return visitor.order(this);
	}

	public int getTax(int tax) {
		return (this.price * this.num) * tax / 100;
	}

	public String list() {
		StringBuilder sb = new StringBuilder(this.name);
		sb.append(", 수량=").append(this.num);
		sb.append(", 가격=").append(this.num * this.price);
		return sb.toString();
	}
}
