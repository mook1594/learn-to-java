#####[GO TO LIST](../README.md)

# 아이템19. 상속을 고려해 설계하고 문서화하라. 그러지 않았다면 상속을 금지하라

### 상속을 고려한 설계와 문서화
- 상속용 클래스는 재정의 할 수 있는 메서드들을 내부적으로 어떻게 이용하는지 문서로 남겨야 한다.
- 클래스 자신의 다른 메서드를 호출
- 어떤 순서로 호출
- @implSpec을 활용한다

### 상속용 클래스의 시험
- 직접 하위클래스를 만들어 하위클래스를 검증해본다
- 상속용 클래스의 생성자는 직접이든 간접이든 재정의 가능 메서드를 호출하면 안된다.
```java
public class Super {
    public Super() { 
        overrideMe();                
    }                
    public void overrideMe() {
    }                                                
}
```
```java
public final class Sub extends Super {
    private final Instant instant;

    Sub() {
        instant = Instant.now();            
    }

    @Override public void overrideMe() {
        System.out.println(instant);
    }
}
```

### 상속에 주의해야하는것
- clone
- readObject
- Serializable

### 상속을 금지
- 클래스를 final로 선언
- 생성자를 모두 외부에서 접근할 수 없도록 한다.
