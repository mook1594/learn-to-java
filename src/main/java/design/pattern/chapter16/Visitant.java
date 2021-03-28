package design.pattern.chapter16;

public class Visitant implements Visitor {

	private int total;
	private int num;

	public Visitant() {
		this.total = 0;
		this.num = 0;
	}

	@Override
	public String order(Visitable visitable) {
		if(visitable instanceof Cart) {
			StringBuilder sb = new StringBuilder();
			sb.append("상품명: ").append(((Cart)visitable).name);
			sb.append(", 수량: ").append(((Cart)visitable).num);
			int total = ((Cart)visitable).price * ((Cart)visitable).num;
			sb.append(", 가격: ").append(total);

			this.total += total;
			sb.append(" => 합계: ").append(this.total);

			this.num++;
			return sb.toString();
		}
		return "";
	}

	public int getTotal() {
		return this.total;
	}

	public int getNum() {
		return this.num;
	}
}
