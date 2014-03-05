package carWashSimulator;

import java.util.ArrayList;

public class CarFactory {
	private ArrayList<Car> cars = new ArrayList<Car>();
	
	public CarFactory(){
		//Constructor
	}
	
	public Car createNewCar(int id){
		return new Car(id);
	}
	
	public int numberOfCarsCreated(){
		return cars.size();
	}
}
