#### [GO TO BACK](../README.md)

# 1. 팩토리 패턴

### 문제점
- 객체 생성에는 의존성 문제가 발견됨
- new 키워드로 직접 생성하는것은 강력한 결합 관계 코드가 된다.
- new를 사용하지 않고는 생성못하나?
- 의존 관계를 느슨하게 하기위해 팩토리 패턴 사용
- 생성을 위임하여 공장에서 생성하도록 함.
    - 클래스로 분리
    - 메서드로 분리
    

### 팩토리 패턴
#### [코드](./Hello.java)

### 장점
- 생성관련된 모든 처리를 별도 클래스 객체로 위임
- 유연성과 확장성 개선
- 어떤 객체를 생성할지 모를 초기 단계에 유용

### 단점
- 새로운 별도의 클래스 필요

### 관련 패턴
- [팩토리 메서드 패턴](../chapter3/README.md)
- [추상 팩토리 패턴](../chapter4/README.md)