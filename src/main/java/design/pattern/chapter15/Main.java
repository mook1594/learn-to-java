package design.pattern.chapter15;

public class Main {

	public static void main(String... args) {
		Command exec1 = new Exec1();

		// exec1.execute();

		Concreate receiver = new Concreate();
		Command exec2 = new Exec2(receiver);

		// exec2.execute();

		Invoker invoker = new Invoker();
		invoker.setCommand("cmd1", exec1);
		invoker.setCommand("cmd2", exec2);
		invoker.setCommand("cmd3", new Command() {
			@Override
			public void execute() {
				// 명령 실행 3
			}

			@Override
			public void undo() {

			}
		});

		// invoker.execute("cmd1");
		// invoker.execute("cmd2");
		for(Command cmd : invoker.commands.values()) {
			cmd.execute();
		}
		exec1.undo();
	}
}
