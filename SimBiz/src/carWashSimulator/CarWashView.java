package carWashSimulator;

import java.util.Observable;

import simulator.SimView;

public class CarWashView extends SimView {
	
	public CarWashView(CarWashState state){
		super(state);
	}

	public void update(Observable obs, Object obj) {
		System.out.println("TODO: Printed View");
	}
}
