##### [GO TO LIST](../README.md)

# 아이템36. 비트 필드 대신 EnumSet을 사용하라

### 비드 필드 열거 상수
``` java
public class Text {
  public static final int STYLE_BOLD = 1 << 0;
  public static final int STYLE_ITALIC = 1 << 1;
  public static final int STYLE_UNDERLINE = 1 << 2;
  public static final int STYLE_STRIKETHROUGH = 1 << 3;
  
  // 매개변수 styles는 0개 이상의 STYLE_ 상수를 비트별 OR한 값이다.
  public void applyStyles(int styles) { ... }
}

text.applyStyles(STYLE_BOLD | STYLE_ITALIC);
```

### 비트 필드 단점
- 상수 구분에 대한 문제
- 원소를 구분하기 까다로움

### EnumSet을 이용한 기능 구현
``` java
public class Text {
  public enum Style { BOLD, ITALIC, UNDERLINE, STRIKETHROUGH }
  
  public void applyStyles(Set<Style> styles) { ... }
}

text.applyStyles(EnumSet.of(Style.BOLD, Style.ITALIC);
```

