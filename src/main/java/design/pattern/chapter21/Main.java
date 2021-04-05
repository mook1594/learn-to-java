package design.pattern.chapter21;

public class Main {

	public static void main() {
		Originator origin = new Originator();

		Hello hello = new Hello("상태1: 안녕하세요");

		// 상태 1 설정 후 원조본을 메멘토를 통해 저장
		origin.setState(hello);
		Memento memento = origin.create();

		hello.setMessage("상태2: Hello nice meet you.");
		origin.restore(memento);
		hello = origin.getState();

		// 원조본 케어테이커 생성
		Originator origin1 = new Originator();
		CareTaker care = new CareTaker();

		Hello hello2 = new Hello("상태1: 안녕하세요");
		origin.setState(hello2);
		care.push(origin);

		hello2.setMessage("상태2: 아아아아아아아");
		origin.setState(hello);
		care.push(origin);

		hello2.setMessage("상태3: dfasfdsadfsadfasd");
		origin.setState(hello);
		care.push(origin);

		Hello obj = care.undo(origin);
		Hello obj1 = care.undo(origin);
		Hello obj2 = care.undo(origin);
	}
}
