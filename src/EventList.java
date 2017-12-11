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
   
   public String toString()
   {
      String s = String.format("%s", eventList);
      return s;
   }
}
