##### [GO TO LIST](../README.md)

# 아이템34. int 상수 대신 열거 타입을 사용하라

### int 상수의 단점
```java
public static final int APPLE_FUJI = 0;
public static final int APPLE_PIPPIN = 0;
public static final int APPLE_GRANNY_SMITH = 2;

public static final int ORANGE_NAVEL = 0;
public static final int ORANGE_TEMPLE = 1;
public static final int ORANGE_BLOOD = 2;
```
- APPLE_FUJI와 ORANGE_NAVEL을 비교하면 같다.

### 자바의 열거 타입
```java
public enum Apple { FUJI, PIPPIN, GRANNY_SMITH }
public enum Orange { NAVEL, TEMPLE, BLOOD }
```
- 컴파일 타입 안전성을 제공한다. 

### 열거타입 활용
```java
public enum Planet {
    MERCURY(3.302e+23, 2.439e6),
    VENUS(4.896e+24, 6.052e6);

    private final double mass;
    private final double radius;
    private final double surfaceGravity;
    
    private static final double G = 6.67400E-11;

    Planet(double mass, double radius) {
        this.mass = mass;
        this.radius = radius;
        surfaceGravity = G * mass / (radius * radius);
    }
    
    public double mass() { return mass; }
    public double radius() { return radius; }
    public double surfaceGravity() { return surfaceGravity; }

    public double surfaceWeight(double mass) {
        return mass * surfaceGravity;
    }
}
```
```java
public class WeightTable {
    public static void main(String[] args) {
        double earthWeight = Double.parseDouble(args[0]);
        double mass = earthWeight / Planet.EARTH.surfaceGravity();
        for(Planet p : Planet.values())
            System.out.println("%s에서의 무게는 %f이다. %n", p, p.surfaceWeight(mass));
    }
}
```

### 값에 따라 분기하는 열거타입
```java
public enum Operation {
    PLUS, MINUS, TIMES, DIVIDE;
    
    public double apply(double x, double y) {
        switch(this) {
        	case PLUS: return x + y;
            case MINUS: return x - y;
            case TIMES: return x * y;
            case DIVIDE: return x / y;
        }
        throw new AssertionError("알 수 없는 연산: " + this);
    }
}
```

### 좀더 좋은코드는?
- 상수별로 메서드를 구현한다.
```java
public enum Operation {
    PLUS { public double apply(double x, double y) { return x + y; }},
    MINUS { public double apply(double x, double y) { return x - y; }},
    TIMES { public double apply(double x, double y) { return x * y; }},
    DIVIDE { public double apply(double x, double y) { return x / y; }};

    public abstract double apply(double x, double y);
}
```
- 데이터
```java
public enum Operation {
    PLUS("+") { public double apply(double x, double y) { return x + y; }},
    MINUS("-") { public double apply(double x, double y) { return x - y; }},
    TIMES("*") { public double apply(double x, double y) { return x * y; }},
    DIVIDE("/") { public double apply(double x, double y) { return x / y; }};

    private final String symbol;
   
    Operation(String symbol) { this.symbol = symbol; }

    @Override public String toString() { return symbol; }
    public abstract double apply(double x, double y);
}
```
```java
private static final Map<String, Operation> stringToEnum = 
    Stream.of(values()).collect(
        toMap(Object::toString, e -> e));
```

### 전략 열거 타입 패턴
- 값에 따라 분기하여 코드를 공유하는 열거타입은 좋지 않다.
```java
enum PayrollDay {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY;

    private static final int MINS_PER_SHIFT = 8 * 60;
    
    int pay(int minutesWorked, int payRate) {
        int basePay = minutesWorked * payRate;        

        int overtimePay;
        switch (this) {
        	case SATURDAY: case SUNDAY:
                overtimePay = basePay / 2;
                break;
            default:
                overtimePay = minutesWorked <= MINS_PER_SHIFT ? 
                    0 : (minutesWorked - MINS_PER_SHIFT) * payRate / 2;
        }
    
        return basePay + overtimePay;
    }
}
```
- 전략 열거 타입 패턴
```java
enum PayrollDay {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY(PayType.WEEKEND), SUNDAY(PayType.WEEKEND);

    private final payType payType;
   
    PayrollDay(PayType payType) { this.payType = payType; }

    int pay(int minutesWorked, int payRate) {
        return payType.pay(minutesWorked, payRate);
    }

    enum PayType {
        WEEKDAY {
            int overtimePay(int minsWorked, int payRate) {
                return minsWorked <= MIN_PER_SHIFT ? 0 : 
                    (minsWorked - MINS_PER_SHIFT) * payRate / 2;
            }
        },
        WEEKEND {
            int overtimePay(int minsWorked, int payRate) {
                return minsWorked * payRate / 2;
            }
        }
    
        abstract int overtimePay(int mins, int payRate);
        private static final int MINS_PER_SHIFT = 8 * 60;

        int pay(int minsWorked, int payRate) {
            int basePay = minsWorked * payRate;
   
            return basePay + overtimePay(minsWorked, payRate);
        }
    }
}
```

### 열거타임은 언제 사용하나?
- 필요한 원소를 컴파일타임에 다 알 수 있는 상수 집합 이면 사용하자
- 열거 타입에 정의된 상수의 수가 고정 불변일 필요는 없다.


