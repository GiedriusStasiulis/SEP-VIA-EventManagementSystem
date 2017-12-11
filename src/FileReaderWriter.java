import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Scanner;

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
	
	public EventList readEventsTextFile() throws FileNotFoundException,ParseException
   {
    Scanner input = null;
    
    try
    {
       input = new Scanner(file);
       while (input.hasNext())
       {
          String line = input.nextLine();
          String[] lineItems = line.split(";");
          
          String eventName=lineItems[0].trim();
          String eventStartDate=lineItems[1].trim();
          String eventStartTime=lineItems[2].trim();
          String eventEndDate=lineItems[3].trim();
          String eventEndTime=lineItems[4].trim();
          double eventPrice=Double.parseDouble(lineItems[5].trim());
          
          double eventDiscount=Double.parseDouble(lineItems[6].trim());
          int eventMaxMembers=Integer.parseInt(lineItems[7].trim());
          
          LocalDate eventStart = LocalDate.parse(eventStartDate);
          LocalDate eventEnd = LocalDate.parse(eventEndDate);
          
          Event eventToAdd = new Event(eventName, eventStart,eventEnd,eventStartTime,eventEndTime,eventMaxMembers,eventPrice,eventDiscount);
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
            output.println(eventList.getEvent(i).getName()+ ";" +eventList.getEvent(i).getStartDate().toString()+ ";"+eventList.getEvent(i).getStartTime() + ";" + eventList.getEvent(i).getEndDate().toString()+ ";"+eventList.getEvent(i).getEndTime()+";"+eventList.getEvent(i).getPrice()+";"+eventList.getEvent(i).getDiscount()+";"+eventList.getEvent(i).getMaxMembers());
         }
         output.flush();
         
      }
      finally
      {
         output.close();
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

	public LecturerList readLecturerTextFile() throws FileNotFoundException, ParseException 
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

				String lecturerName = lineToken[0].trim();
				String lecturerCategory = lineToken[1].trim();
				String lecturerPhoneNumber = lineToken[2].trim();
				String lecturerEmail = lineToken[3].trim();

				Lecturer lecturer = new Lecturer(lecturerName, lecturerCategory, lecturerPhoneNumber, lecturerEmail);
				currentLecturerList.addLecturerToList(lecturer);
			}
			return currentLecturerList;
		}

		finally 
		{
			input.close();
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

				Member member = new Member(memberName, memberEmail);
				currentMemberList.addMemberToList(member);
			}
			return currentMemberList;
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

	public void writeLecturerTextFile(LecturerList lecturerList) throws FileNotFoundException 
	{
		PrintWriter output = null;
		try 
		{
			output = new PrintWriter(file);

			for (int i = 0; i < lecturerList.size(); i++) 
			{
				output.println(lecturerList.getLecturer(i).getName() + "," + lecturerList.getLecturer(i).getCategory()
						+ "," + lecturerList.getLecturer(i).getPhoneNumber() + ","
						+ lecturerList.getLecturer(i).getEmail());
			}
			output.flush();
		} 
		
		finally 
		{
			output.close();
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
				output.println(memberList.getMember(i).getName() + "," + memberList.getMember(i).getEmail());
			}
			output.flush();
		} 
		
		finally 
		{
			output.close();
		}
	}
}