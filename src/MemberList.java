import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MemberList implements Serializable
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
	
	public void clearMemberList()
	{
		for(int i = 0; i < memberList.size(); i ++)
		{
			memberList.clear();
		}
	}
	
	public Member getMember(int index)
	{
		return memberList.get(index);
	}
	
	public void removeMember(int index)
	{
		memberList.remove(index);
	}
	
	public String toString()
	{
		String s = String.format("%s", memberList);
		return s;
	}
}
