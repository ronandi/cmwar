package edu.mccc.cos210.cmwar;
import java.util.List;
/** 
* Board interface for Circuit Maker with AutoRoute
* A board has Components and Connections, and manages them. 
* @author Rohit Kumar
* @version 0.1
*/
public interface Board {
	/**
	* Adds a component to the board.
	* @param c A valid component to be added to the board.
	*/
	public void addComponent(Component c);
	
	/**
	* Deletes a component on the board.
	* @param c A valid component already on the board.
	*/
	public void deleteComponent(Component c);
	
	/**
	* Returns a list of all the components on the board.
	* @return a List of all the Components already on the board.
	*/
	public List<Component> listComponents();
	
	/**
	* Returns a list of all the connections between components on the board.
	* @return Returns a list of all the connections between components on the board.
	*/
	public List<Connection> listConnections();

	/**
	* Adds a connection between two components 
	* @param a A Component to be connected.
	* @param b Opposite Component to be connected to A, and vice versa.
	*/
	public void addConnection(Component a, ConnectionPoint cpA, Component b, ConnectionPoint cpB);

	/** 
	* The long-dreaded method that does all the big work. Transforms the rat's nest into traces.
	*/
	public void autoRoute();
	
	/**
	* Does the opposite of autoRoute(). Returns the traced board to a rat's nest.
	*/
	public void ripUp();

	/**
	* Deletes all connections between componets on the board.
	*/
	public void clearConnections();
	
	/**
	* Saves the board in its current state.
	*/
	public void save();
	
	/**
	* Loads the board in the state it was saved in.
	*/
	public void load();
	
	/**
	* Undos the last change to the Board
	*/
	public void undoLast();

	/**
	* Undos the last change to the Board
	*/
	public void redoLast();
}