package data;

import enums.EventTypeEnum;
import enums.QuarterEnum;

public class Event {
	private class gameTeam{
		final String teamName;
		final boolean isAway;
		final boolean isRecording;
		final boolean isOffense;
		gameTeam(final String teamName, final boolean isAway, final boolean isRecording, final boolean isOffense){
			this.teamName = teamName;
			this.isAway = isAway;
			this.isRecording = isRecording;
			this.isOffense = isOffense;
		}
	}
	
	private final String gameID;
	private final gameTeam awayTeam;
	private final gameTeam homeTeam;
	private final EventTypeEnum eventType;
	private final boolean isHomeOffense;
	private final byte pointNumber;
	private final byte possessionNumber;
	private final byte throwInPossession;
	private final byte homeTeamScore;
	private final byte awayTeamScore;
	private final QuarterEnum quarter;
	private final int quarterTime;
	private final String[] players;
	private final String puller;
	private final Coordinate pullCoordinates;
	private final int pullMs;
	private final String thrower;
	private final Coordinate throwerCoordinates;
	private final Coordinate receiverCoordinates;
	private final Coordinate throwDistance;
	private final String defender;
	private final Coordinate turnoverCoordinates;
	private short index;
	
	
	public Event(String gameID, String awayTeam, String homeTeam, String recordingTeam,
			String offenseTeam, String defenseTeam, boolean isHomeOffense, EventTypeEnum eventType,
			byte pointNumber, byte possessionNumber, byte throwInPossession, byte homeTeamScore,
			byte awayTeamScore, QuarterEnum quarter, int quarterTime, String[] players,
			String puller, Coordinate pullCoordinates, int pullMs, String thrower,
			Coordinate throwerCoordinates, Coordinate receiverCoordinates,
			Coordinate throwDistance, String defender, Coordinate turnoverCoordinates, short index)
	{
		this.gameID = gameID;
		this.awayTeam = new gameTeam(awayTeam, true, (recordingTeam == awayTeam), (offenseTeam == awayTeam));
		this.homeTeam = new gameTeam(homeTeam, true, (recordingTeam == homeTeam), (offenseTeam == homeTeam));
		this.isHomeOffense = isHomeOffense; //=2 if home is offense
		this.eventType = eventType;
		this.pointNumber = pointNumber;
		this.possessionNumber = possessionNumber;
		this.throwInPossession = throwInPossession;
		this.homeTeamScore = homeTeamScore;
		this.awayTeamScore = awayTeamScore;
		this.quarter = quarter;
		this.quarterTime = quarterTime;
		this.players = players;
		this.puller = puller;
		this.pullCoordinates = pullCoordinates;
		this.pullMs = pullMs;
		this.thrower = thrower;
		this.throwerCoordinates = throwerCoordinates;
		this.receiverCoordinates = receiverCoordinates;
		this.throwDistance = throwDistance;
		this.defender = defender;
		this.turnoverCoordinates = turnoverCoordinates;
		this.setIndex(index);
	}


	public String getGameID() {
		return gameID;
	}

	public gameTeam getAwayTeam() {
		return awayTeam;
	}

	public gameTeam getHomeTeam() {
		return homeTeam;
	}

	public EventTypeEnum getEventType() {
		return eventType;
	}

	public boolean isHomeOffense() {
		return isHomeOffense;
	}

	public byte getPointNumber() {
		return pointNumber;
	}

	public byte getPossessionNumber() {
		return possessionNumber;
	}

	public byte getHomeTeamScore() {
		return homeTeamScore;
	}

	public byte getAwayTeamScore() {
		return awayTeamScore;
	}

	public QuarterEnum getQuarter() {
		return quarter;
	}

	public int getQuarterTime() {
		return quarterTime;
	}

	public String[] getPlayers() {
		return players;
	}

	public String getPuller() {
		return puller;
	}

	public Coordinate getPullCoordinates() {
		return pullCoordinates;
	}

	public int getPullMs() {
		return pullMs;
	}

	public String getThrower() {
		return thrower;
	}

	public Coordinate getThrowerCoordinates() {
		return throwerCoordinates;
	}

	public Coordinate getReceiverCoordinates() {
		return receiverCoordinates;
	}

	public Coordinate getThrowDistance() {
		return throwDistance;
	}

	public String getDefender() {
		return defender;
	}

	public Coordinate getTurnoverCoordinates() {
		return turnoverCoordinates;
	}

	public short getIndex() {
		return index;
	}

	public void setIndex(short index) {
		this.index = index;
	}
	
}
