##### [GO TO LIST](../README.md)

# 아이템 26. 로 타입은 사용하지 말라

### 제네릭 타입
- List<E> 에서 원소를 나타내는 E
- 클래스와 인터페이스 선언에 매개변수가 사용되면 제네릭 클래스, 제네릭 인터페이스

### 로 타입 (Raw type)
- 제네릭 타입에서 타입 매개변수를 전혀 사용하지 않을때
- ex) List<E>의 로타입은 List

### 로 타입의 문제점
- 의도하지 않은 원소가 add 될 수 있다. 이걸을 꺼내기전에는 알지 못함.
- 꺼낼때 casting 하면서 에러 발생
- 런타임시 에러보다는 컴파일시에 오류를 감지하는것이 좋음
- 제네릭을 사용하면 위 단점 해결

### 제네릭의 장점
- 안전성
- 표현력

### 원소 타입을 모를때?
- 원소의 타입을 모를때는 로타입을 사용하지말고 와일드 카드를 사용한다.
- 비한정적 와일드카드 타입(unbounded wildcard type): 보통 ?로 사용한다. Set<?>

### 예외! 로 타입을 써야하는 경우
- class 리터럴에는 로 타입을 사용해야한다. (int, double ...)
- 로타입과 제너릭일때 instanceof는 똑같이 동
```java
    if(o instanceof Set) { //로 타입
        Set<?> s = (Set<?>) p; // 와일드 카드 타입
    }
```
- o의 타입이 Set임을 확인한 다음 와일드 카드 타입인 Set<?>로 형 변환해야 한다. (로 타입인 Set이 아니라...)
- 검사 형변환이므로 컴파이러 경고가 뜨지 않음.

### 용어정리
- 매개변수화 타입(parameterized type): List<String>
- 실제 타입 매개 변수(actual type parameter): String
- 제네릭 타입(generic type): List<E>
- 정규 타입 매개변수(formal type parameter): E
- 비한정적 와일드카드 타입(unbounded wildcard type): List<?>
- 로 타입(raw type): List
- 한정적 타입 매개변수(bounded type parameter): <E extends Number>
- 재귀적 타입 한정(recursive type bound): <T extends Comparable<T>>
- 한정적 와일드 카드 타입(bounded wildcard type): List<? extends Number>
- 제네릭 메서드(generic method): static <E> list<E> asList(E[] a)
- 타입 토큰(type token): String.class 
