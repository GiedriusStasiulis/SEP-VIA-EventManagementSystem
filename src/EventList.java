import java.util.ArrayList;

public class EventList 
{
	private ArrayList<Event> eventList;

	public EventList() 
	{
		eventList = new ArrayList<Event>();
	}

	public int size() 
	{
		return eventList.size();
	}

	public void addEventToList(Event event) 
	{
		eventList.add(event);
	}
	
	public void replaceEventInList(int index, Event event) 
	{
		eventList.set(index, event);
	}

	public Event getEvent(int index) 
	{
		return eventList.get(index);
	}
	
	public int getEventIndex(Event event) 
	{
		return eventList.indexOf(event);
	}

	public void deleteEventFromList(int index) 
	{
		eventList.remove(index);
	}	

	public void clearEventList() 
	{
		eventList.clear();
	}

	public String toString() 
	{
		String s = String.format("%s", eventList);
		return s;
	}
}