package data;

import java.util.LinkedList;

public class Player {
	private final LinkedList<PlayerGame> playerGameList;
	private final String playerId;
	private short assists;
	private short goals;
	private short hockeyAssists;
	private short completions;
	private short throwAttempts;
	private short throwaways;
	private short stalls;
	private short callahansThrown;
	private short yardsReceived;
	private short yardsThrown;
	private short hucksAttempted;
	private short hucksCompleted;
	private short catches;
	private short drops;
	private short blocks;
	private short callahans;
	private short pulls;
	private short obPulls;
	private short recordedPulls;
	private short recordedPullsHangtime;
	private short oPointsPlayed;
	private short oPointsScored;
	private short dPointsPlayed;
	private short dPointsScored;
	private short secondsPlayed;
	private short oOpportunities;
	private short oOpportunityScores;
	private short dOpportunities;
	private short dOpportunityStops;
	private short completionPercentage;
	private short throwawayPercentage;
	private short hucksPercentage;
	private short totalYards;
	private short assistsTotal;
	private short scoringResultsParticipated;
	private short throwYardsPerAttempt;
	private short yardsPerReception;
	
	
	public Player(String playerId) {
		this.playerId = playerId;
		this.playerGameList = new LinkedList<>();
		
	}
	
	
	public String getPlayerId() {
		return playerId;
	}

	public LinkedList<PlayerGame> getPlayerGameList() {
		return playerGameList;
	}
	
	public void addEvent(PlayerGame event) {
		playerGameList.add(event);
	}
	
	public void calculateStats() {
		
	}

}
