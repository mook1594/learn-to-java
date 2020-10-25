package modern.java.chapter2.ex2;


import modern.java.chapter2.Apple;
import modern.java.chapter2.Color;

public class AppleGreenColorPredicate implements ApplePredicate {
    @Override
    public boolean test(Apple apple) {
        return Color.GREEN.equals(apple.getColor());
    }
}
