#####[GO TO LIST](../README.md)

# 아이템13. Comparable을 구현할지 고려하라

### Comparable 인터페이스
- compareTo 메서드가 존재

### compareTo의 일반 규약
- x.compareTo(y) == -y.compareTo(x)
- x.compareTo(y) > 0 && y.compareTo(z) > 0 이면, x.compareTo(z) > 0 이다.
- x.compareTo(y) == 0 이면 abs(x.compareTo(z)) == abs(y.compareTo(z)) 이다
- x.compareTo(y) == 0 == x.equals(y) 여야 한다.

### 비교할 타입이 여러개 일때
```java
public int compareTo(PhoneNumber pn) {
    int result = Short.compare(areaCode, pn.areaCode);
    if (result == 0) {
        result = Short.compare(prefix, pn.prefix);
        if(result == 0)
            result = Short.compare(lineNum, pn.lineNum); 
    }
    return result;
}
```

### 비교자 생성 메서드를 활용한 비교자
```java
public static final Comparator<PhoneNumber> COMPARATOR = 
    comparingInt(PhoneNumber pn) -> pn.areaCode)
        .thenComparingInt(pn -> pn.prefix)
        .thenComparingInt(pn -> pn.lineNum);

public int compareTo(PhoneNumber pn) {
    return COMPARATOR.compare(this, pn);
}
```

### 정적 compare 메서드를 활용한 비교자
```java
static Comparator<Object> hashCodeOrder = new Comparator<>() {
    public int compare(Object o1, Object o2) {
        return Integer.compare(o1.hashCode(), o2.hashCode());
    }
}
```

### 비교자 생성 메서드
```java
Comparator<Object> hashCodeOrder = 
    Comparator.comparingInt(o -> o.hashCode());
```

### 결론
- Comparable 인터페이스를 구현하여 정렬하고, 비교한다.
- compareTo 메서드에서 필드값을 비교할때 <, > 연산자는 되도록 쓰지 않는다.
- 박신된 기본타입 클래스가 제공하는 정적 compare메서드나 Comparator인터페이스가 제공하는 비교자 생성 메서드를 활용한다.