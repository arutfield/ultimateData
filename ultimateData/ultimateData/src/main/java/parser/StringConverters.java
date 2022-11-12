package parser;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import data.Coordinate;
import data.Event;
import data.GameDetails;
import data.PlayerGame;
import data.PlayerSeason;
import data.TeamStats;
import enums.EventTypeEnum;
import enums.QuarterEnum;
import exceptions.BadEnumException;
import exceptions.WrongSizeRowException;

public class StringConverters {
	private static final Logger logger = LogManager.getLogger(StringConverters.class);

	public enum GameEventEnum {
		gameID(0), awayTeam(1), homeTeam(2), recordingTeam(3), offenseTeam(4), defenseTeam(5), offense(6), eventType(7),
		pointNumber(9), possessionNumber(10), throwInPossession(11), homeTeamScore(12), awayTeamScore(13), quarter(14),
		quarterTime(15), player1(16), player2(17), player3(18), player4(19), player5(20), player6(21), player7(22),
		puller(23), pullX(24), pullY(25), pullMs(26), thrower(27), throwerX(28), throwerY(29), receiver(30),
		receiverX(31), receiverY(32), throwXDistance(33), throwYDistance(34), throwDistance(35), defender(36),
		turnoverX(37), turnoverY(38), index(39);

		private GameEventEnum(int value) {
			this.value = value;
		}

		private final int value;

		public int getValue() {
			return value;
		}
	}

	public enum PlayerGameEnum {
		gameID(0), playerId(1), jerseyNumber(4), assists(6), goals(7), hockeyAssists(8), completions(9), throwAttempts(10),
		throwaways(11), stalls(12), callahansThrown(13), yardsReceived(14), yardsThrown(15), hucksAttempted(16),
		hucksCompleted(17), catches(18), drops(19), blocks(20), callahans(21), pulls(22), obPulls(23),
		recordedPulls(24), recordedPullsHangtime(25), oPointsPlayed(26), oPointsScored(27), dPointsPlayed(28),
		dPointsScored(29), secondsPlayed(30), oOpportunities(31), oOpportunityScores(32), dOpportunities(33),
		dOpportunityStops(34), completionPercentage(35), throwawayPercentage(36), hucksPercentage(37), totalYards(38),
		assistsTotal(39), scoringResultsParticipated(40), throwYardsPerAttempt(41), yardsPerReception(42);

		private final int value;

		PlayerGameEnum(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	}

	static final int gameEventLength = 40;
	static final int playerGameLength = 43;
	static final short gameDetailLength = 7;

	public static Event convertToEvent(String[] values) throws WrongSizeRowException, BadEnumException {
		if (values.length != gameEventLength)
			throw new WrongSizeRowException(gameEventLength, values.length);
		String[] players = new String[] { values[GameEventEnum.player1.value], values[GameEventEnum.player2.value],
				values[GameEventEnum.player3.value], values[GameEventEnum.player4.value],
				values[GameEventEnum.player5.value], values[GameEventEnum.player6.value],
				values[GameEventEnum.player7.value] };
		Coordinate pullCoordinates = null;
		try {
			pullCoordinates = new Coordinate(Double.valueOf(values[GameEventEnum.pullX.value]),
					Double.valueOf(values[GameEventEnum.pullY.value]));
		} catch (NumberFormatException ex) {
			logger.debug("NumberFormat Exception for finding pull coordinates: " + ex.getMessage());
		}
		Coordinate throwerCoordinates = null;
		try {
			throwerCoordinates = new Coordinate(Double.valueOf(values[GameEventEnum.throwerX.value]),
					Double.valueOf(values[GameEventEnum.throwerY.value]));
		} catch (NumberFormatException ex) {
			logger.debug("NumberFormat Exception for finding thrower coordinates: " + ex.getMessage());
		}
		Coordinate receiverCoordinates = null;
		try {
			receiverCoordinates = new Coordinate(Double.valueOf(values[GameEventEnum.receiverX.value]),
					Double.valueOf(values[GameEventEnum.receiverY.value]));
		} catch (NumberFormatException ex) {
			logger.debug("NumberFormat Exception for finding receiver coordinates: " + ex.getMessage());
		}
		Coordinate throwDistance = null;
		try {
			throwDistance = new Coordinate(Double.valueOf(values[GameEventEnum.throwXDistance.value]),
					Double.valueOf(values[GameEventEnum.throwYDistance.value]));
		} catch (NumberFormatException ex) {
			logger.debug("NumberFormat Exception for finding throw distance: " + ex.getMessage());
		}

		Coordinate turnoverCoordinates = null;
		try {
			turnoverCoordinates = new Coordinate(Double.valueOf(values[GameEventEnum.turnoverX.value]),
					Double.valueOf(values[GameEventEnum.turnoverY.value]));
		} catch (NumberFormatException ex) {
			logger.debug("NumberFormat Exception for finding turnover coordinates: " + ex.getMessage());
		}

		int pullMs = Integer.MIN_VALUE;
		try {
			pullMs = Integer.parseInt(values[GameEventEnum.pullMs.value]);
		} catch (NumberFormatException ex) {
			logger.debug("NumberFormat Exception for pull Ms: " + ex.getMessage());
		}

		return new Event(values[GameEventEnum.awayTeam.value], values[GameEventEnum.homeTeam.value],
				values[GameEventEnum.recordingTeam.value], values[GameEventEnum.offenseTeam.value],
				values[GameEventEnum.defenseTeam.value], values[GameEventEnum.offense.value] == "1",
				EventTypeEnum.convertFromString(values[GameEventEnum.eventType.value]),
				Byte.parseByte(values[GameEventEnum.pointNumber.value]),
				Byte.parseByte(values[GameEventEnum.possessionNumber.value]),
				Byte.parseByte(values[GameEventEnum.throwInPossession.value]),
				Byte.parseByte(values[GameEventEnum.homeTeamScore.value]),
				Byte.parseByte(values[GameEventEnum.awayTeamScore.value]),
				QuarterEnum.convertFromString(values[GameEventEnum.quarter.value]),
				Integer.parseInt(values[GameEventEnum.quarterTime.value]), players, values[GameEventEnum.puller.value],
				pullCoordinates, pullMs, values[GameEventEnum.thrower.value], throwerCoordinates, receiverCoordinates,
				throwDistance, values[GameEventEnum.defender.value], turnoverCoordinates,
				Short.valueOf(values[GameEventEnum.index.value]));
	}

	public static PlayerGame convertToPlayerGame(String[] values) throws WrongSizeRowException, BadEnumException {
		if (values.length != playerGameLength)
			throw new WrongSizeRowException(gameEventLength, values.length);

		double throwYardsPerAttempt = Double.NaN;
		try {
			throwYardsPerAttempt = Double.valueOf(values[PlayerGameEnum.throwYardsPerAttempt.value]);
		} catch (NumberFormatException ex) {
			logger.debug("NumberFormat Exception for throw yards per attempt: " + ex.getMessage());
		}

		double yardsPerReception = Double.NaN;
		try {
			yardsPerReception = Double.valueOf(values[PlayerGameEnum.yardsPerReception.value]);
		} catch (NumberFormatException ex) {
			logger.debug("NumberFormat Exception for yards per reception: " + ex.getMessage());
		}

		return new PlayerGame(values[PlayerGameEnum.gameID.value],
				Short.valueOf(values[PlayerGameEnum.assists.value]),
				Short.valueOf(values[PlayerGameEnum.goals.value]),
				Short.valueOf(values[PlayerGameEnum.hockeyAssists.value]),
				Short.valueOf(values[PlayerGameEnum.completions.value]),
				Short.valueOf(values[PlayerGameEnum.throwAttempts.value]),
				Short.valueOf(values[PlayerGameEnum.throwaways.value]),
				Short.valueOf(values[PlayerGameEnum.stalls.value]),
				Short.valueOf(values[PlayerGameEnum.callahansThrown.value]),
				Short.valueOf(values[PlayerGameEnum.yardsReceived.value]),
				Short.valueOf(values[PlayerGameEnum.yardsThrown.value]),
				Short.valueOf(values[PlayerGameEnum.hucksAttempted.value]),
				Short.valueOf(values[PlayerGameEnum.hucksCompleted.value]),
				Short.valueOf(values[PlayerGameEnum.catches.value]), Short.valueOf(values[PlayerGameEnum.drops.value]),
				Short.valueOf(values[PlayerGameEnum.blocks.value]),
				Short.valueOf(values[PlayerGameEnum.callahans.value]),
				Short.valueOf(values[PlayerGameEnum.pulls.value]), Short.valueOf(values[PlayerGameEnum.obPulls.value]),
				Short.valueOf(values[PlayerGameEnum.recordedPulls.value]),
				Integer.valueOf(values[PlayerGameEnum.recordedPullsHangtime.value]),
				Short.valueOf(values[PlayerGameEnum.oPointsPlayed.value]),
				Short.valueOf(values[PlayerGameEnum.oPointsScored.value]),
				Short.valueOf(values[PlayerGameEnum.dPointsPlayed.value]),
				Short.valueOf(values[PlayerGameEnum.dPointsScored.value]),
				Short.valueOf(values[PlayerGameEnum.secondsPlayed.value]),
				Short.valueOf(values[PlayerGameEnum.oOpportunities.value]),
				Short.valueOf(values[PlayerGameEnum.oOpportunityScores.value]),
				Short.valueOf(values[PlayerGameEnum.dOpportunities.value]),
				Short.valueOf(values[PlayerGameEnum.dOpportunityStops.value]),
				Double.valueOf(values[PlayerGameEnum.completionPercentage.value]),
				Double.valueOf(values[PlayerGameEnum.throwawayPercentage.value]),
				Double.valueOf(values[PlayerGameEnum.hucksPercentage.value]),
				Short.valueOf(values[PlayerGameEnum.totalYards.value]),
				Short.valueOf(values[PlayerGameEnum.assistsTotal.value]),
				Short.valueOf(values[PlayerGameEnum.scoringResultsParticipated.value]), throwYardsPerAttempt,
				yardsPerReception);
	}

	enum GameDetailEnum {
		gameID(0), awayTeam(1), homeTeam(2), awayScore(3), homeScore(4), time(5), week(6);

		private GameDetailEnum(int value) {
			this.value = value;
		}

		private final int value;

		int getValue() {
			return value;
		}

	}

	public static GameDetails convertToGameDetails(String[] values)
			throws WrongSizeRowException, NumberFormatException, ParseException {
		if (values.length != gameDetailLength)
			throw new WrongSizeRowException(gameDetailLength, values.length);
		return new GameDetails(values[GameDetailEnum.awayTeam.value],
				values[GameDetailEnum.homeTeam.value], Short.valueOf(values[GameDetailEnum.awayScore.value]),
				Short.valueOf(values[GameDetailEnum.homeScore.value]),
				parseStartTime(values[GameDetailEnum.time.value]), parseWeek(values[GameDetailEnum.week.value]));
	}

	private static long parseStartTime(String startTime) throws ParseException {
		String removeT = startTime.replace('T', ' ');
		String cutTimeZone = removeT.substring(0, removeT.length()-6);
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date actualDate = (Date) sdfDate.parse(cutTimeZone);
		String[] splitBySpace = cutTimeZone.split(" ", 2);
		Date startOfDate = (Date) sdfDate.parse(splitBySpace[0] + " 00:00:00");
		return actualDate.getTime() - startOfDate.getTime();
	}

	private static short parseWeek(String weekString) {
		try {
			return Short.parseShort(weekString);
		} catch (NumberFormatException ex) {
			if (weekString.equals("playoffs"))
				return 18;
			if (weekString.equals("championship-weekend"))
				return 19;
		}
		throw new NumberFormatException("Unknown value");
	}
	
	enum PlayerSeasonEnum {
		year(0), playerID(1), teamID(4), games(5), assists(6), goals(7),
		hockeyAssists(8), completions(9), throwAttempts(10), throwaways(11),
		stalls(12), callahansThrown(13), yardsReceived(14), yardsThrown(15),
		hucksAttempted(16), hucksCompleted(17), catches(18), drops(19),
		blocks(20), callahans(21), pulls(22), obPulls(23), recordedPulls(24),
		recordedPullsHangtime(25), oPointsPlayed(26), oPointsScored(27),
		dPointsPlayed(28), dPointsScored(29), secondsPlayed(30), oOpportunities(31),
		oOpportunityScores(32), dOpportunities(33), dOpportunityStops(34);

		private PlayerSeasonEnum(int value) {
			this.value = value;
		}

		private final int value;

		int getValue() {
			return value;
		}
	}

	public static PlayerSeason convertToPlayerSeason(String[] values) {
		return new PlayerSeason(Short.parseShort(values[PlayerSeasonEnum.year.value]),
				values[PlayerSeasonEnum.teamID.value],
				Short.parseShort(values[PlayerSeasonEnum.games.value]),
				Short.parseShort(values[PlayerSeasonEnum.assists.value]),
				Short.parseShort(values[PlayerSeasonEnum.goals.value]),
				Short.parseShort(values[PlayerSeasonEnum.hockeyAssists.value]),
				Short.parseShort(values[PlayerSeasonEnum.completions.value]),
				Short.parseShort(values[PlayerSeasonEnum.throwAttempts.value]),
				Short.parseShort(values[PlayerSeasonEnum.throwaways.value]),
				Short.parseShort(values[PlayerSeasonEnum.stalls.value]),
				Short.parseShort(values[PlayerSeasonEnum.callahansThrown.value]),
				Short.parseShort(values[PlayerSeasonEnum.yardsReceived.value]),
				Short.parseShort(values[PlayerSeasonEnum.yardsThrown.value]),
				Short.parseShort(values[PlayerSeasonEnum.hucksAttempted.value]),
				Short.parseShort(values[PlayerSeasonEnum.hucksCompleted.value]),
				Short.parseShort(values[PlayerSeasonEnum.catches.value]),
				Short.parseShort(values[PlayerSeasonEnum.drops.value]),
				Short.parseShort(values[PlayerSeasonEnum.blocks.value]),
				Short.parseShort(values[PlayerSeasonEnum.callahans.value]),
				Short.parseShort(values[PlayerSeasonEnum.pulls.value]),
				Short.parseShort(values[PlayerSeasonEnum.obPulls.value]),
				Short.parseShort(values[PlayerSeasonEnum.recordedPulls.value]),
				Integer.parseInt(values[PlayerSeasonEnum.recordedPullsHangtime.value]),
				Short.parseShort(values[PlayerSeasonEnum.oPointsPlayed.value]),
				Short.parseShort(values[PlayerSeasonEnum.oPointsScored.value]),
				Short.parseShort(values[PlayerSeasonEnum.dPointsPlayed.value]),
				Short.parseShort(values[PlayerSeasonEnum.dPointsScored.value]),
				Short.parseShort(values[PlayerSeasonEnum.secondsPlayed.value]),
				Short.parseShort(values[PlayerSeasonEnum.oOpportunities.value]),
				Short.parseShort(values[PlayerSeasonEnum.oOpportunityScores.value]),
				Short.parseShort(values[PlayerSeasonEnum.dOpportunities.value]),
				Short.parseShort(values[PlayerSeasonEnum.dOpportunityStops.value]));
	}
	
	public enum TeamStatsEnum {
		teamId(1), year(2), divisionId(3), wins(8), losses(9), ties(10), divStanding(11);

		private TeamStatsEnum(int value) {
			this.value = value;
		}

		private final int value;

		public int getValue() {
			return value;
		}
	}

	

	public static TeamStats convertToTeamStats(String[] values) {
		return new TeamStats(Short.valueOf(values[TeamStatsEnum.year.value]),
				values[TeamStatsEnum.divisionId.value], Short.valueOf(values[TeamStatsEnum.wins.value]),
						Short.valueOf(values[TeamStatsEnum.losses.value]),
						Short.valueOf(values[TeamStatsEnum.ties.value]),
						Short.valueOf(values[TeamStatsEnum.divStanding.value]));
	}
}

