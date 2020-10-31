##### [GO TO BACK](../README.md)

# Chapter4. 스트림 소개

### 스트림이란?
> 데이터 처리 연산을 지원하도록 소스에서 추출된 연속되는 요소
- 스트림을 이용하면 선언형으로 컬렉션을 처리할 수 있다.
- 데이터를 병렬로 투명하게 처리할 수 있다.
- 자바 7
```java
List<Dish> lowCaloricDishes = new ArrayList<>();
for(Dish dish: menu) {
    if(dish.getCalories() < 400) {
        lowCaloricDishes.add(dish);
    }
}
Collections.sort(lowCaloricDishes, new Comparator<Dish>() {
    public int compare(Dish dish1, Dish dish2) {
        return Integer.compare(dish1.getCalories(), dish2.getCalories());
    }
});
List<String> lowCaloricDishesName = new ArrayList<>();
for(Dish dish: lowCaloricDishes) {
    lowCaloricDishesName.add(dish.getname());
}
```
- 자바 8
```java
List<String> lowCaloricDishesName = 
    menu.stream() // .parallelStream() 으로 병렬처리 가능
        .filter(d -> d.getCalories() < 400)
        .sorted(comparing(Dish::getCalories))
        .map(Dish::getName)
        .collect(toList());
```
#### 스트림의 장점은?
- 선언형으로 코드를 구현할 수 있다. "XXX에 해당하는것만 선택하라" 같은 동작 수행 가능
- 데이터 처리 파이프라인을 만들 수 있다.
- 병렬 처리 가능 하여 성능이 좋아진다.

