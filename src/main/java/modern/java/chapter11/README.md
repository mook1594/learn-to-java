##### [GO TO BACK](../README.md)

# Chapter11. null 대신 Optional 클래스
> null 참조의 문제점과 null을 멀리해야 하는 이유  
> null 대신 Optional: null로부터 안전한 도메인 모델 재구현하기  
> Optional 활용: null 확인 코드 제거하기  
> Optional에 저장된 값을 확인하는 방법  
> 값이 없을 수도 있는 상황을 고려하는 프로그래밍   

### 11.1 값이 없는 상황을 어떻게 처리할까?
- NullPointerException이 발생
#### 보수적인 자세로 NullPointerException 줄이기
- null을 피하려는 if문을 무지하게 추가하고, 반복 코드가 증가
#### null 때문에 발생하는 문제
- 에러의 근원
- 코드를 어지럽힘
- 아무 의미가 없음
- 자바 철학에 위배됨: 포인터를 숨긴 자바. BUT null pointer가 살아있음
- 형식 시스템에 구명
#### 다른 언어는 null 대신 무얼 사용하나?
- 안전 연산자 (?.) 를 도입해서 문제 해결

### 11.2 Optional 클래스 소개
- Optional<Car>
- 값이 있으면 있는데로, 없으면 Optional.empty()

### 11.3 Optional 적용 패턴
#### Optional 객체 만들기
- 빈 Optional: `Optional.empty();`
- null이 아닌 값으로 Optional 만들기: `Optional.of(car);`
- null값으로 Optional 만들기: `Optional.ofNullable(car);` 
#### 맵으로 Optional의 값을 추출하고 변환하기
```java
String name = null;
if(insurance != null) {
    name = insurance.getName();
}

Optional<Insurance> optInsurance = Optional.ofNullable(insurance);
Optional<String> name = optInsurance.map(Insurance::getName);
```
#### flatMap으로 Optional 객체 연결
```java
Optional<Person> optPerson = Optional.of(person);
Optional<Person> name = 
    optPerson.flatMap(Person::getCar) 
            .flatMap(Car::getInsurance) // 여기서 map은 불가. Optional<Optional<Car>> 
            .map(Insurance::getName)
            .orElse("Unknown");
```
#### Optional 스트림 조작

