package data;

import java.util.LinkedList;

public class Game {
	private final String id;
	private final LinkedList<Event> events;
	
	public Game(String id) {
		this.id = id;
		this.events = new LinkedList<Event>();
	}

	public String getId() {
		return id;
	}

	public LinkedList<Event> getEvents() {
		return events;
	}
	
	public void addEvent(Event event) {
		events.add(event);
	}
}
