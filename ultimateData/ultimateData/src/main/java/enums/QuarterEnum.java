package enums;

import exceptions.BadEnumException;

public enum QuarterEnum {
	First,
	Second,
	Third,
	Fourth,
	Overtime,
	SecondOvertime;
	
	public static QuarterEnum convertFromString(String s) throws BadEnumException {
		switch(s) {
		case "1":
			return QuarterEnum.First;
		case "2":
			return QuarterEnum.Second;
		case "3":
			return QuarterEnum.Third;
		case "4":
			return QuarterEnum.Fourth;
		case "5":
			return QuarterEnum.Overtime;
		case "6":
			return QuarterEnum.SecondOvertime;
		default:
			throw new BadEnumException(s, QuarterEnum.class.getName());
		}
	}
}
