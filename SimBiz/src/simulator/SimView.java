package simulator;

import java.util.Observable;
import java.util.Observer;

/**
 * Abstract view which adds itself as observer to a supplied SimState. <b>Must be extended to application specific view.</b> Multiple views can be attached to an individual state.
 * @author david.rutqvist
 *
 */
public abstract class SimView implements Observer {
	
	/**
	 * Initializes a new view and adds the view as observer of the supplied state
	 * @param state State to be observed
	 */
	public SimView(SimState state)
	{
		state.addObserver(this);//Add as observer
	}
	
	/**
	 * Update view then state changes
	 */
	public abstract void update(Observable obs, Object obj);

}
