##### [GO TO LIST](../README.md)

# 아이템38. 확장할 수 있는 열거 타입이 필요하면 인터페이스를 사용하라

### 열거 타입 확장
```java
public interface Operation {
    double apply(double x, double y);
}
public enum BasicOperation implements Operation {
    PLUS("+") {
        public double apply(double x, double y) { return x + y; }
    },
    MINUS("-") {
        public double apply(double x, double y) { return x + y; }
    },
    TIMES("*") {
        public double apply(double x, double y) { return x * y; }
    },
    DIVIDE("/") {
        public double apply(double x, double y) { return x / y; }
    };

    private final String symbol;
    
    BasicOperation(String symbol) {
        this.symbol = symbol;
    }

    @Override public String toString() {
        return symbol;
    }
}
```

### 확장가능한 열거 타입
```java
public enum ExtendedOperation implements Operation {
    EXP("^") {
        public double apply(double x, double y) {
            return Math.pow(x, y);
        }
    },
    REMAINDER("%") {
        public double apply(double x, double y) {
            return x % y;
        }
    };

    private final String symbol;

    ExtendedOperation(String symbol) {
        this.symbol = symbol;
    }

    @Override public String toString() {
        return symbol;
    }

}
```

### 열거타입 사용
```java
public static void main(String[] args) {
    double x = Double.parseDouble(args[0]);
    double y = Double.parseDouble(args[1]);
    test(ExtendedOperation.class, x, y);
}
private static <T extends Enum<T> & Operation> test(Class<T> opEnumType, double x, double y) {
    for(Operation op : opEnumType.getEnumConstants())
        System.out.printf("%f %s %f = %f%n", x, op, y, op.apply(x, y));
}
```
```java
public static void main(String[] args) {
    double x = Double.parseDouble(args[0]);
    double y = Double.parseDouble(args[1]);
    test(Arrays.asList(ExtendedOperation.values()), x, y);
}
private static test(Collection<? extends  Operation> opSet, double x, double y) {
    for(Operation op : opEnumType.getEnumConstants())
        System.out.printf("%f %s %f = %f%n", x, op, y, op.apply(x, y));
}
```

### 정리
- 열거타입은 확장할 수 없지만 인터페이스를 사용해 같은 효과를 낼 수 있다.
