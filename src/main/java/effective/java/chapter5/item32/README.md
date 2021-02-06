##### [GO TO LIST](../README.md)

# 아이템32. 제네릭과 가변인수를 함께 쓸 때는 신중하라

### 제네릭과 가변인수
- 친하지 않은 사이
- 가변인수는 메서드 넘기는 인수 개수를 클라이언트가 결정
- 가변인수는 배열이 만들어지는데 여기에 제네릭을 넘기면 컴파일 경고발생 (varargs)

### 제네릭과 varargs
- 타입 안전성이 깨짐
```java
static void dangerous(List<String>... stringLists) {
    List<Integer> intList = List.of(42);
    Object[] objects = stringLists;
    objects[0] = intList;   // 힙 오염 발생 
    String s = stringLists[0].get(0);   // ClassCastException
}
```

### 제네릭 가변인수 메서드
```java
Arrays.asList(T... a)
Collections.addAll(Collection<? super T> c, T... elements)
EnumSet.of(E first, E... rest)
```
- 경고를 @SuppressWarnings("unchecked") 로 경고 숨김

### @SafeVarargs
- 메서드 작성자가 그 메서드가 타입 안전함을 보장하는 장치

### 안전함을 어떻게 확신해?
- 가변인수 메서드를 호출 할 때 varargs를 담는 제네릭 배열이 만들어진다.
- 함수에서 이 배열에 수정을 가하지 않고 밖으로 노출되지 않으면 타입이 안전하다.
- 다음은 안전하지 않음. (외부 노출)
```java
static <T> T[] toArray(T... args) {
    return args;
}
```

### 안전하게 varargs를 사용하려면
- 안전하게 사용하는 메서드 (내부에서 생성한걸 return 한다)
```java
@SafeVarargs
static <T> List<T> flatten(List<? extends T>... lists) {
    List<T> result = new ArrayList<>();
    for (List<? extends T> list : lists)
        result.addAll(list);
    return result;
}
``` 
- varargs 매개변수 배열에 아무것도 저장하지 않는다.
- 그 배열(혹은 복제본)을 신뢰할 수 없는 코드에 노출하지 않는다.
```java
static <T> List<T> flatten(List<List<? extends T>> lists) {
    List<T> result = new ArrayList<>();
    for(List<? extends T> list : lists)
        result.addAll(list);
    return result;
}
audience = flatten(List.of(friends, romans, countrymen));
```
```java
static <T> List<T> pickTwo(T a, T b, T c) {
    switch(ThreadLocalRandom.current().nextInt(3)) {
        case 0: return List.of(a, b);
        case 1: return List.of(a, c);
        case 2: return List.of(b, c);
    }
    throw new AssertionError();
}
public static void main(String[] args) {
    List<String> attributes = pickTwo{"좋은", "빠른", "저렴한"};
}
```

### 정리
- 가변인수와 제네릭은 사이가 좋지않다.
- 배열과 제네릭 타입규칙은 서로 다르기 때문에
- 안전한 varargs 매개변수를 사용하고자 한다면 안전한지 확인 후 @SafeVarargs를 단다.
