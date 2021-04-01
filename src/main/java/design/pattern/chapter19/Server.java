package design.pattern.chapter19;

import java.util.ArrayList;

public class Server extends Mediator {
	public Server() {
		this.colleague = new ArrayList<>();
	}

	@Override
	public void createColleague() {
		// 동료 객체 초기화
	}

	// 중재 기능
	public void mediate(String data, String userName) {
		// user 로 부터 서버 메시지를 받음

		// 모든 Colleague에게 전달 받은 메시지를 통보
		this.colleague.stream()
			.forEach(col -> col.message(data));
	}
}
