package data.raw;

import java.util.LinkedList;

import enums.RawDataEnums;
import enums.RawDataEnums.ClosestDefenderTight;
import enums.RawDataEnums.DefenseScheme;
import enums.RawDataEnums.ForceDirection;
import enums.RawDataEnums.YesNoNA;

public class Component4 {

	final byte numberOfMarkers;
	final byte numberOfMarkersPlusPoachers;
	final byte numberOfPoachers;
	final byte numberOfClosestDefenders;
	final RawDataEnums.ClosestDefenderTight closestDefenderTight;
	final RawDataEnums.ForceDirection forceDirection;
	final LinkedList<RawDataEnums.ForceDirection> mainForcePossession;
	final Double throwAngle;
	final RawDataEnums.YesNoNA overheadThrow;
	final boolean anyZoneDOnPossession;
	final boolean anyMixedDOnPossession;
	final boolean anyPersonDOnPossession;
	final DefenseScheme dSchemePossession;
	
	public Component4(byte numberOfMarkers, byte numberOfMarkersPlusPoachers, byte numberOfPoachers,
			byte numberOfClosestDefenders, ClosestDefenderTight closestDefenderTight, ForceDirection forceDirection,
			LinkedList<ForceDirection> mainForcePossession, Double throwAngle, YesNoNA overheadThrow,
			boolean anyZoneDOnPossession, boolean anyMixedDOnPossession, boolean anyPersonDOnPossession,
			DefenseScheme dSchemePossession) {
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
	
}
