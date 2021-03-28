package design.pattern.chapter16;

import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main() {
		Cart cart = new Cart("컵라면", 900, 2);
		cart.list();
		cart.accept(new Visitant());

		Visitant visitant = new Visitant();

		List<Cart> carts = new ArrayList<>();
		carts.add(new Cart("컵라면", 900, 3));
		carts.add(new Cart("아이스크림", 1500, 1));
		carts.add(new Cart("음료수", 2800, 1));

		carts.stream()
			.forEach(c -> c.accept(visitant));
	}
}
