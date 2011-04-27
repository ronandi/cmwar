package edu.mccc.cos210.cmwar;
import java.awt.Rectangle;
import java.awt.Point;

/**
* ConnectionPoint
* @author Rohit Kumar
* @version 0.1
*/
public interface ConnectionPoint {
	public Rectangle getBounds();
	public void setBounds();
	public Point getLocation();
	public void setLocation(int x, int y);
}