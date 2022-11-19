package enums;

import exceptions.BadEnumException;

public class RawDataEnums {

	public enum ForceDirection {
		Left, NA, None, Right, Rotating, Other, StraightUp;

		public static ForceDirection convertToEnum(String val) throws BadEnumException {
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
			case "Rotating/Other":
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
		NS, NA, No, Yes, None;

		public static ClosestDefenderTight convertToEnum(String val) throws BadEnumException {
			switch (val) {
			case "N/S":
				return NS;
			case "NA":
				return NA;
			case "No":
				return No;
			case "none":
			case "None":
				return None;
			case "Yes":
				return Yes;
			default:
				throw new BadEnumException(val, ClosestDefenderTight.class.getName());
			}
		}

	}

	public enum ThrowDirectionEnum {
		DishDownfield, DishDump, DishSwing, Downfield, Dump, FullFieldSwing, PartFieldSwing, NA;

		public static ThrowDirectionEnum convertToEnum(String val) throws BadEnumException {
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
		ZeroToFive, FiveToThirty, OverThirty, OT;

		public static QGroupTimeRemaining convertToEnum(String val) throws BadEnumException {
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
		Win, Lose, Tie;

		public static HomeTeamOutcome convertToEnum(String val) throws BadEnumException {
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
		Yes, No, NA, DefenseScored;

		public static TeamScored convertToEnum(String val) throws BadEnumException {
			switch (val) {
			case "0":
				return No;
			case "1":
				return Yes;
			case "-1":
				return DefenseScored;
			case "NA":
				return NA;
			default:
				throw new BadEnumException(val, TeamScored.class.getName());
			}
		}

	}

	public enum YesNoNA {
		Yes, No, NA;

		public static YesNoNA convertToEnum(String val) throws BadEnumException {
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

		public static FoulSide convertToEnum(String val) throws BadEnumException {
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

		public static BlockType convertToEnum(String val) throws BadEnumException {
			switch (val) {
			case "Deflection":
				return Deflection;
			case "Interception":
				return Interception;
			case "none":
				return None;
			case "Point-Block":
				return PointBlock;
			default:
				throw new BadEnumException(val, BlockType.class.getName());

			}
		}
	}

	public enum TurnoverType {
		blockedPass, Drop, NS, None, SpotFoul, Stall, ThrowawayInBounds, ThrowawayOutofBounds;

		public static TurnoverType convertToEnum(String val) throws BadEnumException {
			switch (val) {
			case "Blocked Pass":
				return blockedPass;
			case "Drop":
				return Drop;
			case "none":
				return None;
			case "N/S":
				return NS;
			case "Spot Foul":
				return SpotFoul;
			case "Stall":
				return Stall;
			case "Throwaway In-Bounds":
				return ThrowawayInBounds;
			case "Throwaway Out-of-Bounds":
				return ThrowawayOutofBounds;
			default:
				throw new BadEnumException(val, TurnoverType.class.getName());

			}
		}
	}

	public enum PassBreak {
		Around, Inside, None, OverTop, Rotating;

		public static PassBreak convertToEnum(String val) throws BadEnumException {
			switch (val) {
			case "Around":
				return Around;
			case "Inside":
				return Inside;
			case "none":
				return None;
			case "Over the Top":
				return OverTop;
			case "Rotating/Other":
				return Rotating;
			default:
				throw new BadEnumException(val, PassBreak.class.getName());

			}
		}
	}

	public enum BrokeMark {
		NS, No, None, Yes;

		public static BrokeMark convertToEnum(String val) throws BadEnumException {
			switch (val) {
			case "No":
				return No;
			case "none":
				return None;
			case "N/S":
				return NS;
			case "Yes":
				return Yes;
			default:
				throw new BadEnumException(val, BrokeMark.class.getName());

			}
		}
	}

	public enum PassType {
		Backhand, Flick, Hammer, NS, None, Other, Scoober;

		public static PassType convertToEnum(String val) throws BadEnumException {
			switch (val) {
			case "Backhand":
				return Backhand;
			case "Flick":
				return Flick;
			case "N/S":
				return NS;
			case "Hammer":
				return Hammer;
			case "none":
				return None;
			case "Other":
				return Other;
			case "Scoober":
				return Scoober;
			default:
				throw new BadEnumException(val, PassType.class.getName());

			}
		}
	}

	public enum Force {
		Away, Home, Rotation, Straight, Unmarked, NS, None;

		public static Force convertToEnum(String val) throws BadEnumException {
			switch (val) {
			case "Away":
				return Away;
			case "Home":
				return Home;
			case "N/S":
				return NS;
			case "none":
				return None;
			case "Rotating/Other":
				return Rotation;
			case "Straight-Up":
				return Straight;
			case "Unmarked/Not Covered":
				return Unmarked;
			default:
				throw new BadEnumException(val, Force.class.getName());

			}
		}
	}

	public enum DefenderDistance {
		TightlyContested, Guarded, Open, Uncovered, NoDefender, Defended, None, Yes, NS;

		public static DefenderDistance convertToEnum(String val) throws BadEnumException {
			switch (val) {
			case "0-1 Yds (Tightly Contested)":
			case "Tightly Contested (0-1 Yds)":
				return TightlyContested;
			case "1-3 Yds (Guarded)":
			case "Guarded (1-3 Yds)":
				return Guarded;
			case "10+ Yds (Uncovered)":
			case "10+ Yds Uncovered":
			case "Uncovered (10+ Yds)":
				return Uncovered;
			case "N/S":
				return NS;
			case "No":
			case "No Defender":
				return NoDefender;
			case "none":
			case "None":
				return None;
			case "Open (3-10 Yds)":
			case "3-10 Yds (Open)":
				return Open;
			case "Yes":
				return Yes;
			default:
				throw new BadEnumException(val, DefenderDistance.class.getName());

			}
		}
	}

	public enum PullInfo {
		Dropped, NS, None, Normal, Other, OutOfBounds, RollerOut;

		public static PullInfo convertToEnum(String val) throws BadEnumException {
			switch (val) {
			case "Dropped":
				return Dropped;
			case "N/S":
				return NS;
			case "none":
				return None;
			case "Normal/In-Bounds":
				return Normal;
			case "Other":
				return Other;
			case "Out-of-Bounds":
				return OutOfBounds;
			case "Roller Out-of-Bounds":
				return RollerOut;
			default:
				throw new BadEnumException(val, PullInfo.class.getName());

			}
		}
	}

	public enum EventType {
		completedPass, Foul, GapInVideo, PullReceived, Timeout, Turnover, FoulTurnover;

		public static EventType convertToEnum(String val) throws BadEnumException {
			switch (val.toLowerCase()) {
			case "completed pass":
				return completedPass;
			case "foul":
				return Foul;
			case "gap in video":
				return GapInVideo;
			case "pull received":
				return PullReceived;
			case "timeout":
				return Timeout;
			case "turnover":
				return Turnover;
			case "turnover/foul":
				return FoulTurnover;
			default:
				throw new BadEnumException(val, EventType.class.getName());

			}
		}
	}

	public enum DefenseScheme {
		None, Mixed, Person, Transition, Zone;

		public static DefenseScheme convertToEnum(String val) throws BadEnumException {
			switch (val) {
			case "Mixed":
				return Mixed;
			case "none":
				return None;
			case "Person":
				return Person;
			case "Transition/None":
				return Transition;
			case "Zone":
				return Zone;
			default:
				throw new BadEnumException(val, DefenseScheme.class.getName());

			}
		}
	}

	public enum FieldType {
		CFL, College, HighSchool, HomeField, NoHashMarks, Unmarked;

		public static FieldType convertToEnum(String val) throws BadEnumException {
			switch (val) {
			case "CFL":
				return CFL;
			case "College":
				return College;
			case "High School":
				return HighSchool;
			case "Home Field":
				return HomeField;
			case "Other (No Hash-Marks)":
				return NoHashMarks;
			case "Other (Unmarked)":
				return Unmarked;
			default:
				throw new BadEnumException(val, FieldType.class.getName());

			}
		}
	}

	public enum OffenseDirection {
		Left, None, Right;

		public static OffenseDirection convertToEnum(String val) throws BadEnumException {
			switch (val) {
			case "none":
				return None;
			case "Left":
				return Left;
			case "Right":
				return Right;
			default:
				throw new BadEnumException(val, OffenseDirection.class.getName());
			}
		}
	}
}
