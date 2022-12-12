package data.raw;

import enums.RawDataEnums;
import enums.RawDataEnums.HomeTeamOutcome;
import enums.RawDataEnums.QGroupTimeRemaining;
import enums.RawDataEnums.TeamScored;
import enums.RawDataEnums.YesNoNA;
import exceptions.ValueException;

public class Component2 {
	final String[] homePlayerIds;
	final String[] awayPlayerIds;
	final String homeTeamLocation;
	final boolean fieldCflType;
	final Short homeAwayDifferential;
	final Short offenseDefenseDifferential;
	final RawDataEnums.YesNoNA regulationDone;
	final Short possessionNumber;
	final Short pointNumber;
	final RawDataEnums.TeamScored teamScoredOnPoint;
	final RawDataEnums.YesNoNA teamScoredOnPossession;
	final Short homeScoreEndofGame;
	final Short awayScoreEndofGame;
	final RawDataEnums.HomeTeamOutcome homeTeamGameOutcome;
	final Double durationEventEstimate;
	final Double gameClockEstimate;
	final Short durationPointEstimate;
	final Double timeRemainingRegularGame;
	final Double timeRemainingQuarter;
	final RawDataEnums.QGroupTimeRemaining timeRemainingQGroup;
	final Short numberThrowOfQuarter;
	final RawDataEnums.YesNoNA lastThrowOfQuarter;	
	
	public Component2(String[] homePlayerIds, String[] awayPlayerIds, String homeTeamLocation, boolean fieldCflType,
			Short homeAwayDifferential, Short offenseDefenseDifferential, RawDataEnums.YesNoNA regulationDone,
			Short possessionNumber, Short pointNumber, TeamScored teamScoredOnPoint, YesNoNA teamScoredOnPossession,
			Short homeScoreEndofGame, Short awayScoreEndofGame, HomeTeamOutcome homeTeamGameOutcome,
			Double durationEventEstimate, Double gameClockEstimate, Short durationPointEstimate, Double timeRemainingRegularGame,
			Double timeRemainingQuarter, QGroupTimeRemaining timeRemainingQGroup, Short numberThrowOfQuarter,
			YesNoNA lastThrowOfQuarter) throws ValueException {
		this.homePlayerIds = homePlayerIds;
		this.awayPlayerIds = awayPlayerIds;
		this.homeTeamLocation = homeTeamLocation;
		this.fieldCflType = fieldCflType;
		this.homeAwayDifferential = homeAwayDifferential;
		this.offenseDefenseDifferential = offenseDefenseDifferential;
		this.regulationDone = regulationDone;
		this.possessionNumber = possessionNumber;
		this.pointNumber = pointNumber;
		this.teamScoredOnPoint = teamScoredOnPoint;
		this.teamScoredOnPossession = teamScoredOnPossession;
		this.homeScoreEndofGame = homeScoreEndofGame;
		this.awayScoreEndofGame = awayScoreEndofGame;
		this.homeTeamGameOutcome = homeTeamGameOutcome;
		this.durationEventEstimate = durationEventEstimate;
		this.gameClockEstimate = gameClockEstimate;
		this.durationPointEstimate = durationPointEstimate;
		this.timeRemainingRegularGame = timeRemainingRegularGame;
		this.timeRemainingQuarter = timeRemainingQuarter;
		this.timeRemainingQGroup = timeRemainingQGroup;
		this.numberThrowOfQuarter = numberThrowOfQuarter;
		this.lastThrowOfQuarter = lastThrowOfQuarter;
	}

}
