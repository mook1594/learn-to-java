#####[GO TO LIST](../README.md)

# 아이템13. clone 재정의는 주의해서 진행하라

### Cloneable 인터페이스?
- 메서드 하나도 없는 인터페이스... 어따씀?
- Object의 protected 메서드인 clone 동작 방식을 결정한다.
- Cloneable을 구현한 클래스의 인스턴스에서 clone을 호출하면 복사한 객체를 반환
그렇지 않은 인스턴스는 CloneNotSupportedException을 던진다.

### clone 메서드 규약
- x.clone() != x => true
- x.clond().getClass == x.getClass => true
- x.clone().eqauls(x) => true / false

### 가변 상태를 참조하지 않는 클래스용 clone
```java
@Override public PhoneNumber clone() {
    try{
        return (PhoneNumber) super.clone();
    } catch(CloneNotSupportedException e) {
        throw new AssertionError();
    }   
}
```

### 가변 상태를 참조하는 클래스용 clone
```java
@Override public Stack clone() {
    try{
        Stack result = (Stack) super.clone();
        result.elements = elements.clone();
        return result;
    } catch(CloneNotSupportedException e) {
        throw new AssertionError();
    }
}
```

### 가변 상태를 공유하는 잘못된 clone 메서드
```java
@Override public HashTable clone(){
    try{
        HashTable result = (HashTable) super.clone();
        result.buckets = buckets.clone();
        return result;
    } catch (CloneNotSupportedException e){
        throw new AssertionError();
    }
}
```

### 복잡한 가변 상태를 갖는 clone
```java
@Override public HashTable clone(){
    try{
        HashTable result = (HashTable) super.clone();
        result.buckets = new Entry[buckets.length];
        for(int i = 0; i< buckets.length; i++)
            if(bucket[i] != null)
                result.bucket[i] = bucket[i].deepCopy();
            return result;
    } catch (CloneNotSupportedException e){
        throw new AssertionError();
    }
}
```

### 복사 생성자
```java
public Yum(Yum yum) { ... }
```

### 복사 팩터리
```java
public static Yum public newInstance(Yum yum) { ... }
```

### 그래서?
- Cloneable을 아무데서나 확장하면 안된다.
- 불변의 final클래스라면 위험이 크진 않지만 성능 관점도 생각해야한다.
- 복제 기능은 생성자와 팩터리를 이용하는게 최고.
