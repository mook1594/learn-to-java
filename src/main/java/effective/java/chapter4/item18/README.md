#####[GO TO LIST](../README.md)

# 아이템18. 상속보다는 컴포지션을 사용하라

### 상속은 캡슐화를 깨트린다.
- 상위클래스에 따라 하위클래스가 영향을 받는다
- 잘못된 설계는 상위클래스 변경에 따라 하위클래스도 같이 변경을 초래한다.

### 상속을 잘못 사용한 예
- 원소의 수를 알 수 있는 HashSet 확장
##### [InstrumentedHashSet](./InstrumentedHashSet.java)
- 이상 없는 코드처럼 보인다.
- 만약 addAll 메서드로 원소 3개를 더한다고 하자
```java
InstrumentedHashSet<String> s = new InstrumentedHashSet<>();
s.addAll(List.of("틱", "틱틱", "펑"));
```
- 이제 getAddCount 메서드를 호출하면 3이라 기대를 하겠지만 6이 반환된다.
- 왜? HashSet의 addAll 메서드는 add 메서드를 사용해 동작하는 코드로 구현되어있다.  
그래서 add의 count 증가와 addAll의 카운트 증가가 중복으로 더해져 6이 된다.
- 이런 경우 하위 클래스에서 addAll을 재정의 하지 않으면 문제가 해결되지만 가정 해법이라는 한계점.


### 상속을 하므로써 발생 할 수 있는 문제
- 상위 클래스가 변경이 일어났는데, 하위 클래스 메서드와 시그니처가 같고 반환타입이 다르면 컴파일 오류

### 이러한 문제를 해결할 수 있을까?
- 기존 클래스의 인스턴스를 참조하게 한다.
- 상속대진 컴포지션을 사용한다.
##### [InstrumentedHashSet](./InstrumentedHashSet.java)

- 전달 클래스
##### [ForwardingSet](./ForwardingSet.java)

### 결론
- 상속은 강력하지만 캡슐화가 깨진다는 단점이 있다.
- 상위클래스가 확장에 고려되지 않고 설계되었다면 상속은 위험.
- 상속대신 컴포지션과 전달을 사용하여 문제를 해결, 래퍼클래스로 구현할 적당한 인터페이스가 있다면 더욱 강력하다.
