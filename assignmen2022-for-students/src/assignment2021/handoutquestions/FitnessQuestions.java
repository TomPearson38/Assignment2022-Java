package assignment2021.handoutquestions;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import assignment2021.codeprovided.fitnesstracker.Participant;
import assignment2021.codeprovided.handoutquestions.AbstractFitnessQuestions;


public class FitnessQuestions extends AbstractFitnessQuestions {

	public FitnessQuestions(Collection<Participant> participants) {
		super(participants);
	}
	
	@Override
	public String toString() {
		// TODO Implement
		return "TODO: FitnessQuestions' toString() method should return the answers to the questions as specified in the handout.\n";
	}

	@Override
	public int getTotalParticipants() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getParticipantsNumberWithHRM() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getParticipantsNumberWithStepsM() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getParticipantsNumberWithDistanceM() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getParticipantsNumberWithEEM() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getTotalHRMCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getTotalStepsCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getTotalDistanceCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getTotalEECount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Integer> getHRMCountPerFT() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getEEMCountForFT1() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Integer> getStepsMCountForFT234() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getDistanceMCountForFT5() {
		// TODO Auto-generated method stub
		return 0;
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
