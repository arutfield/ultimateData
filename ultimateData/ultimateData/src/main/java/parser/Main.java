
package parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.instrument.Instrumentation;
import java.text.ParseException;
import java.util.LinkedList;
import com.opencsv.CSVWriter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import data.Event;
import data.Game;
import data.GameDetails;
import data.Player;
import data.PlayerGame;
import data.PlayerSeason;
import data.Records;
import data.Team;
import data.TeamStats;
import exceptions.BadEnumException;
import exceptions.WrongSizeRowException;
import parser.StringConverters.GameEventEnum;
import parser.StringConverters.PlayerGameEnum;
import parser.StringConverters.TeamStatsEnum;

public class Main {
	private static final Logger logger = LogManager.getLogger(Main.class);

	public static void main(String[] args)
			throws IOException, WrongSizeRowException, BadEnumException, NumberFormatException, ParseException {

		Records.loadAudlGameEvents();
		Records.loadPlayerGameStatsAdvanced();
		Records.loadGames();
		Records.loadPlayerSeasonStats();
		Records.loadTeamStats();
		Records.loadRawData();

		for (Team team : Records.getTeamRecords()) {
			logger.info("Found team " + team.getTeamId() + " with " + team.getTeamStats().size() + " seasons");
		}

		for (Player player : Records.getPlayerRecords()) {
			logger.info(
					"Found player " + player.getPlayerId() + " with " + player.getPlayerGameList().size() + " games");
		}

		for (Game game : Records.getGameRecords()) {
			logger.info("Found game " + game.getId() + " with " + game.getEvents().size() + " events, "
					+ game.getHomeScore() + " to " + game.getAwayScore());
		}

		// rearrange players by o ratio
		LinkedList<PlayerSeason> playerSeasonsSortedByO = new LinkedList<>();
		for (Player player : Records.getPlayerRecords()) {
			String playerId = player.getPlayerId();
			for (PlayerSeason playerSeason : player.getPlayerSeasonList()) {
				if (playerSeason.getoPointsRatio() == null)
					continue;
				if (playerSeasonsSortedByO.size() == 0)
					playerSeasonsSortedByO.add(playerSeason);
				else {
					boolean addedToList = false;
					for (int i = 0; i < playerSeasonsSortedByO.size(); i++) {
						PlayerSeason playerSeasonAdded = playerSeasonsSortedByO.get(i);
						if (playerSeasonAdded.getoPointsRatio() < playerSeason.getoPointsRatio()) {
							playerSeasonsSortedByO.add(i, playerSeason);
							addedToList = true;
							break;
						}
					}
					if (!addedToList)
						playerSeasonsSortedByO.add(playerSeason);
				}
			}
		}
		LinkedList<String[]> csvData = new LinkedList<>();
		csvData.add(new String[] {"Player ID", "oPointsRatio", "dPointsRatio"});
		for (PlayerSeason season : playerSeasonsSortedByO) {
			csvData.add(new String[] {season.getPlayerId(), season.getoPointsRatio().toString(), season.getdPointsRatio() == null ? "" : season.getdPointsRatio().toString()});
		}
		
	        // default all fields are enclosed in double quotes
	        // default separator is a comma
	        try (CSVWriter writer = new CSVWriter(new FileWriter("test.csv"))) {
	            writer.writeAll(csvData);
	        }
		System.out.println("DONE");
	}

}