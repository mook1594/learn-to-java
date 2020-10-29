#####[GO TO LIST](../README.md)

# 아이템10. equals는 일반 규약을 지켜 재정의하라

### eqauls를 재정의? 재정의 하지 않는 조건
- 각 인스턴스가 본질적으로 고유하다.  
(값을 표현하는 것이 아닌 동작하는 개체를 표현하는 클래스)
- 인스턴스의 logical equality를 검사할 일이 없다.
- 상위 클래스에서 재정의한 equals가 하위 클래스에도 딱 들어 맞는다.
- 클래스가 private 숨겨져 있고, equals를 호출 할 일이 없을때.

```java
@Override
public boolean equals(Object o) {
    throw new AssertionError(); // 호출 금지
}
```

### equals를 재정의 해야할 때는 언제인가?
- 두 객체 간에 같은 값인지 알고 싶은때.

### equals를 재정의할 때 반드시 따라야할 규약
- equals는 수학에서 = 개념과 유사. 모든 참조 값은 null이 아님을 조건으로함.
- 반사성: a = a
- 대칭성: a = b 이면 b = a
- 추이성: a = b, b = c 이면 a = c
- 일관성: a = b를 반복해서 확인해도 항상 true
- null-아님: a = null 은 false 다.

### equals를 구현하는 방법
1. == 연산자를 사용해 자기 자신의 참조인지 확인
2. instanceof 연산자로 입력이 올바른 타입인지 확인
3. 입력이 올바른 타입으로 형변환
4. 비교하고자 하는 필드들이 모두 일치하는지 확인
```java
@Override
public boolean equals(Object o) {
    // 1.
    if (o == this)
        return true;
    // 2.
    if (!(o instanceof Apple))
        return false;
    // 3.
    Apple a = (Apple) o;
    // 4.
    return a.getColor == color && a.weight == weight && a.size == size;
}
``` 

### equals를 구현했다면 3가지만 테스트 해보자
- 대칭성, 추이성, 일관성
- equals를 재정의 할 땐 hashCode도 반드시 재정의 하자 ([아이템11](../item11/README.md))

### 그래서요?
- equals는 꼭 필요한 경우가 아니라면 재정의 하지 말자. 기본 Object의 equals가 비교를 정확히 해줄것이다.
- 그치만 재정의 해야할때는 5가지 규약을 확실히 지켜야한다.
- 마지막으로 3가지를 테스트하고 hashCode도 반드시 재정의 하자.
