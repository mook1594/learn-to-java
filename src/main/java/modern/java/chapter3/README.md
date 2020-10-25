# Chapter3. 람다 표현식

### 람다란 ?
- 람다 표현식은 메서드 파라미터를 단순화 한것

### 람다의 특징
- 익명: 메서드에 이름이 없음
- 함수: 메서드처럼 파라미터, 바디, 반환, 예외를 포함
- 전달: 람다 표현식으로 파라미터를 전달
- 간결성: 간단한 표현

### 람다는 코드를 간결하게 한다
- 기존 코드
```java
Comparator<Apple> byWeight = new Comparator<Apple>() {
    public int compare(Apple a1, Apple a2){
        return a1.getWeight().compareTo(a2.getWeight());
    }
};
```
- 람다를 이용
```java
Comparator<Apple> byWeight = 
    (Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight());
```

### 람다의 구성

람다 파라미터 -(화살표)> 람다 바디

- 파라미터  
(Apple a1, Apple a2)
- 화살표  
 -> 
- 바디  
a1.getWeight().compareTo(a2.getWeight());

### 람다 표현식
- boolean 표현식
```java
(List<String> list) -> list.isEmpty()
```
- 객체 생성
```java
() -> new Apple(10)
```
- void 표현식
```java
(Apple a) -> {
    System.out.println(a.getWeight());
}
```
- 객체에서 선택/추출
```java
(String s) -> s.length()
```
- 두 값을 조합
```java
(int a, int b) -> a * b
```
- 두 객체 비교
```java
(Apple a1, Apple a2) ->
    a1.getWeight().compareTo(a2.getWeigth())
```

### 람다의 사용
- 함수형 인터페이스  
: 오직 하나의 추상 메서드를 갖는 인터페이스  
: @FuncionalInterface
```java
public interface Predicate<T>{
    boolean test (T t);
}
public interfacce Comparator<T> {
    int compare(T o1, T o2);
}
public interface Runnable{
    void run();
}
public interface Callable<V> {
    V call throws Exception;
}
public interface PrivilegedAction<T> {
    T run();
}
```

### 람다의 활용
- 실행 어라운드 패턴
: try-with-resources - 자원을 열고, 처리 후 명시적으로 닫을 필요 없음.  
: 순환패턴과 대비되는 패턴 - 자원열고, 처리 후, 자원 닫음
-  [코드](./ex1/Ex1.java)  

### 함수형 인터페이스 사용
- Predicate  
: boolean 반환하는 함수형 인터페이스  
: [코드](./ex2/PredicateEx.java)
- Consumer  
: void를 반환하는 함수형 인터페이스
: [코드](./ex2/ConsumerEx.java)
- Function  
: return 객체를 반환하는 함수형 인터페이스
: [코드](./ex2/FunctionEx.java)

> 박싱: 기본형 -> 참조형  
> 언박싱: 참조형 -> 기본형  
>