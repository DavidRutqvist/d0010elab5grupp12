package simulator;

public abstract class Event {
	protected double priority;
	protected SimState state;
	public Event (double priority){
		this.priority = priority;
	}
	public void setState (SimState state) {
		this.state = state;
	}
	public double getPriority () {
		return priority;
	}
	public abstract void execute();
}
