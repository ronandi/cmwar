package edu.mccc.cos210.cmwar;

import javax.swing.JInternalFrame;
import javax.swing.JDesktopPane;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.KeyStroke;
import javax.swing.BorderFactory;
import javax.swing.border.TitledBorder;
import javax.swing.Box;
import javax.swing.BoxLayout;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


/**
 * CircuitMaker with AutoRouter. Advanced Printed Circuit Board Design. Pure Zen.
 *
 * @author Rohit Kumar
 *
 * @version 0.9
 */
public class CircuitMaker extends JFrame implements ActionListener {
	private JDesktopPane desktop;
	private CMBoard myBoard;
	private WorkInternalFrame workarea;

	/**
	 *
	 * Constructor. Create the original object
	 */
	public CircuitMaker() {
		super("Circuit Maker with AutoRouter");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenSize.width, screenSize.height);
		desktop = new JDesktopPane();
		setContentPane(desktop);
		getContentPane().setBackground(Color.gray); //for that ubiquitous "classic" look 
		createToolbar();
		
		myBoard = new CMBoard();
		createWorkArea();
		setJMenuBar(createMenuBar());
		setResizable(true);
		setVisible(true);
		//setLayout(null);
	}
	private void createToolbar() {
		ToolInternalFrame toolbar = new ToolInternalFrame();
		JPanel componentPanel = new JPanel();
		TitledBorder componentPanelTitle;
		componentPanelTitle = BorderFactory.createTitledBorder("Component Select");
		componentPanel.setBorder(componentPanelTitle);
		toolbar.add(componentPanel);

		ImageIcon icon = new ImageIcon("images/DIP-8-300-scaled.gif");
		JButton dipButton = new JButton(icon);
		dipButton.setActionCommand("dip-8-300");
		dipButton.addActionListener(this);
		dipButton.setToolTipText("DIP-8 300 Mil");
		componentPanel.add(dipButton);

		ImageIcon icon2 = new ImageIcon("images/DIP-14-300-scaled.gif");
		JButton dip14300Button = new JButton(icon2);
		dip14300Button.setActionCommand("dip-14-300");
		dip14300Button.addActionListener(this);
		dip14300Button.setToolTipText("DIP-14 300 Mil");
		componentPanel.add(dip14300Button);

		ImageIcon icon3 = new ImageIcon("images/DIP-16-300-scaled.gif");
		JButton dip16300 = new JButton(icon3);
		dip16300.setActionCommand("dip-16-300");
		dip16300.addActionListener(this);
		dip16300.setToolTipText("DIP-16 300 Mil");
		componentPanel.add(dip16300);

		JButton autoroute = new JButton("AutoRoute");
		autoroute.setAlignmentX(CENTER_ALIGNMENT);
		autoroute.setActionCommand("autoroute");
		autoroute.addActionListener(this);
		JButton ripup = new JButton("Rip Up");
		ripup.setAlignmentX(CENTER_ALIGNMENT);
		ripup.setActionCommand("ripup");
		ripup.addActionListener(this);
		JButton routemore = new JButton("Route More");
		routemore.setAlignmentX(CENTER_ALIGNMENT);
		routemore.setActionCommand("routemore");
		routemore.addActionListener(this);
		JButton redoroute = new JButton("Redo Route");
		redoroute.setAlignmentX(CENTER_ALIGNMENT);
		redoroute.setActionCommand("redoroute");
		redoroute.addActionListener(this);

		Box buttonBox = new Box(BoxLayout.Y_AXIS);
		//buttonBox.add(Box.createHorizontalGlue());
		buttonBox.add(autoroute);
		//buttonBox.add(Box.createHorizontalGlue());
		buttonBox.add(ripup);
		buttonBox.add(routemore);
		buttonBox.add(redoroute);

		toolbar.add(buttonBox, BorderLayout.SOUTH);

		toolbar.setVisible(true);
		desktop.add(toolbar);
	}
	
	private void createWorkArea() {
		workarea = new WorkInternalFrame();
		workarea.add(myBoard.getBoardView());
		workarea.setVisible(true);
		desktop.add(workarea);
	}
	private JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		
		//Set up File Menu
		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_F);
		menuBar.add(fileMenu);

		//Set up File > New
		JMenuItem newItem = new JMenuItem("New");
		newItem.setMnemonic(KeyEvent.VK_N);
		newItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        newItem.setActionCommand("new");
        newItem.addActionListener(this);
        fileMenu.add(newItem);

        //Set up File > Save
        JMenuItem saveItem = new JMenuItem("Save");
		saveItem.setMnemonic(KeyEvent.VK_S);
		saveItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        saveItem.setActionCommand("save");
        saveItem.addActionListener(this);
        fileMenu.add(saveItem);

        //Set up File > Open
        JMenuItem openItem = new JMenuItem("Open");
		openItem.setMnemonic(KeyEvent.VK_O);
		openItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        openItem.setActionCommand("open");
        openItem.addActionListener(this);
        fileMenu.add(openItem);

        //Set up File > Exit
        JMenuItem exitItem = new JMenuItem("Exit");
		exitItem.setMnemonic(KeyEvent.VK_X);
        exitItem.setActionCommand("exit");
        exitItem.addActionListener(this);
        fileMenu.add(exitItem);

        //Set up Edit Menu
		JMenu editMenu = new JMenu("Edit");
		editMenu.setMnemonic(KeyEvent.VK_E);
		menuBar.add(editMenu);

		//Set up Edit > Undo
		JMenuItem undoItem = new JMenuItem("Undo");
		undoItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
        undoItem.setActionCommand("undo");
        undoItem.addActionListener(this);
        editMenu.add(undoItem);

        //Set up Edit > Redo
		JMenuItem redoItem = new JMenuItem("Redo");
		redoItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_Y, ActionEvent.CTRL_MASK));
        redoItem.setActionCommand("redo");
        redoItem.addActionListener(this);
        editMenu.add(redoItem);

        return menuBar;
	}

	/**
	 * In the begining there was main. main is where it all
	 * begins. Blah, blah, blah.
	 *
	 * @param sa The command line arguments
	 */
	public static void main(String[] sa) {
		new CircuitMaker();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("dip-8-300")) {
			CMComponent dip8Component = new CMComponent("dip-8-300");
			CMConnectionPoint connPoint = new CMConnectionPoint (5, 5, 20, 15);
			CMConnectionPoint connPoint1 = new CMConnectionPoint (5, 30, 20, 15);
			CMConnectionPoint connPoint2 = new CMConnectionPoint (5, 55, 20, 15);
			CMConnectionPoint connPoint3 = new CMConnectionPoint (5, 80, 20, 15);
			CMConnectionPoint connPoint4 = new CMConnectionPoint (80, 5, 20, 15);
			CMConnectionPoint connPoint5 = new CMConnectionPoint (80, 30, 20, 15);
			CMConnectionPoint connPoint6 = new CMConnectionPoint (80, 55, 20, 15);
			CMConnectionPoint connPoint7 = new CMConnectionPoint (80, 80, 20, 15);
			dip8Component.addConnectionPoint(connPoint);
			dip8Component.addConnectionPoint(connPoint1);
			dip8Component.addConnectionPoint(connPoint2);
			dip8Component.addConnectionPoint(connPoint3);
			dip8Component.addConnectionPoint(connPoint4);
			dip8Component.addConnectionPoint(connPoint5);
			dip8Component.addConnectionPoint(connPoint6);
			dip8Component.addConnectionPoint(connPoint7);
			myBoard.addComponent(
				dip8Component
			);
			//invalidate();
			//validate();
			repaint();
		}
		if (e.getActionCommand().equals("autoroute")) {
			System.out.println(myBoard.listConnections());
		}
		if (e.getActionCommand().equals("dip-14-300")) {
			CMComponent dip8Component = new CMComponent("dip-14-300");
			CMConnectionPoint connPoint = new CMConnectionPoint (5, 5, 20, 15);
			CMConnectionPoint connPoint1 = new CMConnectionPoint (5, 30, 20, 15);
			CMConnectionPoint connPoint2 = new CMConnectionPoint (5, 55, 20, 15);
			CMConnectionPoint connPoint3 = new CMConnectionPoint (5, 80, 20, 15);
			CMConnectionPoint connPoint8 = new CMConnectionPoint (5, 105, 20, 15);
			CMConnectionPoint connPoint9 = new CMConnectionPoint (5, 130, 20, 15);
			CMConnectionPoint connPoint10 = new CMConnectionPoint (5, 155, 20, 15);
			CMConnectionPoint connPoint4 = new CMConnectionPoint (80, 5, 20, 15);
			CMConnectionPoint connPoint5 = new CMConnectionPoint (80, 30, 20, 15);
			CMConnectionPoint connPoint6 = new CMConnectionPoint (80, 55, 20, 15);
			CMConnectionPoint connPoint7 = new CMConnectionPoint (80, 80, 20, 15);
			CMConnectionPoint connPoint11 = new CMConnectionPoint (80, 105, 20, 15);
			CMConnectionPoint connPoint12 = new CMConnectionPoint (80, 130, 20, 15);
			CMConnectionPoint connPoint13 = new CMConnectionPoint (80, 155, 20, 15);
			dip8Component.addConnectionPoint(connPoint);
			dip8Component.addConnectionPoint(connPoint1);
			dip8Component.addConnectionPoint(connPoint2);
			dip8Component.addConnectionPoint(connPoint3);
			dip8Component.addConnectionPoint(connPoint4);
			dip8Component.addConnectionPoint(connPoint5);
			dip8Component.addConnectionPoint(connPoint6);
			dip8Component.addConnectionPoint(connPoint7);
			dip8Component.addConnectionPoint(connPoint8);
			dip8Component.addConnectionPoint(connPoint9);
			dip8Component.addConnectionPoint(connPoint10);
			dip8Component.addConnectionPoint(connPoint11);
			dip8Component.addConnectionPoint(connPoint12);
			dip8Component.addConnectionPoint(connPoint13);

			myBoard.addComponent(
				dip8Component
			);
			//invalidate();
			//validate();
			repaint();
		}
		if (e.getActionCommand().equals("dip-16-300")) {
			CMComponent dip8Component = new CMComponent("dip-16-300");
			CMConnectionPoint connPoint = new CMConnectionPoint (5, 5, 20, 15);
			CMConnectionPoint connPoint1 = new CMConnectionPoint (5, 30, 20, 15);
			CMConnectionPoint connPoint2 = new CMConnectionPoint (5, 55, 20, 15);
			CMConnectionPoint connPoint3 = new CMConnectionPoint (5, 80, 20, 15);
			CMConnectionPoint connPoint8 = new CMConnectionPoint (5, 105, 20, 15);
			CMConnectionPoint connPoint9 = new CMConnectionPoint (5, 130, 20, 15);
			CMConnectionPoint connPoint10 = new CMConnectionPoint (5, 155, 20, 15);
			CMConnectionPoint connPoint14 = new CMConnectionPoint (5, 180, 20, 15);
			CMConnectionPoint connPoint4 = new CMConnectionPoint (80, 5, 20, 15);
			CMConnectionPoint connPoint5 = new CMConnectionPoint (80, 30, 20, 15);
			CMConnectionPoint connPoint6 = new CMConnectionPoint (80, 55, 20, 15);
			CMConnectionPoint connPoint7 = new CMConnectionPoint (80, 80, 20, 15);
			CMConnectionPoint connPoint11 = new CMConnectionPoint (80, 105, 20, 15);
			CMConnectionPoint connPoint12 = new CMConnectionPoint (80, 130, 20, 15);
			CMConnectionPoint connPoint13 = new CMConnectionPoint (80, 155, 20, 15);
			CMConnectionPoint connPoint15 = new CMConnectionPoint (80, 180, 20, 15);
			dip8Component.addConnectionPoint(connPoint);
			dip8Component.addConnectionPoint(connPoint1);
			dip8Component.addConnectionPoint(connPoint2);
			dip8Component.addConnectionPoint(connPoint3);
			dip8Component.addConnectionPoint(connPoint4);
			dip8Component.addConnectionPoint(connPoint5);
			dip8Component.addConnectionPoint(connPoint6);
			dip8Component.addConnectionPoint(connPoint7);
			dip8Component.addConnectionPoint(connPoint8);
			dip8Component.addConnectionPoint(connPoint9);
			dip8Component.addConnectionPoint(connPoint10);
			dip8Component.addConnectionPoint(connPoint11);
			dip8Component.addConnectionPoint(connPoint12);
			dip8Component.addConnectionPoint(connPoint13);
			dip8Component.addConnectionPoint(connPoint14);
			dip8Component.addConnectionPoint(connPoint15);

			myBoard.addComponent(
				dip8Component
			);
			//invalidate();
			//validate();
			repaint();
		}
		
	}

	private class ToolInternalFrame extends JInternalFrame {
		public ToolInternalFrame() {
			super("Control Toolbar",
				false,
				false,
				false,
				false
			);
			setSize(150, 600);
			setLocation(50, 125);
			
		}
	}

	private class WorkInternalFrame extends JInternalFrame {
		private MyPopupMenu popupmenu;
		public WorkInternalFrame() {
			super("Work Area",
				false,
				false,
				false,
				false
			);
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			setSize(screenSize.width - 500, screenSize.height - 200);
			setLocation(250, 50);
		//	setLayout(null);
			popupmenu = new MyPopupMenu();
			//addMouseListener(this);
		}
		public void mouseClicked(MouseEvent arg0) {
		
	}

	public void mouseEntered(MouseEvent arg0) {
		
	}

	public void mouseExited(MouseEvent arg0) {
		
	}

	public void mousePressed(MouseEvent arg0) {
		
	}

	public void mouseReleased(MouseEvent e) {
		if(e.isPopupTrigger()){
			popupmenu.show(e.getComponent(),
                       e.getX(), e.getY());
		}
		
	}
	}

	private class MyPopupMenu extends JPopupMenu {
		private JMenuItem menu1;

		public MyPopupMenu() {
			menu1 = new JMenuItem("Menu Item 1");
			add(menu1);
			setLightWeightPopupEnabled(true);
		}
	}

}
