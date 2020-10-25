package refactoring.chapter1.model;

import lombok.Data;

@Data
public class Play {
    private String name;
    private String type;

    public Play(String name, String type){
        this.name = name;
        this.type = type;
    }
}
