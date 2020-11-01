##### [GO TO BACK](../README.md)

# Chapter6. 스트림으로 데이터 수집

### 6.1 컬렉터란 무엇인가?
- collect에서는 리듀싱 연산을 이용해서 스트림의 각 요소를 방문하면서 컬렉터가 작업을 처리한다.

##### Collectors 메서드 기능
- 스트림 요소를 하나의 값으로 리듀스하고 요약
- 요소 그룹화
- 요소 분할

### 6.2 리듀싱과 요약
- counting
```java
long howManyDishes = menu.stream().collect(Collectors.counting());
long howManyDishes = menu.stream().count();
```
##### 최대값 최소값 검색
- maxBy
```java
Comparator<Dish> dishCaloriesComparator = 
    Comparator.comparingInt(Dish::getCalories);
Optional<Dish> mostCalorieDish = menu.stream()
    .collect(maxBy(dishCaloriesComparator));
```
##### 요약
- summingInt: 합계
```java
int totalCalories = menu.stream().collect(summingInt(Dish::getCalories));
```
=> summingLong, summingDouble 같은 방식으로 동작함

- averagingInt: 평균
```java
double avgCalories = menu.stream()
    .collect(averagingInt(Dish::getCalories));
```
=> averagingLong, averagingDouble

- summarizingInt: 갯수, 합계, 최소, 최대, 평균
```java
IntSummaryStatistics menuStatistics = menu.stream()
    .collect(summarizingInt(Dish::getCCalories));
```
=> summarizingLong(LongSummaryStatistics), summarizingDouble(DoubleSummaryStatistics)

##### 문자열 연결
- joining
```java
String shortMenu = menu.stream()
    .map(Dish::getName).collect(joining(", "));
```
=> StringBuilder를 내부적으로 사용해 문자열을 만든다. toString이 있다면 map을 생략할 수 있다.

##### 범용 리듀싱 요약 연산
- reducing
```java
int totalCalories = menu.stream().collect(reducing(0, Dish::getCalories, (i, j) -> i + j));
```
=> 인수가 3개 (초기값 or 디폴트값, 정수변환, BinaryOperator)
```java
Optional<Dish> mostCalorieDish = menu.stream()
    .collect(reducing(
        (d1, d2) -> d1.getCalories > d2.getCalories() ? d1 : d2))
```