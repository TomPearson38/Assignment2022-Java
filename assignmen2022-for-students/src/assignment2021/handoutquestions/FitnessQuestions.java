package assignment2021.handoutquestions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.TreeSet;

import assignment2021.codeprovided.fitnesstracker.Participant;
import assignment2021.codeprovided.fitnesstracker.Tracker;
import assignment2021.codeprovided.fitnesstracker.measurements.Measurement;
import assignment2021.codeprovided.fitnesstracker.measurements.MeasurementType;
import assignment2021.codeprovided.handoutquestions.AbstractFitnessQuestions;

public class FitnessQuestions extends AbstractFitnessQuestions {

	public FitnessQuestions(Collection<Participant> participants) {
		super(participants);
	}

	@Override
	public String toString() {
		//Prints the questions to the console.
		String output = "";
		output += "\n\nQ1. The total number of participants in this dataset:  " + getTotalParticipants();
		output += "\n\nQ2. The total number of participants that have heart rate measurements:  " + getParticipantsNumberWithHRM();
		output += "\n\nQ3. The total number of participants that have steps measurements:  " + getParticipantsNumberWithStepsM();
		output += "\n\nQ4. The total number of participants that have distance measurements:  " + getParticipantsNumberWithDistanceM();
		output += "\n\nQ5. The total number of participants that have energy expenditure measurements:  " + getParticipantsNumberWithEEM();
		output += "\n\nQ6. The total count of heart rate measurements in the whole dataset:  " + getTotalHRMCount();
		output += "\n\nQ7. The total count of steps measurements in the whole dataset:  " + getTotalStepsCount();
		output += "\n\nQ8. The total count of distance measurements in the whole dataset:  "  + getTotalDistanceCount();
		output += "\n\nQ9. The total count of energy expenditure measurements in the whole dataset:  " + getTotalEECount();
		output += "\n\nQ10. The total count of heart rate measurements for each fitness tracker for the whole dataset:  " + getHRMCountPerFT();
		output += "\n\nQ11. The total count of energy expenditure measurements for fitness tracker FT1 for the whole dataset:  " + getEEMCountForFT1();
		output += "\n\nQ12. The total count of steps measurements for fitness trackers FT2, FT3 and FT4 for the whole dataset:  " + getStepsMCountForFT234();
		output += "\n\nQ13. The total count of distance measurements for fitness tracker FT5 for the "
				+ "whole dataset:  " + getDistanceMCountForFT5();
		
		output += "\n\nQ14. The list of participant/s with the highest single measurement of steps (across trackers) "
				+ "and the corresponding number of steps:  " + getHighestNumberOfStepsParticipants();
		
		output += "\n\nQ15. The list of participant/s with the highest single measurement of walked distance (across "
				+ "trackers) and the corresponding distance:  " + getHighestWalkedDistanceParticipants();
		
		output += "\n\nQ16. The global average number of steps for FT1 for the whole dataset:  " + getGlobalAverageStepsForFT1();
		output += "\n\nQ17. The list of participant/s with an average individual participant number of steps (for FT1) "
				+ "above the global average number of steps for FT1 \n	(provide average number of steps value per participant):  " + getAvgStepsAboveGlobalParticipantsForFT1();
		
		output += "\n\nQ18. The list of participant/s with an average individual participant number of steps (for FT1)"
				+ " below the global average number of steps for FT1 \n	(provide average number of steps value per participant):  " + getAvgStepsBelowGlobalParticipantsForFT1();
		
		output += "\n\nQ19. The global average energy expenditure for FT2 and FT3, for the whole dataset:  " + getGlobalAverageEEForFT2FT3();
		output += "\n\nQ20. The list of participant/s with an average individual participant energy expenditure (for FT2 "
				+ "and FT3) above the global average energy expenditure \n	for FT2 and FT3 (provide average energy expenditure value per participant):  " + getAvgEEAboveGlobalParticipantsForFT2FT3();
		
		output += "\n\nQ21. The list of participant/s with an average individual participant energy expenditure (for FT2 "
				+ "and FT3) below the global average energy expenditure \n	for FT2 and FT3 (provide average energy expenditure value per participant):  " + getAvgEEBelowGlobalParticipantsForFT2FT3();
		
		output += "\n\nQ22. The global average heart rate for the whole dataset:  " + getGlobalAverageHR();
		output += "\n\nQ23. The list of participant/s with an average individual participant heart rate above the global "
				+ "average heart rate \n	(provide average heart rate value per participant):  " + getAvgHRAboveGlobalParticipants();
		
		output += "\n\nQ24. The list of participant/s with an average individual participant heart rate below the global "
				+ "average heart rate \n	(provide average heart rate value per participant):  " + getAvgHRBelowGlobalParticipants();
		return output;
	}

	@Override
	public int getTotalParticipants() {
		return super.getParticipants().size();
	}

	@Override
	public int getParticipantsNumberWithHRM() {
		//Number of participants with desired measurement type are calculated
		int count = categoryCount(MeasurementType.HEART_RATE);
		return count;
	}

	@Override
	public int getParticipantsNumberWithStepsM() {
		int count = categoryCount(MeasurementType.STEPS);
		return count;
	}

	@Override
	public int getParticipantsNumberWithDistanceM() {
		int count = categoryCount(MeasurementType.DISTANCE);
		return count;
	}

	@Override
	public int getParticipantsNumberWithEEM() {
		int count = categoryCount(MeasurementType.ENERGY_EXPENDITURE);
		return count;
	}

	/**
	 * Counts the number of participants who have the supplied type of measurement
	 * 
	 * @param mt The measurement type being looked for
	 * @return The number of participants that have that measurement type.
	 */
	private int categoryCount(MeasurementType mt) {
		Collection<Participant> participants = super.getParticipants();
		int count = 0;
		//Loops for each participant
		for (Participant current : participants) {
			Collection<Tracker> trackers = current.getAllTrackers();
			Iterator<Tracker> trackerIterator = trackers.iterator();
			boolean hasCategory = false;
			//Checks each tracker for presence of category.
			//If found loop will check next participant
			while (trackerIterator.hasNext() && !hasCategory) {
				Tracker currentTrack = trackerIterator.next();
				List<Measurement> relevantMeasurements = currentTrack.getMeasurementsForType(mt);
				if (!relevantMeasurements.isEmpty()) {
					count++;
					hasCategory = true;
				}
			}
		}

		return count;
	}

	@Override
	public int getTotalHRMCount() {
		int count = categoryTotalCount(MeasurementType.HEART_RATE);
		return count;
	}

	@Override
	public int getTotalStepsCount() {
		int count = categoryTotalCount(MeasurementType.STEPS);
		return count;
	}

	@Override
	public int getTotalDistanceCount() {
		int count = categoryTotalCount(MeasurementType.DISTANCE);
		return count;
	}

	@Override
	public int getTotalEECount() {
		int count = categoryTotalCount(MeasurementType.ENERGY_EXPENDITURE);
		return count;
	}

	/**
	 * Counts the total measurements of each category.
	 * @param mt Measurement type to be counted
	 * @return the total number of measurements
	 */
	private int categoryTotalCount(MeasurementType mt) {
		Collection<Participant> participants = super.getParticipants();
		int count = 0;
		//Checks each participant
		for (Participant current : participants) {
			Collection<Tracker> trackers = current.getAllTrackers();
			Iterator<Tracker> trackerIterator = trackers.iterator();
			//Checks each tracker
			while (trackerIterator.hasNext()) {
				Tracker currentTrack = trackerIterator.next();
				List<Measurement> relevantMeasurements = currentTrack.getMeasurementsForType(mt);
				//Adds number of measurements to the tracker 
				count += relevantMeasurements.size();
			}
		}

		return count;
	}
	
	
	
	
	@Override
	public List<Integer> getHRMCountPerFT() {
		//Map Stores found trackers that have a heart rate monitor
		Map<String, Integer> countPerTracker = new HashMap<String,Integer>();
		Collection<Participant> participants = super.getParticipants();
		
		//For each participant in participants
		for (Participant current : participants) {
			Collection<Tracker> trackers = current.getAllTrackers();
			Iterator<Tracker> trackerIterator = trackers.iterator();
			while (trackerIterator.hasNext()) {
				Tracker currentTrack = trackerIterator.next();
				//All relevant heart rate measurements are loaded
				List<Measurement> relevantMeasurements = currentTrack.getMeasurementsForType(MeasurementType.HEART_RATE);
				//If not empty it will add to map
				if(!relevantMeasurements.isEmpty()) {
					//Tracker doesn't exist
					if(!countPerTracker.containsKey(currentTrack.getName())) {
						countPerTracker.put(currentTrack.getName(), relevantMeasurements.size());
					}
					//Tracker exists
					else {
						countPerTracker.put(currentTrack.getName(), countPerTracker.get(currentTrack.getName()) + relevantMeasurements.size());
					}
				}
			}
		}
		
		List<Integer> returnValue = new ArrayList<Integer>();
	
	    //Converting the map to an arrayList of int's in order to be returned.
	    returnValue = new ArrayList<>(countPerTracker.values());		
	    return returnValue;
	}
	
	
	@Override
	public int getEEMCountForFT1() {
		int countForFT1 = totalForTracker("FT1", MeasurementType.ENERGY_EXPENDITURE);		
		return countForFT1;
	}

	@Override
	public List<Integer> getStepsMCountForFT234() {
		//Calculates the steps for each and adds them to the list.
		List<Integer> stepCount = new ArrayList<Integer>();
		
		stepCount.add(totalForTracker("FT2", MeasurementType.STEPS));
		stepCount.add(totalForTracker("FT3", MeasurementType.STEPS));
		stepCount.add(totalForTracker("FT4", MeasurementType.STEPS));
		
		return stepCount;
	}

	@Override
	public int getDistanceMCountForFT5() {
		int countForFT5 = totalForTracker("FT5", MeasurementType.DISTANCE);		
		return countForFT5;
	}
	
	/**
	 * Calculates the total number of measurements specified in the tracker specified for every participant
	 * @param selectedTracker Selected tracker to be searched for
	 * @param mt The measurement type that is being searched for
	 * @return The count of how many measurements that measurement type has in the specified tracker.
	 */
	private int totalForTracker(String selectedTracker, MeasurementType mt) {
		int count = 0;
		Collection<Participant> participants = super.getParticipants();
		
		//For each participant
		for (Participant current : participants) {
			//Attempts to get measurements for the type
			try {
				//Next tracker
				Tracker currentTrack = current.getTracker(selectedTracker);
				//Relevant measurements are gathered
				List<Measurement> relevantMeasurements = currentTrack.getMeasurementsForType(mt);
				if(!relevantMeasurements.isEmpty()) {
					//Size of measurements are added to count
					count += relevantMeasurements.size();
				}
			}	
			catch (NoSuchElementException e) {
				
			}
		}
		
		return count;
	}

	@Override
	public Set<String> getHighestNumberOfStepsParticipants() {
		Set<String> contents = getHighest(MeasurementType.STEPS);
		return contents;
	}

	@Override
	public Set<String> getHighestWalkedDistanceParticipants() {
		Set<String> contents = getHighest(MeasurementType.DISTANCE);
		return contents;
	}
	
	/**
	 * Finds the participant/s with the highest number of the measurement provided value
	 * @param mt The specified measurement
	 * @return The participant/s with the highest value and the value
	 */
	private Set<String> getHighest(MeasurementType mt){
		//Variables initialised
		Collection<Participant> participants = super.getParticipants();
		Set<String> currentHighestParticipants = new TreeSet<String>();
		int currentHighestNum = 0;
		
		//For each participant
		for (Participant current : participants) {
			//For each tracker of each participant
			for(Tracker currentTracker : current.getAllTrackers()) {
				//Desired measurements are attempted to be loaded
				try {
					List<Measurement> desiredMeasurments = currentTracker.getMeasurementsForType(mt);
					//For each measurement
					for(Measurement currentMeasurement : desiredMeasurments) {
						//Calculates the current value of the current measurement
						int currentMeasureInt = (currentMeasurement.getValue()).intValue();
						//If value is bigger 
						if(currentMeasureInt > currentHighestNum) {
							//Value is saved and the highest participant is changed to the current participant
							currentHighestNum = currentMeasureInt;
							currentHighestParticipants = new TreeSet<String>();
							currentHighestParticipants.add(current.getName() + " With Value of " + currentHighestNum);
						}
						//Value is the same
						else if(currentMeasureInt == currentHighestNum) {
							//The participant is added to the list containing the largest participants if not there already
							if(!currentHighestParticipants.contains(current.getName())) {
								currentHighestParticipants.add(current.getName() + " With Value of " + currentHighestNum);
							}
						}
					}
				}
				catch (NoSuchElementException e) {
					
				}
			}
		}
	
		return currentHighestParticipants;
	}
	
	@Override
	public double getGlobalAverageStepsForFT1() {
		//Values are initialised to be passed into averageCalculator
		Collection<Participant> participants = super.getParticipants();
		Collection<String> trackers = new ArrayList<String>();
		trackers.add("FT1");
		//Average is calculated
		double average = averageCalculator(participants, trackers, MeasurementType.STEPS, false);
		return average;
	}
	
	/**
	 * Calculates the average for a tracker, for a measurement type, for a specified list of participants
	 * @param participants Participants to calculated average for
	 * @param desiredTrackers Tracker/s that are being searched for
	 * @param mt The measurement type being searched for
	 * @param overallAverage If false, only the highest value found in each trackers measurements will contribute to the average.
	 * If true, all values will contribute to the average.
	 * @return The average value
	 */
	private double averageCalculator(Collection<Participant> participants, Collection<String> desiredTrackers, MeasurementType mt, boolean overallAverage) {
		//Initialised variables
		double total = 0.0;
		int count = 0;
		double max = 0.0;
		//For each participant
		for (Participant current : participants) {
			//Attempts to load measurements
			try {
				//For each tracker being searched for
				for(String desiredTracker : desiredTrackers) {
					//Name of tracker is converted to an actual tracker
					Tracker currentTrack = current.getTracker(desiredTracker);
					//Measurements are loaded from the tracker
					List<Measurement> relevantMeasurements = currentTrack.getMeasurementsForType(mt);
					
					if(!relevantMeasurements.isEmpty()) {
						//Only max value will contribute to average
						if(!overallAverage) {
							max = 0;
							//Max value is found
							for(Measurement currentMeasure : relevantMeasurements) {
								if(currentMeasure.getValue().doubleValue() > max) {
									max = currentMeasure.getValue().doubleValue();
								}
							}
							
							//Added to total, count incremented
							total += max;
							count++;
						}
						//All values will contribute to average
						else {
							//For each measurement
							for(Measurement currentMeasure : relevantMeasurements) {
								//Value added to total, count incremented
								total += currentMeasure.getValue().doubleValue();
								count++;
							}
						}
						
						
					}
				}
			}	
			catch (NoSuchElementException e) {
				
			}
		}
		
		//Average calculated
		double average = total/count;		
		return average;
	}

	@Override
	public List<String> getAvgStepsAboveGlobalParticipantsForFT1() {
		//Initialised variables 
		List<String> ParticipantsAboveAvg = new ArrayList<String>();
		Collection<String> trackers = new ArrayList<String>();
		trackers.add("FT1");
		//Average is found
		ParticipantsAboveAvg = findParticipantsAboveOrBelowAverage(trackers, MeasurementType.STEPS, true, false);
		return ParticipantsAboveAvg;
	}

	/**
	 * Finds all participants above and below the average.
	 * @param trackers Trackers to be searched
	 * @param mt Measurement Type being searched for
	 * @param bigger If searching for above average
	 * @param overallAverage If searching for overall average
	 * @return The list of participants above or below average as specified
	 */
	private List<String> findParticipantsAboveOrBelowAverage(Collection<String> trackers, MeasurementType mt, boolean bigger, boolean overallAverage){
		Collection<Participant> participants = super.getParticipants();
		//Average is calculated
		double average = averageCalculator(participants, trackers, mt, overallAverage);
		ArrayList<String> listOfParticipantsAboveOrBelowAvg = new ArrayList<String>();
		
		//For each participant
		for(Participant currentParticipant : participants) {
			//A trivial list of participants is created in order to calculate the individual participant's average
			Collection<Participant> par = new ArrayList<Participant>();
			par.add(currentParticipant);
			//Average calculated
			double participantAverage = averageCalculator(par, trackers, mt, overallAverage);
			//Compares with value and returns true or false if we are searching for a bigger or smaller list of participants
			//This way the same function can be used for both comparisons
			if(biggerOrSmaller(bigger, participantAverage, average)) {
				listOfParticipantsAboveOrBelowAvg.add(currentParticipant.getName() + ", Value = " + participantAverage);
			}
		}
			
		//List is returned
		return listOfParticipantsAboveOrBelowAvg;
	}
	
	/**
	 * Simple comparison which compares two numbers
	 * @param bigger true if bigger comparison is used, false if smaller comparison
	 * @param currentAverage Current average number
	 * @param overallAverage Number to be compared to it
	 * @return
	 */
	private boolean biggerOrSmaller(boolean bigger, double currentAverage, double overallAverage) {
		//If current average is bigger than overall average
		if(bigger == true) {
			if(currentAverage > overallAverage) {
				return true;
			}
			else {
				return false;
			}
		}
		//If current average is smaller than overall average
		else {
			if(currentAverage < overallAverage) {
				return true;
			}
			else {
				return false;
			}
		}
	}
	
	@Override
	public List<String> getAvgStepsBelowGlobalParticipantsForFT1() {
		//Values are initialised to be passed into calculation
		List<String> ParticipantsBelowAvg = new ArrayList<String>();
		Collection<String> trackers = new ArrayList<String>();
		trackers.add("FT1");
		//Participants below the average are calculated
		ParticipantsBelowAvg = findParticipantsAboveOrBelowAverage(trackers, MeasurementType.STEPS, false, false);
		return ParticipantsBelowAvg;
	}

	@Override
	public double getGlobalAverageEEForFT2FT3() {
		//Values are initialised to be passed into averageCalculator
		Collection<Participant> participants = super.getParticipants();
		Collection<String> trackers = new ArrayList<String>();
		trackers.add("FT2");
		trackers.add("FT3");
		//Average is calculated
		double average = averageCalculator(participants, trackers, MeasurementType.ENERGY_EXPENDITURE, false);
		return average;
	}

	@Override
	public List<String> getAvgEEAboveGlobalParticipantsForFT2FT3() {
		List<String> ParticipantsBelowAvg = new ArrayList<String>();
		Collection<String> trackers = new ArrayList<String>();
		trackers.add("FT2");
		trackers.add("FT3");
		ParticipantsBelowAvg = findParticipantsAboveOrBelowAverage(trackers, MeasurementType.ENERGY_EXPENDITURE, true, false);
		return ParticipantsBelowAvg;
	}

	@Override
	public List<String> getAvgEEBelowGlobalParticipantsForFT2FT3() {
		//Values are initialised to be passed into calculation
		List<String> ParticipantsBelowAvg = new ArrayList<String>();
		Collection<String> trackers = new ArrayList<String>();
		trackers.add("FT2");
		trackers.add("FT3");
		//Participants below the average are calculated
		ParticipantsBelowAvg = findParticipantsAboveOrBelowAverage(trackers, MeasurementType.ENERGY_EXPENDITURE, false, false);
		return ParticipantsBelowAvg;
	}

	@Override
	public double getGlobalAverageHR() {
		//All participants are queried
		Collection<Participant> participants = super.getParticipants();
		
		//All tracker names are found
		Collection<String> trackers = findNamesOfEveryTracker(participants);
						
		//Average is calculated
		double average = averageCalculator(participants, trackers, MeasurementType.HEART_RATE, true);
		return average;
	}

	@Override
	public List<String> getAvgHRAboveGlobalParticipants() {
		//All participants are queried
		Collection<Participant> participants = super.getParticipants();
		List<String> ParticipantsAboveAvg = new ArrayList<String>();
		
		//All tracker names are found
		Collection<String> trackers = findNamesOfEveryTracker(participants);
				
		//Participants above average value are calculated
		ParticipantsAboveAvg = findParticipantsAboveOrBelowAverage(trackers, MeasurementType.HEART_RATE, true, true);
		return ParticipantsAboveAvg;
	}

	@Override
	public List<String> getAvgHRBelowGlobalParticipants() {
		//All participants are queried
		Collection<Participant> participants = super.getParticipants();
		List<String> ParticipantsAboveAvg = new ArrayList<String>();
		
		//All tracker names are found
		Collection<String> trackers = findNamesOfEveryTracker(participants);
		
		//Participants below average are calculated
		ParticipantsAboveAvg = findParticipantsAboveOrBelowAverage(trackers, MeasurementType.HEART_RATE, false, true);
		return ParticipantsAboveAvg;
	}
	
	/**
	 * Used to retrieve the names of every tracker in the participants provided
	 * @param participants The participants being searched through
	 * @return The names of every tracker
	 */
	public Collection<String> findNamesOfEveryTracker(Collection<Participant> participants){
		Collection<String> trackers = new ArrayList<String>();
		//For each participant
		for(Participant currentPar : participants) {
			//For each tracker name
			for(String name : currentPar.getAllTrackerNames()) {
				//If not in trackers add it
				if(!trackers.contains(name)) {
					trackers.add(name);
				}
			}
		}
		return trackers;
	}

}
