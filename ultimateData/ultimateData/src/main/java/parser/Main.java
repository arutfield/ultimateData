
package parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.instrument.Instrumentation;
import java.text.ParseException;
import java.util.LinkedList;

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
    public static void main(String[] args) throws IOException, WrongSizeRowException, BadEnumException, NumberFormatException, ParseException {
    	
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
    		logger.info("Found player " + player.getPlayerId() + " with " + player.getPlayerGameList().size() + " games");
    	}
    	
    	for (Game game : Records.getGameRecords()) {
    		logger.info("Found game " + game.getId() + " with " + game.getEvents().size() + " events, " + game.getHomeScore() + " to " + game.getAwayScore());
    	}
    }



}