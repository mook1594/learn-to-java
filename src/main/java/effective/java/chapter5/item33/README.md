##### [GO TO LIST](../README.md)

# 아이템33. 타입 안전 이종 컨테이너를 고려하라

### 안전 이종 컨테이너 패턴
- 매개변수화한 키를 가지고 값을 넣거나 빼는걸 활용한 패턴
- class 리터럴 타입 Class<T> (타입 토큰)
```java
public class Favorites {
    public <T> void putFavorite(Class<T> type, T instance);
    public <T> T getFavorite(Class<T> type);
}

```java
public static void main(String[] args) {
    Favorites f = new Favorites();
    
    f.putFavorite(String.class, "Java");
    f.putFavorite(Integer.class, 0xcafebebe);
    f.putFavorite(Class.class, Favorites.class);
    
    String favoriteString = f.getFavorite(String.class);
    int favoriteInteger = f.getFavorite(Integer.class);
    Class<?> favoriteClass = f.getFavorite(Class.class);

}
```
```java
public class Favorites {
    private Map<Class<?>, Object> favorites = new HashMap<>();
    
    public <T> void putFavorite(Class<T> type, T instance) {
        favorites.put(Objects.requireNonNull(type), instance);
    }
    
    public <T> T getFavorite(Class<T> type) {
        return type.cast(favorites.get(type));
    }
}  
```

### 알아두어야할 두 제약
- 첫번째: 악의적인 클라이언트가 Class 객체를 로타입으로 넘기면 안전성이 깨짐
- 불편을 보장하려면 다음과 같이
```java
public <T> void putFavorite(Class<T> type, T instance) {
    favorites.put(Objects.requireNonNull(type), type.cast(instance));
}
```

- 두번째: 실체화 불가 타입에는 사용 불가
- List<String> 불가 (Class 객체를 얻을 수 없다.)
- 슈퍼 타입 토큰으로 해결 시도 가능 (Spring의 ParameterizedTypeReference)
```java
Favorites f = new Favorites();
List<String> pets = Arrays.asList("개", "고양이", "앵무");
f.putFavorite(new TypeRef<List<String>>(){}, pets);
List<String> listOfStrings = f.getFavorite(new TypeRef<List<String>>(){});
```
- 이 방식에 대한 설명 [링크](http://bit.ly/2NGQi2S)
- 이 방식의 한계 [링크](http://bit.ly/2OfIrdG)

### 한정적 타입 토큰
- 애너테이션 API는 한정적 타입 토큰을 적극 활용한다 [아이템39](../item39/README.md)
```java
public <T extends Annotation> T getAnnotation(Class<T> annotationType);
```

- asSubclass를 사용해 한정적 타입 토큰을 안전하게 형변환 한다.
```java
static Annotation getAnnotation(AnnotatedElement element, String annotationTypeName) {
    Class<?> annotationType = null;
    try{
        annotationType = Class.forName(annotationTypeName);
    } catch (Exception ex) {
        throw new IllegalArgumentException(ex);
    }
    return element.getAnnotation(
        annotationType.asSubclass(Annotation.class)
    );
}
```
