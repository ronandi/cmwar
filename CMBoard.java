package edu.mccc.cos210.cmwar;
import java.util.List;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;


/** 
* CMBoard for Circuit Maker with AutoRoute
* A board has Components and Connections, and manages them. 
* @author Rohit Kumar
* @version 0.1
*@to.do implement two sided boards vs single sided in model
*@to.do implement autoRoute() method
*@to.do implement ripUp() method
*@to.do implement load() method
*@to.do implement save() method
*@to.do implement undo / redo
*/
public class CMBoard extends Observable implements Board {
	/**
	*Holds components on the board
	*/
	private ArrayList<Component> components;
	private JPanel view = new CMBoardView(this);
	public CMBoard() {
		components = new ArrayList<Component>();
	}	

	/**
	* Adds a component to the board.
	* @param c A valid component to be added to the board.
	* @test.status ok
	*/
	public void addComponent(Component c) {
		components.add(c);
		setChanged();
		notifyObservers(c);
	}

	/**
	* Deletes a component on the board.
	* @param c A valid component already on the board.
	* @test.status ok
	*/
	public void deleteComponent(Component c) {
		int x = components.indexOf(c);
		components.remove(x);
	}

	/**
	* Returns a list of all the components on the board.
	* @return a List of all the Components already on the board.
	* @test.status ok
	*/
	public List<Component> listComponents() {
		return components;
	}

	/**
	* Returns a list of all the connections between components on the board.
	* @return Returns a list of all the connections between components on the board.
	* @test.status ok
	*/
	public List<Connection> listConnections() {
		ArrayList<Connection> connectionList = new ArrayList<Connection>();
		for (Component c : components) {
			connectionList.addAll(c.getConnections());
		}
		return connectionList;
	}

	/**
	* Adds a connection between two components 
	* @param a A Component to be connected.
	* @param b Opposite Component to be connected to A, and vice versa.
	* @test.status ok
	*/
	public void addConnection(Component a, ConnectionPoint cpA, Component b, ConnectionPoint cpB) {
		a.connectTo(cpA, cpB);
		b.connectTo(cpA, cpB);		
	}

	/** 
	* The long-dreaded method that does all the big work. Transforms the rat's nest into traces.
	* @to.do Implement this
	* @test.status no
	*/
	public void autoRoute() {
		
	}

	/**
	* Does the opposite of autoRoute(). Returns the traced board to a rat's nest.
	* @to.do Implement this
	* @test.status no
	*/
	public void ripUp() {
		
	}

	/**
	* Deletes all connections between componets on the board.
	* @test.status ok
	*/
	public void clearConnections() {
		for (Component c : components) {
			c.getConnections().clear();
		}
	}

	/**
	* Saves the board in its current state.
	* @to.do Implement this
	* @test.status no
	*/
	public void save() {
		
	}

	/**
	* Loads the board in the state it was saved in.
	* @to.do Implement this
	* @test.status no
	*/
	public void load() {

	}

	/**
	* Undos the last change to the Board
	* @to.do Implement this
	* @test.status no
	*/
	public void undoLast() {
		
	}

	/**
	* Undos the last change to the Board
	* @to.do Implement this
	* @test.status no
	*/
	public void redoLast() {
		
	}
	public static void main(String[] sa) throws Exception {
		CMBoard myBoard = new CMBoard();
		CMComponent myComp = new CMComponent();
		ConnectionPoint cp1 = new CMConnectionPoint(1, 1, 5, 5);
		myComp.rotate(Orientation.Left);
		myComp.setName("test comp 1");
		myComp.addConnectionPoint(cp1);
		myComp.setPosition(5, 6);
		myBoard.addComponent(myComp);
		System.out.println(myBoard.listComponents());
		myBoard.deleteComponent(myComp);
		System.out.println(myBoard.listComponents());
		myBoard.addComponent(myComp);
		CMComponent myComp2 = new CMComponent();
		ConnectionPoint cp2 = new CMConnectionPoint(3, 3, 5, 5);
		myComp2.addConnectionPoint(cp2);
		myBoard.addConnection(myComp, cp1, myComp2, cp2);
		System.out.println(myBoard.listConnections());
		myBoard.clearConnections();
		System.out.println(myBoard.listConnections());

	}
	public JPanel getBoardView() {
		return view;
	}
	private class CMBoardView extends JPanel implements Observer {
		private CMBoard board;
		public CMBoardView(CMBoard board) {
			this.board = board; 
        	this.addMouseListener(new MouseHandler());
        	board.addObserver(this);
		}
		private class MouseHandler extends MouseMotionAdapter implements MouseListener{
			private Point source = new Point();
			public void mouseDragged(MouseEvent e) {
				java.awt.Component c = e.getComponent();
				if (!(e.getSource() instanceof CMBoardView)) {
					int offsetX = (int) (e.getX() - source.getX());
					int offsetY = (int) (e.getY() - source.getY());
					c.setLocation(c.getX()+e.getX() - c.getWidth() / 2, c.getY()+e.getY());
					
				}
				repaint();
			//	invalidate();
				//validate();
			}

			public void mouseClicked(MouseEvent e) {
				
			}

			public void mouseEntered(MouseEvent e) {
				
			}

			public void mouseExited(MouseEvent e) {
				
			}

			public void mousePressed(MouseEvent e) {
				source = e.getPoint();
				System.out.println(e.getPoint());
				//((JComponent)e.getSource()).setBackground(Color.blue);
			}

			public void mouseReleased(MouseEvent e) {
				source = e.getPoint();
			}
		}
		public void update(Observable o, Object arg)  {
			System.out.println("here");
			if (arg instanceof Component) {
				System.out.println("adding component");
				Component c = (Component) arg;
				CMComponent cm = (CMComponent) c;
				cm.getComponentView().addMouseMotionListener(new MouseHandler());
				cm.getComponentView().addMouseListener(new MouseHandler());
				Point p = c.getPosition();
				//cm.getComponentView().setLocation(0,0);
				add(cm.getComponentView());
				cm.getComponentView().setBounds(0,0,0,0);
	
			//	invalidate();
				//validate();
			}
			
		}
	}
}