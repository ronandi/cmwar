package edu.mccc.cos210.cmwar;
import java.awt.Rectangle;
import java.awt.Point;
import java.awt.Dimension;

/**
* CMConnectionPoint: Specifies individual areas on components that can be connected.
* @author Rohit Kumar
* @version 0.1
* 
*/
public class CMConnectionPoint implements ConnectionPoint {
	
	/**
	*Bounding box of a connection point.
	*/
	private Rectangle myBounds;
	public CMConnectionPoint(int x, int y, int width, int height) {
		myBounds = new Rectangle(x, y, width, height);
	}

	/**
	*Sets bounds size of connection point
	*@test.status ok
	*@param width width of bounding box
	*@param height height of bounding box
	*/
	public void setBounds(int width, int height) {
		myBounds.setSize(width, height);
	}

	/**
	*Returns the bounds (size) of connection point
	*@test.status ok
	*@return Dimension: size of bounds
	*/
	public Dimension getBounds() {
		return myBounds.getSize();
	}

	/**
	*Gets location of bounding box.
	*@test.status ok
	*@return Location of bounding box.
	*/
	public Point getLocation() {
		return myBounds.getLocation();
	}

	/**
	*Sets location of bounding box
	*@test.status ok
	*/
	public void setLocation(int x, int y) {
		myBounds.setLocation(x, y);
	}
	public static void main(String[] sa) throws Exception {
		System.out.println("CMConnectionPoint.java test");
		ConnectionPoint cmcp = new CMConnectionPoint(1, 3, 5, 5);
		System.out.println("5x5 connection point at " + cmcp.getLocation() + " created");
		System.out.println(cmcp.getBounds());
		cmcp.setBounds(10, 10);
		System.out.println(cmcp.getBounds());
		cmcp.setLocation(2, 4);
		System.out.println(cmcp.getLocation());
		System.out.println("Test completed");
	}

}