##### [GO TO LIST](../README.md)

# 아이템42. 익명 클래스 보다는 람다를 사용하라
 
### 함수 객체
- 함수 타입을 표현할 때 추상 메서드를 하나만 담은 인터페이스 생성.
- 특정 함수나 동작을 나타내는데 사용
``` java
Collection.sort(words, new Comparator<String>() {
    public int compare(String s1, String s2) {
        return Integer.compare(s1.length(), s2.length());
    }
});
``` 
- 과거에는 익명 클래스 방식을 사용했다.
- 하지만 이 익명 클래스 방식은 코드가 너무 길어 함수형 프로그래밍에 저합하지 않다.

### 함수형 인터페이스
- 자바 8에 추상 메서드 하나 짜리 인터페이스는 특별한 대우를 받는다.  
- 람다식을 사용해 함수형 프로그래밍에 활용할 수 있게됨.
```java
Collection.sort(words,
    (s1, s2) -> Integer.compare(s1.length(), s2.length()));
``` 
- 컴파일러가 문맥을 살펴 타입을 추론.
- 상황에 따라 컴퍼아릴러가 타입을 정하지 못하면 직접 명시.
- 생성메서드를 사용하면 더 간결하게 할 수 있다.
```java
Collections.sort(words, comparingInt(String::length));
```
- java8에 추가된 List에 sort를 사용하면
```java
words.sort(comparingInt(String::length));
```

### 메서드 동작이 상수별로 다를때
```java
public enum Operation {
    PUSH("+") {
        public double apply(double x, double y) { return x + y; }
    }, 
    MINUS("-") {
        public double apply(double x, double y) { return x - y; }  
    },
    TIMES("*") {
        public double apply(double x, double y) { return x * y; }
    },
    TIMES("/") {
        public double apply(double x, double y) { return x / y; }
    };

    private final String symbol;

    Operation(String symbol) { this.symbol = symbol; }

    @Override public String toString() { return symbol; }
    public abstract double apply(double x, double y);
}
```

- 함수 객체를 적용하면 더 간결해진다.
```java
public enum Operation {
    PLUS ("+", (x, y) -> x + y),
    MINUS ("-", (x, y) -> x - y),
    TIMES ("*", (x, y) -> x * y),
    DIVIDE ("/", (x, y) -> x / y);

    private final String symbol;
    private final DoubleBinaryOperator op;
   
    Operation(String symbol, DoubleBinaryOperator op) {
        this.symbol = symbol;
        this.op = op;
    }

    @Override public String toString() { return symbol; }

    public double apply(double x, double y) {
        return op.applyAsDouble(x, y);
    }
}
```

### 언제 람다를 사용해야하나
- 람다는 이름도 없고 문서화도 못한다.
- 코드 자체가 명확히 설명되지 않거나, 줄수가 많아지면 람다를 사용하지 말아야한다.
- 세줄이 넘어가면 가독성이 심히 떨어진다.
- 열거타입도 컴파일때 타입 추론되이므로, 열거타입 생성자 안에 람다는 열거 타입의 인스턴스 멤버에 접근 할 수 없다.
- 람다는 함수형 인터페이스에서만 사용됨

### 익명 클래스는 언제 사용하
- 추상 클래스의 인스턴스를 만들때
- 추상 메서드가 여러개인 인터페이스의 인스턴스를 만들때
- 람다는 자기자신(this)를 가리킬 수 없으므로 익명 클래스를 사용한다.


