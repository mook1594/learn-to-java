#### [GO TO BACK](../README.md)

# 7. 어댑터 패턴 (Adapter)
: 코드를 재사용하기 위해 구조를 변경하는 패턴

### 레거시 코드
- 레커시 코드를 변경해야할 경우들이 있다.
    - 환경에 대한 변화로 코드를 변경해야하는 경우
    - 재사용을 위해서 코드를 리팩토링 해야하는 경우
    - 인터페이스를 통일 시켜야 하는 경우
    - 오류가 있는 경우
    - 수정이 불가한 코드 (코드는 없고 dll 파일만 있을 경우)
    - 방어 코드를 넣는 경우

### 어댑터
- 어댑터: 변환을 처리하는 객체
- 어댑티: 변환을 받아 사용하는 객체
- 어댑터를 통해서 호환성을 맞춘다, 중개 행동 패턴
- 어댑터의 종류
    - 클래스 어댑터: 상속
    - 객체 어댑터: 구성
    
### 클래스 어댑터
- 다중상속을 받아 기존 클래스의 메서드를 다른 메서드로 대체하는 방법
- 장점: 클래스 어댑터는 하나의 클래스로 어댑터 객체를 처리할 수 있다.
- 단점: 계층적으로 클래스를 상속 할 때는 강한 결합이 형성된다.

### 객체 어댑터
- 타깃의 인터페이스를 어댑터로 구성하여 의존성관계로 연결
- 장점: 새로운 클래스로 생성, 유연성 확보
- 단점: 프로그램 코드가 증가

### 설계
#### [코드](./MathAdapter.java)

### 관련 패턴
#### [파사드 패턴](../chapter11/README.md)
#### [브리지 패턴](../chapter8/README.md)
#### [장식자 패턴](../chapter10/README.md)
