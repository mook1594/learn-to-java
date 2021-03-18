#### [GO TO BACK](../README.md)

# 4. 추상 팩토리 패턴

### 팩토리 메서드
#### 패턴의 유사성
- 팩토리 패턴, 팩토리 메서드 패턴, 추상 팩토리 패턴
- 팩토리 패턴 vs 팩토리 메서드 패턴: 추상화 (상속)
- 팩토리 메서드 패턴 vs 추상 팩토리 패턴: 추상화된 그룹을 형성하고 관리 

### 그룹
#### 추상 클래스
- 팩토리 메서드는 팩토리 패턴을 추상화 함으로 클래스의 선언과 구현부를 분리
- 팩토리 메서드는 추상ㄹ화 클래스와 하위 클래스를 1개로만 구성
- 추상 팩토리는 다형성을 적극적으로 활용하여 다수의 하위클래스를 생성하고 관리
#### 공장
- 공장의 개념을 추상화
- 공장은 생산품을 만들어내는데, 하나의 군으로 묶인 그룹들을 공장으로 취급

### 팩토리 그룹
#### [코드](./Main.java)

### 패턴 결합
#### 새로운 푸훔
- Engine을 추가한다고 했을때, 모든 클래스에 Engine관련 코드를 삽입해야하는 수고로움이 있다.

### 장점과 단점
#### 장점
- 그룹간에 영향이 없음, 구현이 달라도 영향이 없음

#### 단점
- 새로운 종류군을 추가하는것은 쉽지않다.
- 계층의 크기가 커지고 복잡해 진다.

### 관련 패턴
#### [빌더 패턴](../chapter5/README.md)
#### [팩토리 메서드 패턴](../chapter3/README.md)
#### [복합체 패턴](../chapter9/README.md)
#### [싱글턴 패턴](../chapter2/README.md)