#### [GO TO BACK](../README.md)

# 11. 파사드 패턴
: 협업에서 관심사를 분리하는 패턴

### 협업을 위한 분리 작업
- 복잡한 구조의 개발 작업: 모듈별로 분리하고 분리된 모듈을 공동으로 개발
- 분리된 모듈의 결합: 모듈끼리 참조로인해 결합도가 생김, 강력한 의존도를 갖게 됨
- 느슨한 결합: 파사드 패턴으로 강력한 결합구조를 느슨한 결합으로 변경
- 간접 접근: 파사드는 인터페이스 계층을 통해서 간접적으로 접근, 하나의 통로 역할만 담당

### 파사드 패턴을 이용한 API
- 구조: 파사드는 시스템에 접근할 수 있는 통로 (고객과 은행전산 시스템을 이어주는 은행원 역할)
- 인터페이스: 서브시스템을 호출 (창구)

### 파사드를 이용한 단순화 
- 단순화
- 캡슐화 배제: 파사드는 단순 인터페이스
- 복잡성 해결
- 의존성 감소

### 잘못된 패턴
```JAVA
public class Thermometer {
    public float temp;
    public float getTemperature() {
        return this.temp;
    }
}
public class Temperature {
    public Thermometer station;
    
    public Temperature(Thermometer station) {
        this.station = station;
    }
    
    public float getTemp() {
        // Thermometer thermometer = this.getThermometer();
        // return thermometer.getTemperature();
        return this.station.getTemperature();
    }
    
    // 불필요한 객체 호출(생성)
    public Thermometer getThermometer() {
        return this.station;
    }
} 
```

### 최소 지식 객체
- 자기 자신만의 객체 사용
- 메서드에 전달된 매개변수 사용
- 메서드에서 생성된 객체 사용
- 객체에 속하는 메서드 사용
```java
public class Car {
    // 클래스의 구성요소 (구성 요소의 메서드는 호출 해도 된다)
    private Engine engine;
    
    public Car(Engine engine) {
        this.engine = engine;
    }
    
    public void start(key) {
        Doors doors = new Doors();
        
        boolean authorized = key.turns();
        if(authorized) {
            // 객체의 구성 요소의 메서드는 호출 가능
            engine.start();
            // 객체 내에 있는 메서드는 호출해도 된다
            this.updateDashboardDisplay();
            // 직접 생성하거나 인스턴스를 만든 객체의 메서드는 호출해도 된다.
            doors.lock();
        }
    }
    
    public void updateDashboardDisplay() {
    	
    }
}
```

### 파사드 패턴
```java
public class Package1 {
	public void process(){
		...
    }
}
public class Package2 {
	public void process(){
		...
	}
}
public class Package3 {
	public void process(){
		...
	}
}

public class Main {
	public static void main(String... args) {
		// 직접 접근
		Package1 pkg1 = new Package1();
		pkg1.process();

		Package2 pkg2 = new Package2();
		pkg2.process();

		Package3 pkg3 = new Package3();
		pkg3.process();
		
		//파사드를 통한 접근
        Facade facade = new Facade();
        facade.processAll();
    }
}

public class Facade {
	private Package1 package1;
	private Package2 package2;
	private Package3 package3;
	
	public Facade() {
		package1 = new Package1();
		package2 = new Package2();
		package3 = new Package3();
    }
    
    public void processAll() {
		this.package1.process();
		this.package2.process();
		this.package3.process();
    }
}
```

### 파사드 패턴의 효과
- 서브 시스템 보호
- 확장성
- 결합도 감소
- 계층화
- 이식성
- 공개 인터페이스

### 관련 패턴
#### [추상 팩토리 패턴](../chapter4/README.md)
#### [어댑터 패턴](../chapter7/README.md)
#### [중재자 패턴](../chapter19/README.md)
#### [싱글턴 패턴](../chapter2/README.md)
