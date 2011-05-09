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

public class CMComponentView extends JPanel {
		private CMComponent myComponent;
		private BufferedImage image;
		public CMComponentView(CMComponent myComponent) {
			setLayout(null);
			setOpaque(false);
			setBackground(Color.black);
			this.myComponent = myComponent;
			try {
				image = ImageIO.read(new FileInputStream("images/" + myComponent.getName() +".gif"));
			} catch (Exception e) {
				System.out.println("image not found");
			}
			this.addMouseListener(new MouseHandler());
		}
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D) g.create();
			AffineTransform matrix = new AffineTransform();
			//matrix.rotate(Math.PI / 2, image.getWidth() / 2, image.getHeight() / 2);
			//g2d.rotate(Math.PI / 2);
			g2d.drawRenderedImage(image, matrix);
			//g2d.setTransform(matrix);
			g2d.dispose();
		}
		protected void paintChildren(Graphics g) {
			super.paintChildren(g);
			Graphics2D g2d = (Graphics2D) g.create();
			//Shape s = new Rectangle2D.Double(80.0, 5.0, 20.0, 15.0);
			g2d.setColor(Color.blue);
			//g2d.draw(s);
			g2d.dispose();
		}
		public Dimension getPreferredSize() {
			System.out.println(image.getWidth());
			System.out.println(image.getHeight());
			return new Dimension(image.getWidth(), image.getHeight());
		}
		public CMComponent getComponent() {
			return myComponent;
		}
		private class MouseHandler extends MouseMotionAdapter implements MouseListener{
			private Point source = new Point();
			private ConnectionPoint cp1;
			private ConnectionPoint cp2;
			private boolean connecting = false;
			public void mouseDragged(MouseEvent e) {
			}

			public void mouseClicked(MouseEvent e) {
				
			}

			public void mouseEntered(MouseEvent e) {
				
			}

			public void mouseExited(MouseEvent e) {
				
			}

			public void mousePressed(MouseEvent e) {
				source = e.getPoint();
				
				//System.out.println("checking...");
				//System.out.println(e.getSource() instanceof CMComponentView);
				
				
				System.out.println(e.getPoint());
				//((JComponent)e.getSource()).setBackground(Color.blue);
			}

			public void mouseReleased(MouseEvent e) {
				source = e.getPoint();
			}
		}
	}