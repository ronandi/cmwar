package edu.mccc.cos210.cmwar;
import java.util.List;
/**
* Connectable interface for Circuit Maker with AutoRouter.
* A Connectable item is one that can be connected to another connectable via a Connection
* @author Rohit Kumar
* @version 0.1 
*/
public interface Connectable {
	/**
	* Returns a list of Connections this component has
	* @return List of connections component has
	*/
	public List<Connection> getConnections();

	/**
	* Connects this component to another
	* @param c Component to connect to.
	*/
	public void connectTo(Component c);
}