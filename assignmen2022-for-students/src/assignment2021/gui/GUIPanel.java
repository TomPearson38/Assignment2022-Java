package assignment2021.gui;

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
	public void addListeners() {
		// TODO add the event handlers here
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

}
