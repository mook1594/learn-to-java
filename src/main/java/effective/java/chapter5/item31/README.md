##### [GO TO LIST](../README.md)

# 아이템31. 한정적 와일드카드를 사용해 API 유연성을 높이라

###  한정적 와일드 카드
- 매가변수화 타입이 생산자라면 
```java
Iterable<? extends E>
```
- 매개변수화 타입이 소비자라면 
```java
Collection<? super E>
```

### 정리
- 와일드 카드 타입을 적용하면 API가 훨씬 유연해진다.
- PECS 공식 => Producer Extends, Consumer super
- Comparable, Comparator 모두 
