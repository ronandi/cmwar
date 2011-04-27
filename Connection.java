package edu.mccc.cos210.cmwar;
import java.util.List;
/**
* Connection interface for Circuit Maker with AutoRouter
* A Connection is what can connect two Connectables / Components
* @author Rohit Kumar
* @version 0.1
*/
public interface Connection {
	/**
	* Method to get the components connected by this connection
	* @return An array of size 2 with the Components
	*/
	public ConnectionPoint[] getEnds();

	/**
	* Method to set the ends of a connection.
	* @param a A Component to be connected.
	* @param b Opposite Component to be connected to A, and vice versa.
	*/
	public void setEnds(ConnectionPoint a, ConnectionPoint b);
}