##### [GO TO LIST](../README.md)

# 아이템24. 멤버 클래스는 되도록 static으로 만들라

### 중첩클래스
- 다른 클래스 안에 정의된 클래스를 의미 (nested class)
- 종류: 정적 멤버 클래스, 멤버 클래스, 익명 클래스, 지역 클래스
 
### 정적 클래스 멤버
- static이 붙었지만 의미상 차이는 크다.
- 바깥 인스턴스에 독릭적이 된다.

### 비정적 클래스 멤버
- 어댑터를 정의할때 자주 사용된다.
- 자신의 반복자 구
```java
public class MySet<E> extends AbstractSet<E> {
    ... // 생략
    
    @Override public Iterator<E> iterator() {
        return new MyIterator();
    }
    
    private class MyIterator implements Iterator<E> {
        ...
    }
}
``` 
- 참조가 눈에 보이지 않아 GC에서 메모리 누수가 발생할 수 있다.

### 결론
- 중첩클래스는 네가지가 있으며 쓰임새가 다 다른다.
- 바깥 인스턴스를 참조한다면 비정적, 그렇지 않으면 정적으로 만든다.
- 한 메서드 안에서만 쓰이고 인스턴스를 생성하는 지점이 한곳이고, 해당 타입으로 적합한 클래스나 인터페이스가 있다면 익명 클래스.
- 그렇지 않으면 지역 클래스로 사용한다.
