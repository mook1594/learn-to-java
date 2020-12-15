##### [GO TO LIST](../README.md)

# 아이템44. 표준 함수형 인터페이스를 사용하라

### 
- 템플릿 메서드 패턴의 매력 감소
- 함수 객체를 받는 정적 팩터리 나 생성자를 제공


- 필요한 용도에 맞는 게 있다면, 직접 구현하지 말고 표준 함수형 인터페이스를 활용하라.

- 기본 함수형 인터페이스에 박싱된 기본 타입을 넣어 사용하지는 말라

- 직접 만든 함수형 인터페이스에는 항상 @FunctionalInterface 애너테이션을 사용하라.

## 함수형 인터페이스 종류
- UnaryOperator<T> : T apply(T t) : String::toLowerCase
- BinaryOperator<T> : T apply(T t1, T t2) : BigInteger::add
- Predicate<T> : boolean test(T t) : Collection::isEmpty
- Function<T, R> : R apply(T t) : Arrays::asList
- Supplier<T> : T get() : Instant::now
- Consumer<T> : void accpet(T t) : System.out.println
