import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *The MemberList class represents an ArrayList() of Member objects. 
 * 
 * @author Group#2 *
 */

public class MemberList 
{
	private static final long serialVersionUID = 1L;
	private ArrayList<Member> memberList;
	
	public MemberList()
	{
		memberList = new ArrayList<Member>();
	}
	
	public int size()
	{
		return memberList.size();
	}

	public void addMemberToList(Member member)
	{
		memberList.add(member);
	}
	
	public void replaceMember(int index, Member member)
	{
		memberList.set(index, member);
	}
	
	public Member getMember(int index)
	{
		return memberList.get(index);
	}
	
	public int getMemberIndex(Member member)
	{
		return memberList.indexOf(member);
	}
	
	public void deleteMember(int index)
	{
		memberList.remove(index);
	}
	
	public boolean checkForDuplicates(MemberList memberList, Member member)
	{
		boolean status = false;
		
		for(int i = 0; i < memberList.size(); i++)
		{
			if(member.equals(memberList.getMember(i)))
				return true;
		}
		
		return status;
	}
	
	public String toString()
	{
		String s = String.format("%s", memberList);
		return s;
	}
}