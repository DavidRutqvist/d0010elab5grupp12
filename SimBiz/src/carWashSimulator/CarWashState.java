package carWashSimulator;

import random.UniformRandomStream;
import simulator.SimState;

public class CarWashState extends SimState {
	private int numRejected = 0;
	private int numAvailableFastWashes = 2;
	private int numAvailableSlowWashes = 2;
	private double fastWashTime = 0;
	private double slowWashTime = 0;
	private double idleTime = 0;
	private double queueTime = 0;
	private double latestUpdateTime = 0;
	private FIFO carQueue;
	private CarFactory factory = new CarFactory();
	
	public CarWashState() {
		this.fastWashTime = new UniformRandomStream(2.8, 4.6, 1234).next();
		this.slowWashTime = new UniformRandomStream(3.5, 6.7, 1234).next();
	}
	
	public int getRejected(){
		return numRejected;
	}
	
	/**
	 * Work in progress. Returns the first car in the queue and then removes it.
	 * @return
	 */
	public Car getFirstCarInLine(){
		return (Car)carQueue.poll();
	}
	
	public void addCarToLine(Car car){
		if (carQueue.offer(car) == false) {
			this.numRejected++;
		}
	}
	
	public int getAvailableFastWashes(){
		return this.numAvailableFastWashes;
	}
	
	public int getAvailableSlowWashes(){
		return this.numAvailableSlowWashes;
	}
	
	public double getFastWashTime(){
		return this.fastWashTime;
	}
	
	public double getSlowWashTime(){
		return this.slowWashTime;
	}
	
	public double getIdleTime(){
		return idleTime;
	}
	
	public void setIdleTime(double time){
		this.idleTime = time;
	}
	
	public double getQueueTime(){
		return queueTime;
	}
	
	public void setQueueTime(double time){
		this.queueTime = time;
	}
	
	public double getLatestUpdateTime(){
		return this.latestUpdateTime;
	}
	
	public CarFactory getCarFactory(){
		return this.factory;
	}
}
