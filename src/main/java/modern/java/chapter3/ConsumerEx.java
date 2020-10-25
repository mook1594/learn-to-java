package modern.java.chapter3;

import java.util.Arrays;
import java.util.List;

public class ConsumerEx {
    @FunctionalInterface
    public interface Consumer<T> {
        void accept(T t);
    }

    public static <T> void forEach(List<T> list, Consumer<T> c) {
        for(T t: list){
            c.accept(t);
        }
    }

    public static void main(String[] args){
        forEach(Arrays.asList(1,2,3,4,5), (Integer i) -> System.out.println(i));
    }
}
