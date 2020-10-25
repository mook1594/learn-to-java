package modern.java.chapter3;

import java.util.ArrayList;
import java.util.List;

public class PredicateEx {
    @FunctionalInterface
    public interface Predicate<T> {
        boolean test(T t);
    }

    public static <T> List<T> filter(List<T> list, Predicate<T> p){
        List<T> results = new ArrayList<>();
        for(T t: list) {
            if(p.test(t)){
                results.add(t);
            }
        }
        return results;
    }

    public static void main(String[] args){
        List<String> listOfStrings = new ArrayList<>();

        Predicate<String> nonEmptyStringPredicate = (String s) -> !s.isEmpty();
        List<String> nonEmpty = filter(listOfStrings, nonEmptyStringPredicate);
    }
}
