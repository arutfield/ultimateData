package data;

import java.util.HashMap;

import exceptions.BadMapException;

public class TeamTable {
	private static HashMap<String, String> teamAbbreviations = new HashMap<String, String>();

	public static void createMap() {
		teamAbbreviations.put("alleycats", "IND");
		teamAbbreviations.put("allstars1", "Rowan");
		teamAbbreviations.put("allstars2", "KPS");
		teamAbbreviations.put("aviators", "LA");
		teamAbbreviations.put("breeze", "DC");
		teamAbbreviations.put("cannons", "JAX");
		teamAbbreviations.put("cannons", "TB");
		teamAbbreviations.put("cascades", "SEA");
		teamAbbreviations.put("constitution", "CON");
		teamAbbreviations.put("cranes", "CLB");
		teamAbbreviations.put("dragons", "ROC");
		teamAbbreviations.put("empire", "NY");
		teamAbbreviations.put("express", "CHA");
		teamAbbreviations.put("flamethrowers", "SF");
		teamAbbreviations.put("flyers", "RAL");
		teamAbbreviations.put("glory", "BOS");
		teamAbbreviations.put("growlers", "SD");
		teamAbbreviations.put("hammerheads", "NJH");
		teamAbbreviations.put("hustle", "ATL");
		teamAbbreviations.put("legion", "DAL");
		teamAbbreviations.put("lions", "SLC");
		teamAbbreviations.put("mechanix", "DET");
		teamAbbreviations.put("nightwatch", "NSH");
		teamAbbreviations.put("nitro", "POR");
		teamAbbreviations.put("outlaws", "OTT");
		teamAbbreviations.put("phoenix", "PHI");
		teamAbbreviations.put("radicals", "MAD");
		teamAbbreviations.put("rampage", "RIR");
		teamAbbreviations.put("revolution", "CIN");
		teamAbbreviations.put("riptide", "VAN");
		teamAbbreviations.put("royal", "MTL");
		teamAbbreviations.put("rush", "TOR");
		teamAbbreviations.put("shred", "SLC");
		teamAbbreviations.put("sol", "AUS");
		teamAbbreviations.put("spiders", "SJ");
		teamAbbreviations.put("spinners", "PHS");
		teamAbbreviations.put("summit", "COL");
		teamAbbreviations.put("thunderbirds", "PIT");
		teamAbbreviations.put("union", "CHI");
		teamAbbreviations.put("windchill", "MIN");
		teamAbbreviations.put("home", "HME");
		teamAbbreviations.put("away", "AWY");

	}
	
	public static String getTeamIdFromAbbrev(String abbrev) throws BadMapException {
		for (String key : teamAbbreviations.keySet()) {
			if (abbrev.equals(teamAbbreviations.get(key)))
				return key;
		}
		if (abbrev.equals("OAK"))
			return "spiders";
		if (abbrev.equals("ATX"))
			return "sol";
		if (abbrev.equals("CAR"))
			return "flyers";
		throw new BadMapException("No key to match " + abbrev);
	}
}
