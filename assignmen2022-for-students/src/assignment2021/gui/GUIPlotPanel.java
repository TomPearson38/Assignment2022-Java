package assignment2021.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

import assignment2021.codeprovided.fitnesstracker.Participant;
import assignment2021.codeprovided.fitnesstracker.Tracker;
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

	private ArrayList<MeasurementAssociation> loadedData;
	
	private ArrayList<Participant> participants;
	
	public GUIPlotPanel(AbstractGUIPanel parentGUIPanel) {
		// TODO
		super(parentGUIPanel);
		participants = (ArrayList<Participant>) ((GUIPanel)parentGUIPanel).getAllParticpants();
		loadedData = new ArrayList<MeasurementAssociation>();

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
	
	public boolean addParticipantsToPlot(String selectedParticipantName, String selectedTrackerName) {
		boolean alreadyAddedTracker = false;
		boolean alreadyAddedParticipant = false;
		
		//Checks if the participant already exists
		for(MeasurementAssociation currentParticipant : loadedData) {
			if(currentParticipant.getName() == selectedParticipantName) {
				alreadyAddedParticipant = true;
				if(!currentParticipant.checkForTracker(selectedTrackerName)) {
					currentParticipant.addTracker(selectedTrackerName);
				}
				else {
					alreadyAddedTracker = true;
				}
				break;
			}
		}
		
		if(alreadyAddedTracker == true) {
			return false;
		}
		else if(alreadyAddedParticipant == true && alreadyAddedTracker == false) {
			return true;
			
			//REDRAW
		}
		else if(alreadyAddedParticipant == false){
			MeasurementAssociation newParticipant = new MeasurementAssociation(selectedParticipantName, selectedTrackerName);
			loadedData.add(newParticipant);
			
			//REDRAW
			
			return true;
		}
		
		//Data is false
		return false;
	}

	public void removeAllCurves() {
		loadedData = new ArrayList<MeasurementAssociation>();
		
		/////REPLOT
	}

}
