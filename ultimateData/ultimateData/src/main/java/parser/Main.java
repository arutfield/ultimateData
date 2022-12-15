
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
import data.TeamTable;
import data.raw.RawData;
import enums.RawDataEnums;
import exceptions.BadEnumException;
import exceptions.BadMapException;
import exceptions.ValueException;
import exceptions.WrongSizeRowException;
import parser.StringConverters.GameEventEnum;
import parser.StringConverters.PlayerGameEnum;
import parser.StringConverters.TeamStatsEnum;

public class Main {
	private static class Ratios {
		int countOLineScores = 0;
		int countTotalScores = 0;
		int countHomeLineScores = 0;
		int homeOLineScoresCount = 0;
		int homeDLineScoresCount = 0;
		int awayOLineScoresCount = 0;
		int awayDLineScoresCount = 0;
		double offenseScoreRatio;
		double homeScoreRatio;

		Ratios() {

		}
	}

	private static final Logger logger = LogManager.getLogger(Main.class);
	private static final Ratios mainRatios = new Ratios();

	public static void main(String[] args) throws Exception {
		//read in and store all data
		TeamTable.createMap();
		Records.loadGames();
		Records.loadAudlGameEvents();
		Records.loadPlayerGameStatsAdvanced();
		Records.loadPlayerSeasonStats();
		Records.loadTeamStats();
		Records.loadRawData();
		// once loaded, do post calculations
		for (Player player : Records.getPlayerRecords()) {
			for (PlayerSeason season : player.getPlayerSeasonList()) {
				season.calculatePostSeasonStatistics();
			}
		}

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

		// rearrange players by overall ratio
		LinkedList<PlayerSeason> playerSeasonsSortedOverall = new LinkedList<>();
		for (Player player : Records.getPlayerRecords()) {
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

		calculateScoringRatios();

		//top of player csv shows some raw statistics about scoring
		LinkedList<String[]> csvData = new LinkedList<>();
		csvData.add(new String[] { "Total scores:", Integer.toString(mainRatios.countTotalScores), "O scores: ",
				Integer.toString(mainRatios.countOLineScores), "ratio: ",
				Double.toString(mainRatios.offenseScoreRatio) });
		csvData.add(new String[] { "Total scores:", Integer.toString(mainRatios.countTotalScores), "Home scores: ",
				Integer.toString(mainRatios.countHomeLineScores), "ratio: ",
				Double.toString(mainRatios.homeScoreRatio) });
		csvData.add(new String[] { "O Home scores ratio",
				Double.toString(((double) mainRatios.homeOLineScoresCount) / (double) mainRatios.countTotalScores) });
		csvData.add(new String[] { "O Away scores ratio",
				Double.toString(((double) mainRatios.awayOLineScoresCount) / (double) mainRatios.countTotalScores) });
		csvData.add(new String[] { "D Home scores ratio",
				Double.toString(((double) mainRatios.homeDLineScoresCount) / (double) mainRatios.countTotalScores) });
		csvData.add(new String[] { "D Away scores ratio",
				Double.toString(((double) mainRatios.awayDLineScoresCount) / (double) mainRatios.countTotalScores) });
		
		//set up headers for CSV and then dump the player season information
		csvData.add(new String[] { "Player ID", "team ID", "year", "overallRatio", "oPointsRatio", "dPointsRatio",
				"oPointsPlayed", "dPointsPlayed", "Percent Offense", "Average throw distance",
				"Average receive distance", "Average Throw Angle", "Average Receive Angle", "Throw Throwaway Rate",
				"Blocks per Point", "Catches per Point", "Drops per Point", "Successful hucks per Point",
				"Score Opportunities per Point", "Scores per Point", "Scores/Opportunity",
				"Defense Opportunities per Point", "Stops per Point", "Stops/Opportunity",
				"Personal stops per opportunity", "Turns involved in per defense play" });
		for (PlayerSeason season : playerSeasonsSortedOverall) {
			String[] stringToAdd = new String[] { season.getPlayerId(), season.getTeamID(),
					Short.toString(season.getYear()), season.getOverallRatio().toString(),
					season.getoPointsRatio().toString(),
					season.getdPointsRatio() == null ? "" : season.getdPointsRatio().toString(),
					Short.toString(season.getoPointsPlayed()), Short.toString(season.getdPointsPlayed()),
					Double.toString(season.getPercentOffense()),
					season.getAverageDistanceThrown() >= 0 ? Double.toString(season.getAverageDistanceThrown()) : "",
					season.getAverageDistanceReceived() >= 0 ? Double.toString(season.getAverageDistanceReceived())
							: "",
					season.getAverageThrowAngle() >= 0 ? Double.toString(season.getAverageThrowAngle()) : "",
					season.getAverageReceiveAngle() >= 0 ? Double.toString(season.getAverageReceiveAngle()) : "",
					season.getThrowawayRatio() >= 0 ? Double.toString(season.getThrowawayRatio()) : "",
					season.getBlocksPerPoint() >= 0 ? Double.toString(season.getBlocksPerPoint()) : "",
					season.getCatchesPerPoint() >= 0 ? Double.toString(season.getCatchesPerPoint()) : "",
					season.getDropsPerPoint() >= 0 ? Double.toString(season.getDropsPerPoint()) : "",
					season.getSuccessfulHucksPerPoint() >= 0 ? Double.toString(season.getSuccessfulHucksPerPoint())
							: "",
					season.getoOpportunitiesPerPoint() >= 0 ? Double.toString(season.getoOpportunitiesPerPoint()) : "",
					season.getoOpportunityScoresPerPoint() >= 0
							? Double.toString(season.getoOpportunityScoresPerPoint())
							: "",
					season.getoOpportunitySuccessPerPoint() >= 0
							? Double.toString(season.getoOpportunitySuccessPerPoint())
							: "",
					season.getdOpportunitiesPerPoint() >= 0 ? Double.toString(season.getdOpportunitiesPerPoint()) : "",
					season.getdOpportunityStopsPerPoint() >= 0 ? Double.toString(season.getdOpportunityStopsPerPoint())
							: "",
					season.getdOpportunitySuccessPerPoint() >= 0
							? Double.toString(season.getdOpportunitySuccessPerPoint())
							: "",
					season.getPersonalStopsPerOpportunity() >= 0
							? Double.toString(season.getPersonalStopsPerOpportunity())
							: "",
					season.getPercentInvolvedInDefensePerThrow() >= 0
							? Double.toString(season.getPercentInvolvedInDefensePerThrow())
							: "",

			};
			csvData.add(stringToAdd);
		}

		exportToCsv(csvData, "player_results_");

		//start looking at teams and perform appropriate calculations
		LinkedList<TeamStats> rankedTeamStats = orderSeasonsByRank();
		calculatePPGs();
		for (Game game : Records.getGameRecords()) {
			game.countPassAttempts();
			game.countPassesAgainstMan();
		}

		for (Team team : Records.getTeamRecords()) {
			team.calculationsForEachSeason();
		}

		//dump to CSV
		LinkedList<String[]> teamCSVData = new LinkedList<>();
		teamCSVData.add(new String[] { "Team name", "Year", "Average pt differential", "W/L ratio", "Ranking", "# Wins",
				"PPG", "PPG Against", "Pass Attempts", "Man Pass Ratio", "Completions per Game",
				"Average time between passes", "Average distance thrown", "Average angle thrown"});

		for (TeamStats teamStat : rankedTeamStats) {
			teamCSVData.add(new String[] { teamStat.getTeamId(), String.valueOf(teamStat.getYear()),
					(teamStat.getAveragePointDifferential() != Double.NaN
							? String.valueOf(teamStat.getAveragePointDifferential())
							: ""),
					String.valueOf(teamStat.getTeamSeasonRating()), String.valueOf(teamStat.getSeasonRanking()),
					String.valueOf(teamStat.getWins()), String.valueOf(teamStat.getAveragePPG()),
					printIfPositive(teamStat.getAveragePPGAgainst()),
					printIfPositive(teamStat.getAveragePassAttempts()),
					printIfPositive(teamStat.getManPassRatio()),
					printIfPositive(teamStat.getCompletionsPerGame()),
					printIfPositive(teamStat.getAverageGapTime()),
					printIfPositive(teamStat.getAverageDistanceThrown()),
					printIfPositive(teamStat.getAverageThrowAngle())});
		}
		exportToCsv(teamCSVData, "team_results_");
	}

	private static String printIfPositive(Double value) {
		if (value > 0)
			return String.valueOf(value);
		else
			return "";
	}
	
	
	/** export content to csv file
	 * @param csvContent content to dump
	 * @param filename name of file to dump into
	 */
	private static void exportToCsv(LinkedList<String[]> csvContent, String filename) {
		// default all fields are enclosed in double quotes
		// default separator is a comma
		int attemptsMade = 0;
		while (true) {
			try (CSVWriter writer = new CSVWriter(new FileWriter(filename + attemptsMade + ".csv"))) {
				writer.writeAll(csvContent);
				break;
			} catch (Exception ex) {
				logger.error(ex.getMessage());
				attemptsMade++;
			}
		}

	}

	/** Calculate percentage of points where home scores and team starting on offense scores
	 * @throws IOException error
	 */
	private static void calculateScoringRatios() throws IOException {
		for (RawData rawData : Records.getRawDataRecords()) {
			if (rawData.getScoringPass() == RawDataEnums.YesNoNA.Yes) {
				if (rawData.getOffenseHome() == RawDataEnums.YesNoNA.NA)
					throw new IOException("shouldn't be here");
				mainRatios.countTotalScores++;
				if (rawData.getOffenseHome() == RawDataEnums.YesNoNA.Yes) {
					mainRatios.countHomeLineScores++;
					if (rawData.isHomeTeamOLine()) {
						mainRatios.countOLineScores++;
						mainRatios.homeOLineScoresCount++;
					} else {
						mainRatios.homeDLineScoresCount++;
					}
				} else {
					if (!rawData.isAwayTeamDLine()) {
						mainRatios.countOLineScores++;
						mainRatios.awayOLineScoresCount++;
					} else {
						mainRatios.awayDLineScoresCount++;
					}
				}
			}
		}
		mainRatios.offenseScoreRatio = (double) (mainRatios.countOLineScores) / (double) (mainRatios.countTotalScores);
		mainRatios.homeScoreRatio = (double) (mainRatios.countHomeLineScores) / (double) (mainRatios.countTotalScores);
		logger.info("Total scores " + mainRatios.countTotalScores + ", O scores: " + mainRatios.countOLineScores
				+ ", ratio: " + mainRatios.offenseScoreRatio);
	}

	
	/** Order the teams' seasons by how well they did
	 * @return list of seasons
	 * @throws Exception error
	 */
	private static LinkedList<TeamStats> orderSeasonsByRank() throws Exception {
		LinkedList<TeamStats> statList = new LinkedList<>();
		for (Team team : Records.getTeamRecords()) {
			boolean statAdded = false;
			for (TeamStats listTeamStat : team.getTeamStats()) {
				// iterate already ordered list
				for (int i = 0; i < statList.size(); i++) {
					TeamStats currentStat = statList.get(i);
					//System.out.println("current team " + listTeamStat.getTeamId() + ", " + listTeamStat.getYear());
					if (Double.isNaN(listTeamStat.getAveragePointDifferential())) {
						continue;
					}
					if (listTeamStat.getAveragePointDifferential() > currentStat.getAveragePointDifferential()
							|| (listTeamStat.getAveragePointDifferential() == currentStat.getAveragePointDifferential()
									&& listTeamStat.getTeamSeasonRating() > currentStat.getTeamSeasonRating())) {
						statList.add(i, listTeamStat);
						statAdded = true;
						break;
					}
				}
				if (!statAdded && listTeamStat != null)
					statList.add(listTeamStat);
			}
		}
		int i = 0;
		for (TeamStats statInList : statList) {
			statInList.setSeasonRanking(i);
			i++;
		}
		return statList;
	}

	/**
	 * calculate average number of points each team scored per game
	 */
	private static void calculatePPGs() {
		for (Game game : Records.getGameRecords()) {
			int year = game.getYear();
			String homeTeam = game.getHomeTeam();
			String awayTeam = game.getAwayTeam();
			for (Team team : Records.getTeamRecords()) {

				if (team.getTeamId().equals(homeTeam)) {
					for (TeamStats stats : team.getTeamStats())
						if (stats.getYear() == year) {
							stats.addToPointsScored(game.getHomeScore());
							stats.addToPointsScoredAgainst(game.getAwayScore());
							break;
						}
				} else if (team.getTeamId().equals(awayTeam)) {
					for (TeamStats stats : team.getTeamStats()) {
						if (stats.getYear() == year) {
							stats.addToPointsScored(game.getAwayScore());
							stats.addToPointsScoredAgainst(game.getHomeScore());
							break;
						}
					}
				}
			}

			for (Team team : Records.getTeamRecords()) {
				for (TeamStats stats : team.getTeamStats()) {
					stats.setAveragePPG((double) stats.getPointsScored()
							/ (double) (stats.getWins() + stats.getLosses() + stats.getTies()));
					stats.setAveragePPGAgainst((double) stats.getPointsAgainst()
							/ (double) (stats.getWins() + stats.getLosses() + stats.getTies()));
				}
			}

		}
	}

}