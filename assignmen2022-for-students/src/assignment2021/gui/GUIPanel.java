package assignment2021.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import assignment2021.codeprovided.fitnesstracker.Participant;
import assignment2021.codeprovided.fitnesstracker.measurements.MeasurementType;
import assignment2021.codeprovided.gui.AbstractGUIPanel;

public class GUIPanel extends AbstractGUIPanel {

	private static final long serialVersionUID = -1257936627636425453L;

	private ArrayList<MeasurementAssociation> loadedData;
	
	public GUIPanel(Collection<Participant> participants) {
		super(participants);
		loadedData = new ArrayList<MeasurementAssociation>();
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
	
	
	class pageEventListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
			if(source == addCurvesButton) {
				String selectedParticipantName = getSelectedParticipantName();
				String selectedTrackerName = getSelectedTrackersNames();
				boolean isDataUpdated;
				
				if(selectedTrackerName == "all") {					
					Collection<String> trackerNames = ((Participant) participants.toArray()[0]).getAllTrackerNames();
					for(String currentTracker : trackerNames) {
						isDataUpdated = visualisedCurvesPanel.addParticipantsToPlot(selectedParticipantName, currentTracker);
						System.out.println(isDataUpdated);
					}
					
				}
				else {
					isDataUpdated = visualisedCurvesPanel.addParticipantsToPlot(selectedParticipantName, selectedTrackerName);
					System.out.println(isDataUpdated);
				}
				
				//TODO
				//Add redraw functionality
				
			}
			else if(source == removeAllCurvesButton) {
				visualisedCurvesPanel.removeAllCurves();
			}
			else if(source == comboListMeasurementType) {

			}
		}
		
	}
	
	class checkBoxListener implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {
			Object source = e.getSource();
			if(source == cbIncrements) {

			}
			else if(source == cbAverageValue) {

			}
			else if(source == cbMaxMinValues) {

			}
		}
		
	}
	
}


