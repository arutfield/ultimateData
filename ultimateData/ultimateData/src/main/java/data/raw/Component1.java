package data.raw;

import java.util.Date;
import java.util.LinkedList;

import enums.RawDataEnums;
import enums.RawDataEnums.BlockType;
import enums.RawDataEnums.FoulSide;
import enums.RawDataEnums.TurnoverType;

public class Component1 {

	final RawDataEnums.TurnoverType turnoverType;
	final RawDataEnums.BlockType blockType;
	final RawDataEnums.FoulSide foulSide;
	final Date gameDate;
	final Weather weather;
	final String defendingTeamId;
	final String throwerId;
	final String targetedReceiverId;
	final LinkedList<String> closestDefenders;
	final LinkedList<String> markers;
	final LinkedList<String> poachers;
	final LinkedList<String> deflectors;
	final String pullThrowerId;
	final String pullReceiverId;
	final String oFoulerId;
	final LinkedList<String> dFoulerIds;
	final String oFouledId;
	final String dFouledId;
	
	public Component1(TurnoverType turnoverType, BlockType blockType, FoulSide foulSide, Date gameDate,
			Weather weather, String defendingTeamId, String throwerId, String targetedReceiverId,
			LinkedList<String> closestDefenders, LinkedList<String> markers, LinkedList<String> poachers,
			LinkedList<String> deflectors, String pullThrowerId, String pullReceiverId, String oFoulerId,
			LinkedList<String> dFoulerIds, String oFouledId, String dFouledId) {
		this.turnoverType = turnoverType;
		this.blockType = blockType;
		this.foulSide = foulSide;
		this.gameDate = gameDate;
		this.weather = weather;
		this.defendingTeamId = defendingTeamId;
		this.throwerId = throwerId;
		this.targetedReceiverId = targetedReceiverId;
		this.closestDefenders = closestDefenders;
		this.markers = markers;
		this.poachers = poachers;
		this.deflectors = deflectors;
		this.pullThrowerId = pullThrowerId;
		this.pullReceiverId = pullReceiverId;
		this.oFoulerId = oFoulerId;
		this.dFoulerIds = dFoulerIds;
		this.oFouledId = oFouledId;
		this.dFouledId = dFouledId;
	}

}
