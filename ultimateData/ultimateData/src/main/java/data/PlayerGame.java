package data;

public class PlayerGame {

	private final String gameId;
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
	private final short secondsPlayed;
	private final short oOpportunities;
	private final short oOpportunityScores;
	private final short dOpportunities;
	private final short dOpportunityStops;
	private final double completionPercentage;
	private final double throwawayPercentage;
	private final double hucksPercentage;
	private final short totalYards;
	private final short assistsTotal;
	private final short scoringResultsParticipated;
	private final double throwYardsPerAttempt;
	private final double yardsPerReception;

	public PlayerGame(String gameId, short assists, short goals, short hockeyAssists, short completions,
			short throwAttempts, short throwaways, short stalls, short callahansThrown, short yardsReceived,
			short yardsThrown, short hucksAttempted, short hucksCompleted, short catches, short drops, short blocks,
			short callahans, short pulls, short obPulls, short recordedPulls, int recordedPullsHangtime,
			short oPointsPlayed, short oPointsScored, short dPointsPlayed, short dPointsScored, short secondsPlayed,
			short oOpportunities, short oOpportunityScores, short dOpportunities, short dOpportunityStops,
			double completionPercentage, double throwawayPercentage, double hucksPercentage, short totalYards,
			short assistsTotal, short scoringResultsParticipated, double throwYardsPerAttempt,
			double yardsPerReception) {
		this.gameId = gameId;
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
		this.completionPercentage = completionPercentage;
		this.throwawayPercentage = throwawayPercentage;
		this.hucksPercentage = hucksPercentage;
		this.totalYards = totalYards;
		this.assistsTotal = assistsTotal;
		this.scoringResultsParticipated = scoringResultsParticipated;
		this.throwYardsPerAttempt = throwYardsPerAttempt;
		this.yardsPerReception = yardsPerReception;
	}

	public String getGameId() {
		return gameId;
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

	public short getSecondsPlayed() {
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

	public double getCompletionPercentage() {
		return completionPercentage;
	}

	public double getThrowawayPercentage() {
		return throwawayPercentage;
	}

	public double getHucksPercentage() {
		return hucksPercentage;
	}

	public short getTotalYards() {
		return totalYards;
	}

	public short getAssistsTotal() {
		return assistsTotal;
	}

	public short getScoringResultsParticipated() {
		return scoringResultsParticipated;
	}

	public double getThrowYardsPerAttempt() {
		return throwYardsPerAttempt;
	}

	public double getYardsPerReception() {
		return yardsPerReception;
	}

	
}
