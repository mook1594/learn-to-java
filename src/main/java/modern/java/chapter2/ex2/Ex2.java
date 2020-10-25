package modern.java.chapter2.ex2;

import modern.java.chapter2.Apple;
import modern.java.chapter2.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Ex2 {
    public static void main(String[] args){
        List<Apple> inventory = Arrays.asList(new Apple(80, Color.GREEN),
                                                new Apple(155, Color.GREEN),
                                                new Apple(120, Color.RED));

        List<Apple> heavyApples = filterApples(inventory, new AppleHeavyWeightPredicate());
        List<Apple> greenApples = filterApples(inventory, new AppleGreenColorPredicate());

        List<Apple> redApples = filterApples(inventory, new ApplePredicate() {
            @Override
            public boolean test(Apple apple) {
                return Color.RED.equals(apple.getColor());
            }
        });

        List<Apple> result = filterApples(inventory, (Apple apple) -> Color.RED.equals(apple.getColor()));
    }

    // for문 리펙토링
    public static List<Apple> filterApples(final List<Apple> inventory, final ApplePredicate p){
        List<Apple> result = inventory.stream()
                .filter(apple -> p.test(apple))
                .collect(Collectors.toList());
        return result;
    }

    public static List<Apple> filterApplesFor(List<Apple> inventory, ApplePredicate p){
        List<Apple> result = new ArrayList<>();
        for(Apple apple: inventory){
            if(p.test(apple)){
                result.add(apple);
            }
        }
        return result;
    }
}
