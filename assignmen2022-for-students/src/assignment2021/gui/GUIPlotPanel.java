package assignment2021.gui;

import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import assignment2021.codeprovided.fitnesstracker.Participant;
import assignment2021.codeprovided.fitnesstracker.Tracker;
import assignment2021.codeprovided.fitnesstracker.measurements.Measurement;
import assignment2021.codeprovided.fitnesstracker.measurements.MeasurementType;
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

	//Stores  data that has been plotted already
	private ArrayList<MeasurementAssociation> loadedData;
	
	private ArrayList<Participant> participants;
	
	private String maxValueName = "";
	
	public GUIPlotPanel(AbstractGUIPanel parentGUIPanel) {
		super(parentGUIPanel);
		//Participants are loaded
		participants = (ArrayList<Participant>) ((GUIPanel)parentGUIPanel).getAllParticpants();
		//Initialises the stored data
		loadedData = new ArrayList<MeasurementAssociation>();

	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Dimension d = getSize();		
		
		Graphics2D g2 = (Graphics2D) g;
		
		//Boundaries of the graph are defined
		final double CHARTSTARTX = d.width/20;
		final double CHARTENDX = d.width - d.width/20;
		final double CHARTSTARTY = formatHeight(d.height/20);
		final double CHARTENDY = d.height/20;
		final double CHARTINCREMENTX = (1.8/20) * d.width;
		final double CHARTINCREMENTY = (1.8/20) * d.height;
		
		//DRAW AXIS	
		Line2D yAxis = new Line2D.Double(CHARTSTARTX , CHARTSTARTY , CHARTSTARTX , CHARTENDY);
		g2.draw(yAxis);
		Line2D xAxis = new Line2D.Double(CHARTSTARTX , CHARTSTARTY , CHARTENDX, CHARTSTARTY);
		g2.draw(xAxis);
		
		//find the scale modifiers, these are used to ensure that all the data fills the graph in the best
		//possible way that accounts for the range of the selected data
		double[] maxValue = findMaxValueFromAllMeasurements();
		double YSCALE = (0.9 * d.height)/maxValue[0];
		double XSCALE = (0.9 * d.width)/maxValue[1];
		
		//Font size is increased
		g2.setFont(new Font("SANS_SERIF", Font.PLAIN, 15));;
		
		//draw xAxis Scale
		int count = 1;
		for(double i = CHARTSTARTX; i <= CHARTENDX; i += CHARTINCREMENTX) {
			//Dotted lines on the XAxis
			Line2D marker = new Line2D.Double(i, CHARTSTARTY, i, CHARTSTARTY + (d.height/80));
			g2.draw(marker);
			
			//Value of the lines are created and plotted
			double valueOfScale = ((maxValue[1] * count)/10);
			g2.drawString("" + (int)valueOfScale, (int)i,(int)CHARTSTARTY + (int)(d.height/80));
			count++;
		}
		
		//draw yAxis Scale
		count = 0;
		for(double i = CHARTSTARTY; i >= CHARTENDY; i -= CHARTINCREMENTY) {
			//Dotted lines on the YAxis
			Line2D marker = new Line2D.Double(CHARTSTARTX - (d.width/160), i, CHARTSTARTX, i);
			g2.draw(marker);
			
			//Value of the lines are created and plotted
			double valueOfScale = ((maxValue[0] * count)/10);
			g2.drawString("" + (int)valueOfScale, (int)(CHARTSTARTX - (d.width/80)),(int) i);
			count++;
		}
		
		//Count to determine the position in the key
		int keyPos = 1;
		
		//Used in calculating the distance width
		int numberOfTrackers = findNumberOfTrackers();
		int currentNumOfTracker = 0;
		
		//plot the data on the graph
		for(MeasurementAssociation currentSelection : loadedData) {
			//Values are reset
			double[][] dataConvertedToCoordinates = null;
			Participant foundParticipant = null;
			
			//Participant is found which relates to the String value stored in the MeasurementAssociation
			for(Participant currentParticipant : participants) {
				if(currentParticipant.getName() == currentSelection.getName()) {
					foundParticipant = currentParticipant;
					break;
				}
			}
			
			//Checks that participant has been found
			if(foundParticipant != null) {
				//Each tracker from the participant is checked and if it matches on that has been requested to be presented
				//by the user then it is added to the graph
				for(Tracker currentTracker : foundParticipant.getAllTrackers()) {
					if(currentSelection.checkForTracker(currentTracker.getName())) {
						//Measurements for the current selection are loaded
						List<Measurement> relevantMeasurmeents = currentTracker.getMeasurementsForType(((GUIPanel)parentGUIPanel).getSelectedMeasurementType());
						
						//Coordinates for the data created
						dataConvertedToCoordinates = new double[relevantMeasurmeents.size()][2];
						count = 0;
						Double prevValue = 0.0;
						
						//The data's value is scaled to match the scale calculated before. Then it is offset so that it fits on the current graph size
						for(Measurement currentMeasurement : relevantMeasurmeents) {
							dataConvertedToCoordinates[count][0] = (currentMeasurement.getCount() * XSCALE) + CHARTSTARTX;
							
							//If increments selected
							if((((GUIPanel)parentGUIPanel).isShowIncrementsSelected()) && (((GUIPanel)parentGUIPanel).getSelectedMeasurementType() != MeasurementType.DISTANCE)){
								//first value so set to starting position of graph
								if(count == 0) {
									prevValue = currentMeasurement.getValue().doubleValue();
									dataConvertedToCoordinates[0][1] = d.getHeight() - CHARTENDY;
								}
								else {
									//Absolute value of difference is calculated as the measurement instead
									dataConvertedToCoordinates[count][1] = formatHeight(Math.abs(prevValue - currentMeasurement.getValue().doubleValue()) * YSCALE) - CHARTENDY;
									prevValue = currentMeasurement.getValue().doubleValue();
								}
							}
							else {
								//Normal value is used for height
								dataConvertedToCoordinates[count][1] = formatHeight(currentMeasurement.getValue().doubleValue() * YSCALE) - CHARTENDY;
							}
							
							count++;
						}
						g2.setColor(currentSelection.getColour(currentTracker.getName()));						
						
						//If the measurement selected isn't distance then it will be plotted as a line graph and not a bar chart
						if(((GUIPanel)parentGUIPanel).getSelectedMeasurementType() != MeasurementType.DISTANCE) {
			                g2.setStroke(new BasicStroke(3));
							for(int i = 0; i < dataConvertedToCoordinates.length - 1; i++) {
								//Lines are drawn
								Line2D marker = new Line2D.Double(dataConvertedToCoordinates[i][0], dataConvertedToCoordinates[i][1],
										dataConvertedToCoordinates[i + 1][0], dataConvertedToCoordinates[i + 1][1]);						
								
								g2.draw(marker);
							}
						}
						//Data is plotted as a bar chart
						else {
							for(int i = 0; i < dataConvertedToCoordinates.length; i++) {
								//Bars are drawn
								g2.fill((Shape) new Rectangle2D.Double((currentNumOfTracker * ((0.9 * d.width)) / numberOfTrackers) + CHARTSTARTX,
										dataConvertedToCoordinates[i][1], (0.9 * d.width)/numberOfTrackers , CHARTSTARTY - dataConvertedToCoordinates[i][1]));
								currentNumOfTracker++;
							}
						}
						
						//The key for the value is added to the key located in the top left of the graph
						g2.drawString("\u25A0" + " - " + foundParticipant.getName() + " - Tracker " + currentTracker.getName(),
								(int)(CHARTSTARTX + 10), (int)(CHARTENDY + (keyPos * d.height * 0.03)));
						keyPos++;
						
					}
				}
			}
			
		}
		
		//Updates the displayed data text box
		updateDetails();
	}
	
	/**
	 * Participants selected by the user are added to the list of participants plotted
	 * @param selectedParticipantName Participant to be added
	 * @param selectedTrackerName Tracker to be added
	 * @return True if the participant is added, false if the participant already exists
	 */
	public boolean addParticipantsToPlot(String selectedParticipantName, String selectedTrackerName) {
		//Initialisation
		boolean alreadyAddedTracker = false;
		boolean alreadyAddedParticipant = false;
		
		//Checks if the participant already exists
		for(MeasurementAssociation currentParticipant : loadedData) {
			//Finds participant name
			if(currentParticipant.getName() == selectedParticipantName) {
				alreadyAddedParticipant = true;
				//Checks if tracker is associated to plot values
				if(!currentParticipant.checkForTracker(selectedTrackerName)) {
					currentParticipant.addTracker(selectedTrackerName);
				}
				else {
					alreadyAddedTracker = true;
				}
				break;
			}
		}
		
		if(alreadyAddedTracker) {
			return false;
		}
		else if(alreadyAddedParticipant && !alreadyAddedTracker) {
			//Change in stored values so graph is updated
			this.repaint();
			return true;
		}
		else if(!alreadyAddedParticipant){
			//New participant
			MeasurementAssociation newParticipant = new MeasurementAssociation(selectedParticipantName, selectedTrackerName);
			loadedData.add(newParticipant);
			
			//REDRAW
			this.repaint();
			
			return true;
		}
		
		//Data is false
		return false;
	}

	/**
	 * Removes all plotted data and resets all values
	 */
	public void removeAllCurves() {
		loadedData = new ArrayList<MeasurementAssociation>();
		
		/////REPLOT
		this.repaint();

	}
	
	/**
	 * Converts height to opposite value to compensate for java plotting graph from top left corner
	 * @param provided Value to be changed
	 * @return Formatted value
	 */
	private double formatHeight(double provided) {
		return (this.getHeight() - provided);
	}
	
	/**
	 * Finds the maximum value from all the measurements selected to be plotted
	 * @return Maximum value
	 */
	private double[] findMaxValueFromAllMeasurements() {
		//Formatted max value array in which: {Max Value, Max Count}
		double[] maxValue = new double[]{0.0, 0.0};
		
		for(MeasurementAssociation currentSelection : loadedData) {
			Participant foundParticipant = null;
			
			//Searches for participant
			for(Participant currentParticipant : participants) {
				if(currentParticipant.getName() == currentSelection.getName()) {
					foundParticipant = currentParticipant;
					break;
				}
			}
			
			//Checks for participant presence
			if(foundParticipant != null) {
				for(Tracker currentTracker : foundParticipant.getAllTrackers()) {
					//Checks for matching tracker in selected trackers to be plotted
					if(currentSelection.checkForTracker(currentTracker.getName())) {
						//Iterates through all associated measurements
						for(Measurement currentMeasurement : currentTracker.getMeasurementsForType(((GUIPanel)parentGUIPanel).getSelectedMeasurementType())) {
							//If value is bigger it is saved
							if(currentMeasurement.getValue().doubleValue() > maxValue[0]) {
								maxValue[0] = currentMeasurement.getValue().doubleValue();
								maxValueName = foundParticipant.getName();
							}
						}
						//If count of position is larger it is saved
						double count = currentTracker.getMeasurementsForType(((GUIPanel)parentGUIPanel).getSelectedMeasurementType()).size();
						if(maxValue[1] < count) {
							maxValue[1] = count;
						}
					}
				}
			}
			
		}
		
		return maxValue;
	}
	
	/**
	 * Number of total trackers is calculated for plotted data
	 * @return The total number of trackers
	 */
	private int findNumberOfTrackers() {
		int count = 0;
		for(MeasurementAssociation currentSelection : loadedData) {
			count += currentSelection.getAllTrackers().size();
		}
		return count;
		
	}
	
	/**
	 * Graph is redrawn
	 */
	public void redrawGraph() {
		this.repaint();
	}

	/**
	 * Updates the details that are displayed on the current selection summary
	 */
	private void updateDetails() {
		String text = "";
		if((loadedData == null) || (loadedData.isEmpty())) {
			text = "No Data Selected";
		}
		else {	
			double[] maxValue = findMaxValueFromAllMeasurements();
			text += "Number of Participants Selected: " + loadedData.size();
			text += "\nNumber of Trackers currently Selected: " + findNumberOfTrackers();
			text += "\nMax Value is currently \"" + maxValue[0] + "\" and it belongs to Participant \"" + maxValueName + "\"";
		}
		
		
		
		((GUIPanel)parentGUIPanel).updateCurrentSelectionSummary(text);
	}
}
