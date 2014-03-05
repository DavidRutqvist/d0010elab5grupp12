package simulator;

import java.util.Observable;
import java.util.Observer;

public abstract class SimView implements Observer {
	
	public SimView(SimState state)
	{
		state.addObserver(this);
	}
	
	public abstract void update(Observable obs, Object obj);

}
