import java.io.FileNotFoundException;
import java.text.ParseException;

public class VIAoms 
{
	private Member member;
	private MemberList memberList = new MemberList();
	private MemberList nonPaidMemberList = new MemberList();
	private MemberList memberEmailList = new MemberList();
	private static final String FILENAME = "MemberList.txt";
	private FileReaderWriter memberFile = new FileReaderWriter(FILENAME);
	
	////////////////   SPONSOR  VARIABLES //////////////////
	
	private Sponsor sponsor;
	private SponsorList sponsorList;
	private static final String SPONSORFILENAME = "SponsorList.txt";
	private FileReaderWriter sponsorFile = new FileReaderWriter(SPONSORFILENAME);
		
	///////////////////////////////////////////////////////
	
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
	
	public void editMember(int index, Member member) throws FileNotFoundException, ParseException
	{
		memberList.clearMemberList();
		memberList = memberFile.readMemberTextFile();
		memberList.replaceMember(index, member);
		memberFile.writeMemberTextFile(memberList);
	}	
	
	public void editSponsor(int index, Sponsor sponsor) throws FileNotFoundException, ParseException
	{
	   sponsorList.clearSponsorList();
	   sponsorList =sponsorFile.readSponsorTextFile();
	   sponsorList.replaceSponsor(index, sponsor);
	   sponsorFile.writeSponsorTextFile(sponsorList);
	}
	
	public void deleteMember(int index) throws FileNotFoundException, ParseException
	{
		memberList.clearMemberList();
		memberList = memberFile.readMemberTextFile();
		memberList.deleteMember(index);		
		memberFile.writeMemberTextFile(memberList);
	}
	
	public void deleteSponsor(int index) throws FileNotFoundException, ParseException
	{
	   sponsorList.clearSponsorList();
      sponsorList =sponsorFile.readSponsorTextFile();
      sponsorList.deleteSponsor(index);
      sponsorFile.writeSponsorTextFile(sponsorList);
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
	
	public MemberList getMemberList() throws FileNotFoundException, ParseException
	{
		memberList.clearMemberList();
		memberList = memberFile.readMemberTextFile();
		return memberList;
	}
	
	public SponsorList getSponsorList() throws FileNotFoundException, ParseException
	{
	   sponsorList.clearSponsorList();
	   sponsorList=sponsorFile.readSponsorTextFile();
	   return sponsorList;
	}
	
	public boolean checkForDuplicates(Member member) throws FileNotFoundException, ParseException
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
}
