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
			String lastId = null;
			Game game = null;
			while ((line = br.readLine()) != null) {
				if (!seenHeaderLine) {
					seenHeaderLine = true;
					continue;
				}
				logger.debug(line);
				String[] values = line.split(",");
				String gameId = values[GameEventEnum.gameID.getValue()];
				if (!gameId.equals(lastId)) {
					if (game != null) {
						gameRecords.add(game);
					}
					boolean gameFound = false;
					for (Game recordedGame : gameRecords) {
						if (recordedGame.getId().equals(gameId)) {
							game = recordedGame;
							gameFound = true;
						}
					}
					if (!gameFound)
						game = new Game(gameId);
					lastId = gameId;
				}
				game.addEvent(StringConverters.convertToEvent(values));
			}
		}
	}

	public static void loadPlayerGameStatsAdvanced()
			throws FileNotFoundException, IOException, WrongSizeRowException, BadEnumException {
		boolean seenHeaderLinePlayer = false;
		try (BufferedReader br = new BufferedReader(new FileReader("../playergamestatsADV.csv"))) {
			String line;
			String lastId = null;
			Player player = null;
			while ((line = br.readLine()) != null) {
				if (!seenHeaderLinePlayer) {
					seenHeaderLinePlayer = true;
					continue;
				}
				logger.debug(line);
				String[] values = line.split(",");
				String playerId = values[PlayerGameEnum.playerId.getValue()];
				if (!playerId.equals(lastId)) {
					if (player != null) {
						playerRecords.add(player);
					}
					boolean playerFound = false;
					for (Player recordedPlayer : playerRecords) {
						if (recordedPlayer.getPlayerId().equals(playerId)) {
							player = recordedPlayer;
							playerFound = true;
						}
					}
					if (!playerFound)
						player = new Player(playerId);
					lastId = playerId;
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
				logger.debug(line);
				PlayerSeason playerSeason = StringConverters.convertToPlayerSeason(values);
				String playerId = values[PlayerGameEnum.playerId.getValue()];
				// see if it already exists
				boolean playerFound = false;
				for (Player recordedPlayer : playerRecords) {
					if (recordedPlayer.getPlayerId().equals(playerId)) {
						playerFound = true;
						recordedPlayer.addSeason(playerSeason);
						break;
					}
				}
				if (!playerFound) {
					Player newPlayer = new Player(playerId);
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
				if (line.contains('"' + "")) {// this isn't the ideal solution, but double commas is very complicated to
												// deal with. TODO: fix
					continue;
				}
				String[] values = line.split(",");
				logger.info(line);
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
