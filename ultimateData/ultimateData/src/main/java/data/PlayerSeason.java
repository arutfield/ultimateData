package data;

public class PlayerSeason {
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

}
