##### [GO TO LIST](../README.md)

# 아이템39. 명명 패턴보다 애너테이션을 사용하라.

### 명명 패턴에 단점
- 오타에 취약하다
- 올바른 요소에 사용되라라는 법은 없다.
- 정해진 룰을 따라하도록하는 마땅한 방법이 없다.

### 애너테이션 사용
- 애너테이션은 이 문제를 해결해준다.
```java
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Test {
}
```
- 메타애너테이션: 애너테이션을 만드는데 사용되는 에너테이션
- Retention 유지되어야하는 표시
- Target 선언되어야하는 요소 (매서드, 클래스, 필드 등)

### 애너테이션을 처리하는 코드
- @Test 애너테이션을 달았을때 동작하도록 처리하는 코드 
```java
public class RunTests {
    public static void main(String[] args) throws Exception {
        int test = 0;
        int passed = 0;
        Class<?> testClass = Class.forName(args[0]);
        for(Method m : testClass.getDeclaredMethods()) {
            if(m.isAnnotationPresent(Test.class)) {
            	tests++;
                try{
                	m.invoke(null);
                    passed++;
                } catch(InvocationTargetException wrappedExc) {
                	Throwable exc = wrappedExc.getCause();
                    System.out.println(m + "실패:" + exc);
                } catch(Exception exc) {
                	System.out.println("잘못 사용한 @Test" + n);
                }
            }
            System.out.printf("성공: %d, 실패: %d%n", passed, test - passed);
        }
    }
}
```
- isAnnotationPresent가 실행할 메서드를 찾아주는 메서드.

### 특정 예외를 던저야 성공하는 테스트
```java
@Retension(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ExceptionTest {
    Class<? extends Throwable> value();
}
```
```java
if(m.isAnnotationPresent(ExceptionTest.class)) {
    tests++;
    try {
        m.invoke(null);
        System.out.println("테스트 %s 실패: 예외를 던지지 않음 %n", m);
    } catch(IvocationTargetException wrappedEx) {
        Throwable exc = wrappedEx.getCause();
        Class<? extends Throwable> excType =
            m.getAnnotation(ExceptionTest.class).value();
        if(excType.isInstance(exc)) {
            passed++;
        } else {
            System.out.println("테스트 %s 실")
        }
    }
}
```

### 배열 매개 변수를 받는 애너테이션
```java
@Retention(RetentionPolicy.RUNTIME) 
@Target(ElementType.METHOD)
public @interface ExceptionTest {
    Class<? extends Throwable>[] value();
}
```
```java
@ExceptionTest({IndexOutOfBoundsException.class, NullPointerException.class})
public static void doublyBad() {
}
```
```java
if(m.isAnnotationPresent(ExceptionTest.class)){
    tests++;
    try {
        m.invoke(null);
        System.out.println("테스트 %s 실패: 예외를 던지지 않음");
    } catch(Throwable wrappedExc) {
        Throwable exc = wrappedExc.getCause();
        int oldPassed = passed;
        Class<? extends Throwable>[] excTypes =
            m.getAnnotation(ExceptionTest.class).value();
        for(Class<? extends Throwable> excType : excTypes) {
            if(excType.isInstance(exc)) {
                passed++;
                break;
            }   
        }   
        if(passed == oldPassed)
            System.out.println("테스트 실패")
    }
}
```

