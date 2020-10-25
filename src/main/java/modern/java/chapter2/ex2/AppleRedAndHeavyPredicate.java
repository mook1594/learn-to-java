package modern.java.chapter2.ex2;

import modern.java.chapter2.Apple;
import modern.java.chapter2.Color;

public class AppleRedAndHeavyPredicate implements ApplePredicate {
    @Override
    public boolean test(Apple apple) {
        return Color.RED.equals(apple.getColor()) && apple.getWeight() > 150;
    }
}
