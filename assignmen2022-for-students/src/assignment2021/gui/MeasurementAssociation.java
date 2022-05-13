package assignment2021.gui;

import java.util.ArrayList;
import java.util.HashMap;

import assignment2021.codeprovided.fitnesstracker.measurements.Measurement;
import assignment2021.codeprovided.fitnesstracker.*;

public class MeasurementAssociation {
	private String participantName;
	private ArrayList<String> plottedTrackers;
	
	public MeasurementAssociation(String partName, String firstTracker) {
		participantName = partName;
		plottedTrackers = new ArrayList<String>();
		plottedTrackers.add(firstTracker);
	}
	
	public String getName() {
		return participantName;
	}
	
	public void setName(String newName) {
		participantName = newName;
	}
	
	public ArrayList<String> getAllTrackers(){
		return plottedTrackers;
	}
	
	public void addTracker(String trackerToBeAdded) {
		plottedTrackers.add(trackerToBeAdded);
	}
	
	
	public void removeTracker(String trackerToBeRemoved) {
		if(plottedTrackers != null) {
			if(plottedTrackers.contains(trackerToBeRemoved)) {
				plottedTrackers.remove(trackerToBeRemoved);
			}
		}
	}
	
	public boolean checkForTracker(String trackerSearchedFor) {
		if(plottedTrackers != null) {
			if(!plottedTrackers.isEmpty()) {
				for(String currentTracker: plottedTrackers) {
					if(currentTracker == trackerSearchedFor) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	
}
