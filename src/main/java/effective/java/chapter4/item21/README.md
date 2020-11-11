##### [GO TO LIST](../README.md)
 
 # 아이템21. 인터페이스는 구현하는 쪽을 생각해 설계하라
 
 ### 코드품질
 - 생각할 수 있는 모든 상황에서 불변식을 해치지 않는 디폴트 메서드를 작성하기란 어려운 법이다.
 
 ### java8에서 Default 메서드
 ```java
default boolean removeIf(Predicate<? super E> filter) {
    Objects.requireNonNull(filter);
    boolean result = false;
    
    for(Iterator<E> it = iterator(); it.hasNext(); ) {
        if(filter.test(it.next())) {
            it.remove();
            result = true;
        }
    }
    return result;
}
```

### 디폴트 메서드의 무서움
- 디폴트 메서드는 기존 구현체에 런타임 오류를 일으킬 수 있다.
- 그래서 기존 인터페이스에는 디폴트 메서드를 추가하려는 일은 가급적 피해야한다.

