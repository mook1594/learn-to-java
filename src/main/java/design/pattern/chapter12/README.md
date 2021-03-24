#### [GO TO BACK](../README.md)

# 12. 플라이웨이트 패턴

### 메모리 자원
#### 중복된 코드를 제거
```java
public class Hello {
	public String console(String msg) {
		return msg + "\n";
    }
    public String browser(String msg) {
		return msg + "<br />";
    }
}

public class Korean extends Hello {
	private Hello hello;
	public Korean(Hello hello) {
		this.hello = hello;
    }
	public String hello() {
		return this.console("안녕하세요");
    }
}

public class English extends Hello {
	private Hello hello;
	public English(Hello hello) {
		this.hello = hello;
	}
	public String hello() {
		return this.console("Hello");
    }
}

public static void main(String... args) {
	Hello hello = new Hello(); // 공유객체
	public Korean ko = new Korean(hello);
	System.out.println(ko.hello());
	public English ko = new English(hello);
	System.out.println(en.hello());
}
```
### 플라이 웨이트 패턴
: 중복 코드를 방지하기 위한 패턴
- 객체 재사용
- 공유객체를 공유: 메모리 절약

#### 자원관리
- 팩토리 패턴, 싱글턴 패턴을 사용하여 객체를 위임하여 자원을 효율적으로 관리
```java
public class Hello {
	private static Hello instance;
	public static Hello instance() {
		if(intance == null) {
			this.instance = new Hello();
		}
		return this.instance;
    }
	public String console(String msg) {
		return msg + "\n";
    }
    public String browser(String msg) {
		return msg + "<br />";
    }
}
public class Factory {
	public Hello make() {
		return Hello.instance();
    }
}

public static void main(String... args) {
	Hello hello1 = (new Factory()).make();
	Hello hello2 = (new Factory()).make();
}
```
#### 공유 저장소
- 팩토리에서 효과적으로 공유객체 자원을 관리

```java
import java.util.HashMap;

public class Factory {
	private Map<String, Hello> pool = new HashMap();

	public Hello make(Hello name) {
        if(!pool.hasKey(name.console())) {
        	pool.put(name.console(), name.instance());
		}
		return pool.get(name.console());
	}
}
```

### [플라이패턴 실습](./Main.java)

### 관련 패턴
### [프록시 패턴](../chapter13/README.md)
### [복합체 패턴](../chapter9/README.md)
### [싱글턴 패턴](../chapter2/README.md)
### [전략 패턴](../chapter23/README.md)
### [상태 패턴](../chapter20/README.md)
