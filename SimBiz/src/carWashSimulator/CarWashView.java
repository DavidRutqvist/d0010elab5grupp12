package carWashSimulator;

import java.text.DecimalFormat;
import java.util.Observable;

import simulator.SimView;

/**
 * A view that observes the state and prints changes to the console.
 * @author Emil
 *
 */
public class CarWashView extends SimView {
	private CarWashState state;
	private DecimalFormat twoDec = new DecimalFormat("#.##");
	private String currentCWVEvent = "";
	private String currentCWVCar = "";
	
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
		
		if((state.getCurrentCWSEvent() instanceof CarWashStartEvent)){
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
			System.out.printf("%-10.10s %-10.10s %-10.10s %-10.10s %-10.10s %-10.10s %-10.10s %-10.10s %-10.10s%n",
					"Time", "Fast", "Slow", "Id", "Event", "IdleTime", "QueueTime", "QueueSize", "Rejected");
		}
		
		// Gets a string corresponding to current event.
		if (state.getCurrentCWSEvent() instanceof CarWashStartEvent){
			currentCWVEvent = "Start";
			currentCWVCar = "-";
		}
		else if (state.getCurrentCWSEvent() instanceof CarWashStopEvent){
			currentCWVEvent = "Stop";
			currentCWVCar = "-";
		}
		else if (state.getCurrentCWSEvent() instanceof ArriveEvent){
			currentCWVEvent = "Arrive";
			currentCWVCar = String.valueOf(((ArriveEvent) state.getCurrentCWSEvent()).getCar().getID());
		}
		else if (state.getCurrentCWSEvent() instanceof LeaveEvent){
			currentCWVEvent = "Leave";
			currentCWVCar = String.valueOf(((LeaveEvent) state.getCurrentCWSEvent()).getCar().getID());
		}
		else { currentCWVEvent = "Unrecognised"; }
		
		System.out.printf("%-10.2f %-10.10s %-10.10s %-10.10s %-10.10s %-10.2f %-10.2f %-10.10s %-10.10s%n",
				state.getLatestUpdateTime(), state.getAvailableFastWashes(), state.getAvailableSlowWashes(), currentCWVCar,
				currentCWVEvent, state.getIdleTime(), state.getQueueTime(), state.getCarQueueSize(), state.getRejected());
		
		if(state.getCurrentCWSEvent() instanceof CarWashStopEvent){
			System.out.println("-----------------------------------");
			System.out.println("Total idle machine time " + twoDec.format(state.getIdleTime()));
			System.out.println("Total queueing time: " + twoDec.format(state.getQueueTime()));
			System.out.println("Mean queueing time: " + twoDec.format(state.getMeanQueueTime()));
			System.out.println("Rejected cars: " + state.getRejected());
		}
		
	}
}
