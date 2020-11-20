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

### 6.3 그룹화
```java
Map<Dish.Type, List<Dish>> dishesByType = 
    menu.stream().collect(groupingBy(Dish::getType));
```

```java
Map<CaloricLevel, List<Dish>> dishesByCaloricLevel = menu.stream().collect(
    groupingBy(dish -> {
        if(dish.getCalories() <= 400) return CaloricLevel.DIET;
        else if(dish.getCalories() <= 700) return CaloricLevel.NORMAL;
        else return CaloricLevel.FAT;
    })
);
```

- 맵 필터링
```java
Map<Dish.Type, List<Dish>> caloricDishesByType = 
    menu.stream()
        .collect(groupingBy(Dish::getType,
            .filtering(dish -> dish.getCalories() > 500, toList())));
```

- grouping에 flatMapping 활용
```java
Map<Dish.Type, Set<String>> dishNamesByType = 
    menu.stream()
        .collect(groupingBy(Dish::getType,
            flatMapping(dish -> dishTags.get(dish.getName()).stream(), toSet())));          
```

- 다수준 그룹화
```java
Map<Dish.Type, Map<CaloricLevel, List<Dish>>> dishesByTypeCaloricLevel = 
    menu.stream().collect(
        groupingBy(Dish::getType,
            groupingBy(dish -> {
                if(dish.getCalories()) <= 400)
                    return CaloricLevel.DIET;
                else if(dish.getCalories() <= 700)
                    return CaloricLevel.NORMAL;
                else return CalroicLevel.FAT;
            })
        )
    )
```

- 가장 높은것 그룹
```java
Map<Dish.Type, Optional<Dish>> mostCaloricByType = 
    menu.stream()
        .collect(groupingBy(Dish::getType,
                        maxBy(comparingInt(Dish::getCalories))));          
```

- 서브 그룹에서 가장 높은 것
```java
Map<Dish.Type, Dish> mostCaloricByType = 
    menu.stream()
        .collect(groupingBy(Dish::getType,
                collectingAndThen(
                    maxBy(comparingInt(Dish::getCalories)),
            Optional::get
        )))          
```

- partitionBy
```java
Map<Boolean, Dish> mostCaloricPartitionedByVegetarian = 
    menu.stream().collect(
        partitioningBy(Dish::isVegetarian,
            collectingAndThen(maxBycomparingInt(Dish::getCalories(), Optional::get)))       
        )   
```

#### Collectors 클래스의 정적 팩터리 메서드
- toList: 스트림의 모든 항목을 리스트로 수집  
```java
List<Dish> dishes = menuStream.collect(toList());
```
- toSet: 스트림의 모든 항목을 중복 없는 집합으로 수집
```java
Set<Dish> dishes = menuStream.collect(toSet());
```
- toCollection: 스트림의 모든 항목을 컬렉션으로 수집
```java
Collection<Dish> dishes = menuStream.collect(toCollection(), ArrayList::new);
```
- counting: 스트림 항목 수 계산
```java
long howManyDishes = menuStream.collect(counting());
```
- summingInt: 스트림 항목 정수 합계 계산
```java
int totalCalories = menuStream.collect(summingInt(Dish::getCalories));
```
- averagingInt: 스트림 항목 평균값 계산
```java
double avgCalories = menuStream.collect(averagingInt(Dish::getCalories));
```
- summarizingInt: 스트림 항목 최대,최소,합계, 평균등 통계 수집
```java
IntSummaryStatistics menuStatistics = menuStream.collect(summarizingInt(Dish::getCalories));
```
- joining: toString 호출한 문자열 연결
```java
String shortMenu = menuStream.map(Dish::getName).collect(joining(", "))
```
- maxBy: 스트림 최대값 요소를 반환
```java
Optional<Dish> fattest = menuStream.collect(maxBy(comparingInt(Dish::getCalories)));
```   
- minBy: 스트림 최소값 요소를 반환
```java
Optional<Dish> lighttest = menuStream.collect(minBy(comparingInt(Dish::getCalories)));
```
- reducing: 초기값으로 부터 각 요소를 반복하면서 행위를 처리하고 하나의 값으로 리듀싱
```java
int totalCalories = menuStream.collect(reducing(0, Dish::getCalories, Integer::sum));
```
- collectingAndThen: 다른 컬렉터를 감싸고 결과에 반환함수 적용
```java
int howManyDishes = menuStream.collect(collectingAndThen(toList(), List::size));
```
- groupingBy: 하나의 값을 기준으로 항목을 그룹하여 맵화 시킴
```java
Map<Dish.Type, List<Dish>> dishesByType = menuStream.collect(groupingBy(Dish::getType));
```
- partitioningBy: 프리디케이트를 스트림의 각 항목에 적용한 결과로 항목 분할
```java
Map<Boolean, List<Dish>> vegetarianDishes = menuStream.collect(partitioningBy(Dish::isVegetarian));
```
