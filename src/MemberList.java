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
	
	public void replaceMemberInList(int index, Member member)
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
	
	public void deleteMemberFromList(int index)
	{
		memberList.remove(index);
	}
	
	public void clearMemberList()
	{
		memberList.clear();
	}
	
	public String toString()
	{
		String s = String.format("%s", memberList);
		return s;
	}
}