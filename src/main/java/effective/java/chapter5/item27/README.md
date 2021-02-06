##### [GO TO LIST](../README.md)

# 아이템 27. 비검사 경고를 제거하라

### 경고를 제거하자.
- 제너릭을 사용하면 경고를 보게된다.
- 할수 있는 모든 비검사 경고를 제거하자.

### 경고를 제거할 수 없다면?
- 안전하다고 확신할 수 있다면 @SuppressWarnings("unchecked") 를 달아 경고를 제거하자.
- 가능한한 좁은 범위에서 annotation을 달자
- annotaion을 추가하면서 그에대한 주석도 달자.

```java
public <T> T[] toArray(T[] a) {
    if (a.length < size) {
        // 생성한 배열 매개변수로 받은 배열의 타입이 모두 T[]로 같으므로
        // 올바른 형 변환이다.
        @SuppressWarnings("unchecked")
        (T[]) result = (T[]) Arrays.copyOf(elements, size, a.getClass());
        return result;
    }
    System.arraycopy(elements, 0, a, 0, size);
    if(a.length > size) 
        a[size] = null;
    return a;
}
```
