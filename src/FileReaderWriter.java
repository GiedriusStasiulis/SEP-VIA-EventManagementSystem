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

	private EventList currentEventList = new EventList();
	private SponsorList currentSponsorList = new SponsorList();
	private LecturerList currentLecturerList = new LecturerList();
	private MemberList currentMemberList = new MemberList();

	private ArrayList<String> eventMembersRegistered = new ArrayList<String>();
	private ArrayList<String> eventNonMembersRegistered = new ArrayList<String>();
	private ArrayList<String> eventCategoriesAdded = new ArrayList<String>();
	private ArrayList<String> eventLecturersAdded = new ArrayList<String>();

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

	public void writeEventTextFile(EventList eventList) throws FileNotFoundException 
	{
		PrintWriter output = null;
		
		try 
		{
			output = new PrintWriter(file);
			for (int i = 0; i < eventList.size(); i++) 
			{
				output.println(eventList.getEvent(i).getEventTitle() + "," + eventList.getEvent(i).getEventType() + ","
						+ eventList.getEvent(i).getEventCategory() + "," + eventList.getEvent(i).getEventLecturer()
						+ "," + eventList.getEvent(i).getEventStartDate() + ","
						+ eventList.getEvent(i).getEventStartTime() + "," + eventList.getEvent(i).getEventEndDate()
						+ "," + eventList.getEvent(i).getEventEndTime() + ","
						+ eventList.getEvent(i).getEventNumberOfTickets() + ","
						+ eventList.getEvent(i).getEventTicketsRemaining() + "," + eventList.getEvent(i).getEventPrice()
						+ "," + eventList.getEvent(i).getEventDiscount() + ","
						+ eventList.getEvent(i).getEventDuration() + "," + eventList.getEvent(i).getEventStatus());
			}
			output.flush();

		} finally {
			output.close();
		}
	}

	public EventList readEventTextFile() throws FileNotFoundException, ParseException, IOException 
	{
		Scanner input = null;

		try 
		{
			input = new Scanner(file);
			while (input.hasNext()) 
			{
				String line = input.nextLine();
				String[] lineItems = line.split(",");

				String eventTitle = lineItems[0].trim();
				String eventType = lineItems[1].trim();
				String eventCategory = lineItems[2].trim();
				String eventLecturer = lineItems[3].trim();
				LocalDate eventStartDate = LocalDate.parse(lineItems[4].trim());
				String eventStartTime = lineItems[5].trim();
				LocalDate eventEndDate = LocalDate.parse(lineItems[6].trim());
				String eventEndTime = lineItems[7].trim();
				int maxMembers = Integer.parseInt(lineItems[8].trim());
				int ticketsRemaining = Integer.parseInt(lineItems[9].trim());
				double price = Double.parseDouble(lineItems[10].trim());
				double discount = Double.parseDouble(lineItems[11].trim());
				int duration = Integer.parseInt(lineItems[12].trim());
				String status = lineItems[13].trim();

				Event eventToAdd = new Event(eventTitle, eventType, eventCategory, eventLecturer, eventStartDate,
						eventStartTime, eventEndDate, eventEndTime, maxMembers, ticketsRemaining, price, discount,
						status);

				currentEventList.addEventToList(eventToAdd);

			}
			return currentEventList;
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
						+ sponsorList.getSponsor(i).getPhoneNumber());
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
			while (input.hasNext()) 
			{
				String line = input.nextLine();
				String[] lineToken = line.split(",");
				
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

	public void writeLecturerTextFile(LecturerList lecturerList) throws FileNotFoundException 
	{
		PrintWriter output = null;
		
		try 
		{
			output = new PrintWriter(file);

			for (int i = 0; i < lecturerList.size(); i++) 
			{
				output.println(lecturerList.getLecturer(i).getLecturerName() + ","
						+ lecturerList.getLecturer(i).getLecturerCategory() + ","
						+ lecturerList.getLecturer(i).getLecturerEmail() + ","
						+ lecturerList.getLecturer(i).getLecturerPhoneNumber());
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
	
	public void writeMemberTextFile(MemberList memberList) throws FileNotFoundException 
	{
		PrintWriter output = null;
		
		try 
		{
			output = new PrintWriter(file);

			for (int i = 0; i < memberList.size(); i++) 
			{
				output.println(memberList.getMember(i).getName() + "," + memberList.getMember(i).getAddress() + ","
						+ memberList.getMember(i).getPhoneNumber() + "," + memberList.getMember(i).getEmail() + ","
						+ memberList.getMember(i).getMemberSince() + ","
						+ memberList.getMember(i).getMembershipStatus());
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
			while (input.hasNext()) 
			{
				String line = input.nextLine();
				String[] lineToken = line.split(",");

				String memberName = lineToken[0].trim();
				String memberAddress = lineToken[1].trim();
				String memberPhoneNumber = lineToken[2].trim();
				String memberEmail = lineToken[3].trim();
				LocalDate memberSince = LocalDate.parse(lineToken[4].trim());
				String membershipStatus = lineToken[5].trim();

				Member member = new Member(memberName, memberAddress, memberPhoneNumber, memberEmail, memberSince,
						membershipStatus);
				
				currentMemberList.addMemberToList(member);
			}
			return currentMemberList;
		}
		finally 
		{
			input.close();
		}
	}
	
	public void writeEventMemberFile(ArrayList<String> eventMemberList)
			throws FileNotFoundException 
	{
		PrintWriter output = null;
		
		try 
		{
			output = new PrintWriter(file);
			for (int i = 0; i < eventMemberList.size(); i++) 
			{
				output.println(eventMemberList.get(i));
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
		Scanner input = null;
		eventMembersRegistered.clear();

		try 
		{
			input = new Scanner(file);

			while (input.hasNext()) 
			{
				String line = input.nextLine();
				eventMembersRegistered.add(line);
			}
			
			return eventMembersRegistered;
		}
		finally 
		{
			input.close();
		}
	}
	
	public void writeNonMemberTextFile(ArrayList<String> eventNonMemberList)
			throws FileNotFoundException 
	{
		PrintWriter output = null;
		
		try 
		{
			output = new PrintWriter(file);

			for (int i = 0; i < eventNonMemberList.size(); i++) 
			{
				output.println(eventNonMemberList.get(i));
			}
			output.flush();
		}
		finally 
		{
			output.close();
		}
	}
	
	public ArrayList<String> readNonMemberTextFile() throws FileNotFoundException 
	{
		Scanner input = null;
		eventNonMembersRegistered.clear();

		try 
		{
			input = new Scanner(file);

			while (input.hasNext()) 
			{
				String line = input.nextLine();
				eventNonMembersRegistered.add(line);
			}

			return eventNonMembersRegistered;
		}
		finally {
			input.close();
		}
	}

	public void writeEventCategoriesTextFile(ArrayList<String> eventCategoriesList) throws FileNotFoundException 
	{
		PrintWriter output = null;
		
		try 
		{
			output = new PrintWriter(file);

			for (int i = 0; i < eventCategoriesList.size(); i++) 
			{
				output.println(eventCategoriesList.get(i));
			}
			output.flush();
		}
		finally 
		{
			output.close();
		}
	}

	public ArrayList<String> readEventCategoriesTextFile() throws FileNotFoundException 
	{
		Scanner input = null;
		eventCategoriesAdded.clear();

		try 
		{
			input = new Scanner(file);

			while (input.hasNext()) 
			{
				String line = input.nextLine();
				eventCategoriesAdded.add(line);
			}

			return eventCategoriesAdded;
		}
		finally 
		{
			input.close();
		}
	}	
	
	public void writeEventLecturersTextFile(ArrayList<String> eventLecturersList) throws FileNotFoundException
	{
		PrintWriter output = null;
		
		try 
		{
			output = new PrintWriter(file);

			for (int i = 0; i < eventLecturersList.size(); i++) 
			{
				output.println(eventLecturersList.get(i));
			}
			output.flush();
		}
		finally 
		{
			output.close();
		}
	}
	
	public ArrayList<String> readEventLecturersTextFile() throws FileNotFoundException
	{
		Scanner input = null;
		eventLecturersAdded.clear();

		try 
		{
			input = new Scanner(file);

			while (input.hasNext()) 
			{
				String line = input.nextLine();
				eventLecturersAdded.add(line);
			}

			return eventLecturersAdded;
		}
		finally 
		{
			input.close();
		}
	}	
}