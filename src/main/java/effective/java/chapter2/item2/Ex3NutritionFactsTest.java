package effective.java.chapter2.item2;

public class Ex3NutritionFactsTest {
    public static void main(String[] args){
        Ex3NutritionFacts cocaCola = new Ex3NutritionFacts.Builder(240, 8)
                .calories(100)
                .sodium(35)
                .carbonhydrate(27)
                .build();
    }
}
