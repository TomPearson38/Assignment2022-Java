package assignment2021.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Arrays;
import java.util.Collection;

import assignment2021.codeprovided.fitnesstracker.Participant;
import assignment2021.codeprovided.fitnesstracker.measurements.MeasurementType;
import assignment2021.codeprovided.gui.AbstractGUIPanel;

public class GUIPanel extends AbstractGUIPanel {

	private static final long serialVersionUID = -1257936627636425453L;

	public GUIPanel(Collection<Participant> participants) {
		super(participants);
		// TODO Auto-generated constructor stub
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSelectedTrackersNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MeasurementType getSelectedMeasurementType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isShowAverageSelected() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isShowMinMaxSelected() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isShowIncrementsSelected() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	class pageEventListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
			if(source == addCurvesButton) {
				
			}
			else if(source == removeAllCurvesButton) {
				
			}
		}
		
	}
	
	class checkBoxListener implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
}


