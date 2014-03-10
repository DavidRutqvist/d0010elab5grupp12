package carWashSimulator;

import java.text.DecimalFormat;
import java.util.Observable;

import simulator.SimView;
import simulator.StartEvent;
import simulator.StopEvent;

/**
 * A view that observes the state and prints changes to the console.
 * @author Emil
 *
 */
public class CarWashView extends SimView {
	private CarWashState state;
	private DecimalFormat twoDec = new DecimalFormat("#.##");
	private String currentCWVEvent = "";
	
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
		
		if((state.getCurrentCWSEvent() instanceof StartEvent)){
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
		
		System.out.print(twoDec.format(state.getLatestUpdateTime()) + "\t");
		System.out.print(state.getAvailableFastWashes() + "\t");
		System.out.print(state.getAvailableSlowWashes() + "\t");
		System.out.print(state.getCurrentCar() + "\t");
		
		// Gets a string corresponding to current event.
		if (state.getCurrentCWSEvent() instanceof StartEvent){
			currentCWVEvent = "Start";
		}
		if (state.getCurrentCWSEvent() instanceof StopEvent){
			currentCWVEvent = "Stop";
		}
		if (state.getCurrentCWSEvent() instanceof ArriveEvent){
			currentCWVEvent = "Arrive";
		}
		if (state.getCurrentCWSEvent() instanceof LeaveEvent){
			currentCWVEvent = "Leave";
		}
		else { currentCWVEvent = "Unrecognised"; }
		
		System.out.print(currentCWVEvent + "\t");
		System.out.print(twoDec.format(state.getIdleTime()) + "\t");
		System.out.print(twoDec.format(state.getQueueTime()) + "\t");
		System.out.print(state.getCarQueueSize()+ "\t");
		System.out.println(state.getRejected() + "\t");	
		
		if(state.getCurrentCWSEvent() instanceof StopEvent){
			System.out.println("-----------------------------------");
			System.out.println("Total idle machine time " + twoDec.format(state.getIdleTime()));
			System.out.println("Total queueing time: " + twoDec.format(state.getQueueTime()));
			System.out.println("Mean queueing time: " + twoDec.format(state.getMeanQueueTime()));
			System.out.println("Rejected cars: " + state.getRejected());
		}
		
	}
}
