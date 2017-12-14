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
   
   public Event getEvent(int index)
   {
      return eventList.get(index);
   }
   
   public void deleteEvent(int index)
	{
	   eventList.remove(index);
	}
   
   public void replaceEvent(int index, Event event)
	{
		eventList.set(index, event);
	}
   
   public int getEventIndex(Event event)
	{
		return eventList.indexOf(event);
	}
   
   public boolean checkForDuplicates(EventList eventList, Event event)
	{
		boolean status = false;
		
		for(int i = 0; i < eventList.size(); i++)
		{
			if(event.equals(eventList.getEvent(i)))
				return true;
		}
		
		return status;
	}
   
   public String toString()
   {
      String s = String.format("%s", eventList);
      return s;
   }
}
