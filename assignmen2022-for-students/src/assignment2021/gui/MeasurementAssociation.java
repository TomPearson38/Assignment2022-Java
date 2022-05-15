package assignment2021.gui;

import java.util.ArrayList;
import java.util.HashMap;

import assignment2021.codeprovided.fitnesstracker.measurements.Measurement;
import assignment2021.codeprovided.fitnesstracker.*;

/**
 * A class to keep track of the values already plotted on the graph
 * @author tomap
 * 15/02/2022
 */
public class MeasurementAssociation {
	private String participantName;
	private ArrayList<String> plottedTrackers;
	
	/**
	 * Constructor
	 * @param partName Participant name
	 * @param firstTracker First tracker to be added
	 */
	public MeasurementAssociation(String partName, String firstTracker) {
		participantName = partName;
		plottedTrackers = new ArrayList<String>();
		plottedTrackers.add(firstTracker);
	}
	
	/**
	 * Gets participant name
	 * @return participant name
	 */
	public String getName() {
		return participantName;
	}
	
	/**
	 * Sets participant name
	 * @param newName participant name
	 */
	public void setName(String newName) {
		participantName = newName;
	}
	
	/**
	 * Gets all trackers
	 * @return All trackers
	 */
	public ArrayList<String> getAllTrackers(){
		return plottedTrackers;
	}
	
	/**
	 * adds tracker
	 * @param trackerToBeAdded tracker to be added to association
	 */
	public void addTracker(String trackerToBeAdded) {
		plottedTrackers.add(trackerToBeAdded);
	}
	
	/**
	 * Removes a tracker
	 * @param trackerToBeRemoved
	 */
	public void removeTracker(String trackerToBeRemoved) {
		if(plottedTrackers != null) {
			if(plottedTrackers.contains(trackerToBeRemoved)) {
				plottedTrackers.remove(trackerToBeRemoved);
			}
		}
	}
	
	/**
	 * Checks for presence of a tracker
	 * @param trackerSearchedFor The tracker to be searched for
	 * @return If the tracker is present in the set
	 */
	public boolean checkForTracker(String trackerSearchedFor) {
		if(plottedTrackers != null) {
			if(!plottedTrackers.isEmpty()) {
				for(String currentTracker: plottedTrackers) {
					if(currentTracker.equals(trackerSearchedFor)) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	
}
