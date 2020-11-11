##### [GO TO LIST](../README.md)
 
 # 아이템22. 인터페이스는 타입을 정의하는 용도로만 사용하라
 
 ### 상수 인터페이스 안티패턴
 ```java
public interface PhysicalConstants {

    // 아보가드로 수 (1/몰)
    static final double AVOGADROS_NUMBER = 6.022_140_857e23;

    // 볼츠만 상수 (J/K)
    static final double BOLTZMANN_CONSTANT = 1.380_648_52e-21;

    // 전자 질량 (kg)
    static final double ELECTRON_MASS = 9.109_383_56e-31;

}
```
- 내부 구현 클래스를 외부로 노출하는 행위.
- 클래이언트 코드가 내부구현 코드에 종속되게 된다.

### 그럼 상수를 어떻게 구성하지?

```java
public class PhysicalConstants {
    private PhysicalConstants() { }
    
    // 아보가드로 수 (1/몰)
    public static final double AVOGADROS_NUMBER = 6.022_140_857e23;

    // 볼츠만 상수 (J/K)
    public static final double BOLTZMANN_CONSTANT = 1.380_648_52e-21;

    // 전자 질량 (kg)
    public static final double ELECTRON_MASS = 9.109_383_56e-31;

}
```

### 인터페이스는 타입을 정의하는 용도로만 쓰고 상수 공개용 수단으로 사용하지는 말자
