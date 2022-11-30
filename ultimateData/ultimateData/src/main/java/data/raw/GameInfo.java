package data.raw;

import enums.QuarterEnum;
import enums.RawDataEnums;
import enums.RawDataEnums.FieldType;
import enums.RawDataEnums.OffenseDirection;

public class GameInfo{
	final String gameTitle;
	final int year;
	final String homeTeamId;
	final String awayTeamId;
	final RawDataEnums.FieldType fieldType;
	final Short homeScore;
	final Short awayScore;
	final RawDataEnums.OffenseDirection offenseDirection;
	final QuarterEnum quarter;
	final Short gameClockSeconds;

	public GameInfo(String gameTitle, String homeTeamId, String awayTeamId, FieldType fieldType, Short homeScore,
			Short awayScore, OffenseDirection offenseDirection, QuarterEnum quarter, Short gameClockSeconds) {
		this.gameTitle = gameTitle;
		String[] splitBySlash = gameTitle.split("/");
		String[] splitByColon = splitBySlash[2].split(":");
		this.year = Integer.valueOf("20" + splitByColon[0]);
		this.homeTeamId = homeTeamId;
		this.awayTeamId = awayTeamId;
		this.fieldType = fieldType;
		this.homeScore = homeScore;
		this.awayScore = awayScore;
		this.offenseDirection = offenseDirection;
		this.quarter = quarter;
		this.gameClockSeconds = gameClockSeconds;
	}		
}
