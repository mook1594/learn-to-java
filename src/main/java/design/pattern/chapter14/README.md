#### [GO TO BACK](../README.md)

# 14. 반복자 패턴
: 내부 구조를 노출하지 않으면서 객체 집합에 순차적으로 접근

### 배열
##### 요소 객체
```java
public class Fruit {
    private String name;
    
    public Fruit(String name) {
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }
}

public class Main {
    public static void main() {
        Fruit[] fruit = new Fruit() {[
            new Fruit("Apple"),
            new Fruit("Banana"),
            new Fruit("Berry"),
            new Fruit("Grape")
        ]};
    }
}
```
### 집합체

```java
import java.util.ArrayList;

public interface Aggregate {
	IteratorObject iterator();
}

public class Collection implements Aggregate {
    private List<Fruit> objs = new ArrayList();
    private int last = 0;
    
    public Fruit getObj(int id) {
        return this.objs.get(id);
    }
    
    public Fruit getLast() {
        return this.objs.get(last);
    }
    
    public void append(Fruit obj) {
        this.objs.add(obj);
        this.last++;
    }
    
    // 인터페이스 구현
    @Override
    public IteratorObject iterator() {
        return new IteratorObject(this);
    }
}

public class IteratorObject implements PloyIterator {
    private Aggregate aggregate;
    private int index = 0;
    
    public IteratorObject(Aggregate agg) {
        this.aggregate = agg;
    }
    
    public boolean isNext() {
        if(this.index >= this.aggregate.getLast()) {
            return false;
        }
        return true;
    }
    
    public Fruit next() {
        obj = this.aggregate.getObj(this.index);
        this.index_++;
        return obj;
    }
}

public class Main {
    public static void main() {
        menu = new Collection();
        menu.append(new Fruit("apple"));
        menu.append(new Fruit("banana"));
        menu.append(new Fruit("Berry"));
        
        loop = menu.iterator();
        
        while(loop.isNext()) {
            item = loop.next();
            item.getName();
        }
    }
}
```

### 반복자
- 객체에 순차 접근
##### iterator 인터페이스
- current
- key
- next
- rewind
- valid
- isNext

### 관련 패턴
#### [방문자 패턴](../chapter16/README.md)
#### [팩토리 메서드 패턴](../chapter3/README.md)
#### [복합체 패턴](../chapter9/README.md)
