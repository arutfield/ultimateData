package ultimateData;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Locale;

import org.junit.jupiter.api.Test;

import data.Coordinate;
import data.Event;
import data.Event.gameTeam;
import data.Game;
import data.GameDetails;
import data.PlayerGame;
import data.PlayerSeason;
import data.TeamStats;
import data.TeamTable;
import data.raw.RawData;
import data.raw.Weather;
import enums.EventTypeEnum;
import enums.QuarterEnum;
import enums.RawDataEnums;
import enums.RawDataEnums.DefenseScheme;
import exceptions.BadEnumException;
import exceptions.BadMapException;
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

	private <T> void checkList(LinkedList<T> desiredStringList, T[] strings) {
		assertEquals(desiredStringList.size(), strings.length);
		for (int i=0; i<desiredStringList.size(); i++)
			assertEquals(desiredStringList.get(i), strings[i]);		
	}

	
	private void checkWeather(Weather weather, Double desiredTemperature, Double desiredWindSpeed, Double desiredWindDirection, Double desiredPrecipitation, RawDataEnums.YesNoNA desiredAnyPrecipitation) {
		if (desiredTemperature == null)
			assertNull(weather.getTemperature());
		else
			assertEquals(desiredTemperature, weather.getTemperature(), 0.001);
		if (desiredWindSpeed == null)
			assertNull(weather.getWindSpeed());
		else
			assertEquals(desiredWindSpeed, weather.getWindSpeed(), 0.001);
		if (desiredWindDirection == null)
			assertNull(weather.getWindDirection());
		else
			assertEquals(desiredWindDirection, weather.getWindDirection(), 0.001);
		if (desiredPrecipitation == null)
			assertNull(weather.getPrecipitation());
		else
			assertEquals(desiredPrecipitation, weather.getPrecipitation(), 0.001);
		assertEquals(desiredAnyPrecipitation, weather.getAnyPrecipitation());
	}
	
	@Test
	public void testTest() {
		assertTrue(true);
	}

	@Test
	public void testConvertToEvent() throws WrongSizeRowException, BadEnumException, NumberFormatException, BadMapException {
		TeamTable.createMap();
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
	public void testConvertToEvent2() throws WrongSizeRowException, BadEnumException, NumberFormatException, BadMapException {
		TeamTable.createMap();
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

		assertEquals("legion", gameDetails.getAwayTeam());
		assertEquals("sol", gameDetails.getHomeTeam());
		assertEquals(23, gameDetails.getAwayScore());
		assertEquals(24, gameDetails.getHomeScore());
		assertEquals(68400000, gameDetails.getTime());
		assertEquals(2, gameDetails.getWeek());
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

	@Test
	public void testConvertTeamStats() {
		String sampleString = "union2015,union,2015,midwest,Midwest,Chicago,Chicago Union,CHI,8,6,1,3";
		String[] values = sampleString.split(",");
		TeamStats teamStats = StringConverters.convertToTeamStats(values);
		assertEquals(2015, teamStats.getYear());
		assertEquals("midwest", teamStats.getDivisionID());
		assertEquals(8, teamStats.getWins());
		assertEquals(6, teamStats.getLosses());
		assertEquals(1, teamStats.getTies());
		assertEquals(3, teamStats.getDivStanding());
	}

	//682
	@Test
	public void testRawData() throws NumberFormatException, BadEnumException, ParseException {
		String sampleString = "8/20/21: Empire vs. Glory,Empire,Glory,High School,7,5,Left,Q1,0:00:00,Zone,Empire,Turnover,11,0.800728463,0.682992254,27,0.151269003,0.810201943,NA,none,none,Unmarked/Not Covered,Backhand,none,none,Blocked Pass,Interception,none,8/20/2021,77.1,2.6,7,0.04,Glory,jwilliams,bjagt,ocable,NA,NA,NA,NA,NA,NA,NA,NA,NA,NA,NA,NA,ocable,NA,NA,NA,NA,NA,NA,NA,NA,NA,bjagt,jwilliams,bkatz1,rweaver,jlithio,jbabbitt,rosgar,thalkyard,bmccann,jkurmanfa,ocable,nthompson,cbartoli,adonadio,New York,1,No,2,2,1,19,13,0,0,25,21,W,3,0:00:00,3,2160,0,0-5 sec,135,1,O-Line,D-Line,O-Line,D-Line,Home,35.6996958,41.99021421,5.902644686,79.53013435,74.09735531,0.469865646,9.033029138,15.32354754,6.290518404,73.62748967,6.290518404,73.62748967,Downfield,18.24239737,-0.987006296,NA,80.9870063,0,0,1,1,0,0,0,1,none,None,None,,85.11667427,0,TRUE,FALSE,FALSE,Zone";
		String[] values = sampleString.split(",");
		RawData rawData = StringConverters.convertToRawData(values);
		assertEquals(RawDataEnums.DefenseScheme.Zone, rawData.getDefenseScheme());
		assertEquals("empire", rawData.getTeamId());
		assertEquals(RawDataEnums.EventType.Turnover, rawData.getEventType());
		assertEquals("jwilliams", rawData.getPlayer1Id());
		checkCoordinates(rawData.getLocation1(), 0.800728463, 0.682992254);
		assertEquals("bjagt", rawData.getPlayer2Id());
		checkCoordinates(rawData.getLocation2(), 0.151269003, 0.810201943);
		assertEquals(Double.NaN, rawData.getPullTime(), 0.001);
		assertEquals(RawDataEnums.PullInfo.None, rawData.getPullInfo());
		assertEquals(RawDataEnums.DefenderDistance.None, rawData.getClosestDefenderDistance());
		assertEquals(RawDataEnums.Force.Unmarked, rawData.getForce());
		assertEquals(RawDataEnums.PassType.Backhand, rawData.getPassType());
		assertEquals(RawDataEnums.BrokeMark.None, rawData.getBrokeMark());
		assertEquals(RawDataEnums.PassBreak.None, rawData.getPassBreak());
		assertEquals(RawDataEnums.TurnoverType.blockedPass, rawData.getTurnoverType());
		assertEquals(RawDataEnums.BlockType.Interception, rawData.getBlockType());
		assertEquals(RawDataEnums.FoulSide.None, rawData.getFoulSide());
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
		Date actualDate = formatter.parse("08/20/2021");
		assertEquals(actualDate, rawData.getGameDate());
		checkWeather(rawData.getWeather(), 77.1, 2.6, 7.0, 0.04, RawDataEnums.YesNoNA.Yes);
		assertEquals("glory", rawData.getDefendingTeamId());
		assertEquals("jwilliams", rawData.getThrowerId());
		assertEquals("bjagt", rawData.getTargetedReceiverId());
		checkList(rawData.getClosestDefenders(), new String[] {"ocable", "NA", "NA", "NA", "NA", "NA"});
		checkList(rawData.getMarkers(), new String[] {"NA", "NA"});
		checkList(rawData.getPoachers(), new String[] {"NA", "NA", "NA", "NA", "NA"});
		checkList(rawData.getDeflectors(), new String[] {"ocable", "NA"});
		assertEquals("NA", rawData.getPullThrowerId());
		assertEquals("NA", rawData.getPullReceiverId());
		assertEquals("NA", rawData.getoFoulerId());
		checkList(rawData.getdFoulerIds(), new String[] {"NA", "NA", "NA"});
		assertEquals("NA", rawData.getoFouledId());
		assertEquals("NA", rawData.getdFouledId());
		assertEquals(new String[] {"bjagt", "jwilliams", "bkatz1", "rweaver", "jlithio", "jbabbitt", "rosgar"}, rawData.getHomePlayerIds());
		assertEquals(new String[] {"thalkyard", "bmccann", "jkurmanfa", "ocable", "nthompson", "cbartoli", "adonadio"}, rawData.getAwayPlayerIds());
		assertEquals("New York", rawData.getHomeTeamLocation());
		assertEquals(false, rawData.isFieldCflType());
		assertEquals(2, (short) rawData.getHomeAwayDifferential());
		assertEquals(2, (short) rawData.getOffenseDefenseDifferential());
		assertEquals(RawDataEnums.YesNoNA.Yes, rawData.isRegulationDone());
		assertEquals(19, (short) rawData.getPossessionNumber());
		assertEquals(13, (short) rawData.getPointNumber());
		assertEquals(RawDataEnums.TeamScored.No, rawData.getTeamScoredOnPoint());
		assertEquals(RawDataEnums.YesNoNA.No, rawData.isTeamScoredOnPossession());
		assertEquals(25, (short) rawData.getHomeScoreEndofGame());
		assertEquals(21, (short) rawData.getAwayScoreEndofGame());
		assertEquals(RawDataEnums.HomeTeamOutcome.Win, rawData.getHomeTeamGameOutcome());
		assertEquals(3, rawData.getDurationEventEstimate(), 0.001);
		assertEquals(0, rawData.getGameClockEstimate(), 0.001);
		assertEquals(3, rawData.getDurationPointEstimate(), 0.001);
		assertEquals(2160, rawData.getTimeRemainingRegularGame(), 0.001);
		assertEquals(0, rawData.getTimeRemainingQuarter(), 0.001);
		assertEquals(RawDataEnums.QGroupTimeRemaining.ZeroToFive, rawData.getTimeRemainingQGroup());
		assertEquals(135, (short) rawData.getNumberThrowOfQuarter());
		assertEquals(RawDataEnums.YesNoNA.Yes, rawData.getLastThrowOfQuarter());
		assertEquals(true, rawData.isoTeamLine());
		assertEquals(RawDataEnums.YesNoNA.Yes, rawData.getDefendingTeamLineDLine());
		assertEquals(true, rawData.isHomeTeamOLine());
		assertEquals(true, rawData.isAwayTeamDLine());
		assertEquals(RawDataEnums.YesNoNA.Yes, rawData.getOffenseHome());
		checkCoordinates(rawData.getLocation1Yards(), 35.6997, 5.902645);
		checkCoordinates(rawData.getLocation2Yards(), 41.99021, 79.53013);
		assertEquals(74.09736, (double) rawData.getLocation1YardsToEndzone(), 0.001);
		assertEquals(0.469866, (double) rawData.getLocation2YardsToEndzone(), 0.001);
		assertEquals(9.033029, (double) rawData.getLocation1YardsFromMiddle(), 0.001);
		assertEquals(15.32355, (double) rawData.getLocation2YardsFromMiddle(), 0.001);
		checkCoordinates(rawData.getThrowDistance(), 6.290518404, 73.62748967);
		checkCoordinates(rawData.getThrowVector(), 6.290518404, 73.62748967);
		assertEquals(rawData.getThrowDirectionCategory(), RawDataEnums.ThrowDirectionEnum.Downfield);
		checkCoordinates(rawData.getPullCoordinates(), 18.24239737, -0.987006296);
		assertEquals(Double.NaN, rawData.getPullYardsFromMiddle(), 0.001);
		assertEquals(80.98701, rawData.getPullYDistance(), 0.001);
		assertEquals(RawDataEnums.YesNoNA.No, rawData.getCompletedPass());
		assertEquals(RawDataEnums.YesNoNA.No, rawData.getScoringPass());
		assertEquals(1, (short) rawData.getThrowNumberInPossession());
		assertEquals(1, (short) rawData.getThrowNumberInPoint());
		//Component 4
		assertEquals(0, (byte) rawData.getNumberOfMarkers());
		assertEquals(0, (byte) rawData.getNumberOfMarkersPlusPoachers());
		assertEquals(0, (byte) rawData.getNumberOfPoachers());
		assertEquals(1, (byte) rawData.getNumberOfClosestDefenders());
		assertEquals(RawDataEnums.ClosestDefenderTight.None, rawData.getClosestDefenderTight());
		assertEquals(RawDataEnums.ForceDirection.None, rawData.getForceDirection());
		checkList(rawData.getMainForcePossession(), new RawDataEnums.ForceDirection[] {RawDataEnums.ForceDirection.None});
		checkList(rawData.getMainForcePossessionDirection(), new RawDataEnums.ForceDirection[] {});
		assertEquals(85.11667, (double) rawData.getThrowAngle(), 0.001);
		assertEquals(RawDataEnums.YesNoNA.No, rawData.getOverheadThrow());
		assertEquals(true, rawData.isAnyZoneDOnPossession());
		assertEquals(false, rawData.isAnyMixedDOnPossession());
		assertEquals(false, rawData.isAnyPersonDOnPossession());
		assertEquals(DefenseScheme.Zone, rawData.getdSchemePossession());
	}

	@Test
	public void testRawDataLine164() throws NumberFormatException, BadEnumException, ParseException {
		String sampleString = "7/24/21: Outlaws vs. Rush,Outlaws,Rush,CFL,9,8,none,Q2,0:10:49,none,Rush,Pull Received,29,0.143718773,0.444617501,27,0.785775253,0.474521303,N/S (gap in video),Normal/In-Bounds,none,none,none,none,none,none,none,none,7/24/2021,NA,NA,NA,NA,Outlaws,NA,NA,NA,NA,NA,NA,NA,NA,NA,NA,NA,NA,NA,NA,NA,NA,NA,cturner,nhirst,NA,NA,NA,NA,NA,NA,khunter,mobrien,badibe,brobinson,cturner,rtoogood,dbenvenut,acarroll,lcomire,mmackenzi,nhirst,jhuynh,dlafrance,wlewis,Ottawa,NA,Yes,1,-1,0,23,19,1,1,17,13,W,6,0:10:49,14,2089,649,30+ sec,11,0,O-Line,D-Line,D-Line,O-Line,Away,29.42126773,27.94252489,-0.345860792,67.48256974,70.34586079,2.51743026,2.754601068,1.275858221,1.478742847,67.82843053,-1.478742847,67.82843053,Downfield,25.39080845,6.648878611,1.269191554,63.35112139,NA,0,0,0,0,0,0,0,none,NA,None Right Straight-Up,Right Straight-Up,-88.75107969,NA,FALSE,FALSE,TRUE,Person";
		String[] values = sampleString.split(",");
		RawData rawData = StringConverters.convertToRawData(values);
		assertEquals(RawDataEnums.DefenseScheme.None, rawData.getDefenseScheme());
		assertEquals("rush", rawData.getTeamId());
		assertEquals(RawDataEnums.EventType.PullReceived, rawData.getEventType());
		assertEquals("cturner", rawData.getPlayer1Id());
		checkCoordinates(rawData.getLocation1(), 0.143719, 0.4446618);
		assertEquals("nhirst", rawData.getPlayer2Id());
		checkCoordinates(rawData.getLocation2(), 0.785775, 0.474521);
		assertEquals(Double.NaN, rawData.getPullTime(), 0.001);
		assertEquals(RawDataEnums.PullInfo.Normal, rawData.getPullInfo());
		assertEquals(RawDataEnums.DefenderDistance.None, rawData.getClosestDefenderDistance());
		assertEquals(RawDataEnums.Force.None, rawData.getForce());
		assertEquals(RawDataEnums.PassType.None, rawData.getPassType());
		assertEquals(RawDataEnums.BrokeMark.None, rawData.getBrokeMark());
		assertEquals(RawDataEnums.PassBreak.None, rawData.getPassBreak());
		assertEquals(RawDataEnums.TurnoverType.None, rawData.getTurnoverType());
		assertEquals(RawDataEnums.BlockType.None, rawData.getBlockType());
		assertEquals(RawDataEnums.FoulSide.None, rawData.getFoulSide());
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
		Date actualDate = formatter.parse("07/24/2021");
		assertEquals(actualDate, rawData.getGameDate());
		checkWeather(rawData.getWeather(), Double.NaN, Double.NaN, Double.NaN, Double.NaN, RawDataEnums.YesNoNA.NA);
		assertEquals("outlaws", rawData.getDefendingTeamId());
		assertEquals("NA", rawData.getThrowerId());
		assertEquals("NA", rawData.getTargetedReceiverId());
		checkList(rawData.getClosestDefenders(), new String[] {"NA", "NA", "NA", "NA", "NA", "NA"});
		checkList(rawData.getMarkers(), new String[] {"NA", "NA"});
		checkList(rawData.getPoachers(), new String[] {"NA", "NA", "NA", "NA", "NA"});
		checkList(rawData.getDeflectors(), new String[] {"NA", "NA"});
		assertEquals("cturner", rawData.getPullThrowerId());
		assertEquals("nhirst", rawData.getPullReceiverId());
		assertEquals("NA", rawData.getoFoulerId());
		checkList(rawData.getdFoulerIds(), new String[] {"NA", "NA", "NA"});
		assertEquals("NA", rawData.getoFouledId());
		assertEquals("NA", rawData.getdFouledId());
		assertEquals(new String[] {"khunter", "mobrien", "badibe", "brobinson", "cturner", "rtoogood", "dbenvenut"}, rawData.getHomePlayerIds());
		assertEquals(new String[] {"acarroll", "lcomire", "mmackenzi", "nhirst", "jhuynh", "dlafrance", "wlewis"}, rawData.getAwayPlayerIds());
		assertEquals("Ottawa", rawData.getHomeTeamLocation());
		assertEquals(true, rawData.isFieldCflType());
		assertEquals(1, (short) rawData.getHomeAwayDifferential());
		assertEquals(-1, (short) rawData.getOffenseDefenseDifferential());
		assertEquals(RawDataEnums.YesNoNA.No, rawData.isRegulationDone());
		assertEquals(23, (short) rawData.getPossessionNumber());
		assertEquals(19, (short) rawData.getPointNumber());
		assertEquals(RawDataEnums.TeamScored.Yes, rawData.getTeamScoredOnPoint());
		assertEquals(RawDataEnums.YesNoNA.Yes, rawData.isTeamScoredOnPossession());
		assertEquals(17, (short) rawData.getHomeScoreEndofGame());
		assertEquals(13, (short) rawData.getAwayScoreEndofGame());
		assertEquals(RawDataEnums.HomeTeamOutcome.Win, rawData.getHomeTeamGameOutcome());
		assertEquals(6, rawData.getDurationEventEstimate(), 0.001);
		assertEquals(649, rawData.getGameClockEstimate(), 0.001);
		assertEquals(14, rawData.getDurationPointEstimate(), 0.001);
		assertEquals(2089, rawData.getTimeRemainingRegularGame(), 0.001);
		assertEquals(649, rawData.getTimeRemainingQuarter(), 0.001);
		assertEquals(RawDataEnums.QGroupTimeRemaining.OverThirty, rawData.getTimeRemainingQGroup());
		assertEquals(11, (short) rawData.getNumberThrowOfQuarter());
		assertEquals(RawDataEnums.YesNoNA.No, rawData.getLastThrowOfQuarter());
		assertEquals(true, rawData.isoTeamLine());
		assertEquals(RawDataEnums.YesNoNA.Yes, rawData.getDefendingTeamLineDLine());
		assertEquals(false, rawData.isHomeTeamOLine());
		assertEquals(false, rawData.isAwayTeamDLine());
		assertEquals(RawDataEnums.YesNoNA.No, rawData.getOffenseHome());
		checkCoordinates(rawData.getLocation1Yards(), 29.42127, -0.34586);
		checkCoordinates(rawData.getLocation2Yards(), 27.94252, 67.48257);
		assertEquals(70.34586, (double) rawData.getLocation1YardsToEndzone(), 0.001);
		assertEquals(2.51743, (double) rawData.getLocation2YardsToEndzone(), 0.001);
		assertEquals(2.754601, (double) rawData.getLocation1YardsFromMiddle(), 0.001);
		assertEquals(1.275858, (double) rawData.getLocation2YardsFromMiddle(), 0.001);
		checkCoordinates(rawData.getThrowDistance(), 1.478743, 67.82843);
		checkCoordinates(rawData.getThrowVector(), -1.47874, 67.82843);
		assertEquals(rawData.getThrowDirectionCategory(), RawDataEnums.ThrowDirectionEnum.Downfield);
		checkCoordinates(rawData.getPullCoordinates(), 25.39081, 6.648879);
		assertEquals(1.269192, rawData.getPullYardsFromMiddle(), 0.001);
		assertEquals(63.35112, rawData.getPullYDistance(), 0.001);
		assertEquals(RawDataEnums.YesNoNA.NA, rawData.getCompletedPass());
		assertEquals(RawDataEnums.YesNoNA.No, rawData.getScoringPass());
		assertEquals(0, (short) rawData.getThrowNumberInPossession());
		assertEquals(0, (short) rawData.getThrowNumberInPoint());
		//Component 4
		assertEquals(0, (byte) rawData.getNumberOfMarkers());
		assertEquals(0, (byte) rawData.getNumberOfMarkersPlusPoachers());
		assertEquals(0, (byte) rawData.getNumberOfPoachers());
		assertEquals(0, (byte) rawData.getNumberOfClosestDefenders());
		assertEquals(RawDataEnums.ClosestDefenderTight.None, rawData.getClosestDefenderTight());
		assertEquals(RawDataEnums.ForceDirection.NA, rawData.getForceDirection());
		checkList(rawData.getMainForcePossession(), new RawDataEnums.ForceDirection[] {RawDataEnums.ForceDirection.None, RawDataEnums.ForceDirection.Right, RawDataEnums.ForceDirection.StraightUp});
		checkList(rawData.getMainForcePossessionDirection(), new RawDataEnums.ForceDirection[] {RawDataEnums.ForceDirection.Right, RawDataEnums.ForceDirection.StraightUp});
		assertEquals(-88.7511, (double) rawData.getThrowAngle(), 0.001);
		assertEquals(RawDataEnums.YesNoNA.NA, rawData.getOverheadThrow());
		assertEquals(false, rawData.isAnyZoneDOnPossession());
		assertEquals(false, rawData.isAnyMixedDOnPossession());
		assertEquals(true, rawData.isAnyPersonDOnPossession());
		assertEquals(DefenseScheme.Person, rawData.getdSchemePossession());
	}


}
