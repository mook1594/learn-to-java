package design.pattern.chapter15;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Invoker {

	public Map<String, Command> commands = new HashMap<>();

	public void setCommand(String key, Command command) {
		commands.put(key, command);
	}

	public void remove(String key) {
		commands.remove(key);
	}

	public void execute(String key) {
		if(commands.containsKey(key)) {
			this.commands.get(key).execute();
		}
		// 실행 순서 저장 후
	}

	public void undo() {
		// 실행 undo
	}
}
