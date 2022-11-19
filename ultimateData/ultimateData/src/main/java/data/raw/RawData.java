package data.raw;

import java.util.Date;
import java.util.LinkedList;

import data.Coordinate;
import enums.QuarterEnum;
import enums.RawDataEnums;
import enums.RawDataEnums.BlockType;
import enums.RawDataEnums.BrokeMark;
import enums.RawDataEnums.ClosestDefenderTight;
import enums.RawDataEnums.DefenderDistance;
import enums.RawDataEnums.DefenseScheme;
import enums.RawDataEnums.EventType;
import enums.RawDataEnums.FieldType;
import enums.RawDataEnums.Force;
import enums.RawDataEnums.ForceDirection;
import enums.RawDataEnums.FoulSide;
import enums.RawDataEnums.HomeTeamOutcome;
import enums.RawDataEnums.OffenseDirection;
import enums.RawDataEnums.PassBreak;
import enums.RawDataEnums.PassType;
import enums.RawDataEnums.PullInfo;
import enums.RawDataEnums.QGroupTimeRemaining;
import enums.RawDataEnums.TeamScored;
import enums.RawDataEnums.ThrowDirectionEnum;
import enums.RawDataEnums.TurnoverType;
import enums.RawDataEnums.YesNoNA;

public class RawData {

	private final GameInfo gameInfo;
	private final Component0 component0;
	private final Component1 component1;
	private final Component2 component2;
	private final Component3 component3;
	private final Component4 component4;

	public RawData(GameInfo gameInfo, Component0 component0, Component1 component1, Component2 component2,
			Component3 component3, Component4 component4) {
		this.gameInfo = gameInfo;
		this.component0 = component0;
		this.component1 = component1;
		this.component2 = component2;
		this.component3 = component3;
		this.component4 = component4;
	}

	public String getGameTitle() {
		return gameInfo.gameTitle;
	}

	public String getHomeTeamId() {
		return gameInfo.homeTeamId;
	}

	public String getAwayTeamId() {
		return gameInfo.awayTeamId;
	}

	public RawDataEnums.FieldType getFieldType() {
		return gameInfo.fieldType;
	}

	public Short getHomeScore() {
		return gameInfo.homeScore;
	}

	public Short getAwayScore() {
		return gameInfo.awayScore;
	}

	public RawDataEnums.OffenseDirection getOffenseDirection() {
		return gameInfo.offenseDirection;
	}

	public QuarterEnum getQuarter() {
		return gameInfo.quarter;
	}

	public Short getGameClockSeconds() {
		return gameInfo.gameClockSeconds;
	}

	public RawDataEnums.DefenseScheme getDefenseScheme() {
		return component0.defenseScheme;
	}

	public String getTeamId() {
		return component0.teamId;
	}

	public RawDataEnums.EventType getEventType() {
		return component0.eventType;
	}

	public String getPlayer1Id() {
		return component0.player1Id;
	}

	public Coordinate getLocation1() {
		return component0.location1;
	}

	public String getPlayer2Id() {
		return component0.player2Id;
	}

	public Coordinate getLocation2() {
		return component0.location2;
	}

	public Double getPullTime() {
		return component0.pullTime;
	}

	public RawDataEnums.PullInfo getPullInfo() {
		return component0.pullInfo;
	}

	public RawDataEnums.DefenderDistance getClosestDefenderDistance() {
		return component0.closestDefenderDistance;
	}

	public RawDataEnums.Force getForce() {
		return component0.force;
	}

	public RawDataEnums.PassType getPassType() {
		return component0.passType;
	}

	public RawDataEnums.BrokeMark getBrokeMark() {
		return component0.brokeMark;
	}

	public RawDataEnums.PassBreak getPassBreak() {
		return component0.passBreak;
	}

	public RawDataEnums.TurnoverType getTurnoverType() {
		return component1.turnoverType;
	}

	public RawDataEnums.BlockType getBlockType() {
		return component1.blockType;
	}

	public RawDataEnums.FoulSide getFoulSide() {
		return component1.foulSide;
	}

	public Date getGameDate() {
		return component1.gameDate;
	}

	public Weather getWeather() {
		return component1.weather;
	}

	public String getDefendingTeamId() {
		return component1.defendingTeamId;
	}

	public String getThrowerId() {
		return component1.throwerId;
	}

	public String getTargetedReceiverId() {
		return component1.targetedReceiverId;
	}

	public LinkedList<String> getClosestDefenders() {
		return component1.closestDefenders;
	}

	public LinkedList<String> getMarkers() {
		return component1.markers;
	}

	public LinkedList<String> getPoachers() {
		return component1.poachers;
	}

	public LinkedList<String> getDeflectors() {
		return component1.deflectors;
	}

	public String getPullThrowerId() {
		return component1.pullThrowerId;
	}

	public String getPullReceiverId() {
		return component1.pullReceiverId;
	}

	public String getoFoulerId() {
		return component1.oFoulerId;
	}

	public LinkedList<String> getdFoulerIds() {
		return component1.dFoulerIds;
	}

	public String getoFouledId() {
		return component1.oFouledId;
	}

	public String getdFouledId() {
		return component1.dFouledId;
	}

	public String[] getHomePlayerIds() {
		return component2.homePlayerIds;
	}

	public String[] getAwayPlayerIds() {
		return component2.awayPlayerIds;
	}

	public String getHomeTeamLocation() {
		return component2.homeTeamLocation;
	}

	public boolean isFieldCflType() {
		return component2.fieldCflType;
	}

	public Short getHomeAwayDifferential() {
		return component2.homeAwayDifferential;
	}

	public Short getOffenseDefenseDifferential() {
		return component2.offenseDefenseDifferential;
	}

	public RawDataEnums.YesNoNA isRegulationDone() {
		return component2.regulationDone;
	}

	public Short getPossessionNumber() {
		return component2.possessionNumber;
	}

	public Short getPointNumber() {
		return component2.pointNumber;
	}

	public RawDataEnums.TeamScored getTeamScoredOnPoint() {
		return component2.teamScoredOnPoint;
	}

	public RawDataEnums.YesNoNA isTeamScoredOnPossession() {
		return component2.teamScoredOnPossession;
	}

	public Short getHomeScoreEndofGame() {
		return component2.homeScoreEndofGame;
	}

	public Short getAwayScoreEndofGame() {
		return component2.awayScoreEndofGame;
	}

	public RawDataEnums.HomeTeamOutcome getHomeTeamGameOutcome() {
		return component2.homeTeamGameOutcome;
	}

	public Short getGameClockEstimate() {
		return component2.gameClockEstimate;
	}

	public Short getDurationPointEstimate() {
		return component2.durationPointEstimate;
	}

	public Double getTimeRemainingRegularGame() {
		return component2.timeRemainingRegularGame;
	}

	public Double getTimeRemainingQuarter() {
		return component2.timeRemainingQuarter;
	}

	public RawDataEnums.QGroupTimeRemaining getTimeRemainingQGroup() {
		return component2.timeRemainingQGroup;
	}

	public Short getNumberThrowOfQuarter() {
		return component2.numberThrowOfQuarter;
	}

	public RawDataEnums.YesNoNA getLastThrowOfQuarter() {
		return component2.lastThrowOfQuarter;
	}

	public boolean isoTeamLine() {
		return component3.oTeamLine;
	}

	public RawDataEnums.YesNoNA getDefendingTeamLineDLine() {
		return component3.defendingTeamLineDLine;
	}

	public boolean isHomeTeamOLine() {
		return component3.homeTeamOLine;
	}

	public boolean isAwayTeamDLine() {
		return component3.awayTeamDLine;
	}

	public RawDataEnums.YesNoNA getOffenseHome() {
		return component3.offenseHome;
	}

	public Coordinate getLocation1Yards() {
		return component3.location1Yards;
	}

	public Coordinate getLocation2Yards() {
		return component3.location2Yards;
	}

	public Double getLocation1YardsToEndzone() {
		return component3.location1YardsToEndzone;
	}

	public Double getLocation2YardsToEndzone() {
		return component3.location2YardsToEndzone;
	}

	public Double getLocation1YardsFromMiddle() {
		return component3.location1YardsFromMiddle;
	}

	public Double getLocation2YardsFromMiddle() {
		return component3.location2YardsFromMiddle;
	}

	public Coordinate getThrowDistance() {
		return component3.throwDistance;
	}

	public Coordinate getThrowVector() {
		return component3.throwVector;
	}

	public RawDataEnums.ThrowDirectionEnum getThrowDirectionCategory() {
		return component3.throwDirectionCategory;
	}

	public Coordinate getPullCoordinates() {
		return component3.pullCoordinates;
	}

	public Double getPullYardsFromMiddle() {
		return component3.pullYardsFromMiddle;
	}

	public Double getPullYDistance() {
		return component3.pullYDistance;
	}

	public RawDataEnums.YesNoNA getCompletedPass() {
		return component3.completedPass;
	}

	public RawDataEnums.YesNoNA getScoringPass() {
		return component3.scoringPass;
	}

	public Short getThrowNumberInPossession() {
		return component3.throwNumberInPossession;
	}

	public Short getThrowNumberInPoint() {
		return component3.throwNumberInPoint;
	}

	public byte getNumberOfMarkers() {
		return component4.numberOfMarkers;
	}

	public byte getNumberOfMarkersPlusPoachers() {
		return component4.numberOfMarkersPlusPoachers;
	}

	public byte getNumberOfPoachers() {
		return component4.numberOfPoachers;
	}

	public byte getNumberOfClosestDefenders() {
		return component4.numberOfClosestDefenders;
	}

	public RawDataEnums.ClosestDefenderTight getClosestDefenderTight() {
		return component4.closestDefenderTight;
	}

	public RawDataEnums.ForceDirection getForceDirection() {
		return component4.forceDirection;
	}

	public LinkedList<RawDataEnums.ForceDirection> getMainForcePossession() {
		return component4.mainForcePossession;
	}

	public Double getThrowAngle() {
		return component4.throwAngle;
	}

	public RawDataEnums.YesNoNA getOverheadThrow() {
		return component4.overheadThrow;
	}

	public boolean isAnyZoneDOnPossession() {
		return component4.anyZoneDOnPossession;
	}

	public boolean isAnyMixedDOnPossession() {
		return component4.anyMixedDOnPossession;
	}

	public boolean isAnyPersonDOnPossession() {
		return component4.anyPersonDOnPossession;
	}

	public DefenseScheme getdSchemePossession() {
		return component4.dSchemePossession;
	}

}
