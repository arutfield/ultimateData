package data.raw;

import data.Coordinate;
import enums.RawDataEnums;
import enums.RawDataEnums.ThrowDirectionEnum;
import enums.RawDataEnums.YesNoNA;

public class Component3 {
	final boolean oTeamLine;
	final RawDataEnums.YesNoNA defendingTeamLineDLine;
	final boolean homeTeamOLine;
	final boolean awayTeamDLine;
	final RawDataEnums.YesNoNA offenseHome;
	final Coordinate location1Yards;
	final Coordinate location2Yards;
	final Double location1YardsToEndzone;
	final Double location2YardsToEndzone;
	final Double location1YardsFromMiddle;
	final Double location2YardsFromMiddle;
	final Coordinate throwDistance;
	final Coordinate throwVector;
	final RawDataEnums.ThrowDirectionEnum throwDirectionCategory;
	final Coordinate pullCoordinates;
	final Double pullYardsFromMiddle;
	final Double pullYDistance;
	final RawDataEnums.YesNoNA completedPass;
	final RawDataEnums.YesNoNA scoringPass;
	final Short throwNumberInPossession;
	final Short throwNumberInPoint;
	public Component3(boolean oTeamLine, YesNoNA defendingTeamLineDLine, boolean homeTeamOLine,
			boolean awayTeamDLine, YesNoNA offenseHome, Coordinate location1Yards, Coordinate location2Yards,
			Double location1YardsToEndzone, Double location2YardsToEndzone, Double location1YardsFromMiddle,
			Double location2YardsFromMiddle, Coordinate throwDistance, Coordinate throwVector,
			ThrowDirectionEnum throwDirectionCategory, Coordinate pullCoordinates, Double pullYardsFromMiddle,
			Double pullYDistance, YesNoNA completedPass, YesNoNA scoringPass, Short throwNumberInPossession,
			Short throwNumberInPoint) {
		this.oTeamLine = oTeamLine;
		this.defendingTeamLineDLine = defendingTeamLineDLine;
		this.homeTeamOLine = homeTeamOLine;
		this.awayTeamDLine = awayTeamDLine;
		this.offenseHome = offenseHome;
		this.location1Yards = location1Yards;
		this.location2Yards = location2Yards;
		this.location1YardsToEndzone = location1YardsToEndzone;
		this.location2YardsToEndzone = location2YardsToEndzone;
		this.location1YardsFromMiddle = location1YardsFromMiddle;
		this.location2YardsFromMiddle = location2YardsFromMiddle;
		this.throwDistance = throwDistance;
		this.throwVector = throwVector;
		this.throwDirectionCategory = throwDirectionCategory;
		this.pullCoordinates = pullCoordinates;
		this.pullYardsFromMiddle = pullYardsFromMiddle;
		this.pullYDistance = pullYDistance;
		this.completedPass = completedPass;
		this.scoringPass = scoringPass;
		this.throwNumberInPossession = throwNumberInPossession;
		this.throwNumberInPoint = throwNumberInPoint;
	}
	
}
