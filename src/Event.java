import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import javafx.scene.control.DatePicker;

//Abstract class Event
public class Event 
{	
	private String eventTitle;
	private String eventType;
	private String eventCategory;
	private String eventLecturer;
	private LocalDate eventStartDate, eventEndDate;	
	private String eventStartTime, eventEndTime;
	private int eventNumberOfTickets;
	private int eventTicketsRemaining;
	private double eventPrice, eventDiscount;
	private String eventStatus;

	private int eventDuration;
	
	private int remTickets;

	ArrayList<String> membersRegistered;
	ArrayList<Member> tempMembersRegistered;

	public Event(String eventTitle, String eventType, String eventCategory, String eventLecturer,
			LocalDate eventStartDate, String eventStartTime, LocalDate eventEndDate, String eventEndTime, int eventNumberOfTickets,
			int eventTicketsRemaining, double eventPrice, double eventDiscount, String eventStatus) throws IOException 
	{		
		this.eventTitle = eventTitle;
		this.eventType = eventType;
		this.eventCategory = eventCategory;
		this.eventLecturer = eventLecturer;		
		this.eventStartDate = eventStartDate;
		this.eventEndDate = eventEndDate;
		this.eventStartTime = eventStartTime;
		this.eventEndTime = eventEndTime;
		this.eventNumberOfTickets = eventNumberOfTickets;
		this.eventPrice = eventPrice;
		this.eventDiscount = eventDiscount;
		this.eventStatus = eventStatus;
		
		this.eventTicketsRemaining = eventTicketsRemaining;
		
		this.membersRegistered = new ArrayList<String>();
		this.tempMembersRegistered = new ArrayList<Member>();
		// this.lecturersRegistered = new ArrayList<String>();
		calculateDuration();
	}

	public String getEventTitle() 
	{
		return eventTitle;
	}
	
	public void setEventTitle(String eventTitle) 
	{
		this.eventTitle = eventTitle;
	}

	public String getEventType() 
	{
		return eventType;
	}

	public void setEventType(String eventType) 
	{
		this.eventType = eventType;
	}

	public String getEventCategory() 
	{
		return eventCategory;
	}

	public void setEventCategory(String eventCategory) 
	{
		this.eventCategory = eventCategory;
	}

	public String getEventLecturer() 
	{
		return eventLecturer;
	}

	public void setEventLecturer(String eventLecturer) 
	{
		this.eventLecturer = eventLecturer;
	}

	public LocalDate getEventStartDate() 
	{
		return eventStartDate;
	}

	public void setEventStartDate(LocalDate eventStartDate) 
	{
		this.eventStartDate = eventStartDate;
	}

	public LocalDate getEventEndDate() 
	{
		return eventEndDate;
	}

	public void setEventEndDate(LocalDate eventEndDate) 
	{
		this.eventEndDate = eventEndDate;
	}

	public String getEventStartTime() 
	{
		return eventStartTime;
	}

	public void setEventStartTime(String eventStartTime) 
	{
		this.eventStartTime = eventStartTime;
	}

	public String getEventEndTime() 
	{
		return eventEndTime;
	}
	
	public void setEventEndTime(String eventEndTime) 
	{
		this.eventEndTime = eventEndTime;
	}

	public int getEventNumberOfTickets() 
	{
		return eventNumberOfTickets;
	}

	public void setEventNumberOfTickets(int eventNumberOfTickets) 
	{
		this.eventNumberOfTickets = eventNumberOfTickets;
	}

	public double getEventPrice() 
	{
		return eventPrice;
	}

	public void setEventPrice(double eventPrice) 
	{
		this.eventPrice = eventPrice;
	}

	public double getEventDiscount() {
		return eventDiscount;
	}
	
	public void setEventDiscount(double eventDiscount) 
	{
		this.eventDiscount = eventDiscount;
	}

	public String getEventStatus() 
	{
		return eventStatus;
	}

	public void setEventStatus(String eventStatus) 
	{
		this.eventStatus = eventStatus;
	}

	public int getEventDuration() 
	{
		return eventDuration;
	}

	public void setEventDuration(int eventDuration) 
	{
		this.eventDuration = eventDuration;
	}

	public ArrayList<String> getMembersRegistered() 
	{
		return membersRegistered;
	}

	public void setMembersRegistered(ArrayList<String> membersRegistered) 
	{
		this.membersRegistered = membersRegistered;
	}

	public ArrayList<Member> getTempMembersRegistered() 
	{
		return tempMembersRegistered;
	}

	public void setTempMembersRegistered(ArrayList<Member> tempMembersRegistered) 
	{
		this.tempMembersRegistered = tempMembersRegistered;
	}

	public int getEventTicketsRemaining() {
		return eventTicketsRemaining;
	}

	public void setEventTicketsRemaining(int eventTicketsRemaining) {
		this.eventTicketsRemaining = eventTicketsRemaining;
	}

	public void calculateDuration() 
	{
		Period intervalPeriod = Period.between(eventStartDate, eventEndDate);
		this.eventDuration = intervalPeriod.getDays() + 1;
	}
	
	public int calculateTicketsRemaining(int totalNum, int participantSize)
	{
		this.eventTicketsRemaining = totalNum - participantSize;
		
		return eventTicketsRemaining;
	}
	
	public void addMemberToEvent(String memberName) throws FileNotFoundException 
	{
		membersRegistered.add(memberName);
	}	
	
	public boolean equals(Object obj) 
	{
		if (!(obj instanceof Event)) {
			return false;
		}

		Event other = (Event) obj;
		return eventTitle.equals(other.eventTitle) && eventType.equals(other.eventType) && eventCategory.equals(other.eventCategory)
				&& eventLecturer.equals(other.eventLecturer) && eventStartDate.equals(other.eventStartDate) && eventStartTime.equals(other.eventStartTime)
				&& eventEndDate.equals(other.eventEndDate) && eventEndTime.equals(other.eventEndTime) && eventNumberOfTickets == other.eventNumberOfTickets && eventPrice == other.eventPrice
				&& eventDiscount == other.eventDiscount && eventDuration == other.eventDuration && eventStatus.equals(other.eventStatus);
	}

	public String toString() 
	{
		String s = String.format("%s, %s, %s, %s, %s, %s, %s, %s, %d, %,.2f, %,.2f, %d, %s", getEventTitle(), getEventType(), getEventCategory(), getEventLecturer(),
													getEventStartDate(), getEventStartTime(),getEventEndDate(),getEventEndTime(),getEventNumberOfTickets(),getEventPrice(),getEventDiscount(),getEventDuration(),getEventStatus());
		return s;
	}
}
