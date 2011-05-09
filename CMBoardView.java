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

public class CMBoardView extends JPanel implements Observer {
		private CMBoard board;
		private Component a;
		private Component b;
		private ConnectionPoint cpA;
		private ConnectionPoint cpB;
		private boolean connecting = false;
		private boolean oneSelected = false;
		private JPopupMenu popup;
		public CMBoardView(CMBoard board) {
			popup = new JPopupMenu();
			popup.setLightWeightPopupEnabled(false);
			setLayout(null);
			this.board = board; 
        	this.addMouseListener(new MouseHandler());
        	board.addObserver(this);
		}
		protected void paintChildren(Graphics g) {
				super.paintChildren(g);
				popup = new JPopupMenu();
				Graphics2D g2d = (Graphics2D) g.create();
				g2d.setRenderingHint(
				RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON
			);
				List<Connection> conns = board.listConnections();
				for(Connection myConn : conns) {
					System.out.println("inloop");
					ConnectionPoint[] ends = myConn.getEnds();
					Component[] compEnds = myConn.getComponentEnds();
					Point p1 = ends[0].getLocation();
					Dimension bounds1 = ends[0].getBounds();
					Point p2 = ends[1].getLocation();
					Dimension bounds2 = ends[1].getBounds();
					double x1 = p1.getX() + (bounds1.getWidth() /2) + compEnds[0].getPosition().getX();
					double y1 = p1.getY() + (bounds1.getHeight() /2 + compEnds[0].getPosition().getY());

					double x2 = p2.getX() + (bounds2.getWidth() /2) + compEnds[1].getPosition().getX();
					double y2 = p2.getY() + (bounds2.getHeight() /2) + compEnds[1].getPosition().getY();

					Line2D shape2 = new Line2D.Double(x1, y1, x2, y2);
					System.out.println(x1 + " " + y1 + " " + x2 + " " + y2);

					g2d.setStroke(
						new BasicStroke(
							2.0f,
							BasicStroke.CAP_ROUND,
							BasicStroke.JOIN_ROUND
						)
					);
					g2d.setColor(Color.red);
					g2d.draw(shape2);
					//shape2 = new Line2D.Double(100, 100, 300, 300);
					//g2d.draw(shape2);
				}
					g2d.dispose();
				
			}
		private class MouseHandler extends MouseMotionAdapter implements MouseListener{
			private Point source = new Point();
			public void mouseDragged(MouseEvent e) {
				if (!connecting) {
				java.awt.Component c = e.getComponent();
				//System.out.println(e.getSource() instanceof CMComponent);
				if (!(e.getSource() instanceof CMBoardView)) {
					int offsetX = (int) (e.getX() - source.getX());
					int offsetY = (int) (e.getY() - source.getY());
					c.setLocation(c.getX()+e.getX() - c.getWidth() / 2, c.getY()+e.getY());
					if (e.getSource() instanceof CMComponentView) {
						CMComponent myComponent = ((CMComponentView)e.getSource()).getComponent();
						myComponent.setPosition(c.getX()+e.getX() - c.getWidth() / 2, c.getY()+e.getY());
					}
					
				}
				repaint();
			//	invalidate();
				//validate();
			}
			}

			public void mouseClicked(MouseEvent e) {
				
				
			}

			public void mouseEntered(MouseEvent e) {
				
			}

			public void mouseExited(MouseEvent e) {
				
			}

			public void mousePressed(MouseEvent e) {
				boolean hi = false;
				source = e.getPoint();
				System.out.println(e.getPoint());
				//((JComponent)e.getSource()).setBackground(Color.blue);

				if (e.getSource() instanceof CMComponentView) {
					CMComponent clickedComp = ((CMComponentView) e.getSource()).getComponent();
					List<ConnectionPoint> connPts = clickedComp.getConnectionPoints();
					for(ConnectionPoint cp : connPts) {
						System.out.println(cp.checkPoint(source));
						if (cp.checkPoint(source)) {
							if (e.isPopupTrigger()) {
								System.out.println("hi");
								ArrayList<Connection> toDelList = new ArrayList<Connection>();
								for (Connection c : board.listConnections()) {
									ConnectionPoint[] ends = c.getEnds();
									if (ends[0].checkPoint(source) || ends[1].checkPoint(source)) {
										toDelList.add(c);
									}
								}
								for (Connection c : toDelList) {
									for (Component comp : board.listComponents()) {
										if (comp.getConnections().contains(c)) {
											System.out.println("removing");
											comp.getConnections().remove(c);
										}
									}
								}
								repaint();
						} else if(connecting && e.getButton() == MouseEvent.BUTTON1) {
								b = clickedComp;
								cpB = cp;
								System.out.println("logic check: " + (connecting && e.isPopupTrigger() == false));
								board.addConnection(a, cpA, b, cpB);
								repaint();
								connecting = false;
							}
							else if (!connecting && !e.isPopupTrigger()) {
								connecting = true;
								 a = clickedComp;
								 cpA = cp;
							}
						}
						else {
							if (e.isPopupTrigger()) {
								popup.show(CMBoardView.this,
                       			e.getXOnScreen(), e.getYOnScreen());
                      			removeSelected(e);
							}
						}
					}
				}
			}

			public void mouseReleased(MouseEvent e) {
				source = e.getPoint();
				System.out.println("popup trigger: " + e.isPopupTrigger());
				if (e.isPopupTrigger()) {
					popup.show(CMBoardView.this,
                       e.getXOnScreen(), e.getYOnScreen());
                    oneSelected = true;
                    removeSelected(e);
				}
			}

			public void removeSelected(MouseEvent e) {
				if (e.getSource() instanceof CMComponentView) {
					CMComponent clickedComp = ((CMComponentView) e.getSource()).getComponent();
					CMComponentView clickedCompView = (CMComponentView) e.getSource();
					clickedComp.clearConnections();
					board.deleteComponent(clickedComp);
					remove(clickedCompView);
					repaint();
				}
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
				cm.getComponentView().setLocation(0,0);
				add(cm.getComponentView());
				//cm.getComponentView().setBounds(0,0,109,101);
				cm.getComponentView().setSize(cm.getComponentView().getPreferredSize());
	
			//	invalidate();
			//	validate();
			}
	}
	private class PopUp extends JPopupMenu {
    JMenuItem anItem;
    JMenuItem anotherItem;
    public PopUp(){
    	setLightWeightPopupEnabled(false);
        anItem = new JMenuItem("Click Me!");
        anotherItem = new JMenuItem("ADFAKGHSDFJKASDFKJFKJASDFKADSFJSKADJFASKFKASDJFKAJFASLKJFLAKSJFLAKSJFLKAJSLFKAJSDFKJSDLKFJSDKLFJASDKLJFLSKDJFLKSADJFLKADJSLFKJASDLKFJASDKLJFLASKDJFLKASJDFLKASDF");
        anotherItem.setVisible(true);
        add(anItem);
        add(anotherItem);
    }
}
}