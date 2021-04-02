package design.pattern.chapter20;

public class LightLamp implements LightState {
	private boolean lightState;
	public LightLamp() {
		this.lightState = false;
	}

	@Override
	public LightLamp lightOn() {
		this.lightState = true;
		return this;
	}

	@Override
	public LightLamp lightOff() {
		this.lightState = false;
		return this;
	}

	@Override
	public boolean lightState() {
		return this.lightState;
	}
}
