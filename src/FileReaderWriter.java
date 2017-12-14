import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * The FileReaderWriter class is responsible for handling operations when
 * reading and writing to a text files.
 * 
 * @author Group#2 *
 */

public class FileReaderWriter 
{
	private File file;

	private SponsorList currentSponsorList = new SponsorList();
	private SponsorList currentSponsors = new SponsorList();;
	private SponsorList sponsorList = new SponsorList();

	private LecturerList currentLecturerList = new LecturerList();
	private LecturerList currentLecturers = new LecturerList();;
	private LecturerList lecturerList = new LecturerList();

	private MemberList currentMemberList = new MemberList();
	private MemberList currentMembers = new MemberList();;
	private MemberList memberList = new MemberList();
	
	private EventList currentEventList = new EventList();
	private EventList currentEvents = new EventList();
	private EventList eventList= new EventList();
	
	private ArrayList<String> membersRegisteredList = new ArrayList<String>();
	private ArrayList<String> membersAlreadyRegistered = new ArrayList<String>();

	public FileReaderWriter(String filename) 
	{
		setFile(filename);
	}

	public void setFile(String filename) 
	{
		file = new File(filename);
	}

	public File getFile() 
	{
		return this.file;
	}
	
	/*public String readEventsMemberFile()
	{
		Scanner input = null;
		
		try {
			input = new Scanner(file);
			
		}
	}*/
	
	public EventList readEventsTextFile() throws FileNotFoundException,ParseException, IOException
	{
    Scanner input = null;
    
    try
    {
       input = new Scanner(file);
       while (input.hasNext())
       {
          String line = input.nextLine();
          String[] lineItems = line.split(",");
          
          String eventTitle=lineItems[0].trim();          
          
          String eventType=lineItems[1].trim();
          
          String eventCategory=lineItems[2].trim();
          
          String eventLecturer = lineItems[3].trim();
          
          LocalDate eventStartDate=LocalDate.parse(lineItems[4].trim());        
          String eventStartTime=lineItems[5].trim();
          LocalDate eventEndDate=LocalDate.parse(lineItems[6].trim());
          String eventEndTime=lineItems[7].trim();
          int maxMembers=Integer.parseInt(lineItems[8].trim());
          double price = Double.parseDouble(lineItems[9].trim());
          double discount = Double.parseDouble(lineItems[10].trim());
          
          int duration = Integer.parseInt(lineItems[11].trim());
          
          String status = lineItems[12].trim();
          
          //LocalDate eventStart = LocalDate.parse(date);
          //LocalDate eventEnd = LocalDate.parse(eventEndDate);
          
          Event eventToAdd = new Event(eventTitle,eventType,eventCategory,eventLecturer,eventStartDate, eventStartTime, eventEndDate, eventEndTime,maxMembers,price,discount,status);
          
          currentEventList.addEventToList(eventToAdd);
          
                   
       }
       return currentEventList;       
    }
    finally
    {
       input.close();
    }
   }	
	
	public void writeEventTextFile(EventList eventList) throws FileNotFoundException
	{
      PrintWriter output = null;
      try
      {
         output = new PrintWriter(file);
         for (int i = 0; i < eventList.size(); i++)
         {
            output.println(eventList.getEvent(i).getEventTitle()+","+eventList.getEvent(i).getEventType()+","+eventList.getEvent(i).getEventCategory()+"," +eventList.getEvent(i).getEventLecturer() + "," + eventList.getEvent(i).getEventStartDate()+","+eventList.getEvent(i).getEventStartTime()+","+eventList.getEvent(i).getEventEndDate()+","+eventList.getEvent(i).getEventEndTime()+","+eventList.getEvent(i).getEventNumberOfTickets()+","+eventList.getEvent(i).getEventPrice()+","+eventList.getEvent(i).getEventDiscount()+","+eventList.getEvent(i).getEventDuration()+","+eventList.getEvent(i).getEventStatus());
         }
         output.flush();
         
      }
      finally
      {
         output.close();
      }
   }
	
	public void writeEventMemberFile(ArrayList<String> membersRegisteredList, String eventName) throws FileNotFoundException
	{
		PrintWriter output = null;
		String file = eventName+"MemberListForEvent.txt";
		try
		{
			output = new PrintWriter(file);
			for ( int i=0; i<membersRegisteredList.size(); i++)
			{
				output.println(membersRegisteredList.get(i));
			}
			output.flush();
			
			
		}
		finally
		{
			output.close();
		}
		   
	}
	
	public ArrayList<String> readEventMemberFile() throws FileNotFoundException
	{
		membersAlreadyRegistered.clear();
		Scanner input = null;
		
		//String file = filenamememberevent+"MemberListForEvent.txt";
		//System.out.println(filename);
		try
		{
			input = new Scanner(file);
			
			int lineNumber = 0;
			while(input.hasNext())
			{
				String line = input.nextLine();
				lineNumber++;
				membersAlreadyRegistered.add(line);
			}
			//System.out.println(membersAlreadyRegistered);
			return membersAlreadyRegistered;
		}
		
		finally 
		{
			input.close();
		}
		
	}
	
	
	public SponsorList readSponsorTextFile() throws FileNotFoundException, ParseException 
	{
		Scanner input = null;

		try 
		{
			input = new Scanner(file);
			int lineNumber = 0;
			while (input.hasNext()) 
			{
				String line = input.nextLine();
				String[] lineToken = line.split(",");
				lineNumber++;

				String sponsorName = lineToken[0].trim();
				String sponsorEmail = lineToken[1].trim();
				String sponsorPhone = lineToken[2].trim();

				Sponsor sponsor = new Sponsor(sponsorName, sponsorEmail, sponsorPhone);
				currentSponsorList.addSponsorToList(sponsor);
			}
			return currentSponsorList;
		}

		finally 
		{
			input.close();
		}
	}
	
	public void writeSponsorTextFile(SponsorList sponsorList) throws FileNotFoundException 
	{
		PrintWriter output = null;
		try 
		{
			output = new PrintWriter(file);

			for (int i = 0; i < sponsorList.size(); i++) 
			{
				output.println(sponsorList.getSponsor(i).getName() + "," + sponsorList.getSponsor(i).getEmail() + ","
						+ sponsorList.getSponsor(i).getPhone());
			}
			output.flush();
		} 
		
		finally 
		{
			output.close();
		}
	}

	public LecturerList readLecturerTextFile() throws FileNotFoundException, ParseException 
	{
		Scanner input = null;

		try 
		{
			input = new Scanner(file);

			while (input.hasNext()) 
			{
				String line = input.nextLine();
				String[] lineToken = line.split(",");

				String lecturerName = lineToken[0].trim();
				String lecturerCategory = lineToken[1].trim();
				String lecturerEmail = lineToken[2].trim();
				String lecturerPhoneNumber = lineToken[3].trim();

				Lecturer lecturer = new Lecturer(lecturerName, lecturerCategory, lecturerEmail, lecturerPhoneNumber);
				currentLecturerList.addLecturerToList(lecturer);
			}
			return currentLecturerList;
		}

		finally 
		{
			input.close();
		}
	}
	
	public void writeLecturerTextFile(LecturerList lecturerList) throws FileNotFoundException 
	{
		PrintWriter output = null;
		try 
		{
			output = new PrintWriter(file);

			for (int i = 0; i < lecturerList.size(); i++) 
			{
				output.println(lecturerList.getLecturer(i).getLecturerName() + "," + lecturerList.getLecturer(i).getLecturerCategory()
						+ "," + lecturerList.getLecturer(i).getLecturerEmail() + ","
						+ lecturerList.getLecturer(i).getLecturerPhoneNumber());
			}
			output.flush();
		} 
		
		finally 
		{
			output.close();
		}
	}

	public MemberList readMemberTextFile() throws FileNotFoundException, ParseException 
	{
		Scanner input = null;

		try 
		{
			input = new Scanner(file);
			int lineNumber = 0;
			while (input.hasNext()) {
				String line = input.nextLine();
				String[] lineToken = line.split(",");
				lineNumber++;

				String memberName = lineToken[0].trim();
				String memberEmail = lineToken[1].trim();
				String membershipStatus = lineToken[2].trim();

				Member member = new Member(memberName, memberEmail, membershipStatus);
				currentMemberList.addMemberToList(member);
			}
			return currentMemberList;
		}

		finally 
		{
			input.close();
		}
	}	

	public void writeMemberTextFile(MemberList memberList) throws FileNotFoundException 
	{
		PrintWriter output = null;
		try 
		{
			 
			output = new PrintWriter(file);

			for (int i = 0; i < memberList.size(); i++) 
			{
				output.println(memberList.getMember(i).getName() + "," + memberList.getMember(i).getEmail() + "," + memberList.getMember(i).getMembershipStatus());
			}
			output.flush();
		} 
		
		finally 
		{
			output.close();
		}
	}
	public void writeNonMemberTextFile(NonMemberList nonMemberList) throws FileNotFoundException 
   {
      PrintWriter output = null;
      try 
      {
         output = new PrintWriter(file);

         for (int i = 0; i < nonMemberList.size(); i++) 
         {
            output.println(nonMemberList.getNonMember(i).getName() + "," + nonMemberList.getNonMember(i).getPhoneNumber());
         }
         output.flush();
      } 
      
      finally 
      {
         output.close();
      }
   }
}