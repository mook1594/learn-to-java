package modern.java.chapter2.ex2;

import modern.java.chapter2.Apple;

public class AppleHeavyWeightPredicate implements ApplePredicate {
    @Override
    public boolean test(Apple apple){
        return apple.getWeight() > 150;
    }
}
