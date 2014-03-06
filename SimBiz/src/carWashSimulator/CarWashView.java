package carWashSimulator;

import java.util.Observable;

import simulator.SimView;

public class CarWashView extends SimView {
	private CarWashState state;
	
	public CarWashView(CarWashState state){
		super(state);
		this.state = state;
	}

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
			System.out.print("-\t");//TODO: car ID
			System.out.print("-\t");//TODO: event ID
			System.out.print(state.getIdleTime() + "\t");
			System.out.print(state.getQueueTime() + "\t");
			System.out.print(state.getCarQueueSize()+ "\t");
			System.out.println(state.getRejected() + "\t");	
		}
		
		if(state.getHasStopped()){
			System.out.println("-----------------------------------");
			System.out.println("Total idle machine time " + state.getIdleTime());
			System.out.println("Total queueing time: " + state.getQueueTime());
			System.out.println("Mean queueing time: -");//TODO: mean queueing time
			System.out.println("Rejected cars: " + state.getRejected());
		}
		
	}
}
