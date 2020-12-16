##### [GO TO LIST](../README.md)

# 아이템45. 스트림은 주의해서 사용하라

### 스트림 API
- 스트림은 데이터 원소의 유한 혹은 무한 시퀀스를 뜻함
- 스트림 파이프라인은 원소들로 수행하는 연산 단계를 표현하는 개념

### 스트림 파이프라인
- 소스 스트림에서 시작하여 종단연산(terminal operation)으로 끝 
- 그 사이 하나 이상의 중간 연산 (intermediate operation)
- 스트림 파이프 라인은 지연 평가 됨 (lazy evaluation)
- lazy 여서 무한 스트림을 다룰 수 있게 됨.

### 플푸언트 API
- 스트림 API는 메서드를 지원
- 파이프라인 하ㄴ를 구성하는 모든 호출 연결

### 스트림의 사용
```java
import java.io.IOException;public class Anagrams {
    public static void main(String[] args) throws IOException {
        File dictionary = new File(args[0]);
        int minGroupSize = Integer.parseInt(args[1]);

        Map<String, Set<String>> groups = new HashMap<>();
        try (Scanner s = new Scanner(dictionary)) {
        	while(s.hasNext()) {
        		String word = s.next();
                groups.computeIfAbsent(alphabetize(word),
                    (unused) -> new TreeSet<>()).add(word);
        	}
        }
        
        for (Set<String> group : groups.values()) 
            if (group.size() >= minGroupSize)
                System.out.println(group.size() + ": " + group);
    }

    private static String alphabetize(String s) {
        char[] a = s.toCharArray();
        Arrays.sort(a);
        return new String(a);
    }
}
```
- computeIfAbsent메서드를 사용하면 키에 다수의 값을 매핑하는 맵을 쉽게 구현 가능 하다.

##### 과하게 스트림을 사용한 케이스
```java
public class Anagrams {
    public static void main(String[] args) throws IOException {
        Path dictionary = Paths.get(args[0]);
        int minGroupSize = Integer.parseInt(args[1]);

        try (Stream<String> words = Files.lines(dictionary)) {
        	words.collect(
                groupingBy(word -> word.chars().sorted()
                                .collect(StringBuilder::new, 
                                    (sb, c) -> sb.append((char) c),
                                    StringBuilder::append).toString()))
            .values().stream()
            .filter(group -> group.size() >= minGroupSize)
            .map(group -> group.size() + ": " + group)
            .forEach(System.out::println);
        }
    }
    private static String alphabetize(String s) {
        char[] a = s.toCharArray();
        Arrays.sort(a);
        return new String(a);
    }

}
```
- 스트림을 과용하면 프로그램이 읽거나 유지보수하기 힘들어진다.

##### 적절한 스트림 사용 케이스
```java
public class Anagrams {
    public static void main(String[] args) throws IOException {
        Path dictionary = Paths.get(args[0]);
        int minGroupSize = Integer.parseInt(args[1]);

        try(Stream<String> words = Files.lines(dictionary)) {
        	words.collect(groupingBy(word -> alphabetize(word)))
                .values().stream()
                .fileter(group -> group.size() >= minGroupSize)
                .forEach(g -> Sytstem.out.println(g.size() + ": " + g));
        }
    }

    private static String alphabetize(String s) {
        char[] a = s.toCharArray();
        Arrays.sort(a);
        return new String(a);
    }
}
```
- 스트림 파이프라인 가독성을 위해서 람다 타입 이름을 잘 정해야한다.
- 도우미 메서드를 적절하게 사용하는 일도 중요. (alphabetize) 
- char 값을 처리할 때는 스트림을 삼가하는 편이 좋다.

### 스트림을 사용할 때?
- 리펙토링 해보고 새 코드가 더 나아보일때만 스트림을 사용하자

### 스트림에서 할 수 없는것
- 지역 변수를 수정하는것 (대부분이 final로 처리된다)
- return, break 처럼 중간에 빠져나갈 수 없다.

### 그럼 스트림을 언제 사용해야할까?
- 원소들의 시퀀스를 일관되게 변경한다.
- 원소들의 시퀀스를 필터링한다.
- 원소들의 시퀀스를 하나의 연산을 사용해 결합한다.
- 원소들의 시퀀스를 컬렉션에 모은다.
- 특정조건에 만족하는 원소를 찾는다.


