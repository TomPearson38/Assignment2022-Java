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
		String firstLine = lines.get(0);
		String[] firstLineSplit = firstLine.split(",");
		Participant participant = new Participant(firstLineSplit[0], Integer.parseInt(firstLineSplit[1]), firstLineSplit[2]);
		
		//Therefore it removes the need to check for it in the loop.
		lines.remove(0);
		MeasurementType mt = MeasurementType.DISTANCE;
		ArrayList<String> currentTrackers = new ArrayList<String>();
		
		for(String line : lines) {
			String[] currentLine = line.split(CELL_SEPARATOR);
			if(currentLine.length == 1) {
				//Category Name is listed.
				mt = MeasurementType.fromMeasurementName(currentLine[0]);
			}
			else if(currentLine[0].equals("Count")) {
				//Tracker Names are listed
				currentTrackers = new ArrayList<String>();
				for(int i = 1; i < currentLine.length; i++) {
					currentTrackers.add(currentLine[i]);
				}
				
			}
			else if(isStringInt(currentLine[0]) && (currentLine.length - 1 == (currentTrackers.size()))) {
			     for(int i = 1; i < currentLine.length; i++) {
			    	 Measurement currentMeasurement = MeasurementFactory.createMeasurement(mt, Integer.parseInt(currentLine[0]), currentLine[i]); 
			    	 participant.addMeasurementToTracker(currentTrackers.get(i-1), currentMeasurement);
			     }
			}
		}
		return participant;
	}
	
	private static boolean isStringInt(String input) {
	    if (input == null) {
	        return false;
	    }
	    int length = input.length();
	    if (length == 0) {
	        return false;
	    }
	    
	    for (int i = 0; i < length; i++) {
	        char digit = input.charAt(i);
	        if (!('0' <= digit && digit <= '9')) {
	            return false;
	        }
	    }
	    return true;
	}
	
}
