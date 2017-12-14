/**
 *The Member class represents an object, which holds two String values: name, email. 
 *Example:
 * @author Group#2 *
 */

public class Member
{
	private static final long serialVersionUID = 1L;
	private String name;
	private String email;
	private String membershipStatus;

	public Member()
	{
		
	}
	
	
	public Member(String name, String email, String membershipStatus) 
	{
		this.name = name;
		this.email = email;
		this.membershipStatus = membershipStatus;
	}

	public String getMembershipStatus() {
		return membershipStatus;
	}

	public void setMembershipStatus(String membershipStatus) {
		this.membershipStatus = membershipStatus;
	}

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public String getEmail() 
	{
		return email;
	}

	public void setEmail(String email) 
	{
		this.email = email;
	}

	public boolean equals(Object obj) 
	{
		if (!(obj instanceof Member)) {
			return false;
		}

		Member other = (Member) obj;
		return name.equals(other.name) && email.equals(other.email) && membershipStatus.equals(other.membershipStatus);
	}

	public String toString() 
	{
		String s = String.format("%s,%s,%s", getName(), getEmail(), getMembershipStatus());
		return s;
	}
}