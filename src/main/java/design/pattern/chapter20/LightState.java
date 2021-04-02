package design.pattern.chapter20;

public interface LightState {
	LightState lightOn();
	LightState lightOff();
	boolean lightState();
}
