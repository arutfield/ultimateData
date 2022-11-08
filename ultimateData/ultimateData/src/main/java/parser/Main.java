
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
import exceptions.BadEnumException;
import exceptions.WrongSizeRowException;
import parser.StringConverters.GameEventEnum;
import parser.StringConverters.PlayerGameEnum;


public class Main {	
    private static final Logger logger = LogManager.getLogger(Main.class);
    public static void main(String[] args) throws IOException, WrongSizeRowException, BadEnumException, NumberFormatException, ParseException {
    	boolean seenHeaderLine = false;
    	LinkedList<Game> gameRecords = new LinkedList<>();
    	try (BufferedReader br = new BufferedReader(new FileReader("../AUDLGameEvents.csv"))) {
    	    String line;
    	    String lastId = null;
    	    Game game = null;
        	while ((line = br.readLine()) != null) {
    	    	if (!seenHeaderLine) {
    	    		seenHeaderLine = true;
    	    		continue;
    	    	}
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
    	        logger.debug(line);
				game.addEvent(StringConverters.convertToEvent(values));
    	    }
    	}
    	for (Game game : gameRecords) {
    		logger.info("game " + game.getId() + " had " + game.getEvents().size() + " events");
    	}
    	
    	
    	boolean seenHeaderLinePlayer = false;
    	LinkedList<Player> playerRecords = new LinkedList<>();
    	try (BufferedReader br = new BufferedReader(new FileReader("../playergamestatsADV.csv"))) {
    	    String line;
    	    String lastId = null;
    	    Player player = null;
        	while ((line = br.readLine()) != null) {
    	    	if (!seenHeaderLinePlayer) {
    	    		seenHeaderLinePlayer = true;
    	    		continue;
    	    	}
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
    	        logger.debug(line);
				player.addEvent(StringConverters.convertToPlayerGame(values));
    	    }
    	}
    	for (Player player : playerRecords) {
    		logger.info("player " + player.getPlayerId() + " had " + player.getPlayerGameList().size() + " games");
    	}
    
    	
    	try (BufferedReader br = new BufferedReader(new FileReader("../games.csv"))) {
    	    String line;
    	    String lastId = null;
    	    Game game = null;
    	    seenHeaderLine = false;
    	    logger.info("game records length " + gameRecords.size());
        	while ((line = br.readLine()) != null) {
    	    	if (!seenHeaderLine) {
    	    		seenHeaderLine = true;
    	    		continue;
    	    	}
    	        String[] values = line.split(",");
    	        GameDetails gameDetails = StringConverters.convertToGameDetails(values);
    	        String gameId = values[GameEventEnum.gameID.getValue()];
    	        //see if it already exists
    	        boolean gameFound = false;
    	        for (Game recordedGame : gameRecords) {
    	        	if (recordedGame.getId().equals(gameId)) {
    	        		gameFound = true;
    	        		//logger.info("found game already");
    	        		recordedGame.addDetails(gameDetails);
    	        		break;
    	        	}
    	        }
    	        if (!gameFound) {
    	        	//logger.info("setting up new game");
    	        	Game newGame = new Game(gameId);
    	        	newGame.addDetails(gameDetails);
    	        	gameRecords.add(newGame);
    	        }
    	        logger.debug(line);
    	    }
    	}
    	logger.info("found " + gameRecords.size() + " games total");
    	
    	
    	
    }



}