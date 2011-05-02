package edu.mccc.cos210.cmwar;
import java.awt.Rectangle;
import java.awt.Point;
import java.awt.Dimension;

/**
* ConnectionPoint: Specifies individual areas on components that can be connected.
* @author Rohit Kumar
* @version 0.1
*/
public interface ConnectionPoint {
	/**
	*Returns the bounds (size) of connection point
	*@return Dimension: size of bounds
	*/
	public Dimension getBounds();

	/**
	*Sets bounds size of connection point
	*@param width width of bounding box
	*@param height height of bounding box
	*/
	public void setBounds(int width, int height);

	/**
	*Gets location of bounding box
	*@return Location of bounding box.
	*/
	public Point getLocation();

	/**
	*Sets location of bounding box
	*/
	public void setLocation(int x, int y);
}