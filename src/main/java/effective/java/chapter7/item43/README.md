##### [GO TO LIST](../README.md)

# 아이템43. 람다보다는 메서드 참조를 사용하라

### map merge 메서드의 사용
```java
map.merge(key, 1, (count, incr) -> count + incr);
```
- Integer.sum을 이용해서 더 간단히 표현
```java
map.merge(key, 1, Integer::sum);
```

### 인스턴스 메서드를 참조
- 수신 객체 (참조 대상 인스턴스)를 특정하는 한정적 인스턴스 메서드 참조
- 수신 객체를 특정하지 않는 비한정적 인스턴스 메서드 참조

### 메서드 참조 유형
- 정적
```java
Integer::parseInt
str -> Integer.parseInt(str)
```

- 한정적 인스턴스
```java
Instant.now()::isAfter
Instant then = Instant.now();
t -> then.isAfter(t);
```

- 비한정적 인스턴스
```java
String::toLowerCase
str -> str.toLowerCase()
```

- 클래스 생성자
```java
TreeMap<K, V>::new
() -> new TreeMap<K, V>()
```

- 배열 생성자
```java
int[]::new
len -> new int[len]
```





 
 
