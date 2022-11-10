package data;

public class TeamStats {
	private final short year;
	private final String divisionID;
	private final short wins;
	private final short losses;
	private final short ties;
	private final short divStanding;
	
	public TeamStats(short year, String divisionID, short wins, short losses, short ties, short divStanding) {
		this.year = year;
		this.divisionID = divisionID;
		this.wins = wins;
		this.losses = losses;
		this.ties = ties;
		this.divStanding = divStanding;
	}

	public short getYear() {
		return year;
	}

	public String getDivisionID() {
		return divisionID;
	}

	public short getWins() {
		return wins;
	}

	public short getLosses() {
		return losses;
	}

	public short getTies() {
		return ties;
	}

	public short getDivStanding() {
		return divStanding;
	}

}
