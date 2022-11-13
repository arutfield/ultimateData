package data;

import java.util.Date;
import java.util.LinkedList;

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
	public class Weather {
		private final double temperature;
		private final double windSpeed;
		private final double windDirection;
		private final double precipitation;
		private final RawDataEnums.YesNoNA anyPrecipitation;
		public Weather(double temperature, double windSpeed, double windDirection, double precipitation,
				RawDataEnums.YesNoNA anyPrecipitation) {
			this.temperature = temperature;
			this.windSpeed = windSpeed;
			this.windDirection = windDirection;
			this.precipitation = precipitation;
			this.anyPrecipitation = anyPrecipitation;
		}
		public double getTemperature() {
			return temperature;
		}
		public double getWindSpeed() {
			return windSpeed;
		}
		public double getWindDirection() {
			return windDirection;
		}
		public double getPrecipitation() {
			return precipitation;
		}
		public RawDataEnums.YesNoNA getAnyPrecipitation() {
			return anyPrecipitation;
		}
		
	}
	private final String homeTeamId;
	private final String awayTeamId;
	private final RawDataEnums.FieldType fieldType;
	private final short homeScore;
	private final short awayScore;
	private final RawDataEnums.OffenseDirection offenseDirection;
	private final QuarterEnum quarter;
	private final short gameClockSeconds;
	private final RawDataEnums.DefenseScheme defenseScheme;
	private final String teamId;
	private final RawDataEnums.EventType eventType;
	private final String player1Id;
	private final Coordinate location1;
	private final String player2Id;
	private final Coordinate location2; 
	private final double pullTime;
	private final RawDataEnums.PullInfo pullInfo;
	private final RawDataEnums.DefenderDistance closestDefenderDistance;
	private final String closestDefenderId;
	private final RawDataEnums.Force force;
	private final RawDataEnums.PassType passType;
	private final RawDataEnums.BrokeMark brokeMark;
	private final RawDataEnums.PassBreak passBreak;
	private final RawDataEnums.TurnoverType turnoverType;
	private final RawDataEnums.BlockType blockType;
	private final String intendedReceiver;
	private final RawDataEnums.FoulSide	foulSide;
	private final Date gameDate;
	private final Weather weather;
	private final String defendingTeamId;
	private final String throwerId;
	private final String targetedReceiverId;
	private final LinkedList<String> closestDefenderIds = new LinkedList<String>();
	private final LinkedList<String> markers;
	private final LinkedList<String> poacherIds = new LinkedList<String>();
	private final LinkedList<String> deflectorIds = new LinkedList<String>();
	private final String pullThrowerId;
	private final String pullReceiverId;
	private final String oFoulerId;
	private final LinkedList<String> dFoulerIds = new LinkedList<String>();
	private final String oFouledId;
	private final String dFouledId;
	private final String[] homePlayerIds = new String[7];
	private final String[] awayPlayerIds = new String[7];
	private final String homeTeamLocation;
	private final boolean fieldCflType;
	private final short homeAwayDifferential;
	private final short offenseDefenseDifferential;
	private final boolean regulationDone;
	private final short possessionNumber;
	private final short pointNumber;
	private final RawDataEnums.TeamScored teamScoredOnPoint;
	private final boolean teamScoredOnPossession;
	private final short homeScoreEndofGame;
	private final short awayScoreEndofGame;
	private final RawDataEnums.HomeTeamOutcome homeTeamGameOutcome;
	private final short gameClockEstimate;
	private final short durationPointEstimate;
	private final short timeRemainingRegularGame;
	private final short timeRemainingQuarter;
	private final RawDataEnums.QGroupTimeRemaining timeRemainingQGroup;
	private final short numberThrowOfQuarter;
	private final RawDataEnums.YesNoNA lastThrowOfQuarter;
	private final boolean oTeamLine;
	private final RawDataEnums.YesNoNA defendingTeamLineDLine;
	private final boolean homeTeamOLine;
	private final boolean awayTeamDLine;
	private final RawDataEnums.YesNoNA offenseHome;
	private final Coordinate location1Yards;
	private final Coordinate location2Yards;
	private final double location1YardsToEndzone;
	private final double location2YardsToEndzone;
	private final double location1YardsFromMiddle;
	private final double location2YardsFromMiddle;
	private final Coordinate throwDistance;
	private final Coordinate throwVector;
	private final RawDataEnums.ThrowDirectionEnum throwDirectionCategory;
	private final Coordinate pullCoordinates;
	private final double pullYardsFromMiddle;
	private final double pullYDistance;
	private final RawDataEnums.YesNoNA completedPass;
	private final RawDataEnums.YesNoNA scoringPass;
	private final short throwNumberInPossession;
	private final short throwNumberInPoint;
	private final byte numberOfMarkers;
	private final byte numberOfMarkersPlusPoachers;
	private final byte numberOfPoachers;
	private final byte numberOfClosestDefenders;
	private final RawDataEnums.ClosestDefenderTight	closestDefenderTight;
	private final RawDataEnums.ForceDirection forceDirection;
	private final LinkedList<RawDataEnums.ForceDirection> mainForcePossession;
	private final double throwAngle;
	private final RawDataEnums.YesNoNA overheadThrow;
	private final RawDataEnums.YesNoNA anyZoneDOnPossession;
	private final boolean anyMixedDOnPossession;
	private final boolean anyPersonDOnPossession;
	private final DefenseScheme dSchemePossession;
	
	public RawData(String homeTeamId, String awayTeamId, FieldType fieldType, short homeScore,
			short awayScore, OffenseDirection offenseDirection, QuarterEnum quarter, short gameClockSeconds,
			DefenseScheme defenseScheme, String teamId, EventType eventType, String player1Id, Coordinate location1,
			String player2Id, Coordinate location2, double pullTime, PullInfo pullInfo,
			DefenderDistance closestDefenderDistance, String closestDefenderId, Force force, PassType passType,
			BrokeMark brokeMark, PassBreak passBreak, TurnoverType turnoverType, BlockType blockType,
			String intendedReceiver, FoulSide foulSide, Date gameDate, Weather weather, String defendingTeamId,
			String throwerId, String targetedReceiverId, LinkedList<String> markers, String pullThrowerId,
			String pullReceiverId, String oFoulerId, String oFouledId, String dFouledId, String homeTeamLocation,
			boolean fieldCflType, short homeAwayDifferential, short offenseDefenseDifferential, boolean regulationDone,
			short possessionNumber, short pointNumber, TeamScored teamScoredOnPoint, boolean teamScoredOnPossession,
			short homeScoreEndofGame, short awayScoreEndofGame, HomeTeamOutcome homeTeamGameOutcome,
			short gameClockEstimate, short durationPointEstimate, short timeRemainingRegularGame,
			short timeRemainingQuarter, QGroupTimeRemaining timeRemainingQGroup, short numberThrowOfQuarter,
			YesNoNA lastThrowOfQuarter, boolean oTeamLine, YesNoNA defendingTeamLineDLine, boolean homeTeamOLine,
			boolean awayTeamDLine, YesNoNA offenseHome, Coordinate location1Yards, Coordinate location2Yards,
			double location1YardsToEndzone, double location2YardsToEndzone, double location1YardsFromMiddle,
			double location2YardsFromMiddle, Coordinate throwDistance, Coordinate throwVector,
			ThrowDirectionEnum throwDirectionCategory, Coordinate pullCoordinates, double pullYardsFromMiddle,
			double pullYDistance, YesNoNA completedPass, YesNoNA scoringPass, short throwNumberInPossession,
			short throwNumberInPoint, byte numberOfMarkers, byte numberOfMarkersPlusPoachers, byte numberOfPoachers,
			byte numberOfClosestDefenders, ClosestDefenderTight closestDefenderTight, ForceDirection forceDirection,
			LinkedList<ForceDirection> mainForcePossession, double throwAngle, YesNoNA overheadThrow,
			YesNoNA anyZoneDOnPossession, boolean anyMixedDOnPossession, boolean anyPersonDOnPossession,
			DefenseScheme dSchemePossession) {
		this.homeTeamId = homeTeamId;
		this.awayTeamId = awayTeamId;
		this.fieldType = fieldType;
		this.homeScore = homeScore;
		this.awayScore = awayScore;
		this.offenseDirection = offenseDirection;
		this.quarter = quarter;
		this.gameClockSeconds = gameClockSeconds;
		this.defenseScheme = defenseScheme;
		this.teamId = teamId;
		this.eventType = eventType;
		this.player1Id = player1Id;
		this.location1 = location1;
		this.player2Id = player2Id;
		this.location2 = location2;
		this.pullTime = pullTime;
		this.pullInfo = pullInfo;
		this.closestDefenderDistance = closestDefenderDistance;
		this.closestDefenderId = closestDefenderId;
		this.force = force;
		this.passType = passType;
		this.brokeMark = brokeMark;
		this.passBreak = passBreak;
		this.turnoverType = turnoverType;
		this.blockType = blockType;
		this.intendedReceiver = intendedReceiver;
		this.foulSide = foulSide;
		this.gameDate = gameDate;
		this.weather = weather;
		this.defendingTeamId = defendingTeamId;
		this.throwerId = throwerId;
		this.targetedReceiverId = targetedReceiverId;
		this.markers = markers;
		this.pullThrowerId = pullThrowerId;
		this.pullReceiverId = pullReceiverId;
		this.oFoulerId = oFoulerId;
		this.oFouledId = oFouledId;
		this.dFouledId = dFouledId;
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
		this.gameClockEstimate = gameClockEstimate;
		this.durationPointEstimate = durationPointEstimate;
		this.timeRemainingRegularGame = timeRemainingRegularGame;
		this.timeRemainingQuarter = timeRemainingQuarter;
		this.timeRemainingQGroup = timeRemainingQGroup;
		this.numberThrowOfQuarter = numberThrowOfQuarter;
		this.lastThrowOfQuarter = lastThrowOfQuarter;
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
		this.numberOfMarkers = numberOfMarkers;
		this.numberOfMarkersPlusPoachers = numberOfMarkersPlusPoachers;
		this.numberOfPoachers = numberOfPoachers;
		this.numberOfClosestDefenders = numberOfClosestDefenders;
		this.closestDefenderTight = closestDefenderTight;
		this.forceDirection = forceDirection;
		this.mainForcePossession = mainForcePossession;
		this.throwAngle = throwAngle;
		this.overheadThrow = overheadThrow;
		this.anyZoneDOnPossession = anyZoneDOnPossession;
		this.anyMixedDOnPossession = anyMixedDOnPossession;
		this.anyPersonDOnPossession = anyPersonDOnPossession;
		this.dSchemePossession = dSchemePossession;
	}

	public String getHomeTeamId() {
		return homeTeamId;
	}

	public String getAwayTeamId() {
		return awayTeamId;
	}

	public RawDataEnums.FieldType getFieldType() {
		return fieldType;
	}

	public short getHomeScore() {
		return homeScore;
	}

	public short getAwayScore() {
		return awayScore;
	}

	public RawDataEnums.OffenseDirection getOffenseDirection() {
		return offenseDirection;
	}

	public QuarterEnum getQuarter() {
		return quarter;
	}

	public short getGameClockSeconds() {
		return gameClockSeconds;
	}

	public RawDataEnums.DefenseScheme getDefenseScheme() {
		return defenseScheme;
	}

	public String getTeamId() {
		return teamId;
	}

	public RawDataEnums.EventType getEventType() {
		return eventType;
	}

	public String getPlayer1Id() {
		return player1Id;
	}

	public Coordinate getLocation1() {
		return location1;
	}

	public String getPlayer2Id() {
		return player2Id;
	}

	public Coordinate getLocation2() {
		return location2;
	}

	public double getPullTime() {
		return pullTime;
	}

	public RawDataEnums.PullInfo getPullInfo() {
		return pullInfo;
	}

	public RawDataEnums.DefenderDistance getClosestDefenderDistance() {
		return closestDefenderDistance;
	}

	public String getClosestDefenderId() {
		return closestDefenderId;
	}

	public RawDataEnums.Force getForce() {
		return force;
	}

	public RawDataEnums.PassType getPassType() {
		return passType;
	}

	public RawDataEnums.BrokeMark getBrokeMark() {
		return brokeMark;
	}

	public RawDataEnums.PassBreak getPassBreak() {
		return passBreak;
	}

	public RawDataEnums.TurnoverType getTurnoverType() {
		return turnoverType;
	}

	public RawDataEnums.BlockType getBlockType() {
		return blockType;
	}

	public String getIntendedReceiver() {
		return intendedReceiver;
	}

	public RawDataEnums.FoulSide getFoulSide() {
		return foulSide;
	}

	public Date getGameDate() {
		return gameDate;
	}

	public Weather getWeather() {
		return weather;
	}

	public String getDefendingTeamId() {
		return defendingTeamId;
	}

	public String getThrowerId() {
		return throwerId;
	}

	public String getTargetedReceiverId() {
		return targetedReceiverId;
	}

	public LinkedList<String> getClosestDefenderIds() {
		return closestDefenderIds;
	}

	public LinkedList<String> getMarkers() {
		return markers;
	}

	public LinkedList<String> getPoacherIds() {
		return poacherIds;
	}

	public LinkedList<String> getDeflectorIds() {
		return deflectorIds;
	}

	public String getPullThrowerId() {
		return pullThrowerId;
	}

	public String getPullReceiverId() {
		return pullReceiverId;
	}

	public String getoFoulerId() {
		return oFoulerId;
	}

	public LinkedList<String> getdFoulerIds() {
		return dFoulerIds;
	}

	public String getoFouledId() {
		return oFouledId;
	}

	public String getdFouledId() {
		return dFouledId;
	}

	public String[] getHomePlayerIds() {
		return homePlayerIds;
	}

	public String[] getAwayPlayerIds() {
		return awayPlayerIds;
	}

	public String getHomeTeamLocation() {
		return homeTeamLocation;
	}

	public boolean isFieldCflType() {
		return fieldCflType;
	}

	public short getHomeAwayDifferential() {
		return homeAwayDifferential;
	}

	public short getOffenseDefenseDifferential() {
		return offenseDefenseDifferential;
	}

	public boolean isRegulationDone() {
		return regulationDone;
	}

	public short getPossessionNumber() {
		return possessionNumber;
	}

	public short getPointNumber() {
		return pointNumber;
	}

	public RawDataEnums.TeamScored getTeamScoredOnPoint() {
		return teamScoredOnPoint;
	}

	public boolean isTeamScoredOnPossession() {
		return teamScoredOnPossession;
	}

	public short getHomeScoreEndofGame() {
		return homeScoreEndofGame;
	}

	public short getAwayScoreEndofGame() {
		return awayScoreEndofGame;
	}

	public RawDataEnums.HomeTeamOutcome getHomeTeamGameOutcome() {
		return homeTeamGameOutcome;
	}

	public short getGameClockEstimate() {
		return gameClockEstimate;
	}

	public short getDurationPointEstimate() {
		return durationPointEstimate;
	}

	public short getTimeRemainingRegularGame() {
		return timeRemainingRegularGame;
	}

	public short getTimeRemainingQuarter() {
		return timeRemainingQuarter;
	}

	public RawDataEnums.QGroupTimeRemaining getTimeRemainingQGroup() {
		return timeRemainingQGroup;
	}

	public short getNumberThrowOfQuarter() {
		return numberThrowOfQuarter;
	}

	public RawDataEnums.YesNoNA getLastThrowOfQuarter() {
		return lastThrowOfQuarter;
	}

	public boolean isoTeamLine() {
		return oTeamLine;
	}

	public RawDataEnums.YesNoNA getDefendingTeamLineDLine() {
		return defendingTeamLineDLine;
	}

	public boolean isHomeTeamOLine() {
		return homeTeamOLine;
	}

	public boolean isAwayTeamDLine() {
		return awayTeamDLine;
	}

	public RawDataEnums.YesNoNA getOffenseHome() {
		return offenseHome;
	}

	public Coordinate getLocation1Yards() {
		return location1Yards;
	}

	public Coordinate getLocation2Yards() {
		return location2Yards;
	}

	public double getLocation1YardsToEndzone() {
		return location1YardsToEndzone;
	}

	public double getLocation2YardsToEndzone() {
		return location2YardsToEndzone;
	}

	public double getLocation1YardsFromMiddle() {
		return location1YardsFromMiddle;
	}

	public double getLocation2YardsFromMiddle() {
		return location2YardsFromMiddle;
	}

	public Coordinate getThrowDistance() {
		return throwDistance;
	}

	public Coordinate getThrowVector() {
		return throwVector;
	}

	public RawDataEnums.ThrowDirectionEnum getThrowDirectionCategory() {
		return throwDirectionCategory;
	}

	public Coordinate getPullCoordinates() {
		return pullCoordinates;
	}

	public double getPullYardsFromMiddle() {
		return pullYardsFromMiddle;
	}

	public double getPullYDistance() {
		return pullYDistance;
	}

	public RawDataEnums.YesNoNA getCompletedPass() {
		return completedPass;
	}

	public RawDataEnums.YesNoNA getScoringPass() {
		return scoringPass;
	}

	public short getThrowNumberInPossession() {
		return throwNumberInPossession;
	}

	public short getThrowNumberInPoint() {
		return throwNumberInPoint;
	}

	public byte getNumberOfMarkers() {
		return numberOfMarkers;
	}

	public byte getNumberOfMarkersPlusPoachers() {
		return numberOfMarkersPlusPoachers;
	}

	public byte getNumberOfPoachers() {
		return numberOfPoachers;
	}

	public byte getNumberOfClosestDefenders() {
		return numberOfClosestDefenders;
	}

	public RawDataEnums.ClosestDefenderTight getClosestDefenderTight() {
		return closestDefenderTight;
	}

	public RawDataEnums.ForceDirection getForceDirection() {
		return forceDirection;
	}

	public LinkedList<RawDataEnums.ForceDirection> getMainForcePossession() {
		return mainForcePossession;
	}

	public double getThrowAngle() {
		return throwAngle;
	}

	public RawDataEnums.YesNoNA getOverheadThrow() {
		return overheadThrow;
	}

	public RawDataEnums.YesNoNA getAnyZoneDOnPossession() {
		return anyZoneDOnPossession;
	}

	public boolean isAnyMixedDOnPossession() {
		return anyMixedDOnPossession;
	}

	public boolean isAnyPersonDOnPossession() {
		return anyPersonDOnPossession;
	}

	public DefenseScheme getdSchemePossession() {
		return dSchemePossession;
	}

}
