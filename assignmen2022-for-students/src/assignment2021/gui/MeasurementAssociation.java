package assignment2021.gui;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * A class to keep track of the values already plotted on the graph
 * @author tomap
 * 15/02/2022
 */
public class MeasurementAssociation {
	private String participantName;
	private ArrayList<String> plottedTrackers;
	private HashMap<String, Color> generatedColours;
	
	/**
	 * Constructor
	 * @param partName Participant name
	 * @param firstTracker First tracker to be added
	 */
	public MeasurementAssociation(String partName, String firstTracker) {
		participantName = partName;
		plottedTrackers = new ArrayList<String>();
		plottedTrackers.add(firstTracker);
		
		generatedColours = new HashMap<String, Color>();
		generatedColours.put(firstTracker, randomColour());
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
		generatedColours.put(trackerToBeAdded, randomColour());
	}
	
	/**
	 * Removes a tracker
	 * @param trackerToBeRemoved
	 */
	public void removeTracker(String trackerToBeRemoved) {
		if(plottedTrackers != null) {
			if(plottedTrackers.contains(trackerToBeRemoved)) {
				generatedColours.remove(trackerToBeRemoved);
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
	
	/**
	 * A random colour is generated an returned
	 * @return Random colour
	 */
	private Color randomColour() {
		//A random RGB values of the colour are generated
		Random rand = new Random();
		float red = rand.nextFloat();
		float green = rand.nextFloat();
		float blue = rand.nextFloat();
		
		//Values are turned into an associated colour then returned
		Color generatedRandomColour = new Color(red, green, blue);
		return generatedRandomColour;
	}
	
	/**
	 * Retrieves the colour associated to the tracker provided
	 * @param tracker Tracker colour to be searched for
	 * @return The tracker colour
	 */
	public Color getColour(String tracker) {
		return generatedColours.get(tracker);
	}
	
	
}
