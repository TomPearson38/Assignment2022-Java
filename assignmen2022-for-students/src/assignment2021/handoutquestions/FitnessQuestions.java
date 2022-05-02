package assignment2021.handoutquestions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

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
		output += "\nQ14. The list of participant/s with the highest single measurement of steps (across trackers) and the corresponding number of steps:  ";
		output += "\nQ15. The list of participant/s with the highest single measurement of walked distance (across trackers) and the corresponding distance:  ";
		output += "\nQ16. The global average number of steps for FT1 for the whole dataset:  ";
		output += "\nQ17. The list of participant/s with an average individual participant number of steps (for FT1) above the global average number of steps for FT1 (provide average number of steps value per participant):  ";
		output += "\nQ18. The list of participant/s with an average individual participant number of steps (for FT1) below the global average number of steps for FT1 (provide average number of steps value per participant):  ";
		output += "\nQ19. The global average energy expenditure for FT2 and FT3, for the whole dataset:  ";
		output += "\nQ20. The list of participant/s with an average individual participant energy expenditure (for FT2 and FT3) above the global average energy expenditure for FT2 and FT3 (provide average energy expenditure value per participant):  ";
		output += "\nQ21. The list of participant/s with an average individual participant energy expenditure (for FT2 and FT3) below the global average energy expenditure for FT2 and FT3 (provide average energy expenditure value per participant):  ";
		output += "\nQ22. The global average heart rate for the whole dataset:  ";
		output += "\nQ23. The list of participant/s with an average individual participant heart rate above the global average heart rate (provide average heart rate value per participant):  ";
		output += "\nQ24. The list of participant/s with an average individual participant heart rate below the global average heart rate (provide average heart rate value per participant):  ";
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<String> getHighestWalkedDistanceParticipants() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getGlobalAverageStepsForFT1() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<String> getAvgStepsAboveGlobalParticipantsForFT1() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getAvgStepsBelowGlobalParticipantsForFT1() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getGlobalAverageEEForFT2FT3() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<String> getAvgEEAboveGlobalParticipantsForFT2FT3() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getAvgEEBelowGlobalParticipantsForFT2FT3() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getGlobalAverageHR() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<String> getAvgHRAboveGlobalParticipants() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getAvgHRBelowGlobalParticipants() {
		// TODO Auto-generated method stub
		return null;
	}

}
