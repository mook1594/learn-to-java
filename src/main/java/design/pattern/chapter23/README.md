#### [GO TO BACK](../README.md)

# 23. 전략 패턴 (Strategy)
: 해결해야 하는 목적을 알고리즘 객체로 분리하여 적용 하는 기법

### 문제 해결 전략
- 전략: 무엇을 할지 정의
- 전술: 어떻게 할지 정의

### 알고리즘
- 주어진 문제를 해결
- 해결 방법을 변경하면서 리팩터링 (전술 변경) 
- 알고리즘 부분은 분리하여 확장에 용의하도록 함
- 인터페이스를 사용: 호환성을 위해 약속
```java
public interface Weapon {
    void attack();
}
```
- 구체화
```java
public class Knife implements Weapon {
    @Override
    public void attack() {
        "칼로 공격";
    }
}
public class Gun implements Weapon {
    @Override
    public void attack() {
        "총으로 공격";
    }
}
```

### 추상화
```java
public abstract class Strategy {
    protected Weapon delegate;
    
    public void setWeapon(Weapon weapon) {
        this.delegate = weapon;
    }
    abstract public void attack();
}

public class Charactor extends Strategy {
    public void attack() {
        if(this.delegate == null) {
            "맨손";
        } else {
            this.delegate.attack();
        }
    }
}
```

### 적용 사례
- 정렬 적용 사례: 여러 알고리즘을 변경하면서 가능
- 통신 적용 사례: LTE, WIFI 교체

### 관련 패턴 
#### [플라이웨이트 패턴](../chapter12/README.md)
#### [추상 팩토리 패턴](../chapter4/README.md)
#### [상태 패턴](../chapter20/README.md)
