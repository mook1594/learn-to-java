#### [GO TO BACK](../README.md)

# 16. 방문자 패턴 (Visitor)
: 공통된 객체의 데이터 구조와 처리를 분리하는 패턴  
: Ex) KIMES 손님(방문자)은 QR 에다가 자기만의 기능을 심어두고 부스(원소)는 방문자가 어떤 기능을 갖던 상관없이 QR을 찍어주면서 자기가 가진 데이터를 전달한다.

### 데이터 처리
- 캡슐화
- 정보 은닉
- 행위 추가 (데이터 연산)
- 데이터 접근 (객체 관계)

### 분리
- 공통 로직을 분리
```java
public class Cart extends Product {
	private String name;
	private int price;
	private int num;
	public Cart(String name, int price, int num) {
		this.name = name;
		this.price = price;
		this.num = num;
    }
    
    public int getTax(int tax) {
		return (this.price * this.num) * tax / 100;
    }
    
    public String list() {
		StringBuilder sb = new StringBuilder(this.name);
		sb.append(", 수량=").append(this.num);
		sb.append(", 가격=").append(this.num * this.price);
		return sb.toString();
    }
}

public class Main {
	public static void main(String... args) {
		Cart cart = new Cart("컵라면", 900, 2);
		cart.list();
    }
}
```

### 원소 객체
#### [원소 코드](./Visitable.java)
#### [구현 코드](./Cart.java)
#### [방문자 코드](./Visitant.java)
#### [실행 코드](./Main.java)

### 관련 패턴
#### [반복자 패턴](../chapter14/README.md)
#### [복합체 패턴](../chapter9/README.md)
#### [인터프리터 패턴](../chapter24/README.md)
