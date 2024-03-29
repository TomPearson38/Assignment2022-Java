package assignment2021.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import assignment2021.codeprovided.fitnesstracker.Participant;
import assignment2021.codeprovided.fitnesstracker.Tracker;
import assignment2021.codeprovided.fitnesstracker.measurements.MeasurementType;
import assignment2021.codeprovided.gui.AbstractGUIPanel;

public class GUIPanel extends AbstractGUIPanel {

	private static final long serialVersionUID = -1257936627636425453L;

	private ArrayList<MeasurementAssociation> loadedData;
	
	MeasurementType currentChartType = MeasurementType.DISTANCE;
	
	public GUIPanel(Collection<Participant> participants) {
		super(participants);
		loadedData = new ArrayList<MeasurementAssociation>();
		dataSummaryGeneration();
	}

	@Override
	protected void loadTrackers() {
		Collection<String> trackerNames = ((Participant) participants.toArray()[0]).getAllTrackerNames();
		trackersArray = new String[trackerNames.size() + 1];
		trackersArray[0] = "all";
		int i = 1;
		for (String trackerName : trackerNames) {
			trackersArray[i] = trackerName;
			i++;
		}
		System.out.println(Arrays.toString(trackersArray));
	}
	
	@Override
	protected void loadParticipantsNames() {
		int i = 0;
		participantsArray = new String[participants.size()];
		for (Participant p : participants) {
			participantsArray[i] = p.getName();
			i++;
		}
		System.out.println(Arrays.toString(participantsArray));
	}
	
	
	@Override
	public void addListeners() {
		//Add curves
		addCurvesButton.addActionListener(new pageEventListener());
		
		//Remove curves
		removeAllCurvesButton.addActionListener(new pageEventListener());
				
		//Shows the increments in the data
		cbIncrements.addItemListener(new checkBoxListener());
		
		//Updates the data to when these are changed
		cbAverageValue.addItemListener(new checkBoxListener());
		
		cbMaxMinValues.addItemListener(new checkBoxListener());
		
		//Change the measurement Type displayed to whatever is listed
		comboListMeasurementType.addActionListener(new pageEventListener());
	}

	@Override
	public String getSelectedParticipantName() {
		Object current = comboListParticipants.getSelectedItem();
		return current.toString();
	}

	@Override
	public String getSelectedTrackersNames() {
		String current = comboListTrackers.getSelectedItem().toString();
		return current;
	}

	public Collection<Participant> getAllParticpants(){
		return participants;
	}
	
	public ArrayList<MeasurementAssociation> getLoadedData(){
		return loadedData;
	}
	
	@Override
	public MeasurementType getSelectedMeasurementType() {
		String measurementName = comboListMeasurementType.getSelectedItem().toString();
		MeasurementType current = MeasurementType.fromMeasurementName(measurementName);
		return current;
	}

	@Override
	public boolean isShowAverageSelected() {
		if(cbAverageValue.isSelected()) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isShowMinMaxSelected() {
		if(cbMaxMinValues.isSelected()) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isShowIncrementsSelected() {
		if(cbIncrements.isSelected()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Calculates the data to be added to the general summary text box
	 */
	private void dataSummaryGeneration() {
		int numOfParticipants = participants.size();
		ArrayList<String> foundTrackers = new ArrayList<String>();
		int numOfFemale = 0;
		int numOfMale = 0;
		int totalNumOfMeasurements = 0;

		for(Participant currentParticipant : participants) {
			if(currentParticipant.getGender().equals("f")) {
				numOfFemale++;
			}
			else {
				numOfMale++;
			}
			Collection<Tracker> trackers = currentParticipant.getAllTrackers();
			for(Tracker currentTracker : trackers) {
				totalNumOfMeasurements += currentTracker.getAllMeasurements().size();
				//Checks to see the tracker has been found previously
				if(!foundTrackers.contains(currentTracker.getName())){
					foundTrackers.add(currentTracker.getName());
				}
			}
		}
		
		//Result string is generated
		String result = "";
		result += "Total Number of Participants: " + numOfParticipants;
		result += "\nTotal Number of Trackers: " + foundTrackers.size();
		result += "\nNumber of Male Participants: " + numOfMale;
		result += "\nNumber of Female Participants:" + numOfFemale;
		result += "\nTotal Number of Measurements Across All Participants: " + totalNumOfMeasurements;
		
		//Text is changed
		datasetSummaryTextArea.setText(result); 	
	}
	
	/**
	 * Updates the details that are displayed on the current selection summary
	 * @param text New Data to be displayed
	 */
	public void updateCurrentSelectionSummary(String text) {
		if(text != null) {
			visualisedCurvesDetailsTextArea.setText(text);
		}
	}
	
	/**
	 * Action listener class for the page elements
	 * @author tomap
	 * 15/05/2022
	 */
	class pageEventListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			//Used to identify what triggered the action event
			Object source = e.getSource();
			if(source == addCurvesButton) {
				String selectedParticipantName = getSelectedParticipantName();
				String selectedTrackerName = getSelectedTrackersNames();
				boolean isDataUpdated;
				
				//Adds every tracker related to the participant
				if(selectedTrackerName == "all") {					
					Collection<String> trackerNames = ((Participant) participants.toArray()[0]).getAllTrackerNames();
					for(String currentTracker : trackerNames) {
						//Data is updated
						isDataUpdated = visualisedCurvesPanel.addParticipantsToPlot(selectedParticipantName, currentTracker);
						System.out.println(isDataUpdated);
					}
					
				}
				else {
					//Only individual participant is added
					isDataUpdated = visualisedCurvesPanel.addParticipantsToPlot(selectedParticipantName, selectedTrackerName);
					System.out.println(isDataUpdated);
				}
				
			}
			//Chart is set to default
			else if(source == removeAllCurvesButton) {
				visualisedCurvesPanel.removeAllCurves();
			}
			//Chart type is changed
			else if(source == comboListMeasurementType) {
				MeasurementType currentType = getSelectedMeasurementType();
				if(currentChartType != currentType) {
					currentChartType = currentType;
					visualisedCurvesPanel.redrawGraph();
				}
				
			}
		}
		
	}
	
	/**
	 * Item listener for page elements
	 * @author tomap
	 * 15/05/2022
	 */
	class checkBoxListener implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {
			Object source = e.getSource();
			if(source == cbIncrements) {
				visualisedCurvesPanel.redrawGraph();
			}
			else if(source == cbAverageValue) {

			}
			else if(source == cbMaxMinValues) {

			}
		}
		
	}
	
}


