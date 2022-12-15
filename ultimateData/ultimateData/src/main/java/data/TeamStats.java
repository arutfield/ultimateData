package data;

import data.raw.RawData;
import enums.RawDataEnums;
import enums.RawDataEnums.EventType;
import exceptions.ValueException;

public class TeamStats {
	private final String teamId;
	private final short year;
	private final String divisionID;
	private final short wins;
	private final short losses;
	private final short ties;
	private final short divStanding;
	private final double teamSeasonRating;
	private double averagePointDifferential = Double.NaN;
	private double percentScoresWereNotPerson;
	private int seasonRanking;
	private int pointsScored;
	private int pointsAgainst;
	private double averagePPG;
	private double averagePPGAgainst;
	private double averagePassAttempts;
	private double manPassRatio;
	private double completionsPerGame;
	private double averageGapTime = Double.NaN;
	private double throwAttempts = Double.NaN;
	private double averageDistanceThrown = Double.NaN;
	private double averageThrowAngle = Double.NaN;
	private double averageReceiveAngle = Double.NaN;

	public TeamStats(String teamId, short year, String divisionID, short wins, short losses, short ties,
			short divStanding) {
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

	public double getCompletionsPerGame() {
		return completionsPerGame;
	}

	/**
	 * calculate statistics for throws for the team this season
	 * @throws ValueException error
	 */
	public void calculateThrowStatistics() throws ValueException {
		double totalDistanceThrown = 0.0;
		int totalThrows = 0;

		//throw distances
		for (Game game : Records.getGameRecords()) {
			if (game.getYear() != year)
				continue;
			for (Event event : game.getEvents()) {
				if (event.getThrowDistance() == null || event.getThrowDistance().getMagnitude() == null) {
					continue;
				}
				if (event.getOffenseTeam().equals(teamId)) {
					totalDistanceThrown += event.getThrowDistance().getMagnitude();
					totalThrows++;
				}
			}
		}

		//throw angles
		double totalThrowAngle = 0.0;
		int totalThrowsForAngle = 0;
		for (RawData rawData : Records.getRawDataRecords()) {
			if (rawData.getYear() != year || Double.isNaN(rawData.getThrowAngle()))
				continue;
			if (rawData.getTeamId().equals(teamId)) {
				totalThrowAngle += Math.abs(rawData.getThrowAngle());
				totalThrowsForAngle++;
			}
		}
		if (this.throwAttempts != 0)
			this.averageDistanceThrown = (double) totalDistanceThrown / ((double) totalThrows);
		if (totalThrowsForAngle != 0) {
			this.averageThrowAngle = totalThrowAngle / ((double) totalThrowsForAngle);
		} else
			this.averageThrowAngle = Double.NaN;

	}

	/**
	 * calculate average time between passes
	 * @throws ValueException error
	 */
	public void calculateAverageTimeBetweenPasses() throws ValueException {
		int totalPassGaps = 0;
		int totalPassGapTimes = 0;
		Double prevTime = 0.0;
		boolean lastEventPass = false;
		for (RawData rawData : Records.getRawDataRecords()) {
			// if not this team or year, skip
			if (!rawData.getTeamId().equals(teamId) || rawData.getYear() != this.year) {
				lastEventPass = false;
				continue;
			}
			if (rawData.getEventType() == RawDataEnums.EventType.completedPass
					|| rawData.getEventType() == RawDataEnums.EventType.PullReceived
					|| rawData.getEventType() == RawDataEnums.EventType.Turnover) {
				//is a pass, though not necessarily successful
				if (rawData.getEventType() == RawDataEnums.EventType.completedPass
						|| rawData.getEventType() == RawDataEnums.EventType.PullReceived) {
					// not a turnover
					if (rawData.getEventType() == RawDataEnums.EventType.completedPass) {
						if (lastEventPass) {
							totalPassGapTimes += ((prevTime - rawData.getGameClockEstimate()) > 0
									? (prevTime - rawData.getGameClockEstimate())
									: 0);
							totalPassGaps++;
						}
					}
					lastEventPass = true;
				}
				// events to record time
				prevTime = rawData.getGameClockEstimate();
				if (prevTime == null) {
					lastEventPass = false;
				}
			} else {
				lastEventPass = false;
			}

		}
		averageGapTime = (double) totalPassGapTimes / (double) totalPassGaps;
	}

	/**
	 * calculate the number of pass attempts per point on average
	 */
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

	/**
	 * find the average number of passes made against man each point
	 * divided by total passes
	 */
	public void calculateAveragePassRatioAgainstManPerPoint() {
		int totalPassesAttempted = 0;
		int totalManPasses = 0;
		int successfulPassesTotal = 0;
		int totalGames = 0;
		for (Game game : Records.getGameRecords()) {
			if (game.getYear() != this.year) {
				continue;
			}
			if (game.getHomeTeam().equals(teamId)) {
				totalGames++;
				totalPassesAttempted += game.getTotalPassesHome();
				totalManPasses += game.getTotalPassesHomeMan();
				successfulPassesTotal += game.getSuccessfulPassesHome();
			} else if (game.getAwayTeam().equals(teamId)) {
				totalGames++;
				totalPassesAttempted += game.getTotalPassesAway();
				totalManPasses += game.getTotalPassesAwayMan();
				successfulPassesTotal += game.getSuccessfulPassesAway();
			}
		}
		manPassRatio = (double) totalManPasses / (double) totalPassesAttempted;
		completionsPerGame = (double) successfulPassesTotal / (double) totalGames;
	}

	public double getAverageGapTime() {
		return averageGapTime;
	}

	/**
	 * calculate the average number of points the team got
	 * minus the average number their opponent got
	 * @return difference
	 */
	public double getAveragePointDifferential() {
		if (!Double.isNaN(averagePointDifferential))
			return averagePointDifferential;
		int pointDiffTotal = 0;
		int gameCounter = 0;
		for (Game game : Records.getGameRecords()) {
			if (game.getYear() != this.year)
				continue;
			if (game.getHomeTeam().equals(teamId)) {
				pointDiffTotal += game.getHomeScore() - game.getAwayScore();
				gameCounter++;
			} else if (game.getAwayTeam().equals(teamId)) {
				pointDiffTotal += game.getAwayScore() - game.getHomeScore();
				gameCounter++;
			}
		}
		averagePointDifferential = (double) pointDiffTotal / (double) gameCounter;
		return averagePointDifferential;
	}

	public double getAverageDistanceThrown() {
		return averageDistanceThrown;
	}

	public double getAverageThrowAngle() {
		return averageThrowAngle;
	}

	public double getAverageReceiveAngle() {
		return averageReceiveAngle;
	}

}
