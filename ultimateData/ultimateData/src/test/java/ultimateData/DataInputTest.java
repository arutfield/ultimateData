package ultimateData;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.util.LinkedList;

import org.junit.jupiter.api.Test;

import data.Coordinate;
import data.Event;
import data.Event.gameTeam;
import data.Game;
import data.GameDetails;
import data.PlayerGame;
import data.PlayerSeason;
import enums.EventTypeEnum;
import enums.QuarterEnum;
import exceptions.BadEnumException;
import exceptions.WrongSizeRowException;
import parser.StringConverters;

public class DataInputTest {
	public void checkCoordinates(Coordinate coordinates, double xTarget, double yTarget) {
		assertEquals(xTarget, coordinates.getX(), 0.01);
		assertEquals(yTarget, coordinates.getY(), 0.01);
	}

	public void checkTeam(gameTeam team, String targetName, boolean isAwayTarget, boolean recordingTarget,
			boolean isOffenseTarget) {
		assertEquals(targetName, team.getTeamName());
		assertEquals(isAwayTarget, team.isAway());
		assertEquals(recordingTarget, team.isRecording());
		assertEquals(isOffenseTarget, team.isOffense());
	}

	@Test
	public void testTest() {
		assertTrue(true);
	}

	@Test
	public void testConvertToEvent() throws WrongSizeRowException, BadEnumException {
		String sampleString = "2022-07-22-LA-SLC,LA,SLC,LA,LA,SLC,2,7,Pull In bounds,7,9,0,3,3,1,422,gsanti,msteiner,nkirchhof,jbaumer,efinley,apadula,ccogswell,msteiner,24.62,39.1,12414,None,None,None,None,None,None,None,None,None,None,nan,nan,45";
		String[] values = sampleString.split(",");
		Event event = StringConverters.convertToEvent(values);
		checkTeam(event.getAwayTeam(), "LA", true, true, true);
		checkTeam(event.getHomeTeam(), "SLC", false, false, false);
		assertEquals(EventTypeEnum.PullInBounds, event.getEventType());
		assertEquals(true, event.isHomeOffense());
		assertEquals(7, event.getPointNumber());
		assertEquals(9, event.getPossessionNumber());
		assertEquals(3, event.getHomeTeamScore());
		assertEquals(3, event.getAwayTeamScore());
		assertEquals(QuarterEnum.First, event.getQuarter());
		assertEquals(422, (int) event.getQuarterTime());
		String[] players = event.getPlayers();
		assertEquals(7, players.length);
		String[] playersCorrect = { "gsanti", "msteiner", "nkirchhof", "jbaumer", "efinley", "apadula", "ccogswell" };
		for (int i = 0; i < players.length; i++)
			assertEquals(playersCorrect[i], players[i]);
		assertEquals("msteiner", event.getPuller());
		checkCoordinates(event.getPullCoordinates(), 24.62, 39.1);
		assertEquals(12414, (int) event.getPullMs());
		assertEquals("None", event.getThrower());
		assertNull(event.getThrowerCoordinates());
		assertNull(event.getReceiverCoordinates());
		assertNull(event.getThrowDistance());
		assertEquals("None", event.getDefender());
		assertNull(event.getTurnoverCoordinates());
		assertEquals(45, event.getIndex());

	}

	@Test
	public void testConvertToEvent2() throws WrongSizeRowException, BadEnumException {
		String sampleString = "2022-07-16-SLC-OAK,SLC,OAK,SLC,OAK,SLC,1,22,Throwaway thrown by recoding team,17,39,0,9,6,2,499,abenton,bjordan,jmerrill,jkerr,lyorgason,jmiller,sconnole,None,None,None,None,bjordan,2.79,23.18,None,None,None,6.82,15.07,16.54138144,None,9.61,38.25,119";
		String[] values = sampleString.split(",");
		Event event = StringConverters.convertToEvent(values);
		checkTeam(event.getAwayTeam(), "SLC", true, true, false);
		checkTeam(event.getHomeTeam(), "OAK", false, false, true);
		assertEquals(EventTypeEnum.ThrowawayByRecordingTeam, event.getEventType());
		assertEquals(false, event.isHomeOffense());
		assertEquals(17, event.getPointNumber());
		assertEquals(39, event.getPossessionNumber());
		assertEquals(9, event.getHomeTeamScore());
		assertEquals(6, event.getAwayTeamScore());
		assertEquals(QuarterEnum.Second, event.getQuarter());
		assertEquals(499, (int) event.getQuarterTime());
		String[] players = event.getPlayers();
		assertEquals(7, players.length);
		String[] playersCorrect = { "abenton", "bjordan", "jmerrill", "jkerr", "lyorgason", "jmiller", "sconnole" };
		for (int i = 0; i < players.length; i++)
			assertEquals(playersCorrect[i], players[i]);
		assertEquals("None", event.getPuller());
		assertNull(event.getPullCoordinates());
		assertNull(event.getPullMs());
		assertEquals("bjordan", event.getThrower());
		checkCoordinates(event.getThrowerCoordinates(), 2.79, 23.18);
		assertNull(event.getReceiverCoordinates());
		checkCoordinates(event.getThrowDistance(), 6.82, 15.07);
		assertEquals("None", event.getDefender());
		checkCoordinates(event.getTurnoverCoordinates(), 9.61, 38.25);
		assertEquals(119, event.getIndex());

	}

	@Test
	public void testConvertToPlayerGameStatsAdvanced() throws WrongSizeRowException, BadEnumException {
		String sampleString = "2022-07-02-MIN-DET,ihahn,Ian,Hahn,14,windchill,2,1,1,6,6,0,0,0,82,71,0,0,7,0,0,0,0,0,0,0,1,1,18,8,1124,15,9,23,14,100,0,0,153,3,4,11.83333333,11.71428571";
		String[] values = sampleString.split(",");
		PlayerGame game = StringConverters.convertToPlayerGame(values);
		assertEquals("2022-07-02-MIN-DET", game.getGameId());
		assertEquals(14, game.getJerseyNumber());
		assertEquals(2, game.getAssists());
		assertEquals(1, game.getGoals());
		assertEquals(1, game.getHockeyAssists());
		assertEquals(6, game.getCompletions());
		assertEquals(6, game.getThrowAttempts());
		assertEquals(0, game.getThrowaways());
		assertEquals(0, game.getStalls());
		assertEquals(0, game.getCallahansThrown());
		assertEquals(82, game.getYardsReceived());
		assertEquals(71, game.getYardsThrown());
		assertEquals(0, game.getHucksAttempted());
		assertEquals(0, game.getHucksCompleted());
		assertEquals(7, game.getCatches());
		assertEquals(0, game.getDrops());
		assertEquals(0, game.getBlocks());
		assertEquals(0, game.getCallahans());
		assertEquals(0, game.getPulls());
		assertEquals(0, game.getObPulls());
		assertEquals(0, game.getRecordedPulls());
		assertEquals(0, game.getRecordedPullsHangtime());
		assertEquals(1, game.getoPointsPlayed());
		assertEquals(1, game.getoPointsScored());
		assertEquals(18, game.getdPointsPlayed());
		assertEquals(8, game.getdPointsScored());
		assertEquals(1124, game.getSecondsPlayed());
		assertEquals(15, game.getoOpportunities());
		assertEquals(9, game.getoOpportunityScores());
		assertEquals(23, game.getdOpportunities());
		assertEquals(14, game.getdOpportunityStops());
		assertEquals(100, game.getCompletionPercentage(), 0.001);
		assertEquals(0, game.getThrowawayPercentage(), 0.001);
		assertEquals(0, game.getHucksPercentage(), 0.001);
		assertEquals(153, game.getTotalYards());
		assertEquals(3, game.getAssistsTotal());
		assertEquals(4, game.getScoringResultsParticipated());
		assertEquals(11.83333, game.getThrowYardsPerAttempt(), 0.001);
		assertEquals(11.71429, game.getYardsPerReception(), 0.001);

	}

	@Test
	public void testConvertToGame() throws NumberFormatException, WrongSizeRowException, ParseException {
		String sampleString = "2021-06-12-DAL-AUS,legion,sol,23,24,2021-06-12T19:00:00-05:00,2";
		String[] values = sampleString.split(",");
		GameDetails gameDetails = StringConverters.convertToGameDetails(values);

		assertEquals("legion",gameDetails.getAwayTeam());
		assertEquals("sol",gameDetails.getHomeTeam());
		assertEquals(23,gameDetails.getAwayScore());
		assertEquals(24,gameDetails.getHomeScore());
		assertEquals(68400000,gameDetails.getTime());
		assertEquals(2,gameDetails.getWeek());
	}
	
	@Test
	public void testConvertToPlayerSeason() {
		String sampleString = "2021,mbrownlee,Marques,Brownlee,empire,12,12,3,5,44,52,8,0,0,475,530,3,1,49,0,12,0,175,20,155,1005972,1,0,211,59,14950,104,59,255,122";
		String[] values = sampleString.split(",");
		PlayerSeason playerSeason = StringConverters.convertToPlayerSeason(values);
		assertEquals(2021, playerSeason.getYear());
		assertEquals("empire", playerSeason.getTeamID());
		assertEquals(12, playerSeason.getGames());
		assertEquals(12, playerSeason.getAssists());
		assertEquals(3, playerSeason.getGoals());
		assertEquals(5, playerSeason.getHockeyAssists());
		assertEquals(44, playerSeason.getCompletions());
		assertEquals(52, playerSeason.getThrowAttempts());
		assertEquals(8, playerSeason.getThrowaways());
		assertEquals(0, playerSeason.getStalls());
		assertEquals(0, playerSeason.getCallahansThrown());
		assertEquals(475, playerSeason.getYardsReceived());
		assertEquals(530, playerSeason.getYardsThrown());
		assertEquals(3, playerSeason.getHucksAttempted());
		assertEquals(1, playerSeason.getHucksCompleted());
		assertEquals(49, playerSeason.getCatches());
		assertEquals(0, playerSeason.getDrops());
		assertEquals(12, playerSeason.getBlocks());
		assertEquals(0, playerSeason.getCallahans());
		assertEquals(175, playerSeason.getPulls());
		assertEquals(20, playerSeason.getObPulls());
		assertEquals(155, playerSeason.getRecordedPulls());
		assertEquals(1005972, playerSeason.getRecordedPullsHangtime());
		assertEquals(1, playerSeason.getoPointsPlayed());
		assertEquals(0, playerSeason.getoPointsScored());
		assertEquals(211, playerSeason.getdPointsPlayed());
		assertEquals(59, playerSeason.getdPointsScored());
		assertEquals(14950, playerSeason.getSecondsPlayed());
		assertEquals(104, playerSeason.getoOpportunities());
		assertEquals(59, playerSeason.getoOpportunityScores());
		assertEquals(255, playerSeason.getdOpportunities());
		assertEquals(122, playerSeason.getdOpportunityStops());
	}

}
