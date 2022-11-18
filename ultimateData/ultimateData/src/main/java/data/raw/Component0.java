package data.raw;

import data.Coordinate;
import enums.RawDataEnums;
import enums.RawDataEnums.BrokeMark;
import enums.RawDataEnums.DefenderDistance;
import enums.RawDataEnums.DefenseScheme;
import enums.RawDataEnums.EventType;
import enums.RawDataEnums.Force;
import enums.RawDataEnums.PassBreak;
import enums.RawDataEnums.PassType;
import enums.RawDataEnums.PullInfo;

public class Component0 {
	final RawDataEnums.DefenseScheme defenseScheme;
	final String teamId;
	final RawDataEnums.EventType eventType;
	final String player1Id;
	final Coordinate location1;
	final String player2Id;
	final Coordinate location2;
	final Double pullTime;
	final RawDataEnums.PullInfo pullInfo;
	final RawDataEnums.DefenderDistance closestDefenderDistance;
	final RawDataEnums.Force force;
	final RawDataEnums.PassType passType;
	final RawDataEnums.BrokeMark brokeMark;
	final RawDataEnums.PassBreak passBreak;
	public Component0(DefenseScheme defenseScheme, String teamId, EventType eventType, String player1Id,
			Coordinate location1, String player2Id, Coordinate location2, Double pullTime, PullInfo pullInfo,
			DefenderDistance closestDefenderDistance, Force force, PassType passType,
			BrokeMark brokeMark, PassBreak passBreak) {
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
		this.force = force;
		this.passType = passType;
		this.brokeMark = brokeMark;
		this.passBreak = passBreak;
	}
}
