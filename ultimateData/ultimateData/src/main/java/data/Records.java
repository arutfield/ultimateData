package data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.LinkedList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import data.raw.RawData;
import exceptions.BadEnumException;
import exceptions.WrongSizeRowException;
import parser.StringConverters;
import parser.StringConverters.GameEventEnum;
import parser.StringConverters.PlayerGameEnum;
import parser.StringConverters.TeamStatsEnum;

public class Records {

	private static final Logger logger = LogManager.getLogger(Records.class);
	private static final LinkedList<Player> playerRecords = new LinkedList<Player>();
	private static final LinkedList<Game> gameRecords = new LinkedList<Game>();
	private static final LinkedList<Team> teamRecords = new LinkedList<Team>();
	private static final LinkedList<RawData> rawDataRecords = new LinkedList<RawData>();

	public static void loadAudlGameEvents()
			throws WrongSizeRowException, BadEnumException, FileNotFoundException, IOException {
		boolean seenHeaderLine = false;
		try (BufferedReader br = new BufferedReader(new FileReader("../AUDLGameEvents.csv"))) {
			String line;
			while ((line = br.readLine()) != null) {
				if (!seenHeaderLine) {
					seenHeaderLine = true;
					continue;
				}
				logger.debug(line);
				String[] values = line.split(",");
				String gameId = values[GameEventEnum.gameID.getValue()];
				Game game = null;
				boolean gameFound = false;
				for (Game recordedGame : gameRecords) {
					if (recordedGame.getId().equals(gameId)) {
						game = recordedGame;
						gameFound = true;
						break;
					}
				}
				if (!gameFound) {
					game = new Game(gameId);
				}
				game.addEvent(StringConverters.convertToEvent(values));
			}
		}
	}

	//add games to a player
	public static void loadPlayerGameStatsAdvanced()
			throws FileNotFoundException, IOException, WrongSizeRowException, BadEnumException {
		boolean seenHeaderLinePlayer = false;
		try (BufferedReader br = new BufferedReader(new FileReader("../playergamestatsADV.csv"))) {
			String line;
			while ((line = br.readLine()) != null) {
				if (!seenHeaderLinePlayer) {
					seenHeaderLinePlayer = true;
					continue;
				}
				logger.debug(line);
				String[] values = line.split(",");
				String playerId = values[PlayerGameEnum.playerId.getValue()];
				Player player = null;
				//if this isn't the last player
				boolean alreadyHavePlayer = false;
				for (Player currentPlayer : playerRecords ) {
					if (currentPlayer.getPlayerId().equals(playerId)) {
						alreadyHavePlayer = true;
						player = currentPlayer;
					}
				}
				if (!alreadyHavePlayer) {
					player = new Player(playerId);
					logger.error("added new player " + player.getPlayerId());
					playerRecords.add(player);
				}

				player.addGame(StringConverters.convertToPlayerGame(values));
			}
		}

	}

	public static void loadGames()
			throws FileNotFoundException, IOException, NumberFormatException, WrongSizeRowException, ParseException {
		try (BufferedReader br = new BufferedReader(new FileReader("../games.csv"))) {
			String line;
			boolean seenHeaderLine = false;
			while ((line = br.readLine()) != null) {
				if (!seenHeaderLine) {
					seenHeaderLine = true;
					continue;
				}
				logger.debug(line);
				String[] values = line.split(",");
				GameDetails gameDetails = StringConverters.convertToGameDetails(values);
				String gameId = values[GameEventEnum.gameID.getValue()];
				// see if it already exists
				boolean gameFound = false;
				for (Game recordedGame : gameRecords) {
					if (recordedGame.getId().equals(gameId)) {
						gameFound = true;
						recordedGame.addDetails(gameDetails);
						break;
					}
				}
				if (!gameFound) {
					Game newGame = new Game(gameId);
					newGame.addDetails(gameDetails);
					gameRecords.add(newGame);
				}
			}
		}
		System.out.println("games found " + gameRecords.size());
	}

	public static void loadPlayerSeasonStats() throws FileNotFoundException, IOException {
		try (BufferedReader br = new BufferedReader(new FileReader("../playerseasonstats.csv"))) {
			String line;
			boolean seenHeaderLine = false;
			logger.info("game records length " + gameRecords.size());
			while ((line = br.readLine()) != null) {
				if (!seenHeaderLine) {
					seenHeaderLine = true;
					continue;
				}
				String[] values = line.split(",");
				logger.error(line);
				PlayerSeason playerSeason = StringConverters.convertToPlayerSeason(values);
				String playerId = values[PlayerGameEnum.playerId.getValue()];
				// see if it already exists
				boolean playerFound = false;
				for (Player recordedPlayer : playerRecords) {
					if (recordedPlayer.getPlayerId().equals(playerId)) {
						logger.error("adding to existing player " + recordedPlayer.getPlayerId());
						playerFound = true;
						recordedPlayer.addSeason(playerSeason);
						break;
					}
				}
				if (!playerFound) {
					Player newPlayer = new Player(playerId);
					logger.error("adding new player " + newPlayer.getPlayerId());
					newPlayer.addSeason(playerSeason);
					playerRecords.add(newPlayer);
				}
			}
		}
		
	}
	
	public static void loadTeamStats() throws IOException {
		// add teams
		try (BufferedReader br = new BufferedReader(new FileReader("../teamStats.csv"))) {
			String line;
			boolean seenHeaderLine = false;
			while ((line = br.readLine()) != null) {
				if (!seenHeaderLine) {
					seenHeaderLine = true;
					continue;
				}
				String[] values = line.split(",");
				TeamStats teamStats = StringConverters.convertToTeamStats(values);
				String teamId = values[TeamStatsEnum.teamId.getValue()];
				// see if it already exists
				boolean teamFound = false;
				for (Team recordedTeam : teamRecords) {
					if (recordedTeam.getTeamId().equals(teamId)) {
						teamFound = true;
						recordedTeam.addTeamStats(teamStats);
						break;
					}
				}
				if (!teamFound) {
					Team newTeam = new Team(teamId);
					newTeam.addTeamStats(teamStats);
					teamRecords.add(newTeam);
				}
				logger.debug(line);
			}
		}

	}

	public static void loadRawData() throws IOException, NumberFormatException, BadEnumException, ParseException {
		// add teams
		try (BufferedReader br = new BufferedReader(new FileReader("../appDataRaw.csv"))) {
			String line;
			boolean seenHeaderLine = false;
			while ((line = br.readLine()) != null) {
				if (!seenHeaderLine) {
					seenHeaderLine = true;
					continue;
				}
				LinkedList<Integer> commaIndices = new LinkedList<Integer>();
				if (line.contains('"' + "")) {// this isn't the ideal solution, but double commas is very complicated to
					int quoteIndex = line.indexOf('"' + "");
					int quoteIndex2 = line.substring(quoteIndex + 1).indexOf('"' + "") + quoteIndex + 1;
					String quotedSection = line.substring(quoteIndex + 1, quoteIndex2);
					for (int i=0; i<quotedSection.length(); i++) {
						if (quotedSection.charAt(i) == ',') {
							commaIndices.add(i);
						}
					}
					//remove commas to _ (will be replaced later)
					for (int i=quoteIndex; i<quoteIndex2; i++) {
						if (line.charAt(i) == ',')
							line = line.substring(0, i) + "_" + line.substring(i+ 1);
					}
					//convert quotes to commas
					line = line.substring(0, quoteIndex) + line.substring(quoteIndex + 1);
					line = line.substring(0, quoteIndex2 - 1) + line.substring(quoteIndex2);

				}
				String[] values = line.split(",");
				logger.debug(line);
				RawData rawData = StringConverters.convertToRawData(values);
				rawDataRecords.add(rawData);
			}
		}

	}

	public static LinkedList<Player> getPlayerRecords() {
		return playerRecords;
	}

	public static LinkedList<Game> getGameRecords() {
		return gameRecords;
	}

	public static LinkedList<Team> getTeamRecords() {
		return teamRecords;
	}

	public static LinkedList<RawData> getRawDataRecords() {
		return rawDataRecords;
	}

}
