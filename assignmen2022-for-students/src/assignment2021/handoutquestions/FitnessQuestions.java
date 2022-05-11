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
		String output = "";
		output += "\nQ1. The total number of participants in this dataset:  " + getTotalParticipants();
		output += "\nQ2. The total number of participants that have heart rate measurements:  " + getParticipantsNumberWithHRM();
		output += "\nQ3. The total number of participants that have steps measurements:  " + getParticipantsNumberWithStepsM();
		output += "\nQ4. The total number of participants that have distance measurements:  " + getParticipantsNumberWithDistanceM();
		output += "\nQ5. The total number of participants that have energy expenditure measurements:  " + getParticipantsNumberWithEEM();
		output += "\nQ6. The total count of heart rate measurements in the whole dataset:  " + getTotalHRMCount();
		output += "\nQ7. The total count of steps measurements in the whole dataset:  " + getTotalStepsCount();
		output += "\nQ8. The total count of distance measurements in the whole dataset:  "  + getTotalDistanceCount();
		output += "\nQ9. The total count of energy expenditure measurements in the whole dataset:  " + getTotalEECount();
		output += "\nQ10. The total count of heart rate measurements for each fitness tracker for the whole dataset:  " + getHRMCountPerFT();
		output += "\nQ11. The total count of energy expenditure measurements for fitness tracker FT1 for the whole dataset:  " + getEEMCountForFT1();
		output += "\nQ12. The total count of steps measurements for fitness trackers FT2, FT3 and FT4 for the whole dataset:  " + getStepsMCountForFT234();
		output += "\nQ13. The total count of distance measurements for fitness tracker FT5 for the whole dataset:  " + getDistanceMCountForFT5();
		output += "\nQ14. The list of participant/s with the highest single measurement of steps (across trackers) and the corresponding number of steps:  " + getHighestNumberOfStepsParticipants();
		output += "\nQ15. The list of participant/s with the highest single measurement of walked distance (across trackers) and the corresponding distance:  " + getHighestWalkedDistanceParticipants();
		output += "\nQ16. The global average number of steps for FT1 for the whole dataset:  " + getGlobalAverageStepsForFT1();
		output += "\nQ17. The list of participant/s with an average individual participant number of steps (for FT1) above the global average number of steps for FT1 \n	(provide average number of steps value per participant):  " + getAvgStepsAboveGlobalParticipantsForFT1();
		output += "\nQ18. The list of participant/s with an average individual participant number of steps (for FT1) below the global average number of steps for FT1 \n	(provide average number of steps value per participant):  " + getAvgStepsBelowGlobalParticipantsForFT1();
		output += "\nQ19. The global average energy expenditure for FT2 and FT3, for the whole dataset:  " + getGlobalAverageEEForFT2FT3();
		output += "\nQ20. The list of participant/s with an average individual participant energy expenditure (for FT2 and FT3) above the global average energy expenditure \n	for FT2 and FT3 (provide average energy expenditure value per participant):  " + getAvgEEAboveGlobalParticipantsForFT2FT3();
		output += "\nQ21. The list of participant/s with an average individual participant energy expenditure (for FT2 and FT3) below the global average energy expenditure \n	for FT2 and FT3 (provide average energy expenditure value per participant):  " + getAvgEEBelowGlobalParticipantsForFT2FT3();
		output += "\nQ22. The global average heart rate for the whole dataset:  " + getGlobalAverageHR();
		output += "\nQ23. The list of participant/s with an average individual participant heart rate above the global average heart rate \n	(provide average heart rate value per participant):  " + getAvgHRAboveGlobalParticipants();
		output += "\nQ24. The list of participant/s with an average individual participant heart rate below the global average heart rate \n	(provide average heart rate value per participant):  " + getAvgHRBelowGlobalParticipants();
		return output;
	}

	@Override
	public int getTotalParticipants() {
		return super.getParticipants().size();
	}

	@Override
	public int getParticipantsNumberWithHRM() {
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
		for (Participant current : participants) {
			Collection<Tracker> trackers = current.getAllTrackers();
			Iterator<Tracker> trackerIterator = trackers.iterator();
			boolean hasCategory = false;
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
		for (Participant current : participants) {
			Collection<Tracker> trackers = current.getAllTrackers();
			Iterator<Tracker> trackerIterator = trackers.iterator();
			while (trackerIterator.hasNext()) {
				Tracker currentTrack = trackerIterator.next();
				List<Measurement> relevantMeasurements = currentTrack.getMeasurementsForType(mt);
				count += relevantMeasurements.size();
			}
		}

		return count;
	}
	
	
	
	
	@Override
	public List<Integer> getHRMCountPerFT() {
		//Stores found trackers that have a heart rate monitor
		Map<String, Integer> countPerTracker = new HashMap<String,Integer>();
		Collection<Participant> participants = super.getParticipants();
		
		for (Participant current : participants) {
			Collection<Tracker> trackers = current.getAllTrackers();
			Iterator<Tracker> trackerIterator = trackers.iterator();
			while (trackerIterator.hasNext()) {
				Tracker currentTrack = trackerIterator.next();
				List<Measurement> relevantMeasurements = currentTrack.getMeasurementsForType(MeasurementType.HEART_RATE);
				if(!relevantMeasurements.isEmpty()) {
					if(!countPerTracker.containsKey(currentTrack.getName())) {
						countPerTracker.put(currentTrack.getName(), relevantMeasurements.size());
					}
					else {
						countPerTracker.put(currentTrack.getName(), countPerTracker.get(currentTrack.getName()) + relevantMeasurements.size());
					}
				}
			}
		}
		
		List<Integer> returnValue = new ArrayList<Integer>();
	
	    //Converting the map to an arrayList in order to be returned.
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
	
	private int totalForTracker(String selectedTracker, MeasurementType mt) {
		int count = 0;
		Collection<Participant> participants = super.getParticipants();
		
		for (Participant current : participants) {
			try {
				Tracker currentTrack = current.getTracker(selectedTracker);
				List<Measurement> relevantMeasurements = currentTrack.getMeasurementsForType(mt);
				if(!relevantMeasurements.isEmpty()) {
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
	
	private Set<String> getHighest(MeasurementType mt){
		Collection<Participant> participants = super.getParticipants();
		Set<String> currentHighestParticipants = new TreeSet<String>();
		int currentHighestNum = 0;
		
		for (Participant current : participants) {
			for(Tracker currentTracker : current.getAllTrackers()) {
				List<Measurement> desiredMeasurments = currentTracker.getMeasurementsForType(mt);
				for(Measurement currentMeasurement : desiredMeasurments) {
					int currentMeasureInt = (currentMeasurement.getValue()).intValue();
					if(currentMeasureInt > currentHighestNum) {
						currentHighestNum = currentMeasureInt;
						currentHighestParticipants = new TreeSet<String>();
						currentHighestParticipants.add(current.getName() + " With Value of " + currentHighestNum);
					}
					else if(currentMeasureInt == currentHighestNum) {
						if(!currentHighestParticipants.contains(current.getName())) {
							currentHighestParticipants.add(current.getName() + " With Value of " + currentHighestNum);
						}
					}
				}
			}
		}
	
		return currentHighestParticipants;
	}
	
	@Override
	public double getGlobalAverageStepsForFT1() {
		Collection<Participant> participants = super.getParticipants();
		Collection<String> trackers = new ArrayList<String>();
		trackers.add("FT1");
		double average = averageCalculator(participants, trackers, MeasurementType.STEPS, false);
		return average;
	}
	
	private double averageCalculator(Collection<Participant> participants, Collection<String> desiredTrackers, MeasurementType mt, boolean overallAverage) {
		double total = 0.0;
		int count = 0;
		double max = 0.0;
		for (Participant current : participants) {
			try {
				for(String desiredTracker : desiredTrackers) {
					Tracker currentTrack = current.getTracker(desiredTracker);
					List<Measurement> relevantMeasurements = currentTrack.getMeasurementsForType(mt);

					if(!relevantMeasurements.isEmpty()) {
						if(!overallAverage) {
							max = 0;
							for(Measurement currentMeasure : relevantMeasurements) {
								if(currentMeasure.getValue().doubleValue() > max) {
									max = currentMeasure.getValue().doubleValue();
								}
							}
							
							total += max;
							count++;
						}
						else {
							for(Measurement currentMeasure : relevantMeasurements) {
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
		
		double average = total/count;
		
		System.out.println("" + total + "/" + count + "=" + average);
		
		return average;
	}

	@Override
	public List<String> getAvgStepsAboveGlobalParticipantsForFT1() {
		List<String> ParticipantsAboveAvg = new ArrayList<String>();
		Collection<String> trackers = new ArrayList<String>();
		trackers.add("FT1");
		ParticipantsAboveAvg = findParticipantsAboveOrBelowAverage(trackers, MeasurementType.STEPS, true, false);
		return ParticipantsAboveAvg;
	}

	private List<String> findParticipantsAboveOrBelowAverage(Collection<String> trackers, MeasurementType mt, boolean bigger, boolean overallAverage){
		Collection<Participant> participants = super.getParticipants();
		double average = averageCalculator(participants, trackers, mt, overallAverage);
		ArrayList<String> listOfParticipantsAboveOrBelowAvg = new ArrayList<String>();
		
		for(Participant currentParticipant : participants) {
			Collection<Participant> par = new ArrayList<Participant>();
			par.add(currentParticipant);
			double participantAverage = averageCalculator(par, trackers, mt, overallAverage);
			if(biggerOrSmaller(bigger, participantAverage, average)) {
				listOfParticipantsAboveOrBelowAvg.add(currentParticipant.getName() + ", Value = " + participantAverage);
			}
		}
			
		return listOfParticipantsAboveOrBelowAvg;
	}
	
	private boolean biggerOrSmaller(boolean bigger, double currentAverage, double overallAverage) {
		if(bigger == true) {
			if(currentAverage > overallAverage) {
				return true;
			}
			else {
				return false;
			}
		}
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
		List<String> ParticipantsBelowAvg = new ArrayList<String>();
		Collection<String> trackers = new ArrayList<String>();
		trackers.add("FT1");
		ParticipantsBelowAvg = findParticipantsAboveOrBelowAverage(trackers, MeasurementType.STEPS, false, false);
		return ParticipantsBelowAvg;
	}

	@Override
	public double getGlobalAverageEEForFT2FT3() {
		Collection<Participant> participants = super.getParticipants();
		Collection<String> trackers = new ArrayList<String>();
		trackers.add("FT2");
		trackers.add("FT3");
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
		List<String> ParticipantsBelowAvg = new ArrayList<String>();
		Collection<String> trackers = new ArrayList<String>();
		trackers.add("FT2");
		trackers.add("FT3");
		ParticipantsBelowAvg = findParticipantsAboveOrBelowAverage(trackers, MeasurementType.ENERGY_EXPENDITURE, false, false);
		return ParticipantsBelowAvg;
	}

	@Override
	public double getGlobalAverageHR() {
		Collection<Participant> participants = super.getParticipants();
		Collection<String> trackers = new ArrayList<String>();
		
		for(Participant currentPar : participants) {
			for(String name : currentPar.getAllTrackerNames()) {
				if(!trackers.contains(name)) {
					trackers.add(name);
				}
			}
		}
				
		double average = averageCalculator(participants, trackers, MeasurementType.HEART_RATE, true);
		return average;
	}

	@Override
	public List<String> getAvgHRAboveGlobalParticipants() {
		Collection<Participant> participants = super.getParticipants();
		List<String> ParticipantsAboveAvg = new ArrayList<String>();
		Collection<String> trackers = new ArrayList<String>();
		
		for(Participant currentPar : participants) {
			for(String name : currentPar.getAllTrackerNames()) {
				if(!trackers.contains(name)) {
					trackers.add(name);
				}
			}
		}
		
		ParticipantsAboveAvg = findParticipantsAboveOrBelowAverage(trackers, MeasurementType.HEART_RATE, true, true);
		return ParticipantsAboveAvg;
	}

	@Override
	public List<String> getAvgHRBelowGlobalParticipants() {
		Collection<Participant> participants = super.getParticipants();
		List<String> ParticipantsAboveAvg = new ArrayList<String>();
		Collection<String> trackers = new ArrayList<String>();
		
		for(Participant currentPar : participants) {
			for(String name : currentPar.getAllTrackerNames()) {
				if(!trackers.contains(name)) {
					trackers.add(name);
				}
			}
		}
		
		ParticipantsAboveAvg = findParticipantsAboveOrBelowAverage(trackers, MeasurementType.HEART_RATE, false, true);
		return ParticipantsAboveAvg;
	}

}
