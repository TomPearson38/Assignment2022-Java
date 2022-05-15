package assignment2021.dataloading;

import java.util.List;
import java.util.ArrayList;

import assignment2021.codeprovided.dataloading.AbstractDataLoader;
import assignment2021.codeprovided.dataloading.DataParsingException;
import assignment2021.codeprovided.fitnesstracker.Participant;
import assignment2021.codeprovided.fitnesstracker.measurements.Measurement;
import assignment2021.codeprovided.fitnesstracker.measurements.MeasurementType;
import assignment2021.codeprovided.fitnesstracker.measurements.MeasurementFactory;


public class DataLoader extends AbstractDataLoader {

	@Override
	public Participant loadDataLines(List<String> lines) throws DataParsingException {
		//First line is different from all the others so is analysed separately.
		String firstLine = lines.get(0);
		String[] firstLineSplit = firstLine.split(",");
		Participant participant = new Participant(firstLineSplit[0], Integer.parseInt(firstLineSplit[1]), firstLineSplit[2]);
		
		//Therefore it removes the need to check for it in the loop.
		lines.remove(0);
		MeasurementType mt = null;
		ArrayList<String> currentTrackers = new ArrayList<String>();
		
		for(String line : lines) {
			String[] currentLine = line.split(CELL_SEPARATOR);
			if(currentLine.length == 1) {
				//Only one element in the line, therefore it must be a measurement type.
				mt = MeasurementType.fromMeasurementName(currentLine[0]);
			}
			else if(currentLine[0].equals("Count")) {
				//Tracker Names are listed when first word is count
				currentTrackers = new ArrayList<String>();
				for(int i = 1; i < currentLine.length; i++) {
					currentTrackers.add(currentLine[i]);
				}
				
			}
			else if(isStringInt(currentLine[0]) && (currentLine.length - 1 == (currentTrackers.size()))) {
				//Tracker values are listed
			     for(int i = 1; i < currentLine.length; i++) {
			    	 Measurement currentMeasurement = MeasurementFactory.createMeasurement(mt, Integer.parseInt(currentLine[0]), currentLine[i]); 
			    	 participant.addMeasurementToTracker(currentTrackers.get(i-1), currentMeasurement);
			     }
			}
		}
		return participant;
	}
	
	/**
	 * Checks if the input string is a integer
	 * @param input Input String
	 * @return If the string is an integer
	 */
	private static boolean isStringInt(String input) {
		//Trivial
		if (input == null) {
	        return false;
	    }
	    int length = input.length();
	    if (length == 0) {
	        return false;
	    }
	    
	    //If character lies outside the range of 0 and 9 ascii values then the string is not an int.
	    for (int i = 0; i < length; i++) {
	        char digit = input.charAt(i);
	        if (!('0' <= digit && digit <= '9')) {
	            return false;
	        }
	    }
	    
	    //All checks passed
	    return true;   
	}
	
}
