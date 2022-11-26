
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
import data.raw.RawData;
import enums.RawDataEnums;
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
		LinkedList<PlayerSeason> playerSeasonsSortedOverall = new LinkedList<>();
		for (Player player : Records.getPlayerRecords()) {
			String playerId = player.getPlayerId();
			for (PlayerSeason playerSeason : player.getPlayerSeasonList()) {
				if (playerSeason.getoPointsRatio() == null)
					continue;
				if (playerSeasonsSortedOverall.size() == 0)
					playerSeasonsSortedOverall.add(playerSeason);
				else {
					boolean addedToList = false;
					for (int i = 0; i < playerSeasonsSortedOverall.size(); i++) {
						PlayerSeason playerSeasonAdded = playerSeasonsSortedOverall.get(i);
						if (playerSeasonAdded.getOverallRatio() < playerSeason.getOverallRatio()) {
							playerSeasonsSortedOverall.add(i, playerSeason);
							addedToList = true;
							break;
						}
					}
					if (!addedToList)
						playerSeasonsSortedOverall.add(playerSeason);
				}
			}
		}

		int countOLineScores = 0;
		int countTotalScores = 0;
		int countHomeLineScores = 0;
		int homeOLineScoresCount = 0;
		int homeDLineScoresCount = 0;
		int awayOLineScoresCount = 0;
		int awayDLineScoresCount = 0;
		for (RawData rawData : Records.getRawDataRecords()) {
			if (rawData.getScoringPass() == RawDataEnums.YesNoNA.Yes) {
				if (rawData.getOffenseHome() == RawDataEnums.YesNoNA.NA)
					throw new IOException("shouldn't be here");
				countTotalScores++;
				if (rawData.getOffenseHome() == RawDataEnums.YesNoNA.Yes) {
					countHomeLineScores++;
					if (rawData.isHomeTeamOLine()) {
						countOLineScores++;
						homeOLineScoresCount++;
					} else {
						homeDLineScoresCount++;
					}
				} else {
					if (!rawData.isAwayTeamDLine()) {
						countOLineScores++;
						awayOLineScoresCount++;
					} else {
						awayDLineScoresCount++;
					}
				}
			}
		}
		double offenseScoreRatio = (double) (countOLineScores) / (double) (countTotalScores);
		double homeScoreRatio = (double) (countHomeLineScores) / (double) (countTotalScores);
		logger.info("Total scores " + countTotalScores + ", O scores: " + countOLineScores + ", ratio: " + offenseScoreRatio);
		
		LinkedList<String[]> csvData = new LinkedList<>();
		csvData.add(new String[] {"Total scores:", Integer.toString(countTotalScores), "O scores: ", Integer.toString(countOLineScores), "ratio: ", Double.toString(offenseScoreRatio)});
		csvData.add(new String[] {"Total scores:", Integer.toString(countTotalScores), "Home scores: ", Integer.toString(countHomeLineScores), "ratio: ", Double.toString(homeScoreRatio)});
		csvData.add(new String[] {"O Home scores ratio", Double.toString(((double) homeOLineScoresCount) / (double) countTotalScores)});
		csvData.add(new String[] {"O Away scores ratio", Double.toString(((double) awayOLineScoresCount) / (double) countTotalScores)});
		csvData.add(new String[] {"D Home scores ratio", Double.toString(((double) homeDLineScoresCount) / (double) countTotalScores)});
		csvData.add(new String[] {"D Away scores ratio", Double.toString(((double) awayDLineScoresCount) / (double) countTotalScores)});
		csvData.add(new String[] { "Player ID", "year", "overallRatio", "oPointsRatio", "dPointsRatio", "oPointsPlayed",
				"dPointsPlayed", "Percent Offense" });
		for (PlayerSeason season : playerSeasonsSortedOverall) {
			csvData.add(new String[] { season.getPlayerId(), Short.toString(season.getYear()),
					season.getOverallRatio().toString(),
					season.getoPointsRatio().toString(),
					season.getdPointsRatio() == null ? "" : season.getdPointsRatio().toString(),
					Short.toString(season.getoPointsPlayed()), Short.toString(season.getdPointsPlayed()),
					Double.toString(season.getPercentOffense())});
		}

		// default all fields are enclosed in double quotes
		// default separator is a comma
		try (CSVWriter writer = new CSVWriter(new FileWriter("test.csv"))) {
			writer.writeAll(csvData);
		}
		System.out.println("DONE");
	}

}