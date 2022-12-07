package data;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;

import data.raw.RawData;
import enums.EventTypeEnum;
import enums.RawDataEnums;
import exceptions.ValueException;

public class Game {
	private final String id;
	private Date currentDate;
	private final int year;
	private final LinkedList<Event> events;
	private short homeScore = Short.MIN_VALUE;
	private String homeTeam = null;
	private short awayScore = Short.MIN_VALUE;
	private String awayTeam = null;
	private short week;
	private long startTime;
	private short homePassAttempts = 0;
	private short awayPassAttempts = 0;
	private final EventTypeEnum[] passingEnums = {
			EventTypeEnum.ThrowawayByOpposingTeam,
			EventTypeEnum.ScoreByOpposingTeam,
			EventTypeEnum.Pass,
			EventTypeEnum.Goal,
			EventTypeEnum.Drop,
			EventTypeEnum.ThrowawayByRecordingTeam,
			EventTypeEnum.CallahanThrownByRecordingTeam,	
	};
	private int totalPassesHome = 0;
	private int totalPassesAway = 0;
	private int totalPassesHomeMan = 0;
	private int totalPassesAwayMan = 0;

	
	public Game(String id) {
		this.id = id;
		String[] splitByDash = id.split("-");
		this.year = Integer.valueOf(splitByDash[0]);
		int month;
		int day;
		try {
			month = Integer.valueOf(splitByDash[1]);
			day = Integer.valueOf(splitByDash[2]);
			Calendar myCalendar = new GregorianCalendar(year, month-1, day);
			currentDate = myCalendar.getTime();
		} catch (NumberFormatException ex) {
			currentDate = null;
		}
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

	public void addDetails(GameDetails gameDetails) throws ValueException {
		if (this.awayTeam != null && !gameDetails.getAwayTeam().equals(awayTeam)) {
			throw new ValueException("away team", awayTeam, gameDetails.getAwayTeam());
		}

		if (this.homeTeam != null && !gameDetails.getHomeTeam().equals(this.homeTeam)) {
			throw new ValueException("home team", homeTeam, gameDetails.getHomeTeam());
		}

		if (this.awayScore != Short.MIN_VALUE && gameDetails.getAwayScore() != this.awayScore) {
			throw new ValueException("away score", Integer.toString(awayScore), Integer.toString(gameDetails.getAwayScore()));
		}

		if (this.homeScore != Short.MIN_VALUE && gameDetails.getHomeScore() != this.homeScore) {
			throw new ValueException("home score", Integer.toString(homeScore), Integer.toString(gameDetails.getHomeScore()));
		}
		
		this.awayTeam = gameDetails.getAwayTeam();
		this.homeTeam = gameDetails.getHomeTeam();
		this.awayScore = gameDetails.getAwayScore();
		this.homeScore = gameDetails.getHomeScore();
		this.startTime = gameDetails.getTime();
		this.week = gameDetails.getWeek();
		
	}
	
	public short getHomePassAttempts() {
		return homePassAttempts;
	}

	public short getAwayPassAttempts() {
		return awayPassAttempts;
	}

	public EventTypeEnum[] getPassingEnums() {
		return passingEnums;
	}

	public int getTotalPassesHome() {
		return totalPassesHome;
	}

	public int getTotalPassesAway() {
		return totalPassesAway;
	}

	public int getTotalPassesHomeMan() {
		return totalPassesHomeMan;
	}

	public int getTotalPassesAwayMan() {
		return totalPassesAwayMan;
	}
	
	
	public void countPassAttempts() {
		for (Event event : getEvents()) {
			boolean isPassingEvent = false;
			for (EventTypeEnum passingEnum : passingEnums) {
				if (event.getEventType() == passingEnum) {
					isPassingEvent = true;
					break;
				}
			}
			if (isPassingEvent) {
				if (event.getOffenseTeam().equals(homeTeam)) {
					homePassAttempts++;
				} else if (event.getOffenseTeam().equals(awayTeam)) {
					awayPassAttempts++;
				}
			}
		}
	}

	public Date getCurrentDate() {
		return currentDate;
	}
	
	public int getYear() {
		return year;
	}
	
	public void countScoresAgainstMan() {
		for (RawData rawData : Records.getRawDataRecords()) {
			if (!rawData.getGameDate().equals(currentDate))
				continue;
			System.out.println("teams we found away: " + rawData.getAwayTeamId() + " , home: " + rawData.getHomeTeamId());
			System.out.println("teams we want away: " + awayTeam + " , home: " + homeTeam);
			System.out.println("date we're checking " + rawData.getGameDate().toString() + ", " + currentDate.toString());
			if (rawData.getHomeTeamId().equals(homeTeam) && rawData.getAwayTeamId().equals(awayTeam)) {
				if (rawData.getEventType() == RawDataEnums.EventType.completedPass) {
					if (rawData.getOffenseHome() == RawDataEnums.YesNoNA.Yes) {
						totalPassesHome++;
						if (rawData.getdSchemePossession() == RawDataEnums.DefenseScheme.Person)
							totalPassesHomeMan++;
					} else {
						totalPassesAway++;
						if (rawData.getdSchemePossession() == RawDataEnums.DefenseScheme.Person)
							totalPassesAwayMan++;
					}
						
				}
			}
			
		}
	}
}
