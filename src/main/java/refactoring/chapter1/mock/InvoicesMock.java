package refactoring.chapter1.mock;

import lombok.Data;
import refactoring.chapter1.model.Invoice;
import refactoring.chapter1.model.Performance;

import java.util.ArrayList;
import java.util.List;

@Data
public class InvoicesMock {
    List<Invoice> invoices;

    public InvoicesMock(){
        this.invoices = new ArrayList<>();
        Invoice invoice = new Invoice();
        invoice.setCustomer("BigCo");

        Performance performance1 = new Performance("hamlet", 55);
        Performance performance2 = new Performance("asLike", 35);
        Performance performance3 = new Performance("othello", 40);
        invoice.setPerformances(new ArrayList<>());
        invoice.getPerformances().add(performance1);
        invoice.getPerformances().add(performance2);
        invoice.getPerformances().add(performance3);

        invoices.add(invoice);
    }

    public Invoice at(int idx){
        return this.invoices.get(idx);
    }
}
