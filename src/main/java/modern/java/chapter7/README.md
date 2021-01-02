##### [GO TO BACK](../README.md)

# Chapter7. 병렬 데이터 처리와 성능
> 병렬 스트림으로 데이터 병렬 처리하기  
> 병렬 스트림의 성능 분석  
> 포크/조인 프레임워크  
> Spliterator로 스트림 데이터 쪼개기

### 7.1 병렬 스트림
#### 병렬 스트림
- parallelStream() 호츨하면 병렬스트림이 생성된다.
- 스트림 요소를 여러 청크로 분할한 스트림
- 두 수를 더하는 무한스트림 예제
- 기존 java
```java
public long iterativeSum(long n) {
    long result = 0;
    for(long i = 1L; i <= n; i++) {
        result += i;
    }
    return result;
}
```
- 스트림
```java
public long sequentialSum(long n) {
    return Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .reduce(0L, Long::sum);
}
```
- 병렬스트림
```java
public long parallelSum(long n) {
    return Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .parallel() // 스트림을 병렬 스트림으로 변환
                .reduce(0L, Long::sum);
}
```

##### 병렬 스트림에서 병렬 작업 수행하는 스레드는 어디서 나온 아이 인가?
- 내부적으로 ForkJoinPool을 사용하여 프로세서 수, Runtime.getRuntime().availableProcessors()에 반환되는 값을 쓰레드로 갖는다.
- 이 값을 특정값으로 지정할 수 없고, 기본값을 사용하는걸 권장

#### 스트림 성능 측정
##### JMH(java Microbenchmark Harness) 활용
- pom.xml
```xml
<dependency>
    <groupId>org.openjdk.jmh</groupId>
    <artifactId>jmh-core</artifactId>
    <version>1.17.4</version>
</dependency>
<dependency>
    <groupId>org.openjdk.jmh</groupId>
    <artifactId>jmh-generator-annprocess</artifactId>
    <version>1.17.4</version>
</dependency>
```
```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-shade-plugin</artifactId>
            <executions>
                <execution>
                    <phase>package</phase>
                    <goals><goal>shade</goal></goals>
                    <configuration>
                        <finalName>benchmarks</finalName>
                        <transformers>
                            <transformer implementation=
                                "org.apache.maven.plugins.shade.resource.ManifestResourceTransformer">
                                <mainClass>org.openjdk.jmh.Main</mainClass>                            
                            </transformer>
                        </transformers>
                    </configuration>
                </execution>
            </executions>
        </plugin>
    </plugins>
</build>
```
```java
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Fork(2, jvmArgs={"-Xms4G", "-Xmx4G"}) // 4GB 힙 공간을 제공한 환경애서 두번 벤치마크를 수행해 결과의 신뢰성 확보
public class parallelStreamBenchmark {
    private static final long N = 10_000_000L;
    
    @Benchmark
    public long sequentialSum() {
        return Stream.iterate(1L, i -> i + 1).limit(N)
                    .reduce(0L, Long::sum);
    }
    
    @TearDown(Level.Invocation) // 매 번 벤치마크 실행 후 GC 동작 시도 (같은 상황)
    public void tearDown() {
        System.gc();
    }
}
```
- 반복 결과로 박싱된 객체가 만들어지므로, 숫자를 더하려면 언박싱을 해야한다
- 반복 작업은 병렬로 수행할 수 있는 독립 단위로 나누기가 어렵다 (iterate 본질적으로 순차적이기 때문에)
- 병렬 스트림은 올바르개 사용하는지 확인해야한다. 잘못하면 더 느려질 수 있기 떄문.

#### 병렬 스트림의 올바른 사용방법
- 공유된 상ㅊ태를 바꾸는 알고리즘을 사용하는것. 스레드에서 동시접근으로 데이터 레이스 문제 발생

#### 병렬 스트림을 효과적으로 사용하는 방법
- 직접 성능측정 해라 (병렬이 언제나 순차보다 빠른 것이 아님)
- 박싱을 주의하라 (자동 박싱과 언박싱은 성능을 크게 저하 시킴) - IntStream, LongStream, DoubleStream 이용할 것
- limit, findFirst 순서에 의존하는 연산은 병렬에서는 비싼 비용을 치러야한다.
- 전체 파이프라인 연산 비용을 고려하라. 요소수 N, 처리비용Q 일때 스트림 파이프라인 처리비용은 N*Q. Q가 커진다면 병렬에 효과적
- 소량의 데이터에서는 병렬 스트림이 도움 되지 않는다.
- ArrayList, IntStream 분해성 우수, HashSet, TreeSet 좋음. LinkedList, Stream.iterate 나쁨

### 7.2 포크/조인 프레임워크

#### RecursiveTask 활용
스레드 풀을 이용하려면 RecursiveTask<R>의 서브클래스를 만들어야한다.
``` java
protected abstract R compute();

@Override
if(태스크가 충분히 작거나 더 이상 분할할 수 없으면) {
    순차적으로 태스크 계산 
} else {
    태스크를 두 서브태스크로 분할 
    태스크가 다시 서브태스크로 분할되도록 이 메서드를 재귀적으로 호출함
    모든 서브태스크의 연산이 완료될 때까지 기다림
    각 서브태스크의 결과를 합침
}
```
- 포크/조인 프레임워크를 이용해서 병렬 합계 수행
```java
public class ForkJoinSumCalculator extends java.util.concurrent.RecursiveTask<Long> {
    private final long[] numbers;
    private final int start;
    private final int end;
    public static final long THRESHOLD = 10_000;
    
    public ForkJoinSumcalculator(long[] numbers) {
        this(numbers, 0, numbers.length);
    }
    private ForkJoinSumCalculator(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }
    @Override
    protected Long compute() {
        int length = end - start;
        if(length <= THRESHOLD) {
            return computeSequentially();
        }
        ForkJoinSumCalculator leftTask = new ForkJoinSumCalculator(numbers, start, start + length/2);
        leftTask.fork();
        ForkJoinSumcalculator rightTask = new ForkJoinSumCalculator(numbers, start + length/2, end);
        Long rightResult = rightTask.compute();
        Long leftResult = leftTask.join();
        return leftResult + rightResult;
    }
    private long computeSequentially() { // 더 분할 할 수 없을 대 계산하는 단순 알고리즘
        long sum = 0;
        for (int i = start; i < end; i++) {
            sum += numbers[i];
        }
        return sum;
    }
}
```
```java
public static long forkJoinSum(long n) {
    long[] numbers = LongStream.rangeClosed(1, n).toArray();
    ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbers);
    return new ForkJoinPool().invoke(task);
}
```
