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
	
	
	private Lecturer lecturer;
	private LecturerList lecturerList = new LecturerList();
	private static final String LECTURERFILENAME = "LecturerList.txt";
	private FileReaderWriter lecturerFile = new FileReaderWriter(LECTURERFILENAME);		
	
	////////////////   SPONSOR  VARIABLES //////////////////
	
	private Sponsor sponsor;
	private SponsorList sponsorList = new SponsorList();
	private static final String SPONSORFILENAME = "SponsorList.txt";
	private FileReaderWriter sponsorFile = new FileReaderWriter(SPONSORFILENAME);
		
	///////////////////////////////////////////////////////	
	
	private Member member;
	private MemberList memberList = new MemberList();
	private MemberList nonPaidMemberList = new MemberList();
	private static final String MEMBERFILENAME = "MemberList.txt";
	private FileReaderWriter memberFile = new FileReaderWriter(MEMBERFILENAME);	
	
	public void createEvent(String eventTitle, String eventType, String eventCategory, String eventLecturer,
			LocalDate eventStartDate, String eventStartTime, LocalDate eventEndDate, String eventEndTime, int eventNumberOfTickets,
			double eventPrice, double eventDiscount, String eventStatus) throws IOException, ParseException
	{
		event = new Event(eventTitle,eventType,eventCategory,eventLecturer,eventStartDate,eventStartTime,eventEndDate,eventEndTime,eventNumberOfTickets,eventPrice,eventDiscount,eventStatus);
		eventList.clearEventList();
		eventList = eventFile.readEventsTextFile();
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
	
	public void addSponsor(String name, String email, String phoneNumber) throws FileNotFoundException, ParseException
	{
	   sponsor = new Sponsor(name,email,phoneNumber);
	   sponsorList.clearSponsorList();
	   sponsorList = sponsorFile.readSponsorTextFile();
	   sponsorList.addSponsorToList(sponsor);
	   sponsorFile.writeSponsorTextFile(sponsorList);
	   
	}
	public void addMember(String memberName, String memberEmail, String membershipStatus) throws FileNotFoundException, ParseException
	{
		member = new Member(memberName,memberEmail,membershipStatus);
		memberList.clearMemberList();
		memberList = memberFile.readMemberTextFile();
		memberList.addMemberToList(member);
		memberFile.writeMemberTextFile(memberList);
	}
	
	
	
	public void editEvent(int index, Event event) throws ParseException, IOException
	{
		eventList.clearEventList();
		eventList = eventFile.readEventsTextFile();
		eventList.replaceEvent(index, event);
		eventFile.writeEventTextFile(eventList);
	}	
		
	public void editLecturer(int index, Lecturer lecturer) throws FileNotFoundException, ParseException
	{
		lecturerList.clearLecturerList();
		lecturerList = lecturerFile.readLecturerTextFile();
		lecturerList.replaceLecturer(index, lecturer);
		lecturerFile.writeLecturerTextFile(lecturerList);
	}
	
	public void editSponsor(int index, Sponsor sponsor) throws FileNotFoundException, ParseException
	{
	   sponsorList.clearSponsorList();
	   sponsorList = sponsorFile.readSponsorTextFile();
	   sponsorList.replaceSponsor(index, sponsor);
	   sponsorFile.writeSponsorTextFile(sponsorList);
	}
	
	public void editMember(int index, Member member) throws FileNotFoundException, ParseException
	{
		memberList.clearMemberList();
		memberList = memberFile.readMemberTextFile();
		memberList.replaceMember(index, member);
		memberFile.writeMemberTextFile(memberList);
	}	
	
	
	public void deleteEvent(int index) throws ParseException, IOException
	{
		eventList.clearEventList();
		eventList = eventFile.readEventsTextFile();
		eventList.deleteEvent(index);
		eventFile.writeEventTextFile(eventList);
	}	
		
	public void deleteLecturer(int index) throws FileNotFoundException, ParseException
	{
		lecturerList.clearLecturerList();
		lecturerList = lecturerFile.readLecturerTextFile();
		lecturerList.deleteLecturer(index);
		lecturerFile.writeLecturerTextFile(lecturerList);
	}	
	
	public void deleteSponsor(int index) throws FileNotFoundException, ParseException
	{
	   sponsorList.clearSponsorList();
      sponsorList =sponsorFile.readSponsorTextFile();
      sponsorList.deleteSponsor(index);
      sponsorFile.writeSponsorTextFile(sponsorList);
	}
	
	public void deleteMember(int index) throws FileNotFoundException, ParseException
	{
		memberList.clearMemberList();
		memberList = memberFile.readMemberTextFile();
		memberList.deleteMember(index);		
		memberFile.writeMemberTextFile(memberList);
	}
	
	
	
	
	
	public EventList getEventList() throws ParseException, IOException
	{
		eventList.clearEventList();
		eventList = eventFile.readEventsTextFile();
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
	   eventList = eventFile.readEventsTextFile();
	   
	   for ( int i=0;i<eventList.size();i++)
	   {
	      if(event.equals(eventList.getEvent(i)))
	      {
	         status= true;
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
	

	public void writeMembersToFile(ArrayList<String> memberList, String eventTitle) throws FileNotFoundException
	{
		memberEventFile.setFile(eventTitle + "MemberListForEvent.txt");
		memberEventFile.writeEventMemberFile(memberList, eventTitle);
	}
	
	
	
	
	public ArrayList<String> readMembersFromFile(String eventTitle) throws FileNotFoundException
	{
		ArrayList<String> membersAdded;
		memberEventFile.setFile(eventTitle + "MemberListForEvent.txt");
		membersAdded = memberEventFile.readEventMemberFile();
		
		return membersAdded;
	}
	
	
	
	
	public void writeNonMembersToFile(ArrayList<String> nonMemberList, String eventTitle) throws FileNotFoundException
	{
		nonMemberEventFile.setFile(eventTitle + "NonMembersForEvent.txt");
		nonMemberEventFile.writeNonMemberTextFile(nonMemberList,eventTitle);
	}
	
	
	
	
	
	public ArrayList<String> readNonMembersFromFile(String eventTitle) throws FileNotFoundException
	{
		ArrayList<String> nonMemberList;
		nonMemberEventFile.setFile(eventTitle + "NonMembersForEvent.txt");
		
		nonMemberList = nonMemberEventFile.readNonMemberTextFile(eventTitle);
				
		return nonMemberList;
	}
	
}






















