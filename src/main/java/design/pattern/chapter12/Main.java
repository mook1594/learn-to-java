package design.pattern.chapter12;

public class Main {
	public static void main(String... args) {
		Factory share = new Factory();

		String name = "goooogllleee";

		for(char c : name.toCharArray()) {
			FlyWeight fw = share.getCode(c);
			System.out.println(fw.code());
		}
	}
}
