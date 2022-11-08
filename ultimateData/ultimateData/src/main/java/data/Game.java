package data;

import java.util.LinkedList;

public class Game {
	private final String id;
	private final LinkedList<Event> events;
	private short homeScore;
	private String homeTeam;
	private short awayScore;
	private String awayTeam;
	private short week;
	private long startTime;
	
	public Game(String id) {
		this.id = id;
		this.events = new LinkedList<Event>();
	}

	public String getId() {
		return id;
	}

	public LinkedList<Event> getEvents() {
		return events;
	}
	
	public void addEvent(Event event) {
		events.add(event);
	}

	public short getHomeScore() {
		return homeScore;
	}

	public void setHomeScore(short homeScore) {
		this.homeScore = homeScore;
	}

	public String getHomeTeam() {
		return homeTeam;
	}

	public void setHomeTeam(String homeTeam) {
		this.homeTeam = homeTeam;
	}

	public short getAwayScore() {
		return awayScore;
	}

	public void setAwayScore(short awayScore) {
		this.awayScore = awayScore;
	}

	public String getAwayTeam() {
		return awayTeam;
	}

	public void setAwayTeam(String awayTeam) {
		this.awayTeam = awayTeam;
	}

	public short getWeek() {
		return week;
	}

	public void setWeek(short week) {
		this.week = week;
	}

	public void addDetails(GameDetails gameDetails) {
		this.awayTeam = gameDetails.getAwayTeam();
		this.homeTeam = gameDetails.getHomeTeam();
		this.awayScore = gameDetails.getAwayScore();
		this.homeScore = gameDetails.getHomeScore();
		this.startTime = gameDetails.getTime();
		this.week = gameDetails.getWeek();
		
	}
}
