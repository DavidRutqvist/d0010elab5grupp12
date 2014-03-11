package carWashSimulator;

import java.text.DecimalFormat;
import java.util.Formatter;
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
	private Formatter fmt = new Formatter();
	private String currentCWVEvent = "";
	private String currentCWVCar = "";
	private int CMVAvailableFastWashes;
	private int CMVAvailableSlowWashes;
	
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
		
		System.out.print(twoDec.format(state.getLatestUpdateTime()) + "\t");
		System.out.print(state.getAvailableFastWashes() + "\t");
		System.out.print(state.getAvailableSlowWashes() + "\t");
		
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
		
		System.out.print(currentCWVCar + "\t");
		System.out.print(currentCWVEvent + "\t");
		System.out.print(twoDec.format(state.getIdleTime()) + "\t");
		System.out.print(twoDec.format(state.getQueueTime()) + "\t");
		System.out.print(state.getCarQueueSize()+ "\t");
		System.out.println(state.getRejected() + "\t");	
		
		if(state.getCurrentCWSEvent() instanceof CarWashStopEvent){
			System.out.println("-----------------------------------");
			System.out.println("Total idle machine time " + twoDec.format(state.getIdleTime()));
			System.out.println("Total queueing time: " + twoDec.format(state.getQueueTime()));
			System.out.println("Mean queueing time: " + twoDec.format(state.getMeanQueueTime()));
			System.out.println("Rejected cars: " + state.getRejected());
		}
		
	}
}
