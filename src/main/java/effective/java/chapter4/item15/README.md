##### [GO TO LIST](../README.md)

# 아이템15. 클래스 멤버의 접근 권한을 최소화 하라

### 잘 설계된 컴포넌트?
- 클래스 내부 데이터와 구현정보를 얼마나 잘 숨겼는가.
- 정보은닉, 캡슐화

### 캡슐화의 장점
- 시스템 개발 속도를 높인다. 여러 컴포넌트를 병렬로 개발할 수 있다.
- 시스템 관리 비용을 낮춘다. 컴포넌트의 이해가 빠르고 디버깅 할 수 있으며, 다른 컴포넌트로에 교체도 어렵지 않다.
- 성능 최적화에 도움을 준다. 최적화할 컴포넌트를 정하고 최적화 할 수 있다.
- 소프트웨어 재사용성을 높인다. 외부에 의존하지 않고 독립적인 컴포넌트면, 전혀 다른 환경에서도 유용하게 쓰일 수 있다.
- 큰 시스템을 제작하는 난이도를 낮춰준다. 개별 컴포넌트 동작을 검증하기 때문.

### 자바에서의 캡슐화 활용
- 접근 제한자를 적재 적소에 활용한다.
- private: 멤버를 선언한 클래스에서만 접근 가능
- package-private(default); 패키지내에서는 public, 외에선 private
- protected: package-private의 접근 범위와 동일하며, 추가로 하위 클래스에서도 접근 가능.
- public: 모든 곳에서 접근

### 공개 해도 되는것?
- 추상 개념의 상수라면 public static final로 사용해도 좋다.
- 대신 툴변 객체 이어야 한다.

### 주의해야할것
- 배열은 조심. 길이가 0이 아니면 변경가능하니 주의가 필요
```java
// 보안 허점이 숨어 있다.
public static final Thing[] VALUES = { ... };
```

### 해결방법
- 배열을 private 으로 하고, public으로 불변 리스트를 추가한다
```java
private static final Thing[] PRIVATE_VALUES = { ... };
public static final List<Thing> VALUES = 
    Collections.unmodifiableList(Arrays.asList(PRIVATE_VALUES));
```
- 배열을 private로 하고 복사본을 반환환다. (방어적 복사)
```java
private static final Thing[] PRIVATE_VALUES = { ... };
public static final Thing[] values() {
    return PRIVATE_VALUES.clone();
}
```

### 자바 9에서 모듈 시스템 활용
- 클래스를 외부에 공개하지 않으면서 같은 모듈을 이루는 패키지 사이에서 자유롭게 공유가능

### 결론
- 클래스 요소 접근은 가능한 최소한으로 하라.
- 상수용 public static final 필드 외에는 public을 지양한다.
- public static final이 참조하는 객체는 반드시 불변!
