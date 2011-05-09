package edu.mccc.cos210.cmwar;
import java.awt.Point;
import java.util.List;

/**
* Component interface for Circuit Maker with AutoRouter.
* A Component is an item that can be used in a circuit
* @author Rohit Kumar
* @version 0.1 
*/
public interface Component {
	/**
	* Connects this component to another
	* @param a Component connection point to connect to.
	* @param b Component connection point to connect to.
	*/
	public void connectTo(ConnectionPoint a, ConnectionPoint b, Component compB);

	/**
	* Rotates this component
	* @param newOrient New orientation for Component.
	*/
	public void rotate(Orientation newOrient);

	/**
	* Returns the orientation of the component (changed with rotate())
	* @return Orientation enum describing current orientation: Up, Down, Left, Right (facing)
	*/
	public Orientation getOrientation();

	/**
	* Returns the name of this component.
	* Likely helpful for use in GUI, and debugging
	* @return String name of component 
	*/
	public String getName();

	/**
	* Sets name of component.
	* @param n New new for component.
	* @test.status ok
	*/
	public void setName(String n);

	/**
	* Returns a list of Connections this component has
	* @return List of connections component has
	*/
	public List<Connection> getConnections();

	/**
	*Gets connection points for a component.
	*@return list of ConnectionPoints 
	*/
	public List<ConnectionPoint> getConnectionPoints();

	/**
	*Adds a connection point to a component
	*@param newPoint ConnectionPoint to add to Component.
	*/
	public void addConnectionPoint(ConnectionPoint newPoint);
	
	/**
	* Returns location of Component
	* @return Point object with location coordinates
	*/
	public Point getPosition();
	
	/**
	* Sets the position
	* @param x X coordinate for new location of component
	* @param y Y coordination for new location of component
	*/
	public void setPosition(int x, int y);

}