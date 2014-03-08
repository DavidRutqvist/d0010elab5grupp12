package carWashSimulator;

import java.util.ArrayList;

/**
 * An object representing a car factory that is able to create new car objects.
 * @author Emil
 *
 */
public class CarFactory {
	private int counterID = 0;
	private ArrayList<Car> cars = new ArrayList<Car>();
	
	/**
	 * Creates a new car with counterID and then increments counterID.
	 * @return the new car.
	 */
	public Car createNewCar(){
		return new Car(counterID++);
	}
	
	/**
	 * @return number of cars created.
	 */
	public int numberOfCarsCreated(){
		return cars.size();
	}
}
