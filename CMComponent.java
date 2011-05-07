package edu.mccc.cos210.cmwar;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.util.List;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;

/**
* Component interface for Circuit Maker with AutoRouter.
* A Component is an item that can be used in a circuit
* @author Rohit Kumar
* @version 0.1 
*/
public class CMComponent implements Component {
	/**
	*List of ConnectionPoints a Component has.
	*/
	private ArrayList<ConnectionPoint> connPointList;
	/**
	*List of Connections a Component has.
	*/
	ArrayList<Connection> myConns;
	/**
	*Orientation of Component.
	*/
	private Orientation orient;
	/**
	*Name of the component
	*/
	private String name;
	/**
	*Location of the component
	*/
	private Point location;

	private JPanel view;
	
	public CMComponent() {
		connPointList = new ArrayList<ConnectionPoint>();
		myConns = new ArrayList<Connection>();
		orient = Orientation.Up;
		name = "";
		location = new Point (0, 0);
		view = new CMComponentView(this);
	}
	public CMComponent(String name) {
		connPointList = new ArrayList<ConnectionPoint>();
		myConns = new ArrayList<Connection>();
		orient = Orientation.Up;
		this.name = name;
		location = new Point (0, 0);
		view = new CMComponentView(this);
	}


	/**
	* Connects this component to another
	* @param a Component connection point to connect to.
	* @param b Component connection point to connect to.
	* @test.status ok
	*/
	public void connectTo(ConnectionPoint a, ConnectionPoint b) {
		Connection conn = new CMConnection();
		conn.setEnds(a, b);
		myConns.add(conn);
	}

	/**
	* Rotates this component
	* @param newOrient New orientation for Component.
	* @test.status ok
	*/
	public void rotate(Orientation newOrient) {
		orient = newOrient;
	}

	/**
	* Returns the orientation of the component (changed with rotate())
	* @return Orientation enum describing current orientation: Up, Down, Left, Right (facing)
	* @test.status ok
	*/
	public Orientation getOrientation() {
		return orient;
	}

	/**
	* Returns the name of this component.
	* Likely helpful for use in GUI, and debugging
	* @return String name of component.
	* @test.status ok
	*/
	public String getName() {
		return name;
	}

	/**
	* Sets name of component.
	* @param n New new for component.
	* @test.status ok
	*/
	public void setName(String n) {
		name = n;
	}

	/**
	* Returns a list of Connections this component has
	* @return List of connections component has
	* @test.status ok
	*/
	public List<Connection> getConnections() {
		return myConns;
	}

	/**
	*Gets connection points for a component.
	*@return list of ConnectionPoints 
	*@test.status ok
	*/
	public List<ConnectionPoint> getConnectionPoints() {
		return connPointList;
	}

	/**
	*Adds a connection point to a component
	*@param newPoint ConnectionPoint to add to Component.
	*@test.status ok
	*/
	public void addConnectionPoint(ConnectionPoint newPoint) {
		connPointList.add(newPoint);
	}

	/**
	* Returns location of Component
	* @return Point object with location coordinates
	* @test.status ok
	*/
	public Point getPosition() {
		return location;
	}

	/**
	* Sets the position
	* @param x X coordinate for new location of component
	* @param y Y coordination for new location of component
	* @test.status ok
	*/
	public void setPosition(int x, int y) {
		location.setLocation(x, y);
	}
	
	public static void main(String[] sa) throws Exception {
		CMComponent myComp = new CMComponent();
		ConnectionPoint cp1 = new CMConnectionPoint(1, 1, 5, 5);
		myComp.rotate(Orientation.Left);
		myComp.setName("test comp 1");
		myComp.addConnectionPoint(cp1);
		myComp.setPosition(5, 6);
		CMComponent myComp2 = new CMComponent();
		ConnectionPoint cp2 = new CMConnectionPoint(3, 3, 5, 5);
		myComp2.addConnectionPoint(cp2);
		myComp.connectTo(cp1, cp2);
		System.out.println(myComp.getConnectionPoints());
		System.out.println(myComp.getConnections());
		System.out.println(myComp.getOrientation());
	}

	public JPanel getComponentView() {
		return view;
	}

	private class CMComponentView extends JPanel {
		private CMComponent myComponent;
		private BufferedImage image;
		public CMComponentView(CMComponent myComponent) {
			setLayout(null);
			//setOpaque(false);
			setBackground(Color.black);
			this.myComponent = myComponent;
			try {
				image = ImageIO.read(new FileInputStream("images/DIP-8-300.gif"));
			} catch (Exception e) {
				System.out.println("image not found");
			}
		}
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D) g.create();
			//g2d.drawRenderedImage(image, new AffineTransform());
			g2d.dispose();
		}
		public Dimension getPreferredSize() {
			System.out.println(image.getWidth());
			System.out.println(image.getHeight());
			return new Dimension(image.getWidth(), image.getHeight());
		}
	}
	
}