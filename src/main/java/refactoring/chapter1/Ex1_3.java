package refactoring.chapter1;

import refactoring.chapter1.mock.InvoicesMock;
import refactoring.chapter1.mock.PlaysMock;
import refactoring.chapter1.model.Invoice;
import refactoring.chapter1.model.Performance;
import refactoring.chapter1.model.Play;

import java.util.Map;

public class Ex1_3 {

    private static Map<String, Play> plays;

    public static void main(String[] args){
        InvoicesMock invoices = new InvoicesMock();
        PlaysMock playsMock = new PlaysMock();
        plays = playsMock.getPlayMap();
        String result = statement(invoices.at(0));
        System.out.println(result);
    }

    private static String statement(Invoice invoice) {
        int totalAmount = 0;
        int volumeCredits = 0;
        String result = "청구내역 (고객명: " + invoice.getCustomer() + ")\n";

        for(Performance perf : invoice.getPerformances()){
            int thisAmount = amountFor(perf, playFor(perf));

            volumeCredits += volumeCreditsFor(perf);

            // 청구 내역을 출력한다.
            result += " " + playFor(perf).getName() + ": $" + thisAmount / 100 + "(" + perf.getAudience() + "석)\n";
            totalAmount += thisAmount;
        }
        result += "총액: $" + totalAmount / 100 + "\n";
        result += "적립 포인트: " + volumeCredits + "점\n";

        return result;
    }

    private static int amountFor(Performance performance, Play play){
        int result = 0;

        switch (play.getType()) {
            case "tragedy": // 비극
                result = 40000;
                if (performance.getAudience() > 30) {
                    result += 1000 * (performance.getAudience() - 30);
                }
                break;
            case "comedy": // 희극
                result = 30000;
                if (performance.getAudience() > 20) {
                    result += 10000 + 500 * (performance.getAudience() - 20);
                }
                result += 300 * performance.getAudience();
                break;
            default:
                throw new RuntimeException("알 수 없는 장르: " + play.getType());
        }

        return result;
    }

    private static Play playFor(Performance performance){
        return plays.get(performance.getPlayID());
    }

    private static int volumeCreditsFor(Performance performance){
        int volumeCredits = 0;

        // 포인트를 적립한다.
        volumeCredits += Math.max(performance.getAudience() - 30, 0);

        // 희극 관객 5명마다 추가 포인트를 제공한다.
        if("comedy".equals(playFor(performance).getType())) {
            volumeCredits += Math.floor(performance.getAudience() / 5);
        }
        return volumeCredits;
    }
}
