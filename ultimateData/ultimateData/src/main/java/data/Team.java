package data;

import java.util.LinkedList;

public class Team {
	private final String teamId;
	private final LinkedList<TeamStats> teamStats = new LinkedList<>();
	
	public Team(String teamId) {
		this.teamId = teamId;
	}

	public LinkedList<TeamStats> getTeamStats() {
		return teamStats;
	}
	
	public String getTeamId() {
		return teamId;
	}

	public void addTeamStats(TeamStats teamStat) {
		teamStats.add(teamStat);
	}

}
