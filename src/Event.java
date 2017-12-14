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
	
	private String eventLecturer;
	private LocalDate eventStartDate, eventEndDate;
	private String status;
	private String startTime, endTime;
	private int maxMembers;
	private double price, discount;
	
	private String eventType;	

	private String eventCategory;
	
	private int eventDuration;
	
	ArrayList<String> membersRegistered;
	ArrayList<Member> tempMembersRegistered;
	
	
	
	public Event(String eventTitle,String eventType,String eventCategory,String eventLecturer, LocalDate eventStartDate, String startTime, LocalDate eventEndDate, String endTime,int maxMembers, double price, double discount, String status) throws IOException 
	{
		this.eventTitle = eventTitle;
		this.eventType=eventType;
		this.eventCategory=eventCategory;
		this.eventLecturer = eventLecturer;
		this.status=status;
		this.eventStartDate = eventStartDate;
		this.eventEndDate = eventEndDate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.maxMembers = maxMembers;
		this.price = price;
		this.discount = discount;
		this.membersRegistered = new ArrayList<String>();
		this.tempMembersRegistered = new ArrayList<Member>();
		//this.lecturersRegistered = new ArrayList<String>();
		calculateDuration();		
	}





	


	


	
	public String getEventTitle() {
		return eventTitle;
	}












	public void setEventTitle(String eventTitle) {
		this.eventTitle = eventTitle;
	}












	public String getEventLecturer() {
		return eventLecturer;
	}












	public void setEventLecturer(String eventLecturer) {
		this.eventLecturer = eventLecturer;
	}












	public LocalDate getEventStartDate() {
		return eventStartDate;
	}












	public void setEventStartDate(LocalDate eventStartDate) {
		this.eventStartDate = eventStartDate;
	}












	public LocalDate getEventEndDate() {
		return eventEndDate;
	}












	public void setEventEndDate(LocalDate eventEndDate) {
		this.eventEndDate = eventEndDate;
	}












	public String getStatus() {
		return status;
	}












	public void setStatus(String status) {
		this.status = status;
	}












	public String getStartTime() {
		return startTime;
	}












	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}












	public String getEndTime() {
		return endTime;
	}












	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}












	public int getMaxMembers() {
		return maxMembers;
	}












	public void setMaxMembers(int maxMembers) {
		this.maxMembers = maxMembers;
	}












	public double getPrice() {
		return price;
	}












	public void setPrice(double price) {
		this.price = price;
	}












	public double getDiscount() {
		return discount;
	}












	public void setDiscount(double discount) {
		this.discount = discount;
	}












	public String getEventType() {
		return eventType;
	}












	public void setEventType(String eventType) {
		this.eventType = eventType;
	}












	public String getEventCategory() {
		return eventCategory;
	}












	public void setEventCategory(String eventCategory) {
		this.eventCategory = eventCategory;
	}












	public int getEventDuration() {
		return eventDuration;
	}












	public void setEventDuration(int eventDuration) {
		this.eventDuration = eventDuration;
	}












	public ArrayList<String> getMembersRegistered() {
		return membersRegistered;
	}












	public void setMembersRegistered(ArrayList<String> membersRegistered) {
		this.membersRegistered = membersRegistered;
	}












	public ArrayList<Member> getTempMembersRegistered() {
		return tempMembersRegistered;
	}












	public void setTempMembersRegistered(ArrayList<Member> tempMembersRegistered) {
		this.tempMembersRegistered = tempMembersRegistered;
	}












	public String getMemberByIndex(int index) 
	{
		return membersRegistered.get(index);
	}
	

	public void calculateDuration()
	{
	   Period intervalPeriod= Period.between(eventStartDate, eventEndDate);
	   this.eventDuration=intervalPeriod.getDays()+1;
	   
	}
	
	
	public void addMemberToEvent(String memberName) throws FileNotFoundException
	{
		membersRegistered.add(memberName);
		//System.out.println(membersRegistered);
	}
	
	public String toString() 
	{
		return this.eventTitle;
	}
	
	
	/*
	public void writeRegisteredLecturersToFile() throws FileNotFoundException
	{
	   PrintWriter output = null;
	   String filename = this.eventTitle+"LecturerListForEvent.txt";
	   
	   try
	   {
	      output = new PrintWriter(filename);
	      for ( int i = 0; i<lecturersRegistered.size(); i++)
	      {
	         output.println(lecturersRegistered.get(i));
	      }
	      output.flush();
	      
	   }
	   finally
	   {
	      output.close();
	   }
	   
	} */
}
