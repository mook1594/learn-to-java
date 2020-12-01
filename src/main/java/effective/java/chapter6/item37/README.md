##### [GO TO LIST](../README.md)

# 아이템37. ordinal 인덱싱 대신 EnumMap을 사용하라

### ordinal() 사용 예
```java
class Plant {
    enum LifeCycle { ANNUAL, PERENNIAL, BIENNIAL }

    final String name;
    final LifeCycle lifeCycle;
    
    Plant(String name, LifeCycle lifeCycle) {
        this.name = name;
        this.lifeCycle = lifeCycle;
    }

    @Override public String toString() {
        return name;
    }
}
``` 
``` java
Set<Plant>[] plantByLifeCycle = 
    (Set<Plant>[]) new Set[Plant.LifeCycle.values().length];
for(int i = 0; i < plantsByLifeCycle.length; i++)
    plantsByLifeCycle[i] = new HashSet<>();

for(Plant p : garden)
    plantsByLifeCycle[p.lifeCycle.ordinal()].add(p);

for(int i = 0; i < plantsByLifeCycle.length; i++) {
    System.out.printf("%s: %s\n",
        Plant.LifeCycle.values()[i], plantsByLifeCycle[i]);
}
```
- 문제가 많은 코드.
- 배열은 제네릭과 호환되지 않으니 비검사 형변환을 해야함
- 인덱스 의미에 대한걸 표시해야한다.
- enum 정수값을 보증해야한다.

### EnumMap을 사용해서 문제점 해결
```java
Map<Plant.LifeCycle, Set<Plant>> plantsByLifeCycle = 
    new EnumMap<>(Plant.LifeCycle.class);
for(Plant.LifeCycle lc : Plant.LifeCycle.values())
    plantsCyLifeCycle.put(lc, new HashSet<>());
for(Plant p : garden)
    plantsByLifeCycle.get(p.lifeCycle).add(p);
```
- 형변환을 사용하지 않아도 된다.
- 인덱스 계산도 필요없다.
- ordinal은 내부 배열을 사용하는 걸 제거하므로서 이점이 생긴다.

### Stream을 활용하면
```java
System.out.println(Arrays.stream(garden))
    .collect(groupingBy(p -> p.lifeCycle,
        () -> new EnumMap<>(LifeCycle.class), toSet()));
```

### 다른 예제를 보자
```java
public enum Phase {
    SOLID, LIQUID, GAS;

    public enum Transition {
        MELT, FREEZE, BOIL, CONDENSE, SUBLIME, DEPOSIT;
    
        // 행은 from의 ordinal을, 열은 to의 ordinal을 인덱스로 쓴다.
        private static final Transition[][] TRANSITIONS = {
            { null, MELT, SUBLIME },
            { FREEZE, null, BOIL },
            { DEPOSIT, CONDENSE, null }
        };
    
        // 다른상태로 전이 
        public static Transition from(Phase from, Phase to) {
            return TRANSITIONS[from.ordinal()][to.ordinal()];
        }
    }
}


```
- ArrayIndex, NullPointer Exception 위험

### EnumMap을 사용하면?
```java
public enum Phase {
    SOLD, LIQUID, GAS;
    
    public enum Transition {
        MELT(SOLD, LIQUID), FREEZE(LIQUID, SOLID),
        BOIL(LIQUID, GAS), CONDENSE(GAS, LIQUID),
        SUBLIME(SOLID, GAS), DEPOSIT(GAS, SOLID);
    }

    private final Phase from;
    private final Phase to;

    Transition(Phase from, Phase to) {
        this.from = from;
        this.to = to;
    }

    private static final Map<Phase, Map<Phase, Transition>>
        m = Stream.of(values()).collect(groupingBy(t -> t.from, 
            () -> new EnumMap<>(Phase.class),
            toMap(t -> t.to, t -> t,
               (x, y) -> y, () -> new EnumMap<>(Phase.class))));
   
    public static Transition from(Phase from, Phase to) {
        return m.get(from).get(to);
    }
}
```
- 유지보수 측면에서도 EnumMap이 더 간편하다.
```java
public enum Phase {
    SOLD, LIQUID, GAS, PLASMA;
    
    public enum Transition {
        MELT(SOLD, LIQUID), FREEZE(LIQUID, SOLID),
        BOIL(LIQUID, GAS), CONDENSE(GAS, LIQUID),
        SUBLIME(SOLID, GAS), DEPOSIT(GAS, SOLID),
        IONIZE(GAS, PLASMA), DEIONIZE(PLASMA, GAS);
    }
    ... // 코드 이하 동일 
}
```

### 정리
- enum에서 배열의 인덱스를 얻기 위해서 ordinal을 쓰는 것이 일반적으로 좋지 않다.
- EnumMap<>, 다차원은 EnumMap<..., EnumMap<...>>으로 표현하라.
