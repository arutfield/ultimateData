package enums;

import exceptions.BadEnumException;

public class RawDataEnums {

	public enum ForceDirection {
		Left,
		NA,
		None,
		Right,
		Rotating,
		Other,
		StraightUp;

		public ForceDirection converToEnum(String val) throws BadEnumException {
			switch (val) {
			case "Left":
				return Left;
			case "NA":
				return NA;
			case "None":
				return None;
			case "Right":
				return Right;
			case "Rotating":
				return Rotating;
			case "Other":
				return Other;
			case "Straight-Up":
				return StraightUp;
			default:
				throw new BadEnumException(val, ForceDirection.class.getName());
			}
		}
	}

	public enum ClosestDefenderTight {
		NS,
		NA,
		No,
		Yes,
		None;
		
		public ClosestDefenderTight converToEnum(String val) throws BadEnumException {
			switch (val) {
			case "N/S":
				return NS;
			case "NA":
				return NA;
			case "No":
				return No;
			case "none":
				return None;
			case "Yes":
				return Yes;
			default:
				throw new BadEnumException(val, ClosestDefenderTight.class.getName());
			}
		}
		
	}

	public enum ThrowDirectionEnum {
		DishDownfield,
		DishDump,
		DishSwing,
		Downfield,
		Dump,
		FullFieldSwing,
		PartFieldSwing,
		NA;

		public ThrowDirectionEnum converToEnum(String val) throws BadEnumException {
			switch (val) {
			case "Dish-Downfield":
				return DishDownfield;
			case "Dish-Dump":
				return DishDump;
			case "Dish-Swing":
				return DishSwing;
			case "Downfield":
				return Downfield;
			case "Dump":
				return Dump;
			case "Full-Field-Swing":
				return FullFieldSwing;
			case "Part-Field-Swing":
				return PartFieldSwing;
			case "NA":
				return NA;
			default:
				throw new BadEnumException(val, ThrowDirectionEnum.class.getName());
			}
		}

	}

	public enum QGroupTimeRemaining {
		ZeroToFive,
		FiveToThirty,
		OverThirty,
		OT;
		
		public QGroupTimeRemaining converToEnum(String val) throws BadEnumException {
			switch (val) {
			case "0-5 sec":
				return ZeroToFive;
			case "30+ sec":
				return OverThirty;
			case "5-30 sec":
				return FiveToThirty;
			case "OT":
				return OT;
			default:
				throw new BadEnumException(val, QGroupTimeRemaining.class.getName());
			}
		}

	
	}

	public enum HomeTeamOutcome {
		Win,
		Lose,
		Tie;
		public HomeTeamOutcome converToEnum(String val) throws BadEnumException {
			switch (val) {
			case "W":
				return Win;
			case "L":
				return Lose;
			case "T":
				return Tie;
			default:
				throw new BadEnumException(val, TeamScored.class.getName());
			}
		}
		
	}

	public enum TeamScored {
		Yes,
		No,
		DefenseScored;
		
		public TeamScored converToEnum(String val) throws BadEnumException {
			switch (val) {
			case "0":
				return No;
			case "1":
				return Yes;
			case "-1":
				return DefenseScored;
			default:
				throw new BadEnumException(val, TeamScored.class.getName());
			}
		}

	}

	public enum YesNoNA {
		Yes, No, NA;

		public YesNoNA convertToEnum(String val) throws BadEnumException {
			switch (val) {
			case "0":
				return No;
			case "1":
				return Yes;
			case "NA":
				return NA;
			default:
				throw new BadEnumException(val, YesNoNA.class.getName());
			}
		}
	}

	public enum FoulSide {
		Defense, Offense, NS, None;

		public FoulSide convertToEnum(String val) throws BadEnumException {
			switch (val) {
			case "Defensive Foul":
				return Defense;
			case "N/S":
				return NS;
			case "none":
				return None;
			case "Offensive Foul":
				return Offense;

			}
			throw new BadEnumException(val, FoulSide.class.getName());
		}
	}

	public enum BlockType {
		Deflection, Interception, None, PointBlock;
	}

	public enum TurnoverType {
		blockedPass, Drop, NS, None, SpotFoul, Stall, ThrowawayInBounds, ThrowawayOutofBounds;
	}

	public enum PassBreak {
		Around, Inside, None, OverTop, Rotating;
	}

	public enum BrokeMark {
		NS, No, None, Yes;
	}

	public enum PassType {
		Backhand, Flick, Hammer, NS, None, Other, Scoober;
	}

	public enum Force {
		Away, Home, Rotation, Straight, Unmarked, NS;
	}

	public enum DefenderDistance {
		TightlyContested, Guarded, Open, Uncovered, NoDefender, Defended, NS;
	}

	public enum PullInfo {
		Dropped, NS, None, Normal, Other, OutOfBounds, RollerOut;
	}

	public enum EventType {
		completedPass, Foul, GapInVideo, PullReceived, Timeout, Turnover, FoulTurnover;
	}

	public enum DefenseScheme {
		None, Mixed, Person, Transition, Zone;
	}

	public enum FieldType {
		CFL, College, HighSchool, HomeField, NoHashMarks, Unmarked;
	}

	public enum OffenseDirection {
		Left, None, Right;
	}
}
