import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;

public class VIAoms 
{
	private Event event;
	private EventList eventList = new EventList();
	private static final String EVENTSFILENAME = "EventList.txt";
	private FileReaderWriter eventFile = new FileReaderWriter(EVENTSFILENAME);
	private String filenameMembersEvent = "MemberListForEvent.txt";
	private FileReaderWriter memberEventFile = new FileReaderWriter(filenameMembersEvent);
	private String filenameNonMembersEvent = "NonMembersForEvent.txt";
	private FileReaderWriter nonMemberEventFile = new FileReaderWriter(filenameNonMembersEvent);
	private String filenameEventCategories = "CategoriesForEvent.txt";
	private FileReaderWriter eventCategoriesFile = new FileReaderWriter(filenameEventCategories);	
	private String filenameEventLecturers = "LecturersForEvent.txt";
	private FileReaderWriter eventLecturersFile = new FileReaderWriter(filenameEventLecturers);
	
	private Lecturer lecturer;
	private LecturerList lecturerList = new LecturerList();
	private static final String LECTURERFILENAME = "LecturerList.txt";
	private FileReaderWriter lecturerFile = new FileReaderWriter(LECTURERFILENAME);		
	
	private Sponsor sponsor;
	private SponsorList sponsorList = new SponsorList();
	private static final String SPONSORFILENAME = "SponsorList.txt";
	private FileReaderWriter sponsorFile = new FileReaderWriter(SPONSORFILENAME);

	private Member member;
	private MemberList memberList = new MemberList();
	private MemberList nonPaidMemberList = new MemberList();
	private static final String MEMBERFILENAME = "MemberList.txt";
	private FileReaderWriter memberFile = new FileReaderWriter(MEMBERFILENAME);		
	
	public void createEvent(String eventTitle, String eventType, String eventCategory, String eventLecturer,
			LocalDate eventStartDate, String eventStartTime, LocalDate eventEndDate, String eventEndTime, int eventNumberOfTickets, int eventTicketsRemaining,
			double eventPrice, double eventDiscount, String eventStatus) throws IOException, ParseException
	{
		event = new Event(eventTitle,eventType,eventCategory,eventLecturer,eventStartDate,eventStartTime,eventEndDate,eventEndTime,eventNumberOfTickets,eventTicketsRemaining,eventPrice,eventDiscount,eventStatus);
		eventList.clearEventList();
		eventList = eventFile.readEventTextFile();
		eventList.addEventToList(event);
		eventFile.writeEventTextFile(eventList);
	}
	
	public void addLecturer(String lecturerName, String lecturerCategory, String lecturerEmail, String lecturerPhoneNumber) throws FileNotFoundException, ParseException
	{
		lecturer = new Lecturer(lecturerName,lecturerCategory,lecturerEmail,lecturerPhoneNumber);
		lecturerList.clearLecturerList();
		lecturerList = lecturerFile.readLecturerTextFile();
		lecturerList.addLecturerToList(lecturer);
		lecturerFile.writeLecturerTextFile(lecturerList);
	}
	
	public void addSponsor(String sponsorName, String sponsorEmail, String sponsorPhoneNumber) throws FileNotFoundException, ParseException
	{
	   sponsor = new Sponsor(sponsorName,sponsorEmail,sponsorPhoneNumber);
	   sponsorList.clearSponsorList();
	   sponsorList = sponsorFile.readSponsorTextFile();
	   sponsorList.addSponsorToList(sponsor);
	   sponsorFile.writeSponsorTextFile(sponsorList);
	   
	}
	public void addMember(String memberName, String memberAddress, String memberPhoneNumber, String memberEmail, LocalDate memberSince, String membershipStatus) throws FileNotFoundException, ParseException
	{
		member = new Member(memberName,memberAddress,memberPhoneNumber,memberEmail,memberSince,membershipStatus);
		memberList.clearMemberList();
		memberList = memberFile.readMemberTextFile();
		memberList.addMemberToList(member);
		memberFile.writeMemberTextFile(memberList);
	}
	
	public void editEvent(int index, Event event) throws ParseException, IOException
	{
		eventList.clearEventList();
		eventList = eventFile.readEventTextFile();
		eventList.replaceEventInList(index, event);
		eventFile.writeEventTextFile(eventList);
	}	
		
	public void editLecturer(int index, Lecturer lecturer) throws FileNotFoundException, ParseException
	{
		lecturerList.clearLecturerList();
		lecturerList = lecturerFile.readLecturerTextFile();
		lecturerList.replaceLecturerInList(index, lecturer);
		lecturerFile.writeLecturerTextFile(lecturerList);
	}
	
	public void editSponsor(int index, Sponsor sponsor) throws FileNotFoundException, ParseException
	{
	   sponsorList.clearSponsorList();
	   sponsorList = sponsorFile.readSponsorTextFile();
	   sponsorList.replaceSponsorinList(index, sponsor);
	   sponsorFile.writeSponsorTextFile(sponsorList);
	}
	
	public void editMember(int index, Member member) throws FileNotFoundException, ParseException
	{
		memberList.clearMemberList();
		memberList = memberFile.readMemberTextFile();
		memberList.replaceMemberInList(index, member);
		memberFile.writeMemberTextFile(memberList);
	}	
	
	public void deleteEvent(int index, String eventTitle) throws ParseException, IOException
	{
		eventList.clearEventList();
		eventList = eventFile.readEventTextFile();
		eventList.deleteEventFromList(index);
		eventFile.writeEventTextFile(eventList);
		
		File file1 = new File(eventTitle + filenameMembersEvent);
		File file2 = new File(eventTitle + filenameNonMembersEvent);
		File file3 = new File(eventTitle + filenameEventCategories);
		File file4 = new File(eventTitle + filenameEventLecturers);
		
		file1.delete();
		file2.delete();
		file3.delete();
		file4.delete();
	}	
		
	public void deleteLecturer(int index) throws FileNotFoundException, ParseException
	{
		lecturerList.clearLecturerList();
		lecturerList = lecturerFile.readLecturerTextFile();
		lecturerList.deleteLecturerFromList(index);
		lecturerFile.writeLecturerTextFile(lecturerList);
	}	
	
	public void deleteSponsor(int index) throws FileNotFoundException, ParseException
	{
	   sponsorList.clearSponsorList();
      sponsorList =sponsorFile.readSponsorTextFile();
      sponsorList.deleteSponsorFromList(index);
      sponsorFile.writeSponsorTextFile(sponsorList);
	}
	
	public void deleteMember(int index) throws FileNotFoundException, ParseException
	{
		memberList.clearMemberList();
		memberList = memberFile.readMemberTextFile();
		memberList.deleteMemberFromList(index);		
		memberFile.writeMemberTextFile(memberList);
	}	
	
	public EventList getEventList() throws ParseException, IOException
	{
		eventList.clearEventList();
		eventList = eventFile.readEventTextFile();
		return eventList;
	}	
	
	public LecturerList getLecturerList() throws FileNotFoundException, ParseException
	{
		lecturerList.clearLecturerList();
		lecturerList = lecturerFile.readLecturerTextFile();
		return lecturerList;
	}	
	
	public SponsorList getSponsorList() throws FileNotFoundException, ParseException
	{
	   sponsorList.clearSponsorList();
	   sponsorList=sponsorFile.readSponsorTextFile();
	   return sponsorList;
	}
	
	public MemberList getMemberList() throws FileNotFoundException, ParseException
	{
		memberList.clearMemberList();
		memberList = memberFile.readMemberTextFile();
		return memberList;
	}	
	
	public boolean checkForEventDuplicates(Event event) throws ParseException, IOException
	{
	   boolean status = false;
	   
	   eventList.clearEventList();
	   eventList = eventFile.readEventTextFile();
	   
	   for ( int i=0;i<eventList.size();i++)
	   {
	      if(event.equals(eventList.getEvent(i)))
	      {
	         status= true;
	      }
	   }
	   return status;	         
	}
	
	public boolean checkForLecturerDuplicates(Lecturer lecturer) throws FileNotFoundException, ParseException
	{
		boolean status = false;		
		
		lecturerList.clearLecturerList();
		lecturerList = lecturerFile.readLecturerTextFile();
		
		for(int i = 0; i < lecturerList.size(); i++)
		{
			if(lecturer.equals(lecturerList.getLecturer(i)))
			{
				return true;
			}
		}		
		return status;
	}	
	
	public boolean checkForSponsorDuplicates(Sponsor sponsor) throws FileNotFoundException, ParseException
	{
	   boolean status = false;
	   
	   sponsorList.clearSponsorList();
	   sponsorList = sponsorFile.readSponsorTextFile();
	   
	   for ( int i=0;i<sponsorList.size();i++)
	   {
	      if(sponsor.equals(sponsorList.getSponsor(i)))
	      {
	         status= true;
	      }
	   }
	   return status;	         
	}
	
	public boolean checkForMemberDuplicates(Member member) throws FileNotFoundException, ParseException
	{
		boolean status = false;		
		
		memberList.clearMemberList();
		memberList = memberFile.readMemberTextFile();
		
		for(int i = 0; i < memberList.size(); i++)
		{
			if(member.equals(memberList.getMember(i)))
			{
				return true;
			}
		}		
		return status;
	}
	
	public void addMembersToEvent(ArrayList<String> memberList, String eventTitle) throws FileNotFoundException
	{
		memberEventFile.setFile(eventTitle + "MemberListForEvent.txt");
		memberEventFile.writeEventMemberFile(memberList);
	}	
	
	public ArrayList<String> getEventMembers(String eventTitle) throws FileNotFoundException
	{
		ArrayList<String> membersAdded;
		memberEventFile.setFile(eventTitle + "MemberListForEvent.txt");
		membersAdded = memberEventFile.readEventMemberFile();
		
		return membersAdded;
	}	
	
	public void addNonMembersToEvent(ArrayList<String> nonMemberList, String eventTitle) throws FileNotFoundException
	{
		nonMemberEventFile.setFile(eventTitle + "NonMembersForEvent.txt");
		nonMemberEventFile.writeNonMemberTextFile(nonMemberList);
	}	
	
	public ArrayList<String> getEventNonMembers(String eventTitle) throws FileNotFoundException
	{
		ArrayList<String> nonMemberList;
		nonMemberEventFile.setFile(eventTitle + "NonMembersForEvent.txt");		
		nonMemberList = nonMemberEventFile.readNonMemberTextFile();
				
		return nonMemberList;
	}
	
	public void addCategoriesToEvent(ArrayList<String> categoryList, String eventTitle) throws FileNotFoundException
	{
		eventCategoriesFile.setFile(eventTitle + "CategoriesForEvent.txt");
		eventCategoriesFile.writeEventCategoriesTextFile(categoryList);
	}
	
	public ArrayList<String> getEventCategories(String eventTitle) throws FileNotFoundException
	{
		ArrayList<String> addedCategories;
		eventCategoriesFile.setFile(eventTitle + "CategoriesForEvent.txt");		
		addedCategories = eventCategoriesFile.readEventCategoriesTextFile();
		
		return addedCategories;
	}
	
	public void addLecturerToEvent(ArrayList<String> lecturerList, String eventTitle) throws FileNotFoundException
	{
		eventLecturersFile.setFile(eventTitle + "LecturersForEvent.txt");
		eventLecturersFile.writeEventLecturersTextFile(lecturerList);
	}
	
	public ArrayList<String> getEventLecturers(String eventTitle) throws FileNotFoundException
	{
		ArrayList<String> addedLecturers;
		eventLecturersFile.setFile(eventTitle + "LecturersForEvent.txt");
		addedLecturers = eventLecturersFile.readEventLecturersTextFile();
		
		return addedLecturers;
	}
	
	public MemberList generateListNonPaidMembership() throws FileNotFoundException, ParseException
	{
		nonPaidMemberList.clearMemberList();

		for(int i = 0; i < memberList.size(); i++)
    	{
    		if(memberList.getMember(i).getMembershipStatus().contains("Not"))
			{
    			nonPaidMemberList.addMemberToList(memberList.getMember(i));
			}
    	}
		
		return nonPaidMemberList;
	}	
}