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
		
		//When StartEvent
		if((state.getCurrentCWSEvent() instanceof CarWashStartEvent)){
			System.out.println("Fast machines: " + state.getAvailableFastWashes());
			System.out.println("Slow machines: " + state.getAvailableSlowWashes());
			System.out.print("Fast distribution: (");
			System.out.printf("%.1f%s%.1f%s%n", state.getFastWashTimeDistr()[0], ", ",
					state.getFastWashTimeDistr()[1], ")");
			System.out.print("Slow distribution: (");
			System.out.printf("%.1f%s%.1f%s%n", state.getSlowWashTimeDistr()[0], ", ",
					state.getSlowWashTimeDistr()[1], ")");
			System.out.print("Exponential distribution with lambda = ");
			System.out.printf("%.1f %n", state.getLambda());
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
		
		//Printing the data
		System.out.printf("%-10.2f %-10.10s %-10.10s %-10.10s %-10.10s %-10.2f %-10.2f %-10.10s %-10.10s%n",
				state.getLatestUpdateTime(), state.getAvailableFastWashes(), state.getAvailableSlowWashes(), currentCWVCar,
				currentCWVEvent, state.getIdleTime(), state.getQueueTime(), state.getCarQueueSize(), state.getRejected());
		
		// When StopEvent
		if(state.getCurrentCWSEvent() instanceof CarWashStopEvent){
			System.out.println("-----------------------------------");
			System.out.print("Total idle machine time: ");
			System.out.printf("%.2f %n", state.getIdleTime());
			System.out.print("Total queueing time: ");
			System.out.printf("%.2f %n", state.getQueueTime());
			System.out.print("Mean queueing time: ");
			System.out.printf("%.2f %n", state.getMeanQueueTime());
			System.out.println("Rejected cars: " + state.getRejected());
		}
		
	}
}
