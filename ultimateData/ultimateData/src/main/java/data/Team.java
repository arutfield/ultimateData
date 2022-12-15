package data;

import java.util.LinkedList;

import exceptions.ValueException;

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
	
	/**
	 * perform calculations needed at the season level
	 * @throws ValueException error
	 */
	public void calculationsForEachSeason() throws ValueException {
		for (TeamStats teamStat : teamStats) {
			teamStat.calculateAveragePassAttemptsPerPoint();
			teamStat.calculateAverageTimeBetweenPasses();
			teamStat.calculateAveragePassRatioAgainstManPerPoint();
			teamStat.calculateThrowStatistics();
		}
	}


}
