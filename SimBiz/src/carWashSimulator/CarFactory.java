package carWashSimulator;

import java.util.ArrayList;

/**
 * An object representing a car factory that is able to create
 * new car objects.
 * @author Emil
 *
 */
public class CarFactory {
	private ArrayList<Car> cars = new ArrayList<Car>();
	
	/**
	 * Creates a new car.
	 * @param id the ID for the new car.
	 * @return the new car.
	 */
	public Car createNewCar(int id){
		return new Car(id);
	}
	
	/**
	 * @return number of cars created.
	 */
	public int numberOfCarsCreated(){
		return cars.size();
	}
}
