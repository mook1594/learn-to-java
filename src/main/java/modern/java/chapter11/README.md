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
```java
public Set<String> getCarInsuranceNames(List<Person> persons) {
    return persons.stream()
        .map(Person::getCar)
        .map(optCar -> optCar.flatMap(Car::getInsurance))
        .map(optIns -> optIns.map(Insurance::getName))
        .flatMap(Optional::stream)
        .collect(toSet());
}
```
#### 디폴트 액션과 Optional 언랩
- get(): 기본값, 안전하지 않음, NoSuchElementException 에러
- orElse(): 기본값
- orElseGet(): 값이 없을때만 로드
- orElseThrow: 예외 발생
- ifPresent: 동작ㅇㄹ 실행, 갑이없으면 실행하지 않음
- ifPresentOrElse

#### 두 Option 합치
```java
public Optional<Insurance> nullSafeFindCheapestInsurance(Optional<Person> person, Optional<Car> car) {
    if(person.isPresent() && car.isPresent()) {
        return Optional.of(findCheapestInsurance(person.get(), car.get()));
    }
    return Optional.empty();
}
```
```java
public Optional<Insurance> nullSafeFindCheapsetInsurance(Optional<Person> person, Optional<Car> car) {
    return person.flatMap(p -> car.map(c -> findCheapestInsurance(p, c)));
}
```

#### 필터로 특정값 거르기
```java
public String getCarInsuranceName(Optional<Person> person, int minAge) {
    return person.filter(p -> p.getAge() >= minAge)
                .flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getname)
                .orElse("Unknown");
}
```
- filter: 일치하면 반환, 없으면 빈배열
- flatMap: 값이 존재하면 함수 결과 반환, 없으면 빈 옵션

### 11.4 Optional을 사용한 실용 에제
```java
public int readDuration(Properties props, String name) {
    String value = props.getProperty(name);
    if(value != null) {
        try {
            int i = Integer.parseInt(value);
            if(i > 0) {
                return i;
            }
        } catch(NumberFormatException nfe) { }
    }
    return 0;
}
```
```java
public int readDuration(Properties props, String name) {
    return Optional.ofNullable(props.getProperty(name))
        .flatMap(OptionalUtility::stringToInt)
        .filter(i -> i > 0)
        .orElse(0)
}
```
