##### [GO TO LIST](../README.md)

# 아이템 28. 배열보다는 리스트를 사용하라

### 배열과 제너릭의 차이
- 배열은 공변(하위도 같이 변함)
- 제너릭은 불공변.
- 배열은 Type1 super, Type2 sub일때 Type1[] super, Type2[] sub 이다. 
- Type1, Type2가 있을 떄 List<Type1>은 List<Type2>의 하위 타입도 아니고 상위 타입도 아니다.

##### 호환되지 않는 타입
```java
Object[] objectArray = new Long[1];
objectArray[0] = "타입이 달라 넣을 수 없다.";
```
```java
List<Object> ol = new ArrayList<Long>();
ol.add("타입이 달라 넣을 수 없다.")
```
- 둘다 넣을 수 없지만, Array는 Runtime에 알수 있지만 제너릭은 컴파일시 알 수 있다.

##### 배열은 실체화 된다.
- 배열은 런타임시 타입 정보가 인지됨
- 제너릭은 컴파일시 타입 정보가 인지되고 소거됨(ensure). 런타임에는 알 수도 없다.

##### 제네릭 타입은 매개변수로 사용할 수 없다.
- 배열로 만들 수 없다. new E[]
- 막는이유는? 타입이 안젆아지 않기 때문에
