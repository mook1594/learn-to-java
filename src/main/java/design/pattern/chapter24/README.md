#### [GO TO BACK](../README.md)

# 24. 인터프리터 패턴 (Interpreter)
: 언어적 문법을 표현하는 패턴

### 언어 설계
- BNF 표기법: ::=

### 해석자 패턴 구조
- 추상 구문 트리 인터페이스 (Abstract Expression)
- 종료 기호 (Terminal Expression)
- 비종료 기호 (Non-Terminal Expression)
- 해석기 정보 (Context)
- 문장을 나타내는 추상 구문 트리 (Client)

### 처리계
- [Context 클래스](./Context.java)

### 중간 코드
- 산술 표현: 중위표기법, 후휘 표기법, 전위 표기법

### 해석
#### [복합체 패턴](../chapter9/README.md)
#### [플라이웨이트 패턴](../chapter12/README.md)
#### [방문자 패턴](../chapter16/README.md)
