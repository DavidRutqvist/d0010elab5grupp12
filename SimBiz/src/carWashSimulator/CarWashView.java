package carWashSimulator;

import java.util.Observable;

import simulator.SimView;

/**
 * A view that observes the state and prints changes to the console.
 * @author Emil
 *
 */
public class CarWashView extends SimView {
	private CarWashState state;
	
	/**
	 * Constructor calling super constructor which adds this view as
	 * an observer to state.
	 * @param state containing the information.
	 */
	public CarWashView(CarWashState state){
		super(state);
		this.state = state;
	}
	
	/**
	 * Prints data from state when it notifies observers.
	 */
	public void update(Observable obs, Object obj) {
		
		if(!(state.getHasStarted())){
			System.out.println("Fast machines: " + state.getAvailableFastWashes());
			System.out.println("Slow machines: " + state.getAvailableSlowWashes());
			System.out.println("Fast distribution: (" + state.getFastWashTimeDistr()[0] + ", " 
					+ state.getFastWashTimeDistr()[1] + ")");
			System.out.println("Fast distribution: (" + state.getSlowWashTimeDistr()[0] + ", " 
					+ state.getSlowWashTimeDistr()[1] + ")");
			System.out.println("Exponential distribution with lambda = "
					+ state.getLambda());
			System.out.println("Seed = " + state.getSeed());
			System.out.println("Max Queue size = " + state.getMaxCarQueue());
			System.out.println("----------------------------------------");
			System.out.println("Time\tFast\tSLow\tId\tEvent\tIdleTime\tQueueTime\tQueueSize\tRejected");
		}
		
		else{
			System.out.print(state.getLatestUpdateTime() + "\t");
			System.out.print(state.getAvailableFastWashes() + "\t");
			System.out.print(state.getAvailableSlowWashes() + "\t");
			System.out.print(state.getCurrentCar() + "\t");
			System.out.print(state.getCurrentCWSEvent() + "\t");
			System.out.print(state.getIdleTime() + "\t");
			System.out.print(state.getQueueTime() + "\t");
			System.out.print(state.getCarQueueSize()+ "\t");
			System.out.println(state.getRejected() + "\t");	
		}
		
		if(state.getHasStopped()){
			System.out.println("-----------------------------------");
			System.out.println("Total idle machine time " + state.getIdleTime());
			System.out.println("Total queueing time: " + state.getQueueTime());
			System.out.println("Mean queueing time: " + state.getMeanQueueTime());
			System.out.println("Rejected cars: " + state.getRejected());
		}
		
	}
}
