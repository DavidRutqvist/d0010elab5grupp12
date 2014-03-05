package simulator;

import java.util.Observer;

public abstract class SimView implements Observer {
	
	public SimView(SimState state)
	{
		state.addObserver(this);
	}
	
	public abstract void update();

}
