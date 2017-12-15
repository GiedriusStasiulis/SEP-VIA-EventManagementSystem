import java.time.LocalDate;

/**
 *The Member class represents an object, which holds two String values: name, email. 
 *Example:
 * @author Group#2 *
 */

public class Member
{
	private static final long serialVersionUID = 1L;
	private String name;
	private String address;
	private String phoneNumber;
	private String email;
	private LocalDate memberSince;
	private String membershipStatus;

	public Member()
	{
		
	}	
	
	public Member(String name, String address, String phoneNumber, String email, LocalDate memberSince, String membershipStatus) 
	{
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.memberSince = memberSince;
		this.membershipStatus = membershipStatus;
	}
	
	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public LocalDate getMemberSince() {
		return memberSince;
	}


	public void setMemberSince(LocalDate memberSince) {
		this.memberSince = memberSince;
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
		return name.equals(other.name) && address.equals(other.address) && phoneNumber.equals(other.phoneNumber) && email.equals(other.email) && memberSince.equals(other.memberSince) && membershipStatus.equals(other.membershipStatus);
	}

	public String toString() 
	{
		String s = String.format("%s,%s,%s,%s,%s,%s", getName(), getAddress(), getPhoneNumber(), getEmail(), getMemberSince(), getMembershipStatus());
		return s;
	}
}