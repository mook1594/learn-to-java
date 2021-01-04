##### [GO TO BACK](../README.md)

# Chapter8. 컬릭션 API 개선
> 컬렉션 팩터리 사용하기  
> 리스트 및 집합과 사용할 새로운 관용 패턴 배우기  
> 앱과 사용할 새로운 관용 패턴 배우기  

### 8.1 컬렉션 팩토리
```java
List<String> friends = Arrays.asList("Raphael", "Olivia", "Thibaut");
```

#### UnsupportedOperationException 예외 발생
- Arrays.asList는 내부적으로 고정된 크기의 배열이기때문에 요소를 추가하면 에러가 발생한다.
- 다른방법
```java
Set<String> friends = new HashMap<>(Arrays.asLsit("Raphael", "Oliviia", "Thibaut"));
Set<String> friends = Stream.of("Raphael", "Olivia", "Thibaut")
                            .collect(Collectors.toSet());
```
#### 리스트 팩토리
```java
List<String> friends = List.of("Raphael", "Olivia", "Thibaut");
```
- 여기에 `friends.add("Chih-Chun");`로 요소를 추가하면 UnsupportedOperationException이 발생한다.

#### 집합 팩토리
```java
Set<String> friends = Set.of("Raphael", "Olivia", "Thibaut");
```
- 변경할 수 없는 집합

#### 맵 팩토리
```java
Map<String, Integer> ageOfFriends = 
    Map.of("Raphael", 30, "Olivia", 25, "Thibaut", 26);
Map<String, Integer> ageOfFriends = 
    Map.ofEntries(entry("Raphael", 30),
                    entry("Olivia", 25),
                    entry("Thibaut", 26));
```

### 8.2 리스트와 집합 처리
- sort: 리스트 정렬 (List)
#### removeIf
- 프레디케이트를 만족하는 요소 제거 (List, Set)
```java
for(Iterator<Transaction> iterator = transactions.iterator();iterator.hasNext();) {
    Transaction transaction = iterator.next();
    if(Character.isDigit(transaction.getReferenceCode().charAt(0))) {
        iterator.remove();
    }
}
```
- 복잡한 과정을 다음과 같이 사용할 수 있다.
```java
transactions.removeIf(transaction -> Character.isDigit(transaction.getReferenceCode().charAt(0)))
```
#### replaceAll
- UnaryOperator 함수를 이용해 요소를 변경
```java
for(ListIterator<String> iterator = referenceCodes.listIterator();iterator.hasNext();) {
    String code = iterator.next();
    iterator.set(Character.toUpperCase(code.charAt(0)) + code.substring(1));
}
```
- 다음과같이 변경
```java
referenceCodes.replaceAll(code -> Character.toUpperCase(code.charAt(0)) + code.substring(1));
```

### 8.3 맵 처리
#### forEach 메서드
```java
for(Map.Entry<String, Integer> entry: ageOfFriends.entrySet()) {
    String friend = entry.getKey();
    Integer age = entry.getValue();
    System.out.println(friend + age);
}
```
```java
ageOfFriends.forEach((friends, age) -> System.out.println(friend + age));
```
#### 정렬 메서드
```java
Map<String, String> favoriteMovies = 
    Map.ofEntries(entry("Raphael", "StartWars"), 
            entry("Cristina", "Matrix"), 
            entry("Olivia", "James Bond"));
favoriteMovies
    .entrySet()
    .stream()
    .sorted(Entry.comparingByKey())
    .fforEachOrdered(System.out::println); 
```
#### getOrDefault 메서드
- NullPointerException 방지
```java
Map<String, String> favoriteMovies = 
    Map.ofEntries(entry("Raphael", "StartWars"), 
            entry("Cristina", "Matrix"), 
            entry("Olivia", "James Bond"));
favoriteMovies.getOrDefault("Olivaia", "Matrix"));
favoriteMovies.getOrDefault("Thibaut", "Matrix"));
```
#### 계산 패턴
- computeIfAbsent: 키가 존재하지 않으면 맵에 추가, 존재하면 기존값 반환
```java
Map<String, byte[]> dataToHash = new HashMap<>();
MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
lines.forEach(line -> dataToHash.computeIfAbsent(line, this::calculateDisgest));
private byte[] calculateDisgest(String key) {
    return messageDigest.digest(key.getBytes(StandardCharsets.UTF_8));
}
```
- computeIfPresent: 키가 존재하면 값을 업데이트.
- compute: 키로 값을 저장.
#### 삭제 패턴
```java
favorietMoves.remove(key, value);
```
#### 교체 패턴
```java
favoriteMovies.replaceAll((friend, movie) -> movie.toUpperCase())
```
#### 합침
- 중복된 키에 대한 값 처리
```java
Map<String, String> family = Map.ofEntires();
Map<String, String> friends = Map.ofEntires();

Map<String, String> everyone = newHashMap<>(family);
// 중복키가 없다면
everyone.putAll(friends);
// 중복기 있을시 merge 로 해결
friends.forEach((k, v) -> everyone.merge(k, v, (movie1, movie2) -> movie1 + " & " + movie)); 
```
- null 값도 merge로 간편히 처리
```java
Map<String, Long> moviesToCount = new HashMap<>();
String movieName = "JamesBond";
long count = moviesToCount.get(movieName);
if(count == null) {
    moviesToCount.put(movieName, 1);
} else {
    moviesToCount.put(movieName, count + 1);
}
```
```java
moviesToCount.merge(movieName, 1L, (key, count) -> count + 1L);
```
### 8.4 개선된 ConcurrentHashMap
- ConcurrentHashMap 특정 부분만 잠궈 동시추가, 갱신 작업을 허용. 동기화된 Hashtable 버전에 비해 읽기 쓰기 연산 성능이 월등
#### 리듀스와 검색
- forEach: 각 쌍에 주어진 액션을 실행
- reduce: 모든 쌍을 제공된 리듀스 함수를 이용해 결과로 합침
- search: 널이 아닌 값을 반환할 때까지 각 쌍에 함수를 적용
```java
ConcurrentHashMap<String, Long> map = new ConcurrentHashMap<>();
long parallelismThreshold = 1;
Optional<Integer> maxValue = 
    Optional.ofNullable(map.reduceValues(parallelismThreshold, Long::max));
```
#### 계수
- mappingCount: size 메서드 대신 사용. int 범위를 넘어서는 이후 상황을 대처할 수 있다.
#### 집합뷰
- keySet, newKeySet
