package data;

import data.raw.RawData;

public class PlayerSeason {
	private static final double percentOLineScored = 0.739685658153241;
	private final String playerId;
	private final short year;
	private final String teamID;
	private final short games;
	private final short assists;
	private final short goals;
	private final short hockeyAssists;
	private final short completions;
	private final short throwAttempts;
	private final short throwaways;
	private final short stalls;
	private final short callahansThrown;
	private final short yardsReceived;
	private final short yardsThrown;
	private final short hucksAttempted;
	private final short hucksCompleted;
	private final short catches;
	private final short drops;
	private final short blocks;
	private final short callahans;
	private final short pulls;
	private final short obPulls;
	private final short recordedPulls;
	private final int recordedPullsHangtime;
	private final short oPointsPlayed;
	private final short oPointsScored;
	private final short dPointsPlayed;
	private final short dPointsScored;
	private final int secondsPlayed;
	private final short oOpportunities;
	private final short oOpportunityScores;
	private final short dOpportunities;
	private final short dOpportunityStops;
	private final Double oPointsRatio;
	private final Double dPointsRatio;
	private final Double overallRatio;
	private final Double percentOffense;
	private double averageDistanceThrown = 0.0;
	private double averageDistanceReceived = 0.0;
	private double averageThrowAngle = 0.0;
	private double averageReceiveAngle = 0.0;
	private final double throwawayRatio;
	private final double blocksPerPoint;
	
	
	public PlayerSeason(String playerId, short year, String teamID, short games, short assists, short goals,
			short hockeyAssists, short completions, short throwAttempts, short throwaways, short stalls,
			short callahansThrown, short yardsReceived, short yardsThrown, short hucksAttempted, short hucksCompleted,
			short catches, short drops, short blocks, short callahans, short pulls, short obPulls, short recordedPulls,
			int recordedPullsHangtime, short oPointsPlayed, short oPointsScored, short dPointsPlayed,
			short dPointsScored, int secondsPlayed, short oOpportunities, short oOpportunityScores,
			short dOpportunities, short dOpportunityStops) {
		this.playerId = playerId;
		this.year = year;
		this.teamID = teamID;
		this.games = games;
		this.assists = assists;
		this.goals = goals;
		this.hockeyAssists = hockeyAssists;
		this.completions = completions;
		this.throwAttempts = throwAttempts;
		this.throwaways = throwaways;
		this.stalls = stalls;
		this.callahansThrown = callahansThrown;
		this.yardsReceived = yardsReceived;
		this.yardsThrown = yardsThrown;
		this.hucksAttempted = hucksAttempted;
		this.hucksCompleted = hucksCompleted;
		this.catches = catches;
		this.drops = drops;
		this.blocks = blocks;
		this.callahans = callahans;
		this.pulls = pulls;
		this.obPulls = obPulls;
		this.recordedPulls = recordedPulls;
		this.recordedPullsHangtime = recordedPullsHangtime;
		this.oPointsPlayed = oPointsPlayed;
		this.oPointsScored = oPointsScored;
		this.dPointsPlayed = dPointsPlayed;
		this.dPointsScored = dPointsScored;
		this.secondsPlayed = secondsPlayed;
		this.oOpportunities = oOpportunities;
		this.oOpportunityScores = oOpportunityScores;
		this.dOpportunities = dOpportunities;
		this.dOpportunityStops = dOpportunityStops;
		this.oPointsRatio = (oPointsPlayed == 0) ? null : (double) oPointsScored/(double) oPointsPlayed;
		this.dPointsRatio = (dPointsPlayed == 0) ? null : (double) dPointsScored/(double) dPointsPlayed;
		int totalPointsPlayed = oPointsPlayed + dPointsPlayed;
		this.overallRatio = (double) (oPointsScored + ((double) dPointsScored) * percentOLineScored / (1.0 - percentOLineScored)) / (double) (oPointsPlayed + dPointsPlayed);
		this.percentOffense = (double) (oPointsPlayed) / (double) (totalPointsPlayed);
		
		if (throwAttempts == 0)
			this.throwawayRatio = Double.NaN;
		else
			this.throwawayRatio = (double) (throwaways) / (double) (throwAttempts);
		if (totalPointsPlayed == 0)
			this.blocksPerPoint = Double.NaN;
		else
			this.blocksPerPoint = (double) (blocks) / (double) (totalPointsPlayed);
	}

	public String getPlayerId() {
		return playerId;
	}
	
	public short getYear() {
		return year;
	}


	public String getTeamID() {
		return teamID;
	}
	

	public short getGames() {
		return games;
	}

	public short getAssists() {
		return assists;
	}

	public short getGoals() {
		return goals;
	}

	public short getHockeyAssists() {
		return hockeyAssists;
	}

	public short getCompletions() {
		return completions;
	}

	public short getThrowAttempts() {
		return throwAttempts;
	}

	public short getThrowaways() {
		return throwaways;
	}

	public short getStalls() {
		return stalls;
	}

	public short getCallahansThrown() {
		return callahansThrown;
	}

	public short getYardsReceived() {
		return yardsReceived;
	}

	public short getYardsThrown() {
		return yardsThrown;
	}

	public short getHucksAttempted() {
		return hucksAttempted;
	}

	public short getHucksCompleted() {
		return hucksCompleted;
	}

	public short getCatches() {
		return catches;
	}

	public short getDrops() {
		return drops;
	}

	public short getBlocks() {
		return blocks;
	}

	public short getCallahans() {
		return callahans;
	}

	public short getPulls() {
		return pulls;
	}

	public short getObPulls() {
		return obPulls;
	}

	public short getRecordedPulls() {
		return recordedPulls;
	}

	public int getRecordedPullsHangtime() {
		return recordedPullsHangtime;
	}

	public short getoPointsPlayed() {
		return oPointsPlayed;
	}

	public short getoPointsScored() {
		return oPointsScored;
	}

	public short getdPointsPlayed() {
		return dPointsPlayed;
	}

	public short getdPointsScored() {
		return dPointsScored;
	}

	public int getSecondsPlayed() {
		return secondsPlayed;
	}

	public short getoOpportunities() {
		return oOpportunities;
	}

	public short getoOpportunityScores() {
		return oOpportunityScores;
	}

	public short getdOpportunities() {
		return dOpportunities;
	}

	public short getdOpportunityStops() {
		return dOpportunityStops;
	}

	public Double getoPointsRatio() {
		return oPointsRatio;
	}

	public Double getdPointsRatio() {
		return dPointsRatio;
	}
	
	public Double getOverallRatio() {
		return overallRatio;
	}
	
	public Double getPercentOffense() {
		return percentOffense;
	}
	
	public Double getAverageDistanceThrown() {
		return averageDistanceThrown;
	}

	public Double getAverageDistanceReceived() {
		return averageDistanceReceived;
	}
	
	public Double getAverageThrowAngle() {
		return averageThrowAngle;
	}
	
	public Double getAverageReceiveAngle() {
		return averageReceiveAngle;
	}

	public double getThrowawayRatio() {
		return throwawayRatio;
	}

	public double getBlocksPerPoint() {
		return blocksPerPoint;
	}

	public void calculatePostSeasonStatistics() {
		double totalDistanceThrown = 0.0;
		double totalDistanceReceived = 0.0;
		int totalThrows = 0;
		int totalReceives = 0;
		for (Game game : Records.getGameRecords()) {
			if (game.getYear() != year)
				continue;
			for (Event event : game.getEvents()) {
				if (event.getThrowDistance() == null || event.getThrowDistance().getMagnitude() == null) {
					continue;
				}
				if (event.getThrower().equals(playerId) && event.getOffenseTeam().equals(teamID)) {
						totalDistanceThrown += event.getThrowDistance().getMagnitude();
						totalThrows++;
				} else {
					if (event.getReceiver().equals(playerId)) {
						totalReceives++;
						totalDistanceReceived += event.getThrowDistance().getMagnitude();
					}
				}
			}
		}
		
		double totalThrowAngle = 0.0;
		double totalReceivesAngle = 0.0;
		int totalThrowsForAngle = 0;
		int totalReceivesForAngle = 0;
		for (RawData rawData : Records.getRawDataRecords()) {
			if (rawData.getYear() != year || rawData.getThrowAngle() == Double.NaN)
				continue;
			if (rawData.getThrowerId().equals(playerId) && rawData.getTeamId().equals(teamID)) {
				totalThrowAngle += Math.abs(rawData.getThrowAngle());
				totalThrowsForAngle++;
			} else {
				if (rawData.getTargetedReceiverId().equals(playerId) && rawData.getTeamId().equals(teamID)) {
					totalReceivesForAngle++;
					totalReceivesAngle += Math.abs(rawData.getThrowAngle());
				}
			}
		}
		if (this.throwAttempts != 0)
			this.averageDistanceThrown = totalDistanceThrown / ((double) totalThrows);
		if (totalReceives != 0)
			this.averageDistanceReceived = totalDistanceReceived / ((double) totalReceives);
		if (totalThrowsForAngle != 0)
			this.averageThrowAngle = totalThrowAngle / ((double) totalThrowsForAngle);
		else
			this.averageThrowAngle = Double.NaN;
		
		if (totalReceivesForAngle != 0)
			this.averageReceiveAngle = totalReceivesAngle / ((double) totalReceivesForAngle);
		else
			this.averageReceiveAngle = Double.NaN;
		
	}

}
