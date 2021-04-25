package design.pattern.chapter24;

public class Context {
	private String[] token;
	private int index = 0;

	public Context(String text) {
		this.token = text.split(" ");
	}

	public boolean isStart() {
		if(this.token[index] == "{{") {
			index++;
			return true;
		} else {
			return false;
		}
	}

	public String next() {
		String token = this.token[index];
		index++;
		return token;
	}
}
