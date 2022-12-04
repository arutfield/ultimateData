package data;

public class TeamStats {
	private final String teamId;
	private final short year;
	private final String divisionID;
	private final short wins;
	private final short losses;
	private final short ties;
	private final short divStanding;
	private final double teamSeasonRating;
	private int seasonRanking;
	
	public TeamStats(String teamId, short year, String divisionID, short wins, short losses, short ties, short divStanding) {
		this.teamId = teamId;
		this.year = year;
		this.divisionID = divisionID;
		this.wins = wins;
		this.losses = losses;
		this.ties = ties;
		this.divStanding = divStanding;
		this.teamSeasonRating = (double) wins / (double) (wins + losses);
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

	public int getSeasonRanking() {
		return seasonRanking;
	}

	public void setSeasonRanking(int seasonRanking) {
		this.seasonRanking = seasonRanking;
	}

	public double getTeamSeasonRating() {
		return teamSeasonRating;
	}

	public String getTeamId() {
		return teamId;
	}


}
