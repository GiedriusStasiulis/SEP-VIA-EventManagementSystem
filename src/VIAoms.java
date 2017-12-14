import java.io.FileNotFoundException;
import java.text.ParseException;

public class VIAoms 
{
	private Lecturer lecturer;
	private LecturerList lecturerList = new LecturerList();
	private static final String LECTURERFILENAME = "LecturerList.txt";
	private FileReaderWriter lecturerFile = new FileReaderWriter(LECTURERFILENAME);		
	
	////////////////   SPONSOR  VARIABLES //////////////////
	
	private Sponsor sponsor;
	private SponsorList sponsorList;
	private static final String SPONSORFILENAME = "SponsorList.txt";
	private FileReaderWriter sponsorFile = new FileReaderWriter(SPONSORFILENAME);
		
	///////////////////////////////////////////////////////	
	
	private Member member;
	private MemberList memberList = new MemberList();
	private MemberList nonPaidMemberList = new MemberList();
	private MemberList memberEmailList = new MemberList();
	private static final String FILENAME = "MemberList.txt";
	private FileReaderWriter memberFile = new FileReaderWriter(FILENAME);	
	
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
	   sponsorList =sponsorFile.readSponsorTextFile();
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
	
}
