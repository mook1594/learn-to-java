package design.pattern.chapter24;

import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main() {
		String lex = "{{ 1 1 + }}"; // 후회 표기법
		Context context = new Context(lex);
		List<Expression> stack = new ArrayList<>();

		if(context.isStart()) {
			while(true) {
				String token = context.next();
				if(token == "}}") {
					break;
				} else if(is_number(token)) {
					stack.add(new Terminal(token));
				} else if(token == "+") {
					String left = stack.pop();
					String right = stack.pop();

					Add add = new Add(left, right);
					String value = add.interpret();
					stack.add(new Terminal(value));
				}
			}
		} else {
			"판단할 수 없는 해석";
		}
		stack.pop().interpret();
	}
}
