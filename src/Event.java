import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import javafx.scene.control.DatePicker;

//Abstract class Event
public class Event
{
      private String eventName;
      private LocalDate eventStartDate,eventEndDate;
      private boolean isFinalized;
      private String startTime,endTime;
      private int maxMembers;
      private double price,discount;
      
      ArrayList<Member> membersRegistered;
      ArrayList<Member> tempMembersRegistered;
      
      public Event(String eventName, LocalDate eventStartDate)
      {
         this.eventName = eventName;
         //this.isFinalized=false;         
         this.eventStartDate = eventStartDate;
         /*this.eventEndDate=eventEndDate;
         this.startTime=startTime;
         this.endTime=endTime;
         this.maxMembers=maxMembers;
         this.price=price;
         this.discount=discount;
         this.membersRegistered= new ArrayList<Member>();
         this.tempMembersRegistered = new ArrayList<Member>();*/
      }
      
      

      public LocalDate getEventStartDate() {
		return eventStartDate;
	}



	public void setEventStartDate(LocalDate eventStartDate) {
		this.eventStartDate = eventStartDate;
	}



	public String getEventName() {
		return eventName;
	}



	public void setEventName(String eventName) {
		this.eventName = eventName;
	}



	public Member getMemberByIndex(int index)
      {
         return membersRegistered.get(index);
      }
	
	
      public String toString()
      {
         return this.eventName +" " + this.isFinalized;
      }
}
