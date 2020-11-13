##### [GO TO LIST](../README.md)
 
 # 아이템23. 태그 달린 클래스보다는 클래스 계층구조를 활용하라

### 태그 달린 클래스 예
##### [Figure](./Figure.java)
- 태그 달린 클래스에는 단점이 많다.
- 열거타입, 태그필드, switch등 쓸데없는 코드 증가
- 이런 코드들은 가독성이 나쁘고 오류내기 쉽고 비효율적이다.

### 태그 달린 클래스를 계층 구조로 변경하는 방법
- 계층구조의 루트가 될 추상클래스를 정의한다.
- 태그에 따라 동작이 달라지는 메서드는 추상메서드로 선언.
- 태그와 상관없이 동작하는 메서드는 루트 클래스에 일반 메서드로 추가.
- 하위클래스에서 공통으로 사횽하는건 루트클래스 일반 메서드로 추가.

```java
abstract class Figure {
    abstract double area();
}

class Circle extends Figure {
    final double radius;

    Circle(double radius) { this.radius = radius; }

    @Override double area() { return Math.PI * (radius * radius); }
}

class Rectangle extends Figure {
    final duoble length;
    final double width;
   
    Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }
    
    @Override double are() { return length * width; }
}
```
- 쓸데 없는 코드를 사라졌다.
- final을 활용하므로써 모든 필드를 초기화 했는지 컴파일러가 확인 해준다.
- 만약 정사각형이 추가된다면
```java
class Square extends Rectangle {
    Square(double side) {
        super(side, side);
    }
}
```
