package refactoring.chapter1.mock;

import lombok.Data;
import refactoring.chapter1.model.Play;

import java.util.HashMap;
import java.util.Map;

@Data
public class PlaysMock {
    Map<String, Play> playMap;

    public PlaysMock(){
        playMap = new HashMap<>();
        playMap.put("hamlet", new Play("Hamlet", "tragedy"));
        playMap.put("asLike", new Play("As You Like It", "comedy"));
        playMap.put("othello", new Play("Othello", "tragedy"));
    }
}
