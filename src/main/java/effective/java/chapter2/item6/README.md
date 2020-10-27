#####[GO TO LIST](../README.md)

# 아이템6. 불필요한 객체 생성을 피하라

### 불변객체는 언제든 재사용 할 수 있다. (아이템 17)

- 하지말아야할 것
```java
String s = new String("bikini");
```
- 개선
```java
String s = "bikini";
```

### 생성 비용 줄이기
- 기존코드
```java
return s.matches("^(?=.)M*(C[MD]|D?C{0,3}"
               + "(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");
```
- 개선
```java
import java.util.regex.Pattern;public class RomanNumerals{
    private static final Pattern ROMAN = Pattern.compile("^(?=.)M*(C[MD]|D?C{0,3}"
                    + "(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");
    static boolean isRomanNumeral(String s){
        return ROMAN.matcher(s).matches();
    }
}
```

### 연산에 달으가는 오토박싱에 주의
```java
private static long sum() {
    Long sum = 0L; // long으로 변경하면 비용이 줄어듬
    for(long i = 0; i <= Integer.MAX_VALUE; i++){
        sum += i;
    }
    return sum;
}
```
