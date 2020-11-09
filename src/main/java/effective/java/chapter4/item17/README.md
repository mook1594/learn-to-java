##### [GO TO LIST](../README.md)

# 아이템 17. 변경 가능성을 최소화하라

### 불변 클래스를 왜?
- 가변 클래스보다 설계와 구현에 사용하기 쉽다.
- 오류가 생길 여지가 적고 안전

### 불변을 만드려면?
- 객체의 상태를 변경하는 메서드를 제공하지 않는다.
- 모든 필드를 final로 선언한다.
- 모든 필드를 private로 선언한다.
- 자신 외에는 내부 가변 컴포넌트에 접근할 수 없도록 한다.

### 불변 클래스 예제
```java
public final class Complex {
    private final double re;
    private final double im;
    
    public Complex(double re, double im) {
        this.re = re;
        this.im = im;
    }
    public double realPart() { return re; }
    public double imaginaryPart() { return im; }
    
    public Complex plus(Complex c) {
        return new Complex(re + cc.re, im + c.im);
    }
    
    public Complex minus(Complex c) {
        return new Complex(re - c.re, im - c.im);
    }
    
    public Complex times(Complex c) {
        return new Complex(re * c.re - im * c.im,
                           re * c.im + im * c.re);
    }
    
    public Complex divideBy(Complex c) {
        double temp = c.re * c.re + c.im * c.im;
        return new Complex((re * c.re + im * c.im) / temp,
                           (im * c.re - re * c.im) / temp);
    }
   
    @Override public boolean equals(Object o) {
        if(o == this)
            return true;
        if(!(o instanceof Complex))
            return false;
        Complex c = (Complex) o;
        
        return Double.compare(c.re, re) == 0
                && Double.compare(c.im, im) == 0;
    }

    @Override public int hashCode() {
        return 31 * Double.hashCode(re) + Double.hashCode(im);
    }
    
    @Override public String toString() {
        return "(" + re + " + " + im + "i)";
    }
}
```

### 코드를 살펴보자
- 사칙연산 메서드 살펴보기
- Complex에 주목해 보기
- 함수형, 불변객체에 초점 두고 보기

### 불변객체의 장점
- 불변객체는 스레드에 안전, 안심하고 공유

### 그럼 불변객체의 단점은?
- 값이 다르면 반드시 독립 객체로 만들어야 한다.
- 성능 문제를 야기 시킬 수 있다.


### 그래서
- 꼭 필요가 아닌경우면 불변이어야한다.
- 변경할 수 있는 부분을 최소한으로 줄여서 불변으로 만들자
- 거의 모든 필드는 private final 이어야 한다.
