
package parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.instrument.Instrumentation;
import java.util.LinkedList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import data.Event;
import exceptions.BadEnumException;
import exceptions.WrongSizeRowException;


public class Main {	
    private static final Logger logger = LogManager.getLogger(Main.class);
    public static void main(String[] args) throws IOException, WrongSizeRowException, BadEnumException {
    	LinkedList<Event> records = new LinkedList<>();
    	boolean seenHeaderLine = false;
    	try (BufferedReader br = new BufferedReader(new FileReader("../AUDLGameEvents.csv"))) {
    	    String line;
    	    while ((line = br.readLine()) != null) {
    	    	if (!seenHeaderLine) {
    	    		seenHeaderLine = true;
    	    		continue;
    	    	}
    	        String[] values = line.split(",");
    	        logger.debug(line);
    	        records.add(StringConverters.convertToEvent(values));
    	    }
    	}

    }



}