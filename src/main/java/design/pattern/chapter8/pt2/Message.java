package design.pattern.chapter8.pt2;

public class Message extends Language {

	public Message(Hello language) {
		this.language = language;
	}

	@Override
	public String greeting() {
		return this.language.greeting(); // 분리된 역할 분담
	}
}
