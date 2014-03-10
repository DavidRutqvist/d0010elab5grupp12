package carWashSimulator;

import random.ExponentialRandomStream;
import random.UniformRandomStream;
import simulator.Event;
import simulator.SimState;
import simulator.StartEvent;
import simulator.StopEvent;

/**
 * A state object keeping track of most of the information.
 * @author Emil
 *
 */
public class CarWashState extends SimState {
	private final double[] FASTDISTR = {2.8, 4.6};
	private final double[] SLOWDISTR = {3.6, 6.7};
	private final double LAMBDA = 1.5;
	private final int SEED = 1234;
	private final int MAXCARQUEUE = 5;
	
	private int numRejected = 0;
	private int numAvailableFastWashes = 2;
	private int numAvailableSlowWashes = 2;
	private int nrCarsWaited = 0;
	private double fastWashTime = 0.0;
	private double slowWashTime = 0.0;
	private double idleTime = 0.00;
	private double queueTime = 0.00;
	private double latestUpdateTime = 0.00;
	private String currentCar = "-";
	private Event currentEvent;
	private CarFactory factory = new CarFactory();
	private ExponentialRandomStream expRand = new ExponentialRandomStream(LAMBDA, SEED);
	private FIFO carQueue = new FIFO(MAXCARQUEUE);
	
	/**
	 * Constructor, randomises the fast and slow wash times.
	 */
	public CarWashState() {
		this.fastWashTime = new UniformRandomStream(FASTDISTR[0], FASTDISTR[1], SEED).next();
		this.slowWashTime = new UniformRandomStream(SLOWDISTR[0], SLOWDISTR[1], SEED).next();
	}
	
	// Get constants.
	/**
	 * @return double[] containing distribution for fast wash time.
	 */
	public double[] getFastWashTimeDistr(){
		return FASTDISTR;
	}
	/**
	 * @return double[] containing distribution for fast wash time.
	 */
	public double[] getSlowWashTimeDistr(){
		return SLOWDISTR;
	}
	/**
	 * @return double LAMBDA used for RandomStream.
	 */
	public double getLambda(){
		return LAMBDA;
	}
	/**
	 * @return integer SEED used for RandomStream.
	 */
	public int getSeed(){
		return SEED;
	}
	/**
	 * @return the maximum size allowed for the car queue.
	 */
	public int getMaxCarQueue(){
		return MAXCARQUEUE;
	}
	
	// Get/set available washes.
	/**
	 * @return integer with number of available fast washes.
	 */
	public int getAvailableFastWashes(){
		return this.numAvailableFastWashes;
	}
	/**
	 * Sets available fast washes.
	 * @param num number of available fast washes.
	 */
	public void setAvailableFastWashes(int num){
		this.numAvailableFastWashes = num;
	}
	/**
	 * @return integer with number of available slow washes.
	 */
	public int getAvailableSlowWashes(){
		return this.numAvailableSlowWashes;
	}
	/**
	 * Sets available slow washes.
	 * @param num number of available slow washes.
	 */
	public void setAvailableSlowWashes(int num){
		this.numAvailableSlowWashes = num;
	}
	
	// Get wash time.
	/**
	 * @return time it takes for a fast wash.
	 */
	public double getFastWashTime(){
		return this.fastWashTime;
	}
	/**
	 * @return time it takes for a slow wash.
	 */
	public double getSlowWashTime(){
		return this.slowWashTime;
	}
	
	// Get/set idle time.
	/**
	 * @return total idle time.
	 */
	public double getIdleTime(){
		return idleTime;
	}
	/**
	 * Sets the total idle time.
	 * @param time total idle time.
	 */
	public void setIdleTime(double time){
		this.idleTime = time;
	}
	
	// Get/set queue time.
	/**
	 * @return total queue time.
	 */
	public double getQueueTime(){
		return queueTime;
	}
	/**
	 * Sets the total queue time.
	 * @param time total queue time.
	 */
	public void setQueueTime(double time){
		this.queueTime = time;
	}
	/**
	 * @return the mean queue time per waiting car.
	 */
	public double getMeanQueueTime(){
		return queueTime/nrCarsWaited;
	}
	
	// Add/remove from queue.
	/**
	 * Adds a car to the car queue, unless it's full and the number of rejected cars
	 * is increased.
	 * @param car
	 */
	public void addCarToLine(Car car){
		boolean notFull = carQueue.offer(car);
		if (notFull == false) {
			this.numRejected++;
		}
		else {
			this.nrCarsWaited++;
		}
		currentCar = String.valueOf(car.getID());
	}
	/**
	 * @return the first car in the car queue.
	 */
	public Car getFirstCarInLine(){
		Car first = carQueue.poll();
		currentCar = String.valueOf(first.getID());
		return first;
	}
	
	/**
	 * @return a String of the current cars ID.
	 */
	public String getCurrentCar(){
		return currentCar;
	}
	
	/**
	 * @return number of cars that have been rejected.
	 */
	public int getRejected(){
		return numRejected;
	}
	
	/**
	 * @return the current size of the car queue.
	 */
	public int getCarQueueSize(){
		return carQueue.size();
	}
	
	/**
	 * @return the CarFactory created in this state.
	 */
	public CarFactory getCarFactory(){
		return this.factory;
	}
	
	// Get/set the "current" time.
	/**
	 * @return the time when the state was latest updated.
	 */
	public double getLatestUpdateTime(){
		return this.latestUpdateTime;
	}
	/**
	 * Sets the time when the state was latest updated.
	 * @param time the current time.
	 */
	public void setLatestUpdateTime(double time){
		this.latestUpdateTime = time;
	}
	
	/**
	 * Uses ExponentialRandomStream to calculate when the next arrival should occur.
	 * @return the next arrival time.
	 */
	public double getNewArrivalTime(){
		return this.latestUpdateTime += expRand.next();
	}
	
	// Get/set the current Event.
	/**
	 * @return the current Event that changed this state.
	 */
	public Event getCurrentCWSEvent(){
		return currentEvent;
	}
	/**
	 * Sets the Event that changed the state and notifies observers.
	 * @param event the Event that changed the state.
	 */
	public void setCurrentCWSEvent(Event event){
		this.currentEvent = event;
		if (this.currentEvent instanceof StopEvent || this.currentEvent instanceof StartEvent){
			this.currentCar = "-";
		}
		setChanged();
		notifyObservers();
	}

}
