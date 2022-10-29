package enums;

import exceptions.BadEnumException;

public enum EventTypeEnum {
	StartDPoint(1),
	StartOPoint(2),
	MidpointTimeoutRecordingTeam(3),
	MidpointTimeoutOpposingTeam(5),
	PullInBounds(7),
	PullOutofBounds(8),
	OffsidesRecordingTeam(9),
	OffsidesOpposingTeam(10),
	Block(11),
	Callahan(12),
	ThrowawayByOpposingTeam(13),
	StallAgainstOpposingTeam(14),
	ScoreByOpposingTeam(15),
	PenaltyOnRecordingTeam(16),
	PenaltyOnOpposingTeam(17),
	Pass(18),
	Goal(19),
	Drop(20),
	ThrowawayByRecordingTeam(22),
	CallahanThrownByRecordingTeam(23),
	StallAgainstRecordingTeam(24),
	Injury(25),
	EndofFirstQuarter(28),
	EndofSecondQuarter(29),
	EndofThirdQuarter(30),
	EndofFourthQuarter(31),
	EndofFirstOvertime(32),
	EndofSecondOvertime(33);
	
	private final int value;
	private EventTypeEnum(int value) {
		this.value = value;
	}
	
	public static EventTypeEnum convertFromString(String s) throws BadEnumException {
		int value = Integer.valueOf(s);
		for (EventTypeEnum e : EventTypeEnum.values()) {
			if (e.value == value)
				return e;
		}
			throw new BadEnumException(s, QuarterEnum.class.getName());
		}

	
}
