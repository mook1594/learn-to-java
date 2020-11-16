##### [GO TO LIST](../README.md)

# 아이템25. 톱레벨 클래스는 한 파일에 하나만 담으라

### 한파일에 여러클래스의 위험성
```java
public class Main {
    public static void main(String[] args) {
        System.out.println(Utensil.NAME + Dessert.NAME);
    }
}
```
```java
class Utensil {
    static final String NAME = "pan";
}
class Dessert {
    static final String NAME = "cake";
}
```
- javac Main.java Dessert.java 컴파일 에러 발생
- 컴파일러에 어느 소스파일이 먼저 올라오느냐에 따라 동작이 달라진다

### 해결책은?
- 정적 멤버 클래스 ([아이템24](../item24/README.md))
```java
public class Test {
    public static void main(String[] args) {
        System.out.println(Utensil.NAME, Dessert.NAME);
    }
    
    private static class Utensil {
        static final String NAME = "pan";
    }
    
    private static class Dessert {
        static final String NAME = "cake";
    }   
}
``` 
