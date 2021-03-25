#### [GO TO BACK](../README.md)

# 13. 프록시 패턴
### 객체 대행 프록시
: 프록시는 대리자를 내세워 처리를 위임, 직접적인 접근을 막고 대리할 객체를 구현
### 프록시 종류
- 원격 프록시
- 가상 프록시
- 보호 프록시
- 스마트 프록시
- 방화벽 프록시
- 레퍼런스 프록시
- 동기화 프록시

##### 원본
```java
public class RealSubject { 
    public RealSubject() {
        // 객체 생성
    }
    
    public void action1() {
        System.out.println("실제 action1 작업을 처리합니다.");
    }
    
    public void action2() {
        System.out.println("실제 action2 작업을 처리합니다.");
    }
}

public class Main {
    public static void main() {
        RealSubject sub = new RealSubject();
        sub.action1();
        sub.action2();
    }
}
```
### 객체 분리
- 프록시는 대리인을 의미 하며 대리자를 생성하여 실제 객체에 접근하지 않고 동일한 동작을 하게 만듬
- 프록시는 대리자를 생성하고 대리자는 원복 객체와 동일한 인터페이스를 적용하여 투명성을 갖게 함.
```java
interface Subject {
    public void action1();
	public void action2();
}

public class RealSubject implements Subject {
    @Override
    public void action1() {
        // action1 처리
    }
    
    @Override
    public void action2() {
        // action2 처리
    }
}

public class Proxy implements Subject {
    private RealSubject realSubject;
    
    public Proxy(RealSubject realSubject) {
        this.realSubject = realSubject;
    }
    
    @Override
    public void action1() {
        // 프록시 action1 처리
        this.realSubject.action1();
    }

    @Override
    public void action2() {
        // 프록시 action2 처리
        this.realSubject.action2();
    } 
}

public class Main {
	public static void main() {
		RealSubject sub = new RealSubject();
		Proxy proxy = new Proxy(sub);
		proxy.action1();
		proxy.action2();
	}
}
```
### 행위를 처리하는 핸들러
- 간접 통로: 프록시의 투과적 특성을 이용하여 실체 객체의 행위를 위임하고 처리 요청
- 핸들러 설계: 프록시는 실제 객체와 인터페이스를 맞춰주는게 중요하다. 이를 관리 하는 핸들러를 추가
```java
// Proxy.java
public void _call(method, args) {
    if(method_exists(this._object, method)) {
        this._object.method(args);
    } else {
        throw new RuntimeError("에러")
    }
}
```
### 프록시 팩토리
```java
public class ProxyFactory {
    public Proxy getObject() {
        RealSubject real = new RealSubject();
        return new Proxy(real);
    }
}
```

### 원격 프록시
- 프록시 vs 어댑터: 프록시와 어댑터는 두 객체를 이어준다는 맥락에서 역할로써는 유사하다. 
    - 어댑터 패턴은 서로 다른 인터페이스를 맞춰줌
    - 프록시는 투과성으로 동일한 인터페이스를 사용
- 프록시 vs 원격 프록시
    - 프록시: 객체를 분리하는 역할
    - 원격 프록시: 분리된 객체에 투과성을 결합해 객체를 연결해서 제어
    
#### 원격 프록시로 캐싱 처리
### [코드](./Proxy.java)

### 가상 프록시
: 프로그램의 실행 속도를 개선하기 위한 패턴
- 초기화 로딩
- 게으른 초기화 (lazy loading): 실제 객체를 생성하지 않고 프록시만 생성해 반환
- 속도 개선
#### 플라이웨이트 패턴 결합
#### 플라이웨이트 패턴 결합
```java
public class Proxy implements Subject {
    private RealSubject _object;

    public Proxy() {
        // 프록시 생성자에서 실 객체를 초기화 하지 않는다.
    }
    
    public String action2() {
        // 게으른 객체 생성
        if(this._object == null) this.real();
        this._object.action2();
    }

    private void real() {
        this._object = new RealSubject();
    }

}
```
### 보호용 프록시
- 통제 제어 목적
- 권한 추가
```java
public class Main {
    public static void main() {
        final String ACT1 = "0x01";
		final String ACT2 = "0x02";
		String permit = ACT1;
		
		Proxy proxy = new Proxy(permit);
		proxy.action1();
		proxy.action2();
    }
}
```
### 스마트 참조자
- 실제 객체에 추가 동작을 삽입할때, 프록시를 통해서 추가 행동 처리
