package assignment2021.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import assignment2021.codeprovided.gui.AbstractGUIPanel;
import assignment2021.codeprovided.gui.BasicGUIPlotPanel;

/**
 * A class to represent the GUI panel where the selected curves will be plot
 * using Java 2D. You are expected to write this class.
 *
 */

public class GUIPlotPanel extends BasicGUIPlotPanel {

	/**
	 * Generated Serial version UID
	 */
	private static final long serialVersionUID = -1482643158587603732L;

	public GUIPlotPanel(AbstractGUIPanel parentGUIPanel) {
		// TODO
		super(parentGUIPanel);

	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// TODO
		// Tip 1: Here you will need to access the list of participants,
		// and the GUI selections (e.g. combo boxes and checkboxes)
		System.out.println("Is display average/s selected? " + 
				this.parentGUIPanel.isShowAverageSelected());
		// Tip 2: The classes Participant and Tracker have a wide range of methods
		// that will be vital to get the data points of the curves that you need to plot
		// in this panel
		// Tip 3: See how you can use Java 2D to draw a single line. 
		// Please, replace the following code.
		Dimension d = getSize();
		Graphics2D g2 = (Graphics2D) g;
		Line2D l = new Line2D.Double(0, 0, d.width, d.height);
		g2.draw(l);
	}
}
