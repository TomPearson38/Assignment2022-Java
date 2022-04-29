package assignment2021.dataloading;

import java.util.List;
import java.util.ArrayList;

import assignment2021.codeprovided.dataloading.AbstractDataLoader;
import assignment2021.codeprovided.dataloading.DataParsingException;
import assignment2021.codeprovided.fitnesstracker.Participant;

public class DataLoader extends AbstractDataLoader {

	@Override
	public Participant loadDataLines(List<String> lines) throws DataParsingException {
		// TODO implement
		String firstLine = lines.get(0);
		String[] firstLineSplit = firstLine.split(",");
		Participant participant = new Participant(firstLineSplit[0], Integer.parseInt(firstLineSplit[1]), firstLineSplit[2]);
		
		//Therefore it removes the need to check for it in the loop.
		lines.remove(0);
		String categoryName;
		
		for(String line : lines) {
			String[] currentLine = line.split(CELL_SEPARATOR);
			if(currentLine.length == 1) {
				//Category Name is listed.
				categoryName = currentLine[0];
			}
			else if(currentLine[0] == "Count") {
				//Tracker Names are listed
				ArrayList<String> names = new ArrayList<String>();
				for(int i = 1; i < currentLine.length-1; i++) {
					names.add(currentLine[i]);
				}
			}
			else if(currentLine[0])
		}
		return null;
	}

}
