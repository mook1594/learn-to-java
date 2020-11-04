#####[GO TO LIST](../README.md)

# 아이템12. toString을 항상 재정의하라

### toString의 역할
- Object에 대한 출력을 정의해 줄 수 있다.
- 재정의 하지 않은 toString은 클래스이름@16진수_해시코드 값이 출력된다.
- toString을 잘 구현하면 디버깅이 쉬워진다.
- toString은 그 객체가 가진 정보를 모두 반환하는게 좋다.
- 포맷을 명시하여 조금더 명확하게 사용.
```java
@Override public String toString(){
    return String.format("%03d-%03d-%04d", areaCode, prefix, lineNum);
}
```

### 그래서?
- toString를 올바르게 재정의 하면 시스템 디버깅이 쉬워진다.
