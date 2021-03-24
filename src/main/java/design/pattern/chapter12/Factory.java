package design.pattern.chapter12;

import java.util.HashMap;
import java.util.Map;

public class Factory {
	private Map<Character, FlyWeight> pool = new HashMap<>();

	public FlyWeight getCode(Character character) {
		if(!pool.containsKey(character)) {
			pool.put(character, makeMorseInstance(character));
		}
		return pool.get(character);
	}

	public FlyWeight makeMorseInstance(Character character) {
		switch (character) {
			case 'E':
				return new MorseE();
			case 'G':
				return new MorseG();
			case 'L':
				return new MorseL();
			case 'O':
				return new MorseO();
		}
		throw new RuntimeException("Not Type");
	}
}
