package refactoring.chapter1;

import refactoring.chapter1.mock.InvoicesMock;
import refactoring.chapter1.mock.PlaysMock;
import refactoring.chapter1.model.Invoice;
import refactoring.chapter1.model.Performance;
import refactoring.chapter1.model.Play;
import java.util.Map;

public class Ex1 {
    public static void main(String[] args){
        InvoicesMock invoices = new InvoicesMock();
        PlaysMock plays = new PlaysMock();
        
        String result = statement(invoices.at(0), plays.getPlayMap());
        System.out.println(result);
    }

    private static String statement(Invoice invoice, Map<String, Play> plays) {
        int totalAmount = 0;
        int volumeCredits = 0;
        String result = "청구내역 (고객명: " + invoice.getCustomer() + ")\n";

        for(Performance perf : invoice.getPerformances()){
            Play play = plays.get(perf.getPlayID());
            int thisAmount = 0;

            switch (play.getType()) {
                case "tragedy": // 비극
                    thisAmount = 40000;
                    if (perf.getAudience() > 30) {
                        thisAmount += 1000 * (perf.getAudience() - 30);
                    }
                    break;
                case "comedy": // 희극
                    thisAmount = 30000;
                    if (perf.getAudience() > 20) {
                        thisAmount += 10000 + 500 * (perf.getAudience() - 20);
                    }
                    thisAmount += 300 * perf.getAudience();
                    break;
                default:
                    throw new RuntimeException("알 수 없는 장르: " + play.getType());
            }

            // 포인트를 적립한다.
            volumeCredits += Math.max(perf.getAudience() - 30, 0);

            // 희극 관객 5명마다 추가 포인트를 제공한다.
            if("comedy".equals(play.getType())) {
                volumeCredits += Math.floor(perf.getAudience() / 5);
            }

            // 청구 내역을 출력한다.
            result += " " + play.getName() + ": $" + thisAmount / 100 + "(" + perf.getAudience() + "석)\n";
            totalAmount += thisAmount;
        }
        result += "총액: $" + totalAmount / 100 + "\n";
        result += "적립 포인트: " + volumeCredits + "점\n";

        return result;
    }
}
