##### [GO TO LIST](../README.md)

# 2장. JUnit 핵심 들여다보기

### JUnit 핵심
- Assert: 테스트 하려는 조건을 명시. 만족하지 못하면 에러 발생
- Test: 하나의 테스트를 의미
- Suite: 여러 테스트를 하나로 묶는 수단
- Runner: 테스트를 실행

```java
@RunWith(value=Parameterized.class)
public class ParameterizedTest {
    private double expected;
    private double valueOne;
    private double valueTwo;
    
    @Parameters
    public static Collection<Integer[]> getTestParameters() {
        return Arrays.asList(new Integer[][] {
            {2, 1, 1},
            {3, 2, 1},
            {4, 3, 1},
        });
    }
    
    public ParameterizedTest(double expected, double valueOne, double valueTwo) {
        this.expected = expected;
        this.valueOne = valueOne;
        this.valueTwo = valueTwo;
    }
    
    @Test
    public void sum() {
        Calculator calc = new Calculator();
        assertEquals(expected, calc.add(valueOne, valueTwo), 0);
    }
}   
```

### JUnit 테스트 러너
- JUnit4
- Parameterized: 여러 파라미터 반복 수행
- Suite: 복수 테스트
- @RunWith로 지정

##### JUnitCore 퍼사드
- 결과 취합, 통계 등 제공

##### 테스트 러너 customizing
- Runner클래스를 확장

### Suite로 테스트 묶기
```java
@RunWith(value=org.junit.runners.Suite.class)
@SuiteClasses(value={FolderConfigurationTest.class,
                     FileConfigurationTest.class})
public class FileSystemConfigurationTestSuite {
}
```

##### Suite의 Suite 만들기
```java
public class TestCaseA {
    @Test
    public void testA1() {
        ...
    }
}

public class TestCaseB {
    @Test
    public void testB1() {
        ...
    }
}

@RunWith(value=Suite.class)
@SuiteClasses(value = {TestSuiteA.class})
public class TestSuiteA {
    ...
}

@RunWith(value=Suite.class)
@SuiteClasses(value = {TestSuiteB.class})
public class TestSuiteB {
    ...
}

@RunWith(value=Suite.class)
@SuiteClasses(value = {TestSuiteA.class, TestSuiteB.class})
public class MasterTestSuite {
    ...
}
```
