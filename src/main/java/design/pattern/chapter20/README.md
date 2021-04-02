#### [GO TO BACK](../README.md)

# 20. 상태 패턴 (Status)
: 제어문 대신 캡슐화 하여 독립된 동작을 구분 하는 패턴
: if, switch 문을 없앨 수 있는 패턴

### 상태란
- 값을 조건으로 사용
- 동작을 구분하는 상태 값
- 상수값을 이용하여 비교

### 상태 처리
- 제어문
- if문 상태처리
- switch문 상태 처리

### 상태 패턴
```java
public class JinyOrder {
	public static int OPEN = 1;
	public static int PAY = 2;
	public static int ORDERED = 4;
	public static int FINISH = 8;
	
	public void process(int state) {
		switch(state) {
            case this.OPEN:
            	this.stateOPEN();
            	break;
            case this.PAY:
            	this.statePAY();
            	break;
            case this.ORDERED:
            	this.stateORDERED();
            	break;
            case this.FINISH:
            	this.stateFINISH();
            	break;
		}
    }
}
```
### 상태 캡슐화
```java
public interface State {
	void process();
}
public class StateOPEN implements State {
	public void process() {
		
    }
}
public class statePAY implements State {
	public void process() {
		
    }
}
public class stateORDERED implements State {
	public void process() {
		
    }
}
public class stateFINISH implements State {
	public void process() {
		
    }
}
```

### 객체 생성

```java
public class JinyOrder {
	private Map<Integer, State> state;

	public JinyOrder() {
		this.state = new HashMap();
		this.state.put(ORDER, new StateOPEN());
		this.state.put(PAY, new statePAY());
		this.state.put(ORDERED, new stateORDERED());
		this.state.put(FINISH, new stateFINISH());
	}

	public void process(int state) {
		this.state.get(state).process();
    }
}
public class Main {
	public static void main(String... args) {
		JinyOrder order = JinyOrder();
		order.process(ORDER);
    }
}
```

### 예제
#### [상태값 인터페이스 코드](./LightState.java) 
#### [상태값 제어](./LightLamp.java) 

### 효과
- 조건문 해결
- 런타임
- 확장성
- 변화
