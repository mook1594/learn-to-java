#### [GO TO BACK](../README.md)

# 6. 프로토타입 패턴 (ProtoType)
: new 키워드를 사용하지 않고 객체를 복제해 생성하는 패턴 

### 생성
#### 객체 생성
- new 키워드로 인스턴스화 하여 객체 생성하고 메모리에 적재됨
#### 객체의 상태값
- 상태값을 가진 데이터가 있으면 같은 객체를 여러번 생성하게됨.

### 복사
: new 키워드가 아닌 복사로 객체를 생성하는 또 한가지 방법
#### 복사의 종류
- 얕은 복사: 포인터를 복사
- 깊은 복사: 실제 값까지 복사, Cloneable 인터페이스 사용

### 프로토타입 패턴
: 이미 생성된 객체를 복제한다. 복제할 때는 생성자가 실행되지 않는다.
- 대량 생산에 효과적으로 생성

### 관련 패턴
#### [플라이웨이트 패턴](../chapter12/README.md)
#### [메멘토 패턴](../chapter21/README.md)
#### [복합체 패턴](../chapter9/README.md)
#### [장식자 패턴](../chapter10/README.md)
#### [명령 패턴](../chapter15/README.md)

