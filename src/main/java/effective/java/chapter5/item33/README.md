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
