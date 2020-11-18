##### [GO TO LIST](../README.md)

# 아이템30. 이왕이면 제네릭 메서드로 만들라

### 제너릭 메서드
```java
public static <E> set <E> union(Set<E> s1, Set<E> s2) {
    Set<E> result = new HashSet<>(s1);
    result.addAll(s2);
    return result;
}
```

### 제네릭 싱글턴 팩터리 패턴
```java
private static UnaryOperator<Object> IDENTITY_FN = (t) -> t;

@SuppressWarnings("unchecked")
public static <T> UnaryOperator<T> identityFunction() {
    return (UnaryOperator<T>) IDENTITY_FN;
}
```
```java
public static void main(String[] args) {
    String[] strings = {"삼베", "대마", "나일론"};
    UnaryOperator<String> sameString = identityFunction();
    for (String s : strings)
        System.out.println(sameString.apply(s));

    Number[] numbers = { 1, 2.0, 3L };
    UnaryOperator<Number> sameNumber = identityFunction();
    for(Number n : numbers)
        System.out.println(sameNumber.apply(n));
} 
```

### 재귀적 타입 한정
```java
public static <E extends Comparable<E>> E max(Collection<E> c) {
    if (c.isEmpty())
        throw new IllegalArgumentException("컬렉션이 비어 있습니다.");
    
    E result = null;
    for (E e : c)
        if (result == null || e.compareTo(result) > 0)
            result = Objects.requireNonNull(e);
    return result;
}
```
