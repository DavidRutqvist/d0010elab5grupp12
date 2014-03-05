package carWashSimulator;

import simulator.SimState;

public class CarWashState extends SimState {
	private int numRejected;
	private int numAvailableFastWashes;
	private int numAvailableSlowWashes;
	private double fastWashTime;
	private double slowWashTime;
	private double idleTime;
	private double queueTime;
	private double latestUpdateTime;
	private FIFO carQueue;
	private CarFactory factory = new CarFactory();
	
	public CarWashState() {
		//Constructor
	}
	
	public int getRejected(){
		return numRejected;
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
	
	public CarFactory getCarFactory(){
		return this.factory;
	}
}
