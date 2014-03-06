package carWashSimulator;

import random.ExponentialRandomStream;
import random.UniformRandomStream;
import simulator.SimState;

public class CarWashState extends SimState {
	private final double[] FASTDISTR = {2.8, 4.6};
	private final double[] SLOWDISTR = {3.6, 6.7};
	private final double LAMBDA = 1.5;
	private final int SEED = 1234;
	private final int MAXCARQUEUE = 5;
	
	private int numRejected = 0;
	private int numAvailableFastWashes = 2;
	private int numAvailableSlowWashes = 2;
	private double fastWashTime = 0;
	private double slowWashTime = 0;
	private double idleTime = 0;
	private double queueTime = 0;
	private double latestUpdateTime = 0;
	private boolean started = false;
	private boolean stopped = false;
	private CarFactory factory = new CarFactory();
	private ExponentialRandomStream expRand = new ExponentialRandomStream(LAMBDA, SEED);
	private FIFO carQueue = new FIFO(MAXCARQUEUE);
	
	public CarWashState() {
		this.fastWashTime = new UniformRandomStream(FASTDISTR[0], FASTDISTR[1], SEED).next();
		this.slowWashTime = new UniformRandomStream(SLOWDISTR[0], SLOWDISTR[1], SEED).next();
		changed();
	}
	
	public double[] getFastWashTimeDistr(){
		return FASTDISTR;
	}
	
	public double[] getSlowWashTimeDistr(){
		return SLOWDISTR;
	}
	
	public double getLambda(){
		return LAMBDA;
	}
	
	public int getSeed(){
		return SEED;
	}
	
	public int getRejected(){
		return numRejected;
	}
	
	public int getMaxCarQueue(){
		return MAXCARQUEUE;
	}
	
	public Car getFirstCarInLine(){
		Car first = carQueue.poll();
		changed();
		return first;
	}
	
	public void addCarToLine(Car car){
		boolean notFull = carQueue.offer(car);
		if (notFull == false) {
			this.numRejected++;
		}
		changed();
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
	
	public int getCarQueueSize(){
		return carQueue.size();
	}
	
	public CarFactory getCarFactory(){
		return this.factory;
	}
	
	public double getNewArriveTime(){
		this.latestUpdateTime += expRand.next();
		return latestUpdateTime;
	}
	
	public boolean getHasStarted(){
		return started;
	}
	
	public void setHasStarted(boolean s){
		this.started = true;
		changed();
	}
	
	public boolean getHasStopped(){
		return stopped;
	}
	
	public void setHasStopped(){
		this.stopped = true;
		changed();
	}
	
	private void changed(){
		setChanged();
		notifyObservers();
	}
}
