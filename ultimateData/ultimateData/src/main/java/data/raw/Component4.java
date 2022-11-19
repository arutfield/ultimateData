package data.raw;

import java.util.LinkedList;

import enums.RawDataEnums;
import enums.RawDataEnums.ClosestDefenderTight;
import enums.RawDataEnums.DefenseScheme;
import enums.RawDataEnums.ForceDirection;
import enums.RawDataEnums.YesNoNA;

public class Component4 {

	final Byte numberOfMarkers;
	final Byte numberOfMarkersPlusPoachers;
	final Byte numberOfPoachers;
	final Byte numberOfClosestDefenders;
	final RawDataEnums.ClosestDefenderTight closestDefenderTight;
	final RawDataEnums.ForceDirection forceDirection;
	final LinkedList<RawDataEnums.ForceDirection> mainForcePossession;
	final LinkedList<RawDataEnums.ForceDirection> mainForcePossessionDirection;
	final Double throwAngle;
	final RawDataEnums.YesNoNA overheadThrow;
	final boolean anyZoneDOnPossession;
	final boolean anyMixedDOnPossession;
	final boolean anyPersonDOnPossession;
	final DefenseScheme dSchemePossession;
	
	public Component4(Byte numberOfMarkers, Byte numberOfMarkersPlusPoachers, Byte numberOfPoachers,
			Byte numberOfClosestDefenders, ClosestDefenderTight closestDefenderTight, ForceDirection forceDirection,
			LinkedList<ForceDirection> mainForcePossession, LinkedList<ForceDirection> mainForcePossessionDirection,
			Double throwAngle, YesNoNA overheadThrow,
			boolean anyZoneDOnPossession, boolean anyMixedDOnPossession, boolean anyPersonDOnPossession,
			DefenseScheme dSchemePossession) {
		this.numberOfMarkers = numberOfMarkers;
		this.numberOfMarkersPlusPoachers = numberOfMarkersPlusPoachers;
		this.numberOfPoachers = numberOfPoachers;
		this.numberOfClosestDefenders = numberOfClosestDefenders;
		this.closestDefenderTight = closestDefenderTight;
		this.forceDirection = forceDirection;
		this.mainForcePossession = mainForcePossession;
		this.mainForcePossessionDirection = mainForcePossessionDirection;
		this.throwAngle = throwAngle;
		this.overheadThrow = overheadThrow;
		this.anyZoneDOnPossession = anyZoneDOnPossession;
		this.anyMixedDOnPossession = anyMixedDOnPossession;
		this.anyPersonDOnPossession = anyPersonDOnPossession;
		this.dSchemePossession = dSchemePossession;
	}
	
}
