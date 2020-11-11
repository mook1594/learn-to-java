##### [GO TO LIST](../README.md)

# 아이템20. 추상 클래스보다는 인터페이스를 우선하라

### 다중메커니즘
- 자바가 제공하는 다중 메커니즘은 인터페이스와 추상클래스

### 인터페이스
- 믹스인: 특정 선택적 행위를 제공(Comparable, Iterable, AutoCloseable)  
  추상 클래스로 불가능, 하나만 상속 받을 수 있기때문
  
```java
public interface Singer {
    AudioClip sing(Song s);                            
}
```
```java
public interface SongWriter {
    Song compose(int chartPosition);    
}
```
```java
public interface SingerSongwriter extends Singer, Songwriter {
    AudioClip strum();
    void actSensitive();    
}
```

### 인터페이스 활용
- 인터페이스 이름이 Interface라면 골격 구현 클래스 이름은 AbstractInterface
```java
static List<Integer> intArrayAsList(int[] a) {
    Objects.requireNonNull(a);
    
    // 다이아몬드 연산자를 이렇게 사용하는 건 자바 9부터 가능하
    // 더 낮은 버전은 <Integer>
    return new AbstractList<> () {
        @Override public Integer get(int i) {
            return a[i];    
        }
        @Override public Integer set(int i, Integer val) {
            int oldVal = a[i];
            a[i] = val; // 오토 언박싱
            return oldVal; // 오토박싱
        }
        @Override public int size() {
            return a.length;
        }
    };
}
```

### 골격 구현 클래스
##### [AbstractMapEntry](./AbstractMapEntry.java)

### 그래서요?
- 다중 구현용 타입으로는 이터페이스가 가장 적합
- 골격 구현을 함께 제공하는 방법을 고려
