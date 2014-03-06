package carWashSimulator;

/**
 * An object representing a car.
 * @author Emil
 *
 */
public class Car {
	private int id;
	
	/**
	 * Constructor that assigns the car an ID.
	 * @param id
	 */
	public Car(int id){
		this.id = id;
	}
	
	/**
	 * @return ID of car.
	 */
	public int getID(){
		return id;
	}
}
