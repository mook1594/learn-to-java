##### [GO TO LIST](../README.md)

# 아이템5. 자원을 직접 명시하지 말고 의존 객체 주입을 사용하라
> 싱글턴, 정적 유틸리티 클래스 에서 stateless 를 유지하는게 좋다. 만약 다른 필요한 자원이 있다면 
이 자원이나 팩터리를 생성자에 넘겨받는다. 이러한 의존객체주입은 클래스의 유연성, 재사용성, 테스트 용이성을 높여준다.

-----

### 정적 유릴리티 코드를 잘못 사용한 예 (item4 이용)
```$xslt
public class SpellChecker {
    private static final Lexicon dictionary = ...;
    
    private SpellChecker() {} // 객체 생성 방지
    
    public static boolean isValid(String word) { ... }
    public static List<String> suggestions(String type) { ... }
}
```

### 싱글턴을 잘못 사용한 예 (item3 이용)
```$xslt
public class SpellChecker {
    private final Lexicon dictionary = ...;
    
    private SpellChecker(...) {}
    public static SpellChecker INSTANCE = new SpellChecker(...);
    
    public boolean isValid(String word) { ... }
    public List<String> suggestions(String type) { ... } 
}
```
- 두 코드의 단점은 유틸리티 내에서 생성한 하나만의 사전을 사용하여 유연하지 않고 여러 사전을 테스트 하기 어렵다.

### 위 단점을 보완한 의존 객체를 주입한다.
```$xslt
public class SpellChecker {
    public final Lexicon dictionary;
    
    public SpellChecker(Lexicon dictionary) {
        this.dictionary = Objects.requireNonNull(dictionary);
    }
    
    public boolean isValid(String word) { ... }
    public List<String> suggestions(String type) { ... }
}

```
- 자원이 몇 개든 의존 관계가 어떻든 상관없이 사용할 수 있으며 불변을 보장한다.

### 비슷한 동작을 패턴화하여 사용할 수 있다.
- 팩터리 메서드 패턴
- 자바 8의 Supplier<T> (한정적 와일드 카드 타입 item31)
