#####[GO TO LIST](../README.md)

# 아이템11. equals를 재정의 할때 hashCode도 재정의 하라

### equals를 재정의 했다면 hashCode도 재정의 해야한다.
- equals 비교에 사용되는 정보가 변경되지 않았다면 hashCode 도 항상 같은값을 반환해야한다.
- equals(Object)가 같다고 판단되면, hashCode는 똑같은 값을 반환해야한다.
- equals(Object)가 다른다고 판단될때, 두 객체의 hashCode가 서로 다른 값을 반환할 필요는 없지만 다른 객체에 대해서는 다른값을 반환해야 해시테이블 성능이 좋아진다.

### 해시테이블
- 여러 객체의 해시 코드값이 같으면 해시테이블에 버킷하나에 모두 담겨 연결리스트(LinkedList)처럼 동작한다.
- 해시 코드 값이 똑같으면 O(n)으로 느려진다. 해시코드값을 각각 갖으면 O(1)

### HashCode를 생성하는 요령
##### 기본
```java
@Override public int hashCode() {
    int result = Short.hashCode(areaCode);
    result = 31 * result + Short.hashCode(prefix);
    result = 31 * result + Short.hashCode(lineNum);
    return result;
}
```
- 31을 곱하는 이유는 31이 홀수,소수 이기에 해시코드를 다르게 할 수 있다.
- 만약 이 숫자가 짝수이면 shift연산과 같은 결과를 내기때문에 안좋다.
- 31을 이용하여 시프트연산과 뺄셈으로 대체해 최적화 할 수 있다.
- 31 * i == (i << 5) - i

##### 간단한 hashCode 
```java
@Override public int hashCode() {
    return Objects.hash(lineNum, prefix, areaCode);
}
```
- 코드는 짧지만 속도는 더 느리다.
- 입력 인수 처리를 위한 배열, 박싱, 언박싱에 대한 처리도 있기때문.

##### 지연 초기화 hashCode
```java
@Override public int hashCode() {
    int result = hashCode;
    if(result == 0) {
        result = Short.hashCode(areaCode);
        result = 31 * result + Short.hashCode(prefix);
        result = 31 * result + Short.hashCode(lineNum);
        hashCode = result;
    }
    return result;
}
```

### 그래서 결론은?
- equals를 재정의 할 때는 올바른 프로그램 동작을 원하면 hashCode도 반드시 재정의 해야한다.
- 재정의한 hashCode는 일반 규약을 따르며 서로다른 인스턴스라면 해시코드로 다르게 구현해야한다.
 
