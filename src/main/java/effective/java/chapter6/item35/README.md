##### [GO TO LIST](../README.md)

# 아이템35. ordinal 메서드 대신 인스턴스 필드를 사용하라

### ordinal
- 몇 번째 위치 인지 반환하는 메서드
```java
public enum Ensemble {
    SOLO, DUET, TRIO, QUARTET, QUINTET,
    SEXTET, SEPTET, OCTET, NONET, DECTET;

    public int numberOfMusicians() { return ordinal() + 1; }
}
```
- 단점: 순서를 변경하는 순간 기존 동작들은 버그의 대상이 된다.

### 해결책
- 인스턴스 필드에 저장한다
```java
public enum Ensemble {
    SOLO(1), DUET(2), TRIO(3), QUARTET(4), QUINTET(5),
    SEXTET(6), SEPTET(7), OCTET(8), DOUBLE_QUARTET(8),
    NONET(9), DECTET(10), TRIPLE_QUARTET(12);
    
    private final int numberOfMusicians;
    Ensemble(int size) { this.numberOfMusicians = size; }
    public int numberOfMusicians() { return numberOfMusicians; }
}
```

### 정리
- ordinal은 거의 사용할 일 없다.
