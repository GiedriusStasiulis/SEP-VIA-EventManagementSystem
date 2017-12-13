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
public class Event {
	private String eventTitle;
	private LocalDate eventStartDate, eventEndDate;
	private boolean isFinalized;
	private String startTime, endTime;
	private int maxMembers;
	private double price, discount;
	
	private String eventType;
	private String eventCategory;
	
	private int eventDuration;
	
	ArrayList<Member> membersRegistered;
	ArrayList<Member> tempMembersRegistered;
	ArrayList<Lecturer> lecturersRegistered;
	
	
	public Event(String eventTitle,String eventType,String eventCategory, LocalDate eventStartDate, String startTime, LocalDate eventEndDate, String endTime,int maxMembers, double price, double discount) throws IOException 
	{
		this.eventTitle = eventTitle;
		this.eventType=eventType;
		this.eventCategory=eventCategory;
		// this.isFinalized=false;
		this.eventStartDate = eventStartDate;
		this.eventEndDate = eventEndDate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.maxMembers = maxMembers;
		this.price = price;
		this.discount = discount;
		this.membersRegistered = new ArrayList<Member>();
		this.tempMembersRegistered = new ArrayList<Member>();
		calculateDuration();
		
		
	}

	public LocalDate getEventStartDate() 
	{
		return this.eventStartDate;
	}

	public void setEventStartDate(LocalDate eventStartDate) 
	{
		this.eventStartDate = eventStartDate;
	}

	public LocalDate getEventEndDate() 
	{
		return this.eventEndDate;
	}

	public void setEventEndDate(LocalDate eventEndDate) 
	{
		this.eventEndDate = eventEndDate;
	}

	public String getEventTitle() 
	{
		return eventTitle;
	}

	public void setEventTitle(String eventTitle) 
	{
		this.eventTitle = eventTitle;
	}

	public Member getMemberByIndex(int index) 
	{
		return membersRegistered.get(index);
	}

	public String toString() 
	{
		return this.eventTitle + " " + this.isFinalized;
	}

	public String getStartTime() 
	{
		return this.startTime;
	}

	public String getEndTime() 
	{
		return this.endTime;
	}

	public int getMaxMembers() 
	{
		return this.maxMembers;
	}

	public double getPrice() 
	{
		return this.price;
	}

	public double getDiscount() 
	{
		return this.discount;
	}
	public String getCategory()
	{
	   return this.eventCategory;
	}
	public String getType()
	{
	   return this.eventType;
	}
	public int getDuration()
	{
	   return this.eventDuration;
	}
	
	public void calculateDuration()
	{
	   Period intervalPeriod= Period.between(eventStartDate, eventEndDate);
	   this.eventDuration=intervalPeriod.getDays()+1;
	   
	}
	public void createFileForLecturers() throws IOException
	{
	   String filename = this.getEventTitle()+"LecturerListForEvent.txt";
	   File file = new File(filename);
	   file.createNewFile();
	   
	}
	
}
