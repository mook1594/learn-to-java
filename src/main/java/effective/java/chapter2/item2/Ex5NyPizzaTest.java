package effective.java.chapter2.item2;

public class Ex5NyPizzaTest {
    public static void main(String[] args){
        Ex5NyPizza pizza = new Ex5NyPizza.Builder(Ex5NyPizza.Size.SMALL)
                .addTopping(Ex4Pizza.Topping.SAUSAGE)
                .addTopping(Ex4Pizza.Topping.ONION)
                .build();
    }
}
