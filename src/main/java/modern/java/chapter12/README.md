##### [GO TO BACK](../README.md)

# Chapter12. 새로운 날짜와 시간 API
> 자바8에서 새로운 날짜와 시간 라이브러리를 제공하는 이유  
> 사람이나 기계가 이해할 수 있는 날짜와 시간 표현 방법  
> 시간의 양 정의하기  
> 날짜 조작, 포맷팅, 파싱  
> 시간대와 캘린더 다루기  

### 12.1 LocalDate, LocalTime, Instant, Duration, Period 클래스
#### LocalDate와 LocalTime 사용
```java
LocalDate date = LocalDate.of(2017, 9, 21);
LocalTime time = LocalTime.of(13, 45, 20);
LocalDate date = LocalDate.parse("2017-09-17");
LocalTime time = LocalTime.parse("13:45:20");
```
#### 날짜와 시간 조합
```java
LocalDateTime dt1 = LocalDateTime.of(2017, Month.SEPTEMBER, 21, 13, 54, 20);
```
#### 기계의 날짜와 시간
```java
Instant.ofEpochSecond(3);
```
#### Duration과 Period
```java
Duration d1 = Duration.between(time1, time2);
Period p1 = Period.between(LocalDate.of(2017,9,11), LocalDate.of(2017,9,21));
```
- between: 두 시간 사이 간격
- from: 시간 단위
- of: 주어진 요소에서 간격
- parse: 문자열 싱
- addTo: 현재값을 복사생성하여 Temporal 객체 추가
- get: 현재 간격
- isNegative: 간격이 음수인지
- isZero: 간격이 0인지
- minus: 현재에서 뺀 복사본 생성
- multipleBy: 곱한 복사본 생성
- negated: 주어진 부호 반전 복사본
- plus: 더한 복사본
- subtractFrom: 지정된 Temporal객체 간격 제거

### 12.2 날짜 조정, 파싱, 포매팅
#### TemporalAdjusters
```java
LocalDate date1 = LocalDate.of(2014, 3, 18);
LocalDate date2 = date1.with(nextOrSame(DayOfWeek.SUNDAY));
LocalDate date3 = date2.with(lastDayOfMonth());
```
- dayOfWeekInMonth: 요일에 해당하는 날짜(음수는 월끝에서 거꾸로 계산)
- firstDayOfMonth: 현재달 첫번째 날짜
- firstDayOfNextMonth: 다음달 첫번째 날짜
- firstDayOfNextYear: 내년 첫번째 날짜 
- firstInMonth: 현재 날짜 첫번째 요일에 해당하는 날짜
- lastDayOfMonth:
- lastDayOfNextMonth:
- lastDayOfNextYear:
- lastDayOfYear
- lastInMonth
- next previous
- nextOrSame
- previousOfSame
```java
@FunctionalInterface
public interface TemporalAdjuster {
    Temporal adjustInto(Temporal temporal);
}
public NextWorkingDay implement TemporalAdjuster {
    @Override
    public Temporal adjustInto(Temporal temporal) {
        DayOfWeek dow = DayOfWeek.of(temporal.get(ChronField.DAY_OF_WEEK));
        int dayToAdd = 1;
        if(dow == DayOfWeek.FRIDAY) dayToAdd = 3;
        else if(dow == DayOfWeek.SATURDAY) dayToAdd = 2;
        return temporal.plus(dayToAdd, ChronoUnit.DAYS);
    }
}
```
#### 날짜와 시간 객체 출력과 파싱
- DateTimeFormatter
```java
DateTimeFormatter italianFormatter = new DateTimeFormatterBuilder()
    .appendText(ChronoField.DAY_OF_MONTH)
    .appendLiteral(". ")
    .appendText(ChronoField.MONTH_OF_YEAR)
    .appendLiteral(" ")
    .appendText(ChronoField.YEAR)
    .parseCaseInsensitive()
    .toFormatter(Locale.ITALIAN);
```

### 12.3 다양한 시간대와 캘린더 사용 방법
#### 시간대 사용하기
- ZonedId
#### UTC/Greenwich 기준의 고정 오프셋
- ZoneOffset
