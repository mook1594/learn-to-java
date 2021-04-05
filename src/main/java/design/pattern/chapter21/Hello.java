package design.pattern.chapter21;

public class Hello {
	private String message;

	public Hello(String message) {
		this.message = message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage(){
		return this.message;
	}
}
