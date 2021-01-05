##### [GO TO BACK](../README.md)

# Chapter9. 리팩터링, 테스팅, 디버깅
> 람다 표현식으로 코드 리팩터링하기  
> 람다 표현식이 객체지향 설계 패턴에 미치는 영향  
> 람다 표현식 테스팅  
> 람다 표현식과 스트림 API 사용 코드 디버깅  

### 9.1 가독성과 유연성을 개선하는 리펙터링
#### 익명 클래스를 람다 표현식으로 리펙터링하기
```java
Runnable r1 = new Runnable() {
    public void run() {
        System.out.println("Hello");
    }
};
Runnable r2 = () -> System.out.println("Hello");
```
```java
int a = 10;
Runnable r1 = () -> {
    int a = 2;
    System.out.println(a); // 컴파일 에러
}
Runnable r2 = new Runnable() {
    public void run() { // 람다 표현식 해제
        int a = 2;
        System.out.println(a);
    }
}
```
- 형 모호함 해결
```java
interface Task {
    public void execute();
}
public static void doSomething(Runnable r) { r.run(); }
public static void doSomething(Task r) { r.execute(); }
```
- 강제 캐스팅
```java
doSomething(new Task() {
    public void execute() {
        System.out.println("Danger danger!!");
    }
});
doSomething(() -> System.out.println("Danger dnager!!"));
doSomething((Task)() -> System.out.println("Danger danger!!"));
```
#### 람다 표현식을 메서드 참조로 리팩터링하기
```java
Map<CaloricLevel, List<Dish>> dishesByCaloricLevel = 
    menu.stream()
        .collect(
            groupingBy(dish -> {
                if(dish.getCalories() <= 400) return CaloricLevel.DIET;
                else if(dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                elsee return ClaoricLevel.FAT;
}));
```
->
```java
Map<CaloricLevel, List<Dish>> dishesByCaloricLevel = 
    menu.stream.collect(groupingBy(Dish::getCaloricLevel));
public class Dish {
    ...
    public CaloricLevel getCaloricLevel() {
        if(this.getCalories() <= 400) return CaloricLevel.DIET;
        else if(this.getCalories() <= 700) return CaloricLevel.NORMAL;
        else return CaloricLevel.FAT;
    }
}
```
- comparing, maxBy
```java
inventory.sort((Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWight()));
inventory.sort(comparing(Applee::getWeight));
```
- sum, maximum
```java
int totalCalories = menu.stream().map(Dish::getCalories).reduce(0, (c1, c2) -> c1 + c2);
int totalCalories = menu.stream().collect(summingInt(Dish::getCalories));
```
#### 명령형 데이터 처리를 스트림으로 리팩터링하기
```java
List<String> dishNames = new ArrayList<>();
for(Dish dish : menu) {
    if(dish.getCalories() > 300) {
        diishNames.add(dish.getName());
    }
}

menu.parallelStream()
    .filter(d -> d.getCalories() > 300)
    .map(Dish::getName)
    .collect(toList());
```
#### 코드 유연성 개선
##### 함수형 인터페이스 적용
- 조건부 연기 실행
```java
if(loggeer.isLoggable(Log.FINER)) {
    logger.finer("Problem: " + generateDiagnostic());
}
logger.log(Level.FINER, "Problem: " + generateDiagnostic());
```
```java
public void log (Level level, Supplier<String> msgSupplier)
logger.log(Level.FINER, () -> "Problem: " + generateDiagnostic());
public void log(Level level, Supplier<String> msgSupplier) {
    if(logger.isLoggable(level)) {
        log(level, msgSupplier.get());
    }
}
```
- 실행 어라운드
```java
String oneLine = processFile((BufferedReader b) -> b.readLine());
Strinig twoLine = processFile((BufferedReader b) -> b.readLine() + b.readLine());
public static String processFile(BufferedReaderProcessor p) throws IOException {
    try(BufferedReader br = new BufferedReader(new FileReader("ModernJavaInAction/chap9/data.txt"))) {
        return p.process(br);
    }
}
public interface BufferedReaderProcessor {
    String process(BufferedReader b) throws IOException;
}
```

### 9.2 람다로 객체지향 디자인 패턴 리팩터링하기
#### 전략 패턴(Strategy)
```java
public interface ValidationStrategy {
    boolean execute(String s);
}

public class IsAllLowerCase implements ValidationStrategy {
    public boolean execute(String s) {
        return s.matches("[a-z]+");
    }
}
public class IsNumeric implements ValidationStrategy {
    public boolean execute(String s) {
        return s.matches("\\d+");
    }
}
public class Validator {
    private final ValidationStrategy strategy;
    public Validator(ValidationStrategy v) {
        this.strategy = v;
    }
    public boolean validate(String s) {
        return strategy.execute(s);
    }
}
```
```java
Validator numberValidator = new Validator(new IsNumeric());
boolean b1 = numbericValidator.validate("aaaa");
Validator lowerCaseValidator = new Validator(new IsAllLowerCase());
boolean b2 = lowerCaseValidator.validate("bbbb");
```
- 람다 표현식 사용
```java
Validator numbericValidator = new Validator((String s) -> s.matches("[a-z]+"));
boolean b1 = numberValidator.validate("aaaa");
Validator lowerCaseValidator = new Validator((String s) -> s.matches("\\d+"));
boolean b2 = lowerCaseValidator.validate("bbbb");
```
#### 템플릿 메서드 패턴
```java
abstract class OnlineBanking {
    public void processCustomer(int id) {
        Customer c = Database.getCustomerWithId(id);
        makeCustomerHappy(c);
    }
    abstract void makeCustomerHappy(Customer c);
}
```
- 람다 표현식 사용
```java
abstract class OnlineBanking {
    public void processCustomer(int id, Consumer<Customer> makeCustomerHappy) {
        Customer c = Database.getCustomerWithId(id);
        makeCustomerHappy.accept(c);
    }
}
new OnlineBankingLamda().processCustomer(1337, 
    (Customer c) -> System.out.println("Hello " + c.getName()));
```
#### 옵저버 패턴
```java
interface Observer {
    void notify(String tweet);
}
interface Subject {
    void registerObserver(Observer o);
    void notifyObservers(String tweet);
}
class NYTimes implements Observer {
    public void notify(String tweet) {
        if(tweet != null && tweet.contains("money")) {
            System.out.println("Breaking news in NY! " + tweet);
        }
    }
}
class Guardian implements Observer {
    public void notify(String tweet) {
        if(tweet != null && tweet.contains("wine")) {
            System.out.println("Yet more news from London... " + tweet);
        }
    }
}
class LeMonde implements Observer {
    public void notify(String tweet) {
        if(tweet != null && tweet.contains("wine")) {
            System.out.println("Today cheese, wine and news! " + tweet);
        }
    }
}
class Feed implements Subject {
    private final List<Observer> observers = new ArrayList<>();
    @Override
    public void registerObserver(Observer o) {
        this.observers.add(o);
    }
    @Override
    public void notifyObservers(String tweet) {
        observers.forEach(o -> o.notify(tweet));
    }
}
```
```java
Feed f = new Feed();
f.registerObserver(new NYTimes());
f.registerObserver(new Guardian());
f.registerObserver(new LeMonde());
f.notifyObservers("The queen said her favorite book is MJA");
```
- 람다 표현식 사용하기
```java
f.registerObserver((String tweet) -> {
    if(tweet != null && tweet.contains("money")) {
        System.out.println("Breaking news in NY! " + tweet);
    }
});
f.registerObserver((String tweet) -> {
    if(tweet != null && tweet.contains("queen")) {
        System.out.println("Yet more news from London... " + tweet);
    }
});
```
#### 의무 체인 패턴
```java
public abstract class ProcessingObject<T> {
    protected ProcessingObject<T> successor;
    public void setSuccessor(ProcessingObject<T> successor) {
        this.successor = successor;
    }
    public T handle(T input) {
        T r = handleWork(input);
        if(successor != null) {
            return successor.handle(r);
        }
        return r;
    }
    abstract protected T handleWork(T input);
}

public class HeaderTextProcessing extends ProcessingObject<String> {
    public String handleWork(String text) {
        return "From Raoul, Mario and Alan: " + text;
    }
}

public class SpellCheckerProcessing extends ProcessingObject<String> {
    public String handleWork(String text) {
        return text.replaceAll("labda", "lambda");
    }
}
```
```java
ProcessingObject<String> p1 = new HeaderTextProcessing();
ProcessingObject<String> p2 = new SpellCheckerProcessing();
p1.setSuccessor(p2);
String result = p1.handle("Aren't labdas relly sexy?!!");
```
- 람다 표현식 사용
```java
UnaryOperator<String> headerProcessing = 
    (String text) -> "From Raoul, Mario and Alan: " + text;
UnaryOperator<String> spellCheckerProcessing = 
    (String text) -> text.replaceAll("labda", "lambda");
Function<String, String> pipline = 
    headerProcessing.andThen(spellCheckerProcessing);
String result = pipeline.apply("Aren't labdas really sexy?!!");
```
#### 팩토리 패턴
```java
public class ProductFactory {
    public static Product createProduct(String name) {
        switch(name) {
            case "loan": return new Loan();
            case "stock": return new Stock();
            case "bond": return new Bond();
            default: throw new RuntimeException("No such product " + name);
        }
    }
}
```
```java
Product p = ProductFactory.createProduct("loan");
```
- 람다 표현식  
`Supplier<Product> loanSupplier = Loan::new;`  
`Loan loan = loanSupplier.get();`
```java
final static Map<String, Supplier<Product>> map = new HashMap<>();
static {
    map.put("loan", Loan::new);
    map.put("stock", Stoock::new);
    map.put("bond", Bond::new);
}
public static Product createProduct(String name) {
    Supplier<Product> p = map.get(name);
    if(p != null) return p.get();
    throw new IllegalArgumentException("No such product " + name);
}
```
=> 인수를 여러개 요하는 생성자이면, 사용하기 어렵다

### 9.3 람다 테스팅
#### 보이는 람다 표현식의 동작 테스팅
```java
public class Point {
    public final static Comparator<Point> compareByXAndThenY = 
        comparing(Point::getX).thenComparing(Point::getY);
}
```
```java
@Test
public void testComparingTwoPoints() throws Exception {
    Point p1 = new Point(10, 15);
    Point p2 = new Point(10, 20);
    int result = Point.compareByXAndThenY.compare(p1, p2);
    assertTrue(result < 0);
}
```
#### 람다를 사용하는 메서드의 동작에 집중하라
```java
public static List<Point> moveAllPointsRightBy(List<Point> points, int x) {
    return points.stream()
                    .map(p -> new Point(p.getX() + x, p.getY()))
                    .collect(toList());
}
```
```java
@Test
public void testMoveAllPointsRightBy() throws Exception {
    List<Point> points = Arrays.asList(new Point(5, 5), new Point(10, 5));
    List<Point> expectedPoints = Arrays.asList(new Point(15, 5), new Point(20, 5));
    List<Point> newPoints = Point.moveAllPointsRightBy(points, 10);
    assertEquals(expectedPoints, newPoints);
}
```
#### 복잡한 람다를 개별 메서드로 분할하기

### 9.4 고차원 함수 테스팅

