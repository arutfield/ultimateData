
package parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.instrument.Instrumentation;
import java.util.LinkedList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import data.Event;
import data.Game;
import exceptions.BadEnumException;
import exceptions.WrongSizeRowException;
import parser.StringConverters.GameEventEnum;


public class Main {	
    private static final Logger logger = LogManager.getLogger(Main.class);
    public static void main(String[] args) throws IOException, WrongSizeRowException, BadEnumException {
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
    }



}