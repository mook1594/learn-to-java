#### [GO TO BACK](../README.md)

# 21. 메멘토 패턴 (Memento)
: 객체의 상태를 저장하여 이전 상태로 복구

### 상태 저장
- 세터와 게터를 가지고 상태값을 읽고 변경한다.
#### 상태 이력
- 체크 포인트를 설정해 undo기능을 구현할 수 있다

### 캡슐화
- public은 캡슐화 정책 위반
- 제약을 해결하도록 저장, 복원 작업을 처리하는 중간 매개체 constraintSolver

### 메멘토
- [메멘토 클래스](./Memento.java)
- [원조본(originator)](./Originator.java): 객체와 메멘토의 중간자 역할
- [케어테이커(caretaker)](./CareTaker.java): 다수의 메멘토를 관리하고 보관
- [사용](./Main.java)

### 관련 패턴
#### [명령 패턴](../chapter15/README.md)
#### [프로토타입 패턴](../chapter6/README.md)
#### [상태 패턴](../chapter20/README.md)
#### [반복자 패턴](../chapter14/README.md)
