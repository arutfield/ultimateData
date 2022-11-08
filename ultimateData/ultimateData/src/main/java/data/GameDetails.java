package data;

public class GameDetails {

	private final String awayTeam;
	private final String homeTeam;
	private final short awayScore;
	private final short homeScore;
	private final long time;
	private final short week;

	
	public GameDetails(String awayTeam, String homeTeam, short awayScore, short homeScore,
			long time, short week) {
		this.awayTeam = awayTeam;
		this.homeTeam = homeTeam;
		this.awayScore = awayScore;
		this.homeScore = homeScore;
		this.time = time;
		this.week = week;

	}


	public String getAwayTeam() {
		return awayTeam;
	}


	public String getHomeTeam() {
		return homeTeam;
	}


	public short getAwayScore() {
		return awayScore;
	}


	public short getHomeScore() {
		return homeScore;
	}


	public long getTime() {
		return time;
	}


	public short getWeek() {
		return week;
	}
}
