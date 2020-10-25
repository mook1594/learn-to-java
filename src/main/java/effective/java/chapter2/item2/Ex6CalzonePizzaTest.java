package effective.java.chapter2.item2;

public class Ex6CalzonePizzaTest {
    public static void main(String[] args){
        Ex6CalzonePizza pizza = new Ex6CalzonePizza.Builder()
                .addTopping(Ex4Pizza.Topping.HAM)
                .sauceInside()
                .build();
    }
}
