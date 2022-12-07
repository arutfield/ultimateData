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
	private double percentScoresWereNotPerson;
	private int seasonRanking;
	private int pointsScored;
	private int pointsAgainst;
	private double averagePPG;
	private double averagePPGAgainst;
	private double averagePassAttempts;
	private double manPassRatio;
	
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

	public double getAveragePPG() {
		return averagePPG;
	}

	public void setAveragePPG(double averagePPG) {
		this.averagePPG = averagePPG;
	}

	public double getAveragePPGAgainst() {
		return averagePPGAgainst;
	}

	public void setAveragePPGAgainst(double averagePPGAgainst) {
		this.averagePPGAgainst = averagePPGAgainst;
	}

	public void addToPointsScored(int added) {
		this.pointsScored += added;
	}

	public void addToPointsScoredAgainst(int added) {
		this.pointsAgainst += added;
	}

	public int getPointsScored() {
		return pointsScored;
	}

	public int getPointsAgainst() {
		return pointsAgainst;
	}

	public double getAveragePassAttempts() {
		return averagePassAttempts;
	}

	public void setAveragePassAttempts(double averagePassAttempts) {
		this.averagePassAttempts = averagePassAttempts;
	}
	
	public double getManPassRatio() {
		return manPassRatio;
	}
	
	public void calculateAveragePassAttemptsPerPoint() {
		int totalPassAttempts = 0;
		int totalPoints = 0;
		for (Game game : Records.getGameRecords()) {
			if (game.getYear() != this.year) {
				continue;
			}
			if (game.getHomeTeam().equals(teamId)) {
				totalPassAttempts += game.getHomePassAttempts();
				totalPoints += (game.getHomeScore() + game.getAwayScore());
			} else if (game.getAwayTeam().equals(teamId)) {
				totalPassAttempts += game.getAwayPassAttempts();
				totalPoints += (game.getHomeScore() + game.getAwayScore());				
			}
		}
		averagePassAttempts = (double) totalPassAttempts / (double) totalPoints;
	}

	public void calculateAveragePassRatioAgainstManPerPoint() {
		int totalPasses = 0;
		int totalManPasses = 0;
		for (Game game : Records.getGameRecords()) {
			if (game.getYear() != this.year) {
				continue;
			}
			if (game.getHomeTeam().equals(teamId)) {
				totalPasses += game.getTotalPassesHome();
				totalManPasses += game.getTotalPassesHomeMan();
			} else if (game.getAwayTeam().equals(teamId)) {
				totalPasses += game.getTotalPassesAway();
				totalManPasses += game.getTotalPassesAwayMan();
			}
		}
		manPassRatio = (double) totalManPasses / (double) totalPasses;
	}

}
