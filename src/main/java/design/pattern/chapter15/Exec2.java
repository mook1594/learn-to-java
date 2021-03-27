package design.pattern.chapter15;

public class Exec2 implements Command {

	private Concreate receiver;

	public Exec2(Concreate receiver) {
		this.receiver = receiver;
	}

	@Override
	public void execute() {
		// 명령 실행 2
		this.receiver.action1();
		this.receiver.action2();
	}

	@Override
	public void undo() {

	}
}
