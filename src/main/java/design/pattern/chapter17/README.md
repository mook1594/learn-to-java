#### [GO TO BACK](../README.md)

# 17. 체인 패턴
: 상태값 처리 방법, 상태값 복수 행동 연결, 객체 연결
: 재귀호출로 순차적인 실행, 줄줄이 기차 실행, 전처리에 활용가능

### 제어문
- 조건 처리 (if, switch)

### 동작 조건
- 조건값: 조건에 따라 다르게 동작
- 인터럽트: 실행을 방해하여 들어온 동작을 최우선적으로 처리
- 이벤트: 상태값을 지속적으로 감시하고, 특정 상태값이 되었을때 지정 동작을 실행
- 핸들러: 이벤트 감시를 핸들러가 반복 루프로 감시

### 행동 분리
- 이벤트 처리로직과 출력동작을 분리
```java
public class Order {

	public String execute(){
		return "주문을 처리합니다.";
	}
}

public class Cancel {
	public String execute() {
		return "주문을 취소합니다.";
	}
}

public class Handler {

	public String event(String message) {
		if(message == "order") {
			return (new Order()).execute();
		} else if(message == "cancel") {
			return (new Cancel()).execute();
		}
		return "동직이 없습니다.";
	}
}
```

### 사슬 연결
##### 송수신 분리
```java
public class Order {
	public String execute(String event){
		if(event == "order") {
			return "주문을 처리합니다.";
		}
		return null;
	}
}

public class Cancel {
	public String execute(String event) {
		if (event == "cancel") {
			return "주문을 취소합니다.";
		}
		return null;
	}
}

public class Handler {

	public String event(String message) {
		if((new Order()).execute(message) != null) {
			return (new Order()).execute(message);
		}
		if((new Cancel()).execute(message) != null) {
			return (new Cancel()).execute(message);
		}
	}
}
```
##### 체인 연결
- 하나의 상태값에 복수 동작을 처리 가능하도록함
#### [체인](./Chain.java)
#### [체인 연결](./Order.java)
#### [핸들러 처리](./Handler.java)

### 채인 패턴활용 
- 미들 웨어
- 사전 동작

### 관련 패턴
#### [복합체 패턴](../chapter9/README.md)
#### [명령 패턴](../chapter15/README.md)
