package parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import data.Coordinate;
import data.Event;
import enums.EventTypeEnum;
import enums.QuarterEnum;
import exceptions.BadEnumException;
import exceptions.WrongSizeRowException;

public class StringConverters {
    private static final Logger logger = LogManager.getLogger(StringConverters.class);

	
	enum GameEventEnum {
		gameID(0),
		awayTeam(1),
		homeTeam(2),
		recordingTeam(3),
		offenseTeam(4),
		defenseTeam(5),
		offense(6),
		eventType(7),
		pointNumber(9),
		possessionNumber(10),
		throwInPossession(11),
		homeTeamScore(12),
		awayTeamScore(13),
		quarter(14),
		quarterTime(15),
		player1(16),
		player2(17),
		player3(18),
		player4(19),
		player5(20),
		player6(21),
		player7(22),
		puller(23),
		pullX(24),
		pullY(25),
		pullMs(26),
		thrower(27),
		throwerX(28),
		throwerY(29),
		receiver(30),
		receiverX(31),
		receiverY(32),
		throwXDistance(33),
		throwYDistance(34),
		throwDistance(35),
		defender(36),
		turnoverX(37),
		turnoverY(38),
		index(39);
		
		private GameEventEnum(int value) {
			this.value = value;
		}
		private final int value;
		int getValue() { return value; }
	}
	
	
	static final int gameEventLength = 40;
    static Event convertToEvent(String[] values) throws WrongSizeRowException, BadEnumException {
		if (values.length != gameEventLength)
			throw new WrongSizeRowException(gameEventLength, values.length);
		String[] players = new String[]{values[GameEventEnum.player1.value], values[GameEventEnum.player2.value], values[GameEventEnum.player3.value],
				values[GameEventEnum.player4.value], values[GameEventEnum.player5.value], values[GameEventEnum.player6.value],
				values[GameEventEnum.player7.value]};
		Coordinate pullCoordinates = null;
		try {
			pullCoordinates = new Coordinate(Double.valueOf(values[GameEventEnum.pullX.value]),
					Double.valueOf(values[GameEventEnum.pullY.value]));
		} catch (NumberFormatException ex) {
			logger.warn("NumberFormat Exception for finding pull coordinates: " + ex.getMessage());
		}
		Coordinate throwerCoordinates = null;
		try {
			throwerCoordinates = new Coordinate(Double.valueOf(values[GameEventEnum.throwerX.value]),
					Double.valueOf(values[GameEventEnum.throwerY.value]));
		} catch (NumberFormatException ex) {
			logger.warn("NumberFormat Exception for finding thrower coordinates: " + ex.getMessage());
		}
		Coordinate receiverCoordinates = null;
		try {
			receiverCoordinates = new Coordinate(Double.valueOf(values[GameEventEnum.receiverX.value]),
					Double.valueOf(values[GameEventEnum.receiverY.value]));
		} catch (NumberFormatException ex) {
			logger.warn("NumberFormat Exception for finding receiver coordinates: " + ex.getMessage());
		}
		Coordinate throwDistance = null;
		try {
			throwDistance = new Coordinate(Double.valueOf(values[GameEventEnum.throwXDistance.value]),
					Double.valueOf(values[GameEventEnum.throwYDistance.value]));
		} catch (NumberFormatException ex) {
			logger.warn("NumberFormat Exception for finding throw distance: " + ex.getMessage());
		}		
		
		
		Coordinate turnoverCoordinates = null;
		try {
			turnoverCoordinates = new Coordinate(Double.valueOf(values[GameEventEnum.turnoverX.value]),
					Double.valueOf(values[GameEventEnum.turnoverY.value]));
		} catch (NumberFormatException ex) {
			logger.warn("NumberFormat Exception for finding turnover coordinates: " + ex.getMessage());
		}
		
		int pullMs = Integer.MIN_VALUE;
		try {
			pullMs = Integer.parseInt(values[GameEventEnum.pullMs.value]);
		} catch (NumberFormatException ex) {
			logger.warn("NumberFormat Exception for pull Ms: " + ex.getMessage());
		}

		return new Event(values[GameEventEnum.gameID.value], values[GameEventEnum.awayTeam.value], values[GameEventEnum.homeTeam.value],
				values[GameEventEnum.recordingTeam.value], values[GameEventEnum.offenseTeam.value], values[GameEventEnum.defenseTeam.value],
				values[GameEventEnum.offense.value] == "1", EventTypeEnum.convertFromString(values[GameEventEnum.eventType.value]),
				Byte.parseByte(values[GameEventEnum.pointNumber.value]), Byte.parseByte(values[GameEventEnum.possessionNumber.value]),
				Byte.parseByte(values[GameEventEnum.throwInPossession.value]), Byte.parseByte(values[GameEventEnum.homeTeamScore.value]),
				Byte.parseByte(values[GameEventEnum.awayTeamScore.value]),
				QuarterEnum.convertFromString(values[GameEventEnum.quarter.value]),
				Integer.parseInt(values[GameEventEnum.quarterTime.value]),
				players, values[GameEventEnum.puller.value], pullCoordinates, pullMs,
				values[GameEventEnum.thrower.value], throwerCoordinates, receiverCoordinates, throwDistance, values[GameEventEnum.defender.value],
				turnoverCoordinates, Short.valueOf(values[GameEventEnum.index.value]));
	}
}
