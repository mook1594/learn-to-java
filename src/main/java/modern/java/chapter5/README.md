##### [GO TO BACK](../README.md)

# Chapter5. 스트림 활용

### 5.1 필터링
- 프레디케이트(불리언을 반환하는 함수)로 필터링
- 고유 요소 필터링 (distinct())

### 5.2 스트림 슬라이스 (자바 9)
- filter
```java
List<Dish> fileteredMenu
        = specialMenu.stream()
                .filter(dish.getCalories() < 320)
                .collect(toList());
```
- takeWhile: 처음부터 조건의 브레이크 시점까지의 요소 반환
```java
List<Dish> sliceMenu1
        = specialMenu.stream()
                    .takeWhile(dish -> dish.getCalories() < 320)
                    .collect(toList()); 
```
- dropWhile: 브레이크 시점부터 마지막 요소 반환
```java
List<Dish> sliceMenu2
        = specialMenu.stream()
                    .dropWhile(dish -> dish.getCalories() < 320)
                    .collect(toList());
```
- limit: 최대 n개 요소 반환
```java
List<Dish> dishes = specialMenu.stream()
                                .filter(dish -> dish.getCalories() > 300)
                                .limit(3)
                                .collect(toList());
```
- skip: 처음 n개를 제외한 요소 반환
```java
List<Dish> dishes = menu.stream()
                        .filter(d -> d.getCalories() > 300)
                        .skip(2)
                        .collect(toList());
```

### 5.3 매핑
- flatMap: 평면화된 stream으로 변환

### 5.4 검색과 매칭
- anyMatch: 적어도 한 요소와 일치하는지 확인
- allMatch: 모든 요소가 일치하는지 확인
- noneMatch: 일치하는 요소가 없는지 확인
- findAny: 일치하는 임의의 요소 (병렬에서 주로 사용)
- findFirst: 첫번째 요소 찾기

### 5.5 리듀싱
- reduce
```java
int sum = numbers.stream().reduce(0, (a, b) -> a + b);
```
- > 초기값 0
- > 두 요소를 이용한 계산식
- > a는 0부터 계산하는 기존값, b는 스트림의 요소 값

- 초기값이 없는 reduce
```java
Optional<Integer> sum = numbers.stream().resduce((a,b) -> (a + b));
```
- reduce를 이용한 min
```java
Optional<Integer> min = numbers.stream().reduce(Integer::min);
```
- reduce를 이용한 max
```java
Optional<Integer> max = numbers.steram().reduce(Integer::max);
```
##### reduce 메서드의 장점과 병렬화
- 반복과, reduce를 이용하여 합계를 구하는것 무엇이 다를까? 병렬화!!
- 기존 방법은 sum변수를 공유해야하므로 쉽게 병렬화 하기 어렵다.

### 5.6 연습
[StreamExample](./StreamExample.java)

### 5.7 숫자형 스트림
- sum()
- IntStream
```java
int calories = menu.stream()
                    .mapToInt(Dish::getCalories)
                    .sum();
```
##### 스트림 숫자 스트림 변환
```java
IntStream intStream = menu.stream().mapToInt(Dish::getCalories);
Stream<Integer> stream = intStream.boxed();
```
##### OptionalInt
```java
OptionalInt maxCalories = menu.stream()
                            .mapToInt(Dish::getCalories)
                            .max();
int max = maxCalories.orElse(1);
```
##### 숫자 범위
```java
IntStream evenNumbers = IntStream.rangeClosed(1, 100)
                                .filter(n -> n % 2 == 0);
```
##### 피타고라스 수 예제


### 5.8 스트림 만들기
##### 값으로 스트림 만들기
```java
Stream<String> stream = Stream.of("Modrn", "java", In", "Action");
stream.map(String::toUpperCase).forEach(Sytstem.out::println);
Stream<String> emptyStream = Stream.empty();
```
##### null이 될 수 있는 객체로 스트림 만들기
- null 체크
```java
String homeValue = System.getProperty("home");
Stream<String> homeValueStream = homeValue == null ? Stream.empty() : Stream.of(value);
```
- Stream.ofNullable 을 이용
```java
Stream<String> homeValue = Stream.ofNullable(System.getProperty("home"));
```
```java
Stream<String> values = 
    Stream.of("config", "home", "user")
            .flatMap(key -> Stream.ofNullable(System.getProperty(key)));
```
##### 배열을 스트림으로
```java
int[] numbers = {2, 3, 5, 7, 11, 13};
int sum = Arrays.stream(numbers).sum();
```
##### 파일로 스트림
```java
long uniqueWords = 0;
try(Stream<String> lines = Files.lines(Paths.get("data.txt", Charset.defaultCharset()))) {
    uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" ")))
                                                .distinct()
                                                .count();
} catch(IOException e) {

}
```
##### 함수로 무한 스트림 만들기
- iterate 메서드
```java
Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0]+t[1]})
        .limit(20)
        .map(t -> t[0])
```
- iterate Precate
```java
IntStream.iterate(0, n -> n < 100, n -> n + 4)
```
같은 표현
```java
IntStream.iterate(0, n -> n + 4)
        .takeWhile(n -> n < 100)
```
- 다른 불가능 표현
```java
IntStream.iterate(0, n -> n + 4)
        .fileter(n -> n < 100) // 무한하기때문에 사용할 수 없다.
```
##### generate
```java
IntStream ones = IntStream.generate(() -> 1);
```
```java
IntStream tows = IntStream.generate(new IntSupplier() {
    public int getAsInt() {
        return 2;
    }
})
```
- 피보나치수열 표현
```java
IntSupplier fib = IntSupplier() {
    private int previous = 0;
    private int current = 1;
    public int getAsInt() {
        int oldPrevious = this.previous;
        int nextValue = this.previous + this.current;
        this.previous = this.current;
        this.current = nextValue;
        return oldPrevious;
    }
}
IntStream.generate(fib).limit(10).forEach(System.out::println);
```

##### iterate vs generate
generate: 가변 (mutable)
iterate: 불편 (immutable)