package modern.java.chapter5;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class StreamExample {
    public static void main(String... args){
        whereYear2011OrderByValue();
        System.out.println("");
        distinctCity();
        System.out.println("");
        whereCityCambridgeOrderByName();
        System.out.println("");
        allTraderOrderByName();
        System.out.println("");
        isMillan();
        System.out.println("");
        whereCityCambridgeTraderTransaction();
        System.out.println("");
        maxValueTransaction();
        System.out.println("");
        minValueTransaction();
    }

    private static void minValueTransaction() {
        List<Transaction> transactions = makeSample();
        Optional<Integer> result = transactions.stream()
                .min(Comparator.comparing(Transaction::getValue))
                .map(t -> t.getValue());
//        Optional<Integer> result = transactions.stream()
//                .map(t -> t.getValue())
//                .reduce(Integer::min);

        System.out.println(result.get());
    }

    private static void maxValueTransaction() {
        List<Transaction> transactions = makeSample();
        int result = transactions.stream()
                .map(t -> t.getValue())
                .reduce(0, Integer::max);
        System.out.println(result);
    }

    private static void whereCityCambridgeTraderTransaction() {
        List<Transaction> transactions = makeSample();
        transactions.stream()
                .filter(t -> "Cambridge".equals(t.getTrader().getCity()))
                .forEach(t -> System.out.println(t.toString()));
    }

    private static void isMillan() {
        List<Transaction> transactions = makeSample();

        boolean result = transactions.stream()
                .anyMatch(t -> "Milan".equals(t.getTrader().getCity()));
        System.out.println(result);
    }

    private static void allTraderOrderByName() {
        List<Transaction> transactions = makeSample();
        transactions.stream()
                .map(t -> t.getTrader().getName())
                .distinct()
                .sorted()
                .forEach(t -> System.out.println(t.toString()));
    }

    private static void whereCityCambridgeOrderByName() {
        List<Transaction> transactions = makeSample();

        transactions.stream()
                .filter(t -> "Cambridge".equals(t.getTrader().getCity()))
                .map(t -> t.getTrader())
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .forEach(t -> System.out.println(t.toString()));
    }

    public static void whereYear2011OrderByValue(){
        List<Transaction> transactions = makeSample();

        transactions.stream()
                .filter(t -> t.getYear() == 2011)
                .sorted(Comparator.comparingInt(Transaction::getYear))
                .forEach(t -> System.out.println(t.toString()));
    }

    public static void distinctCity(){
        List<Transaction> transactions = makeSample();

//        transactions.stream()
//                .map(t -> t.getTrader().getCity())
//                .distinct()
//                .forEach(t -> System.out.println(t));
        transactions.stream()
                .map(t -> t.getTrader().getCity())
                .collect(Collectors.toSet())
                .forEach(t -> System.out.println(t));
    }

    private static List<Transaction> makeSample(){
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        return Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
    }
}
