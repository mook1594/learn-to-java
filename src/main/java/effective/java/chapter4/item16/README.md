##### [GO TO LIST](../README.md)

# 아이템 16. public 클래스에서는 public 필드가 아닌 접근자 메서드를 사용하라.

### 이런거 안됨...
```java
class Point {
    public double x;
    public double y;
}
```
- 캡슐화 이점을 제공하지 않음.
- 불변을 보장 하지 않음.
- 외부에서 필드 접근시 부수 작업을 할 수 없다.

### 그래서 캡슐화를 한다.
```java
class Point {
    private double x;
    private double y;
    
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }   
   
    public double getX() { return x; }
    public double getY() { return y; }
    
    public void setX(double x) { this.x = x; }
    public void setY(double y) { this.y = y; }
}
```
- 필드를 private으로 변경
- setter와 getter를 추가

### 이렇게 하면 뭐가 좋지?
- 외부에서 접근할때 접근자(getter)를 제공하므로써 내부 표현 방식을 얼마든지 변경할 수 있다.

### 그렇담 불변 필드를 노출했을때
```java
public final class Time {
    private static final int HOURS_PER_DAY = 24;
    private static final int MINUTES_PER_HOUR = 60;
    
    public final int hour;
    public final int minute;
    
    public Time(int hour, int minute) {
        if(hour < 0 || hour >= HOURS_PER_DAY)
            throw new IllegalArgumentException("시간: " + hour)
        if(minute < 0 || minute >= MINUTES_PER_HOUR)
            throw new IllegalArgumentException("분: " + minute)
    
        this.hour = hour;
        this.minute = minute;
    }
    
    ... // 나머지 코드 생략
```

### 핵심 정리
- public 클래스는 절대 가변을 노출 하면 안된다.
- 불변 필드는 노출해도 괜찮지만 안심은 금물
