package design.pattern.chapter19;

public class Main {
	public static void main(String... args) {
		Server server = new Server();

		User user1 = new User("james");
		user1.setMediator(server);
		server.addColleague(user1);

		User user2 = new User("jiny");
		user2.setMediator(server);
		server.addColleague(user2);

		User user3 = new User("eric");
		user3.setMediator(server);
		server.addColleague(user3);

		user1.send("안녕하세요 저는 james 입니다.");
		user1.send("안녕하세요 저는 jiny 입니다.");
		user1.send("안녕하세요 저는 eric 입니다.");
	}
}
