package refactoring.chapter1.model;

import lombok.Data;

@Data
public class Performance {
    private String playID;

    private int audience;

    public Performance(String playID, int audience){
        this.playID = playID;
        this.audience = audience;
    }
}
